package vladyslav.lubenets.nac.network;

import java.io.Serializable;

import vladyslav.lubenets.nac.network.SocketConnection.SocketConnectionException;

import junit.framework.TestCase;

public class SocketConnectionTest extends TestCase {
    SocketConnectionRealization socketConnection;
    SocketConnectionRealization socketConnectionSlave;
    public static final String host = "127.0.0.1";
    public static final int port = 1234;
    Serializable fName = "Test transaction";

    @Override
    public void setUp() {
        socketConnection = new SocketConnectionRealization();
        socketConnectionSlave = new SocketConnectionRealization();
        
    }

    @Override
    public void tearDown() {
        socketConnection.close();
        socketConnectionSlave.close();
    }

    public void testServerClient() {
        new FakeServerThread(socketConnectionSlave, port, fName).start();
        try {
            Thread.sleep(2000);
            socketConnection.connect(host, port);
            System.out.println("wait before connection");
            Thread.sleep(5000);
            Serializable result = socketConnection.read();
            assertEquals(result, fName);

        } catch (InterruptedException ex) {
            System.out.println("Interrupted exception");
        } catch (SocketConnectionException ex) {
            System.out.println("Socket connection exception");
        }

    }

}
