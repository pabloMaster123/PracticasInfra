package co.edu.uniquindio.practica2.Ejercicio1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class UsersTCPServer {
    public static final int PORT = 3400;
    private ServerSocket listener;
    private Socket serverSideSocket;
    private PrintWriter toNetwork;
    private BufferedReader fromNetwork;

    public UsersTCPServer() {
        System.out.println("Echo TCP server is running on port: " + PORT);
    }

    protected void init() throws Exception {
        listener = new ServerSocket(PORT);
        int contador = 0;
        HashMap<String, Integer> usuarios = new HashMap<String, Integer>();
        while (true) {
            serverSideSocket = listener.accept();
            createStreams(serverSideSocket);
            contador = protocol(serverSideSocket, contador, usuarios);
        }
    }

    public int protocol(Socket socket, int contador, HashMap<String, Integer> usuarios) throws Exception {
        String message = fromNetwork.readLine();

        String answer;

        String[] partes = message.split(" ");

        switch (partes[0]) {

            case "LOGIN":
                if (usuarios.containsKey(partes[1])) {
                    usuarios.put(partes[1],  usuarios.get(partes[1]) + 1);
                    answer = partes[0] + " Bienvenido " + partes[1] + " Usted ah ingresado " + usuarios.get(partes[1]) + " veces";
                    toNetwork.println(answer);
                } else {
                    usuarios.put(partes[1], 0);
                    contador++;
                    answer = partes[0] + " Bienvenido " + partes[1] + " Usted es el usuario " + contador;
                    toNetwork.println(answer);
                }
                break;

        }

        return contador;
    }

    private void createStreams(Socket socket) throws Exception {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static void main(String args[]) throws Exception {
        UsersTCPServer es = new UsersTCPServer();
        es.init();
    }
}
