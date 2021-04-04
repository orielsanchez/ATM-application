package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.EnemyRequest;
import edu.sdccd.cisc191.f.EnemyResponse;
import edu.sdccd.cisc191.f.CardRequest;
import edu.sdccd.cisc191.f.CardResponse;
import edu.sdccd.cisc191.f.PlayerRequest;
import edu.sdccd.cisc191.f.PlayerResponse;


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
        System.out.println("Server running...");
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            PlayerRequest request = PlayerRequest.fromJSON(inputLine);
            PlayerResponse response = new PlayerResponse(request.getId(), 4, 20);
            CardRequest cardRequest = CardRequest.fromJSON(inputLine);
            CardResponse cardResponse = new CardResponse(request.getId(), "Strike", 2, "Attack", "Player strikes the enemy with their weapon, dealing x damage", 5, 5, 5);

            EnemyRequest enemyRequest = EnemyRequest.fromJSON((inputLine));
            EnemyResponse enemyResponse = new EnemyResponse((enemyRequest.getId()), "Shrek", 10, 100, 10, 1, 1, false);

            out.println(PlayerResponse.toJSON(response));
            out.println(EnemyResponse.toJSON(enemyResponse));
            out.println(CardResponse.toJSON(cardResponse));


            System.out.printf("Sending response to client %d\n", request.getId());
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start(4444);
            server.stop();
            System.out.println("Server stopped.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} //end class Server
