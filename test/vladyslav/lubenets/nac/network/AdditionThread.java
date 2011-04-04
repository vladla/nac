package vladyslav.lubenets.nac.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class AdditionThread implements Runnable {
    SocketWrapper socketWrapper = new SocketWrapper();
    ServerSocket serverSocket = null;
    Thread t;
    
    public AdditionThread() {
        t = new Thread(this, "Server's Thread");
        t.start();
    }
    
    public void run() {
        try {
             serverSocket = socketWrapper.createServerSocket();
             Socket socket = serverSocket.accept();
             socketWrapper.writeToSocket(socket);
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }

    }

}
