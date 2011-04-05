package vladyslav.lubenets.nac.network;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import vladyslav.lubenets.nac.network.SocketConnection.SocketConnectionException;

public class FakeServerThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(FakeServerThread.class.getSimpleName());

    private final SocketConnection connection;
    private final int listenPort;
    private final Serializable transferData;

    public FakeServerThread(SocketConnection connection, int listenPort, Serializable transferData) {
        if (connection == null) throw new NullPointerException();
        if (listenPort < 1 || listenPort > 65535) throw new IllegalArgumentException();
        if (transferData == null) throw new NullPointerException();

        this.connection = connection;
        this.listenPort = listenPort;
        this.transferData = transferData;
    }

    @Override
    public void run() {
        try {
            connection.accept(listenPort);
            connection.write(transferData);
            connection.close();
        } catch (SocketConnectionException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);
        }
    }
}
