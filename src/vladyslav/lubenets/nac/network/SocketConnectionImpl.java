package vladyslav.lubenets.nac.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketConnectionImpl implements SocketConnection {

    private static final int TIMEOUT = 5000;
    private static final String TIMEOUT_MSG = "Timeout!";
    private static final String DATA_IS_NULL = "Data is null!";
    private Socket client;
    private Socket FromServerToClient;
    private ServerSocket server;
    private InputStream input;
    private OutputStream output;

    public void connect(String host, int port) throws SocketConnectionException {
        try {
            SocketAddress socketAddress;
            InetAddress inetAddress = InetAddress.getByName(host);
            socketAddress = new InetSocketAddress(inetAddress, port);
            client = new Socket();
            client.connect(socketAddress, TIMEOUT);
            input = client.getInputStream();
            output = client.getOutputStream();
            if (!client.isConnected()) throw new SocketConnectionException(TIMEOUT_MSG);
        } catch (IOException ex) {
            throw new SocketConnectionException(ex);
        }

    }

    public void accept(int port) throws SocketConnectionException {
        try {
            server = new ServerSocket(port);
            server.setSoTimeout(TIMEOUT);
            FromServerToClient = server.accept();
            input = FromServerToClient.getInputStream();
            output = FromServerToClient.getOutputStream();
        } catch (IOException ex) {
            throw new SocketConnectionException(ex);
        }
        return;
    }

    public Serializable read() throws SocketConnectionException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(input);
            Serializable readFromOis = (Serializable) ois.readObject();
            return readFromOis;

        } catch (IOException e) {
            throw new SocketConnectionException(e);
        } catch (ClassNotFoundException ex) {
            throw new SocketConnectionException(ex);
        }
    }

    public void write(Serializable data) throws SocketConnectionException {
        ObjectOutputStream oos = null;
        if (data.equals(null)) throw new NullPointerException(DATA_IS_NULL);
        try {

            oos = new ObjectOutputStream(output);

            oos.writeObject(data);

        } catch (IOException ex) {
            throw new SocketConnectionException(ex);
        }
    }

    public void close() {
        try {
            if (client!=null) client.close();
            if (server!=null) server.close();
            client = null;
            server = null;
            input = null;
            output = null;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
