package vladyslav.lubenets.nac.network;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;


public class SocketWrapper {
//    Socket clientSocket;
//    ServerSocket serverSocket;
    PrintWriter out = null;
    BufferedReader in = null;
    
    public static final String inAddress = "127.0.0.1";
    public static final int pt = 1234;
    
    
//    boolean flagServerCreated = false;
//    
//    
//    int portNumber;
//    
//    public enum State {
//        OPEN, CLOSED;
//    }
//
    public enum Result {
        SUCCESS, FAIL, ERROR
    }
//
//    public Result connect(String host, @SuppressWarnings("hiding") int portNumber) {
//        
//       try {
//           if(flagServerCreated==true){
//               return Result.FAIL;
//           }
//        clientSocket = new Socket(host, portNumber);
//    } catch (UnknownHostException ex) {
//        ex.printStackTrace();
//    } catch (IOException ex) {
//        ex.printStackTrace();
//        return Result.FAIL;
//    }
//        
//        return Result.SUCCESS;
//    }
//
//    public Result create(int i) {
//        try {
//            serverSocket = new ServerSocket(i);
//            flagServerCreated = true;
//        } catch (IOException e) {
//            return Result.ERROR;
//        }
//        return Result.SUCCESS;
//    }
//
//
//    
//    public State getState() {
//        if (!(serverSocket.isClosed())) {
//            return State.OPEN;
//        } return State.CLOSED;
//        
//    }
//
//    public void close() {
//
//        try {
//            if (!(serverSocket.isClosed())) {
//                serverSocket.close();
//            }
//            if (!(clientSocket.isClosed())) {
//                clientSocket.close();
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//    }
//
//
//    public String readByClient() {
//        try {
//            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            if (in.equals(NetworkTest.STRING_TO_WRITE)) {
//                return NetworkTest.STRING_TO_WRITE;
//            }
//        } catch (IOException ex) {
//            // TODO Auto-generated catch block
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    public Result writeByClient(String stringToWrite) {
//        
//        try {
//            out = new PrintWriter(clientSocket.getOutputStream(), true);
//            out.write(stringToWrite);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        
//        if (out.equals(null)) {
//            return Result.SUCCESS;
//        }
//            return Result.FAIL;
//    }
//    
//    public String readByServer() {
//        try {
//            in = new BufferedReader(new InputStreamReader(serverSocket.accept().getInputStream()));
//            if (in.equals(NetworkTest.STRING_TO_WRITE)) {
//                return NetworkTest.STRING_TO_WRITE;
//            }
//        } catch (IOException ex) {
//            // TODO Auto-generated catch block
//            ex.printStackTrace();
//        }
//        return null;
//    }
//    
//    
//    public Result writeByServer(String stringToWrite) {
//    
//        try {
//            out = new PrintWriter(serverSocket.accept().getOutputStream(), true);
//            out.write(stringToWrite);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        
//        if (out.equals(null)) {
//            return Result.SUCCESS;
//        }
//            return Result.FAIL;
//    }

    public String client(String outInetAddress, int outPort) {
        try {
            InetAddress inetAddress = InetAddress.getByName(outInetAddress);
            int port = outPort;
            SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);
            
            Socket socket = new Socket();
            int timeoutMs = 2000;   
            socket.connect(socketAddress, timeoutMs);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));           
            String returnFromSocket = in.readLine();
            socket.close();
            return returnFromSocket;
            
        } catch (IOException e) {
            System.out.println("Input error!");
        }
        return null;
        }

    public void writeToSocket(Socket socket) {
        try {
            String stringToWrite = NetworkTest.FTP_RESULT;
            out = new PrintWriter(socket.getOutputStream(), true);
            out.write(stringToWrite);
        } catch (IOException e) {
            System.out.println("Input error!");
        }
        return;
    }
    
    public ServerSocket createServerSocket() {
        try {
            System.out.println("I do not sleep");
            ServerSocket srv = new ServerSocket(pt);
            System.out.println("1");
            return srv;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
