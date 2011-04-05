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
             System.out.println("CLIENT ACCEPTED!");
             socket.getOutputStream().write(NetworkTest.FTP_RESULT.getBytes());
             System.out.println("DATA SENT TO: " + socket);
             t.sleep(5000);
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        

    }

}
