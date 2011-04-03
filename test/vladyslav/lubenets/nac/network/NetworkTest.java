package vladyslav.lubenets.nac.network;

import junit.framework.TestCase;
import vladyslav.lubenets.nac.network.SocketWrapper.Result;
import vladyslav.lubenets.nac.network.SocketWrapper.State;


public class NetworkTest extends TestCase {
    public static final String STRING_TO_WRITE = "Some information to transport";
    SocketWrapper socketWrapperServer; 
    SocketWrapper socketWrapperClient; 
    
    @Override
    public void setUp() {
        socketWrapperServer = new SocketWrapper();
        socketWrapperClient = new SocketWrapper();        
    }

    
    
    // Server side testing
    
    public void testServerCreateSocket() {
        Result result = socketWrapperServer.create(1234);
        assertEquals(result, Result.SUCCESS);
        
        Result result1 = socketWrapperServer.connect("172.0.0.1", 1234);
        assertEquals(result1, Result.FAIL);
                
        SocketWrapper.State state = socketWrapperServer.getState();
        assertEquals(state, State.OPEN);
        
        socketWrapperServer.close();
        
        state = socketWrapperServer.getState();
        assertEquals(state, State.CLOSED);
                
    }


    public void testSocketReading() {
        Result result = socketWrapperServer.create(1234);
        assertEquals(result, Result.SUCCESS);
        
        Result result2 = socketWrapperClient.connect("172.0.0.1", 1234);
        assertEquals(result2, Result.SUCCESS);

        Result result3 = socketWrapperClient.write(STRING_TO_WRITE);
        assertEquals(result3, Result.SUCCESS);

        String result4 = socketWrapperServer.read();
        assertEquals(result4, Result.SUCCESS);

        Result result5 = socketWrapperServer.write(STRING_TO_WRITE);
        assertEquals(result5, Result.SUCCESS);
        
        String result6 = socketWrapperClient.read();
        assertEquals(result6, Result.SUCCESS);
        
        socketWrapperServer.close();
        
        assertEquals(socketWrapperServer.getState(), State.CLOSED);


        socketWrapperClient.close();
        
        assertEquals(socketWrapperClient.getState(), State.CLOSED);

    }

    //Client side testing

    public void testClientEstablishSocketConnection() {
        Result result = socketWrapperServer.connect("127.0.0.1", 1234);
        assertEquals(result, Result.SUCCESS);
        
        Result result1 = socketWrapperServer.create(1234);
        assertEquals(result1, Result.FAIL);
        
        
        SocketWrapper.State state = socketWrapperServer.getState();
        assertEquals(state, State.OPEN);
        
        socketWrapperServer.close();
        
        state = socketWrapperServer.getState();
        assertEquals(state, State.CLOSED);

    }

    
}
