package vladyslav.lubenets.nac.network;

import junit.framework.TestCase;
import vladyslav.lubenets.nac.game.Game.Result;
import vladyslav.lubenets.nac.network.SocketWrapper.State;


public class NetworkTest extends TestCase {
    SocketWrapper socketWrapper; 

    
    @Override
    public void setUp() {
        socketWrapper = new SocketWrapper();
    }

    
    
    // Server side testing
    
    public void testServerCreateSocket() {
        Result result = socketWrapper.create(1234);
        assertEquals(result, Result.SUCCESS);
        
        Result result1 = socketWrapper.connect("172.0.0.1", 1234);
        assertEquals(result1, Result.FAIL);
                
        SocketWrapper.State state = socketWrapper.getState();
        assertEquals(state, State.OPEN);
        
        socketWrapper.close();
        
        state = socketWrapper.getState();
        assertEquals(state, State.CLOSED);
                
    }

    
    public void testServerWritting() {
        Result result = socketWrapper.write("The string for writting...");
        assertEquals(result, Result.SUCCESS);

        
        SocketWrapper.State state = socketWrapper.getState();
        assertEquals(state, State.OPEN);
        
        socketWrapper.close();
        
        state = socketWrapper.getState();
        assertEquals(state, State.CLOSED);

    }

    public void testServerReading() {
        String result = socketWrapper.read();
        assertEquals(result, "test");
        
        
        SocketWrapper.State state = socketWrapper.getState();
        assertEquals(state, State.OPEN);
        
        socketWrapper.close();
        
        state = socketWrapper.getState();
        assertEquals(state, State.CLOSED);

    }
    
    //Client side testing

    public void testClientEstablishSocketConnection() {
        Result result = socketWrapper.connect("127.0.0.1", 1234);
        assertEquals(result, Result.SUCCESS);
        
        Result result1 = socketWrapper.create(1234);
        assertEquals(result1, Result.FAIL);
        
        
        SocketWrapper.State state = socketWrapper.getState();
        assertEquals(state, State.OPEN);
        
        socketWrapper.close();
        
        state = socketWrapper.getState();
        assertEquals(state, State.CLOSED);

    }

    
    public void testClientWritting() {
        Result result = socketWrapper.write("The string for writting...");
        assertEquals(result, Result.SUCCESS);

        SocketWrapper.State state = socketWrapper.getState();
        assertEquals(state, State.OPEN);
        
        socketWrapper.close();
        
        state = socketWrapper.getState();
        assertEquals(state, State.CLOSED);

    }

    public void testClientReading() {
        String result = socketWrapper.read();
        assertEquals(result, Result.SUCCESS);

        SocketWrapper.State state = socketWrapper.getState();
        assertEquals(state, State.OPEN);
        
        socketWrapper.close();
        
        state = socketWrapper.getState();
        assertEquals(state, State.CLOSED);

    }
    

}
