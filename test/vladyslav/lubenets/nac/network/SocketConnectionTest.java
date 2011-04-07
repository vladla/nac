package vladyslav.lubenets.nac.network;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import vladyslav.lubenets.nac.network.SocketConnection.SocketConnectionException;

import junit.framework.TestCase;

public class SocketConnectionTest extends TestCase {
    private static final Logger LOGGER = Logger.getLogger(FakeServerThread.class.getSimpleName());
    private static final int TIMEOUT_FOR_THE_MAIN_THREAD = 5000;
    
    SocketConnection socketConnection;
    SocketConnection socketConnectionSlave;
    public static final String host = "127.0.0.1";
    public static final int port = 1234;
    Serializable fName = "Test transaction";
    
    
    @Override
    public void setUp() {
        socketConnection = new SocketConnectionImpl();
        socketConnectionSlave = new SocketConnectionImpl();    
    }

    @Override
    public void tearDown() {
        socketConnection.close();
        socketConnectionSlave.close();
    }

    public void testServerClient() {
 
        try {
            new FakeServerThread(socketConnectionSlave, port, fName).start();
            Thread.sleep(TIMEOUT_FOR_THE_MAIN_THREAD);
            socketConnection.connect(host, port);
            Serializable result = socketConnection.read();
            assertEquals(result, fName);

        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);
        } catch (SocketConnectionException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);
        }

    }

}
