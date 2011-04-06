package vladyslav.lubenets.nac.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketConnectionRealization implements SocketConnection {

    private Socket socketClient;
    private Socket socketServer;

    boolean serverWillDoOperation = false;

    public void connect(String host, int port) throws SocketConnectionException {
        try {
            SocketAddress socketAddress;
            InetAddress inetAddress;
            inetAddress = InetAddress.getByName(host);
            socketAddress = new InetSocketAddress(inetAddress, port);
            socketClient.connect(socketAddress, 5000);
            if (!(socketClient.isConnected())) throw new SocketConnectionException("Timeout!");
        } catch (IOException ex) {
            throw new SocketConnectionException("IOException!");
        }

    }

    public void accept(int port) throws SocketConnectionException {
        try {
            serverWillDoOperation = true;
            ServerSocket serverSocket;
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(120000);
            socketServer = serverSocket.accept();
            if (!(socketClient.isConnected())) throw new SocketConnectionException("Timeout!");
        } catch (IOException ex) {
            throw new SocketConnectionException("IOException!");
        }
        return;
    }

    public Serializable read() throws SocketConnectionException {
        ObjectInputStream ois = null;
        try {
            if (serverWillDoOperation) {
                ois = new ObjectInputStream(socketServer.getInputStream());
            } else if (!(serverWillDoOperation)) {
                ois = new ObjectInputStream(socketClient.getInputStream());
            } else
                throw new SocketConnectionException("Unknown role");
            ois.close();
            Serializable readFromOis = (Serializable) ois.readObject();
            return readFromOis;

        } catch (IOException e) {
            throw new SocketConnectionException("IOException!");
        } catch (ClassNotFoundException ex) {
            throw new SocketConnectionException("Class not found!");
        }
    }

    public void write(Serializable data) throws SocketConnectionException {
        ObjectOutputStream oos = null;
        if (data.equals(null)) throw new NullPointerException("Data is null!");
        try {
            if (serverWillDoOperation) {
                oos = new ObjectOutputStream(socketServer.getOutputStream());
            } else if (!(serverWillDoOperation)) {
                oos = new ObjectOutputStream(socketClient.getOutputStream());
            } else
                throw new SocketConnectionException("Unknown role");
            oos.writeObject(data);

        } catch (IOException ex) {
            throw new SocketConnectionException("IOException!");
        }
    }

    public void close() {
        try {
            socketClient.close();
            socketServer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
