package vladyslav.lubenets.nac.network;

import junit.framework.TestCase;

public class NetworkTest extends TestCase {
//    public static final String STRING_TO_WRITE = "Some information to transport";
    SocketWrapper socketWrapper;
    public static final String FTP_RESULT = "Test";

//    SocketWrapper socketWrapperClient; 
//    
    @Override
    public void setUp() {
        socketWrapper = new SocketWrapper();
//        socketWrapperClient = new SocketWrapper();        
    }

//
//    @Override
//    public void tearDown() {
//        socketWrapperServer.close();
//        socketWrapperClient.close();
//        
//    }    
//   
//    
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

    public void testServer() {
        new AdditionThread();

        try {
            System.out.println("wait before connection");
            Thread.sleep(5000);
            
        } catch (InterruptedException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }

        String resultFromClient = socketWrapper.client(SocketWrapper.inAddress, SocketWrapper.pt);
        assertEquals(resultFromClient.equals(FTP_RESULT), false);

    }

}
