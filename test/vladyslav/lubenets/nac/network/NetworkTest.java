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

    @Override
    public void tearDown() {
        socketWrapperServer.close();
        socketWrapperClient.close();
        
    }    
   
    
    public void testServerCreateSocket() {
        Result result = socketWrapperServer.create(1234);
        assertEquals(result, Result.SUCCESS);
        
        result = socketWrapperServer.connect("127.0.0.1", 1234);
        assertEquals(result, Result.FAIL);
                
        SocketWrapper.State state = socketWrapperServer.getState();
        assertEquals(state, State.OPEN);
        
        socketWrapperServer.close();
        
        state = socketWrapperServer.getState();
        assertEquals(state, State.CLOSED);
                
    }


    public void testSocketReading() {
        
        Result result = socketWrapperServer.create(1234);
        assertEquals(result, Result.SUCCESS);
        
        result = socketWrapperClient.connect("127.0.0.1", 1234);
        assertEquals(result, Result.SUCCESS);
        

        result = socketWrapperClient.writeByClient(STRING_TO_WRITE);
        assertEquals(result, Result.SUCCESS);

        String resultString = socketWrapperServer.readByServer();
        assertEquals(resultString, STRING_TO_WRITE);

        result = socketWrapperServer.writeByServer(STRING_TO_WRITE);
        assertEquals(result, Result.SUCCESS);
        
        resultString = socketWrapperClient.readByClient();
        assertEquals(resultString, STRING_TO_WRITE);
        
        socketWrapperServer.close();
        assertEquals(socketWrapperServer.getState(), State.CLOSED);


        socketWrapperClient.close();
        assertEquals(socketWrapperClient.getState(), State.CLOSED);

    }

    public void testClientCreationFaild() {
        Result result = socketWrapperServer.create(1234);
        assertEquals(result, Result.SUCCESS);
                
        result = socketWrapperClient.connect("127.0.0.1", 1234);
        assertEquals(result, Result.SUCCESS);
        
        result = socketWrapperClient.create(1234);
        assertEquals(result, Result.FAIL);
                
    }
    

}
