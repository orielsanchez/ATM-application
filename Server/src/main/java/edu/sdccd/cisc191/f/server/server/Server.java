package edu.sdccd.cisc191.f.server.server;

import edu.sdccd.cisc191.f.*;
import edu.sdccd.cisc191.f.server.account.AccountService;
import edu.sdccd.cisc191.f.server.account.AccountRepository;
import edu.sdccd.cisc191.f.server.account.AccountUtils;

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
 *
 * @author Andrew Huang
 * @author Oriel Sanchez
 * @author Jonathan Plata
 * @author Stanley Schwarz Jr.
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Starts the server by opening sockets, I/O streams and setting up the AccountRepository.
     *
     * @param port the port number
     * @param accountRepository the AccountRepository object
     * @throws Exception an exception of some sorts has occurred
     */
    public void start(int port, AccountRepository accountRepository) throws Exception {
        System.out.println("Server started...");
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        AccountService.setAccountRepository(accountRepository);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            // Variables
            Account account;
            Account recipientAccount;

            // Reads the RequestType
            RequestType requestType = RequestTypeParser.parseRequestType(inputLine);

            // Determines what the do based on the requestType
            switch (requestType) {
                // Sends an AccountResponse object to the output stream
                case ACC:
                    AccountRequest accountRequest = AccountRequest.fromJSON(inputLine);
                    long cardNumber = accountRequest.getCardNumber();
                    String PIN = accountRequest.getPIN();
                    System.out.println("account request info received: " + cardNumber + " " + PIN);

                    if (cardNumber == 0L && PIN.equals("0")) {
                        account = AccountUtils.createAccount();
                        AccountService.addAccountToDatabase(account);
                    } else if (String.valueOf(cardNumber).length() != 16) {
                        account = null;
                    } else {
                        String numForCheck = String.valueOf(cardNumber).substring(0, 15);
                        String checkNum = numForCheck + AccountUtils.getLuhnNum(numForCheck);

                        if (!checkNum.equals(String.valueOf(cardNumber))) {
                            account = null;
                        } else {

                            account = accountRepository.findAccountByCardNumber(cardNumber);
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
                // Sends a DepositResponse object to the output stream
                case DEP:
                    DepositRequest depositRequest = DepositRequest.fromJSON(inputLine);
                    if (depositRequest != null) {
                        account = accountRepository.findAccountByCardNumber(depositRequest.getCardNumber());
                        boolean depositSuccessful = account.deposit(depositRequest.getDepositAmount());
                        if (depositSuccessful) {
                            AccountService.updateAccount(account);
                            DepositResponse depositResponse = new DepositResponse(account.getID(), account.getCardNumber(), account.getPIN()
                            , account.getBalance());
                            out.println(DepositResponse.toJSON(depositResponse));
                        } else {
                            System.out.println("Deposit unsuccessful");
                        }
                    }
                    break;
                // Sends a WithdrawResponse object to the output stream
                case WIT:
                    WithdrawRequest withdrawRequest = WithdrawRequest.fromJSON(inputLine);
                    if (withdrawRequest != null) {
                        account = accountRepository.findAccountByCardNumber(withdrawRequest.getCardNumber());
                        boolean withdrawSuccessful = account.withdraw(withdrawRequest.getWithdrawAmount());
                        if (withdrawSuccessful) {
                            AccountService.updateAccount(account);
                            WithdrawResponse withdrawResponse = new WithdrawResponse(account.getID(), account.getCardNumber(), account.getPIN(), account.getBalance());
                            out.println(WithdrawResponse.toJSON(withdrawResponse));
                        } else {
                            System.out.println("Withdraw unsuccessful");
                            WithdrawResponse withdrawResponse = new WithdrawResponse(account.getID(), account.getCardNumber(), account.getPIN(), account.getBalance());
                            out.println(WithdrawResponse.toJSON(withdrawResponse));
                        }
                    }
                    break;
                // Sends a TransferFundsResponse object to the output stream
                case TRA:
                    TransferFundsRequest transferFundsRequest = TransferFundsRequest.fromJSON(inputLine);
                    if (transferFundsRequest != null) {
                        account = accountRepository.findAccountByCardNumber(transferFundsRequest.getSenderCardNumber());
                        recipientAccount = accountRepository.findAccountByCardNumber(transferFundsRequest.getRecipientCardNumber());

                        boolean transferSuccessful = AccountService.transferFunds(account, transferFundsRequest.getTransferAmount(), recipientAccount);

                        account = accountRepository.findAccountByCardNumber(transferFundsRequest.getSenderCardNumber());

                        TransferFundsResponse transferFundsResponse = new TransferFundsResponse(transferSuccessful, account.getBalance());
                        out.println(TransferFundsResponse.toJSON(transferFundsResponse));
                        }
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * Stops the Server by closing the I/O streams and client/server sockets
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
} //end class Server