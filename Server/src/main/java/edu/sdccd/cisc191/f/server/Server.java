package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.*;
import edu.sdccd.cisc191.f.server.controller.AccountController;

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

    public void start(int port, AccountRepository accountRepository) throws Exception {
        System.out.println("Server started...");
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        AccountController.setAccountRepository(accountRepository);


        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            Account account;
            Account transfereeAccount;
            RequestType requestType = RequestTypeParser.parseRequestType(inputLine);
            System.out.println(requestType);

            switch (requestType) {
                case ACC:
                    AccountRequest accountRequest = AccountRequest.fromJSON(inputLine);
                    long cardNumber = accountRequest.getCardNumber();
                    String PIN = accountRequest.getPIN();
                    System.out.println("account request info received: " + cardNumber + " " + PIN);

                    if (cardNumber == 0L && PIN.equals("0")) {
                        account = AccountUtils.createAccount();
                        AccountController.addAccountToDatabase(account);
                    } else if (String.valueOf(cardNumber).length() != 16) {
                        account = null;
                    } else {
                        String numForCheck = String.valueOf(cardNumber).substring(0, 15);
                        String checkNum = numForCheck + AccountUtils.getLuhnNum(numForCheck);

                        if (!checkNum.equals(String.valueOf(cardNumber))) {
                            account = null;
                        } else {

                            account = accountRepository.findAccountByCardNumber(cardNumber);
                            // account = Main.database.getAccount(cardNumber);
                        }

                        if (account == null || !PIN.equals(account.getPIN())) {
                            System.out.println("Wrong card number or PIN!\n");
                            account = null;
                        } else {
                            System.out.println("Successful login!");
                        }
                    }

                    if (account != null) {
                        AccountResponse accountResponse = new AccountResponse(account.getID(),
                                account.getCardNumber(),
                                account.getPIN(),
                                account.getBalance());
                        out.println(AccountResponse.toJSON(accountResponse));
                    } else {
                        out.println(AccountResponse.toJSON(new AccountResponse(-1, -1L, "-1", -1)));
                    }

                    break;
                case DEP:
                    DepositRequest depositRequest = DepositRequest.fromJSON(inputLine);
                    if (depositRequest != null) {
                        account = accountRepository.findAccountByCardNumber(depositRequest.getCardNumber());
                        boolean depositSuccessful = account.deposit(depositRequest.getDepositAmount());
                        if (depositSuccessful) {
                            AccountController.updateAccount(account);
                            DepositResponse depositResponse = new DepositResponse(account.getID(), account.getCardNumber(), account.getPIN()
                            , account.getBalance());
                            out.println(DepositResponse.toJSON(depositResponse));
                        } else {
                            System.out.println("Deposit unsuccessful");
                        }
                    }
                    break;

                case WIT:
                    WithdrawRequest withdrawRequest = WithdrawRequest.fromJSON(inputLine);
                    if (withdrawRequest != null) {
                        account = accountRepository.findAccountByCardNumber(withdrawRequest.getCardNumber());
                        boolean withdrawSuccessful = account.withdraw(withdrawRequest.getWithdrawAmount());
                        if (withdrawSuccessful) {
                            AccountController.updateAccount(account);
                            WithdrawResponse withdrawResponse = new WithdrawResponse(account.getID(), account.getCardNumber(), account.getPIN(), account.getBalance());
                            out.println(WithdrawResponse.toJSON(withdrawResponse));
                        } else {
                            System.out.println("Withdraw unsuccessful");
                            WithdrawResponse withdrawResponse = new WithdrawResponse(account.getID(), account.getCardNumber(), account.getPIN(), account.getBalance());
                            out.println(WithdrawResponse.toJSON(withdrawResponse));
                        }
                    }
                    break;

                case TRA:

                    break;

                default:
                    break;
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