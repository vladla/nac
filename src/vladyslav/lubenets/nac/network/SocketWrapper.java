package vladyslav.lubenets.nac.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketWrapper implements SocketInterface {

    private SocketAddress socketAddress;
    private Socket socket;
    private ServerSocket serverSocket;


    public void creatingClientOrServer(String clientOrServer) {
        if (clientOrServer.equals("client")) {
            try {
                InetAddress inetAddress = InetAddress.getByName(INTERNET_ADDRESS);
                socketAddress = new InetSocketAddress(inetAddress, PORT);
                socket = new Socket();
            } catch (IOException ex) {
                System.out.println();
            }

        } else if (clientOrServer.equals("server")) {
            try {
                serverSocket = new ServerSocket(PORT);
            } catch (IOException ex) {
                System.out.println(INPUT_ERROR);
            }
        }
    }

    public Result clientConnect() {
        try {
            socket.connect(socketAddress, TIMEOUT);
            if (socket.isConnected()) {
                return Result.SUCCESS;
            }
            if (!(socket.isConnected())) {
                return Result.FAIL;
            }
        } catch (IOException ex) {
            System.out.println(INPUT_ERROR);
        }

        return null;

    }

    public Result clientAccept() {
        try {
            socket = serverSocket.accept();
            if (socket.isConnected()) {
                return Result.SUCCESS;
            }
            if (!(socket.isConnected())) {
                return Result.FAIL;
            }
        } catch (IOException ex) {
            System.out.println(INPUT_ERROR);
        }
        return null;

    }

    public String readFromSocket() {
        try {

            byte[] result = new byte[10000];
            socket.getInputStream().read(result);
            String msg = new String(result);
            System.out.println("result retrieved: " + msg);
            socket.close();
            return msg;

        } catch (IOException e) {
            System.out.println(INPUT_ERROR);
        }
        return null;
    }

    public void writeToSocket(String whatToWrite) {
        try {
            socket.getOutputStream().write(whatToWrite.getBytes());
        } catch (IOException ex) {
            System.out.println(INPUT_ERROR);
        }

    }

    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println(INPUT_ERROR);
        }
    }

}
