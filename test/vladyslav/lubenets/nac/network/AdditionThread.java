package vladyslav.lubenets.nac.network;

import java.net.ServerSocket;


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
             socketWrapper.creatingClientOrServer(NetworkTest.SERVER);
             socketWrapper.clientAccept();
             System.out.println("CLIENT ACCEPTED!");
             socketWrapper.writeToSocket(NetworkTest.TEST);
             Thread.sleep(5000);
             String result = socketWrapper.readFromSocket();
             System.out.println(result);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        

    }

}
