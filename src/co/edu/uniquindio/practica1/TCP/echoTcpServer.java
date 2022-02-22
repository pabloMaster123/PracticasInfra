package co.edu.uniquindio.practica1.TCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class echoTcpServer {

    public static final int PORT = 3400;
    private ServerSocket listener;
    private Socket serverSideSocket;
    private PrintWriter toNetwork;
    private BufferedReader fromNetwork;

    public echoTcpServer() {
        System.out.println("Echo TCP server is running on port: " + PORT);
    }
    protected void init() throws Exception {
        listener = new ServerSocket(PORT);
        while (true) {
            serverSideSocket = listener.accept();
            InetSocketAddress socketAddress = (InetSocketAddress) serverSideSocket.getRemoteSocketAddress();
            String clientIpAddress = socketAddress.getAddress()
                    .getHostAddress();
            if (clientIpAddress.equals("192.168.196.100")) {
                serverSideSocket.setSoTimeout(10 * 1000);
                createStreams(serverSideSocket);
                protocol(serverSideSocket);
            } else {
                serverSideSocket.close();
            }
        }
    }
    public void protocol(Socket socket) throws Exception {
        String message = fromNetwork.readLine();
        System.out.println("[Server] From client: " + message);
        try {
            InetSocketAddress socketAddress = (InetSocketAddress) serverSideSocket.getRemoteSocketAddress();
            String clientIpAddress = socketAddress.getAddress()
                    .getHostAddress();
            System.out.println(clientIpAddress);
            String answer = message;
            toNetwork.println(answer);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    private void createStreams(Socket socket) throws Exception {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public static void main(String args[]) throws Exception {
        echoTcpServer es = new echoTcpServer();
        es.init();
    }

}
