package vladyslav.lubenets.nac;

import junit.framework.TestCase;
import vladyslav.lubenets.nac.Game.Result;
import vladyslav.lubenets.nac.ServerSide.State;


public class NetworkTest extends TestCase {
    ServerSide serverSide; 
    ClientSide clientSide;

    
    public void setUp() {
        serverSide = new ServerSide();
        clientSide = new ClientSide();
    }

    
    
    // Server side testing
    
    public void testServerCreateSocket() {
        Result result = serverSide.create("127.0.0.1", 1234);
        assertEquals(result, Result.SUCCESS);
        
        ServerSide.State state = serverSide.getState();
        assertEquals(state, State.OPEN);
        
        serverSide.close();
        
        state = serverSide.getState();
        assertEquals(state, State.CLOSED);
                
    }

    public void testServerTestConnection() {
        Result result = serverSide.test("The string for testing...");
        assertEquals(result, Result.SUCCESS);

        
        ServerSide.State state = serverSide.getState();
        assertEquals(state, State.OPEN);
        
        serverSide.close();
        
        state = serverSide.getState();
        assertEquals(state, State.CLOSED);

    }
    
    public void testServerWritting() {
        Result result = serverSide.write("The string for writting...");
        assertEquals(result, Result.SUCCESS);

        
        ServerSide.State state = serverSide.getState();
        assertEquals(state, State.OPEN);
        
        serverSide.close();
        
        state = serverSide.getState();
        assertEquals(state, State.CLOSED);

    }

    public void testServerReading() {
        Result result = serverSide.read();
        assertEquals(result, Result.SUCCESS);
        
        
        ServerSide.State state = serverSide.getState();
        assertEquals(state, State.OPEN);
        
        serverSide.close();
        
        state = serverSide.getState();
        assertEquals(state, State.CLOSED);

    }
    
    //Client side testing

    public void testClientEstablishSocketConnection() {
        Result result = clientSide.connect("127.0.0.1", 1234);
        assertEquals(result, Result.SUCCESS);
        
        
        ClientSide.State state = clientSide.getState();
        assertEquals(state, State.OPEN);
        
        clientSide.close();
        
        state = clientSide.getState();
        assertEquals(state, State.CLOSED);

    }

    public void testClientTestConnection() {
        Result result = clientSide.test("The string for testing...");
        assertEquals(result, Result.SUCCESS);
        
        ClientSide.State state = clientSide.getState();
        assertEquals(state, State.OPEN);
        
        clientSide.close();
        
        state = clientSide.getState();
        assertEquals(state, State.CLOSED);

    }
    
    public void testClientWritting() {
        Result result = clientSide.write("The string for writting...");
        assertEquals(result, Result.SUCCESS);

        ClientSide.State state = clientSide.getState();
        assertEquals(state, State.OPEN);
        
        clientSide.close();
        
        state = clientSide.getState();
        assertEquals(state, State.CLOSED);

    }

    public void testClientReading() {
        Result result = clientSide.read();
        assertEquals(result, Result.SUCCESS);

        ClientSide.State state = clientSide.getState();
        assertEquals(state, State.OPEN);
        
        clientSide.close();
        
        state = clientSide.getState();
        assertEquals(state, State.CLOSED);

    }
    

}
