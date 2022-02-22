package co.edu.uniquindio.practica2.Ejercicio1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class UsersTCPCliente {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static final String SERVER = "localhost";
    public static final int PORT = 3400;

    private PrintWriter toNetwork;
    private BufferedReader fromNetwork;

    private Socket clientSideSocket;

    public UsersTCPCliente() {
        System.out.println("Echo TCP client is running ...");
    }

    public void init() throws Exception {
        clientSideSocket = new Socket(SERVER, PORT);

        createStreams(clientSideSocket);

        protocol(clientSideSocket);

        clientSideSocket.close();
    }

    public void protocol(Socket socket) throws Exception {
        String fromUser = "LOGIN";
        System.out.print("Ingrese un nombre usuario: ");

        fromUser = fromUser + " " + SCANNER.nextLine();

        toNetwork.println(fromUser);

        String fromServer = fromNetwork.readLine();
        System.out.println("[Client] From server: " + fromServer);
    }

    private void createStreams(Socket socket) throws Exception {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public static void main(String args[]) throws Exception {
        UsersTCPCliente ec = new UsersTCPCliente();
        ec.init();
    }


}
