package edu.sdccd.cisc191.f.client;

import edu.sdccd.cisc191.f.*;

import java.net.*;
import java.io.*;

/**
 * This program opens a connection to a computer specified
 * as the first command-line argument.  If no command-line
 * argument is given, it prompts the user for a computer
 * to connect to.  The connection is made to
 * the port specified by LISTENING_PORT.  The program reads one
 * line of text from the connection and then closes the
 * connection.  It displays the text that it read on
 * standard output.  This program is meant to be used with
 * the server program, DateServer, which sends the current
 * date and time on the computer where the server is running.
 */

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Starts a connection with a server
     *
     * @param ip the host name
     * @param port the port number
     * @throws IOException
     */
    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    /**
     * Sends an AccountRequest through the client socket
     *
     * @param cardNumber the 16-digit card number
     * @param PIN the account PIN
     * @return an AccountResponse object
     * @throws Exception
     */
    public AccountResponse sendAccountRequest(long cardNumber, String PIN) throws Exception {
        out.println(AccountRequest.toJSON(new AccountRequest(RequestType.ACC, cardNumber, PIN)));
        return AccountResponse.fromJSON(in.readLine());
    }

    /**
     * Sends a DepositRequest through the client socket
     *
     * @param cardNumber the 16-digit card number
     * @param depositAmount the deposit amount
     * @return A DepositResponse object
     * @throws Exception
     */
    public DepositResponse sendDepositRequest(long cardNumber, double depositAmount) throws Exception {
        out.println(DepositRequest.toJSON(new DepositRequest(RequestType.DEP, cardNumber, depositAmount)));
        return DepositResponse.fromJSON(in.readLine());
    }

    /**
     * Sends a WithdrawRequest through the client socket
     *
     * @param cardNumber the 16-digit card number
     * @param withdrawAmount the withdraw amount
     * @return A WithdrawResponse object
     * @throws Exception
     */
    public WithdrawResponse sendWithdrawRequest(long cardNumber, double withdrawAmount) throws Exception {
        out.println(WithdrawRequest.toJSON(new WithdrawRequest(RequestType.WIT, cardNumber, withdrawAmount)));
        return WithdrawResponse.fromJSON(in.readLine());
    }

    /**
     * Sends a TransferFundsRequest through the client socket
     *
     * @param senderCardNumber the sender's card number
     * @param transferAmount the amount to transfer
     * @param recipientCardNumber the recipient's card number
     * @return a TransferFundsResponse object
     * @throws Exception
     */
    public TransferFundsResponse sendTransferFundsRequest(long senderCardNumber, double transferAmount, long recipientCardNumber) throws Exception {
        out.println(TransferFundsRequest.toJSON(new TransferFundsRequest(RequestType.TRA, senderCardNumber, transferAmount, recipientCardNumber)));
        return TransferFundsResponse.fromJSON(in.readLine());
    }


    /**
     * Closes the client socket connection
     *
     * @throws IOException
     */
    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
} //end class Client

