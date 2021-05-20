package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.Account;
import edu.sdccd.cisc191.f.AccountRequest;
import edu.sdccd.cisc191.f.AccountResponse;

import java.net.*;
import java.io.*;

/**
 * This program is a server that takes connection requests on
 * the port specified by the constant LISTENING_PORT.  When a
 * connection is opened, the program sends the current time to
 * the connected socket.  The program will continue to receive
 * and process connections until it is killed (by a CONTROL-C,
 * for example).  Note that this server processes each connection
 * as it is received, rather than creating a separate thread
 * to process the connection.
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            AccountRequest request = AccountRequest.fromJSON(inputLine);

            long cardNumber = request.getCardNumber();
            String PIN = request.getPIN();
            Account account;

            if (cardNumber == 0L && PIN.equals("0")) {
                account = AccountUtils.createAccount();
            } else {
                String numForCheck = String.valueOf(cardNumber).substring(0, 15);
                String checkNum = numForCheck + AccountUtils.getLuhnNum(numForCheck);

                if (!checkNum.equals(String.valueOf(cardNumber))) {
                    account = null;
                } else {
                    account = Main.database.getAccount(cardNumber);
                }

                if (account == null || !PIN.equals(account.getPIN())) {
                    System.out.println("Wrong card number or PIN!\n");
                } else {
                    System.out.println("Successful login!");
                }
            }
            if (account != null) {
                AccountResponse response = new AccountResponse(account.getID(),
                        account.getCardNumber(),
                        account.getPIN(),
                        account.getBalance());
                out.println(AccountResponse.toJSON(response));
            }
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
} //end class Server