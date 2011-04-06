package vladyslav.lubenets.nac.network;

import junit.framework.TestCase;


public class NetworkTest extends TestCase {
    SocketWrapper socketWrapper;
    public static final String TEST = "Test";
    public static final String SERVER = "server";
    public static final String CLIENT = "client";
    
    @Override
    public void setUp() {
        socketWrapper = new SocketWrapper();
    }

    @Override
    public void tearDown() {
        socketWrapper.closeSocket();        
    }    

    
//    public void testServerCreateSocket() {
//        Result result = socketWrapperServer.create(1234);
//        assertEquals(result, Result.SUCCESS);
//        
//        result = socketWrapperServer.connect("127.0.0.1", 1234);
//        assertEquals(result, Result.FAIL);
//                
//        SocketWrapper.State state = socketWrapperServer.getState();
//        assertEquals(state, State.OPEN);
//        
//        socketWrapperServer.close();
//        
//        state = socketWrapperServer.getState();
//        assertEquals(state, State.CLOSED);
//                
//    }
//
//
//    public void testSocketReading() {
//        
//        Result result = socketWrapperServer.create(1234);
//        assertEquals(result, Result.SUCCESS);
//        
//        result = socketWrapperClient.connect("127.0.0.1", 1234);
//        assertEquals(result, Result.SUCCESS);
//        
//
//        result = socketWrapperClient.writeByClient(STRING_TO_WRITE);
//        assertEquals(result, Result.SUCCESS);
//
//        String resultString = socketWrapperServer.readByServer();
//        assertEquals(resultString, STRING_TO_WRITE);
//
//        result = socketWrapperServer.writeByServer(STRING_TO_WRITE);
//        assertEquals(result, Result.SUCCESS);
//        
//        resultString = socketWrapperClient.readByClient();
//        assertEquals(resultString, STRING_TO_WRITE);
//        
//        socketWrapperServer.close();
//        assertEquals(socketWrapperServer.getState(), State.CLOSED);
//
//
//        socketWrapperClient.close();
//        assertEquals(socketWrapperClient.getState(), State.CLOSED);
//
//    }
//
//    public void testClientCreationFaild() {
//        Result result = socketWrapperServer.create(1234);
//        assertEquals(result, Result.SUCCESS);
//                
//        result = socketWrapperClient.connect("127.0.0.1", 1234);
//        assertEquals(result, Result.SUCCESS);
//        
//        result = socketWrapperClient.create(1234);
//        assertEquals(result, Result.FAIL);
//                
//    }

    public void testServerClient() {
        new AdditionThread();

        try {
            socketWrapper.creatingClientOrServer(CLIENT);
            System.out.println("wait before connection");
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        vladyslav.lubenets.nac.network.SocketInterface.Result result = socketWrapper.clientConnect();
        assertEquals(result, SocketWrapper.Result.SUCCESS);

        String resultFromServer = socketWrapper.readFromSocket();
        assertEquals(resultFromServer, TEST);

        socketWrapper.writeToSocket(TEST);

    }
        
}
