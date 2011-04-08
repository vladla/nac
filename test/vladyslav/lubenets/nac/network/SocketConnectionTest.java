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
    public static final String HOST = "127.0.0.1";
    public static final String BAD_HOST = "127.1.2.1";
    public static final int PORT = 1234;
    public static final int BAD_PORT = 1111;
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
            new FakeServerThread(socketConnectionSlave, PORT, fName).start();
            Thread.sleep(TIMEOUT_FOR_THE_MAIN_THREAD);
            socketConnection.connect(HOST, PORT);
            Serializable result = socketConnection.read();
            assertEquals(result, fName);

        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);
        } catch (SocketConnectionException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);
        }

    }

    public void testBadHostAndPort() {
        try {
            new FakeServerThread(socketConnectionSlave, PORT, fName).start();
            Thread.sleep(TIMEOUT_FOR_THE_MAIN_THREAD);
            socketConnection.connect(BAD_HOST, BAD_PORT);
            Serializable result = socketConnection.read();
            
            assertFalse(true);
            
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);

        } catch (SocketConnectionException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);
        }
        
    }
    
    public void testReadAfterClose() {
        
        try {
            new FakeServerThread(socketConnectionSlave, PORT, fName).start();
            Thread.sleep(TIMEOUT_FOR_THE_MAIN_THREAD);
            socketConnection.connect(HOST, PORT);
            socketConnection.close();
            
            Serializable result = socketConnection.read();
            assertFalse(true);
            
            
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);
        } catch (SocketConnectionException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);
        }  catch (NullPointerException ex) { 
            LOGGER.log(Level.SEVERE, "ERROR", ex);
        }
    }
    
}
