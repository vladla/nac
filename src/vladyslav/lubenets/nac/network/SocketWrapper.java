package vladyslav.lubenets.nac.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketWrapper {
    Socket clientSocket;
    ServerSocket serverSocket;
    PrintWriter out = null;
    BufferedReader in = null;
    boolean flagServerCreated = false;
    
    
    int portNumber;
    
    public enum State {
        OPEN, CLOSED;
    }

    public enum Result {
        SUCCESS, FAIL, ERROR
    }

    public Result connect(String host, @SuppressWarnings("hiding") int portNumber) {
        
       try {
           if(flagServerCreated==true){
               return Result.FAIL;
           }
        clientSocket = new Socket(host, portNumber);
    } catch (UnknownHostException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
        return Result.FAIL;
    }
        
        return Result.SUCCESS;
    }

    public Result create(int i) {
        try {
            serverSocket = new ServerSocket(i);
            flagServerCreated = true;
        } catch (IOException e) {
            return Result.ERROR;
        }
        return Result.SUCCESS;
    }


    
    public State getState() {
        try {
            if (!(serverSocket.accept().isClosed())) {
                return State.OPEN;
            }
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } return State.CLOSED;
        
    }

    public void close() {

        try {
            serverSocket.close();
            clientSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    public String readByClient() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            if (in.equals(NetworkTest.STRING_TO_WRITE)) {
                return NetworkTest.STRING_TO_WRITE;
            }
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    public Result writeByClient(String stringToWrite) {
        
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.write(stringToWrite);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        if (out.equals(null)) {
            return Result.SUCCESS;
        }
            return Result.FAIL;
    }
    
    public String readByServer() {
        try {
            in = new BufferedReader(new InputStreamReader(serverSocket.accept().getInputStream()));
            if (in.equals(NetworkTest.STRING_TO_WRITE)) {
                return NetworkTest.STRING_TO_WRITE;
            }
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }
    
    
    public Result writeByServer(String stringToWrite) {
    
        try {
            out = new PrintWriter(serverSocket.accept().getOutputStream(), true);
            out.write(stringToWrite);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        if (out.equals(null)) {
            return Result.SUCCESS;
        }
            return Result.FAIL;
    }




}
