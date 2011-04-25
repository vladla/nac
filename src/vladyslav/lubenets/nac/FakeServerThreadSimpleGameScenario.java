package vladyslav.lubenets.nac;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import vladyslav.lubenets.nac.game.Game;
import vladyslav.lubenets.nac.game.Game.Player;
import vladyslav.lubenets.nac.network.FakeServerThread;
import vladyslav.lubenets.nac.network.SocketConnection;
import vladyslav.lubenets.nac.network.TransportObject;
import vladyslav.lubenets.nac.network.SocketConnection.SocketConnectionException;

public class FakeServerThreadSimpleGameScenario extends Thread {
    private static final Logger LOGGER = Logger.getLogger(FakeServerThread.class.getSimpleName());

    private static final int TIMEOUT_FOR_THE_MAIN_THREAD = 5000;
    private final SocketConnection connection;
    private final int listenPort;

    public FakeServerThreadSimpleGameScenario (SocketConnection connection, int listenPort) {
        if (connection == null) throw new NullPointerException();
        if (listenPort < 1 || listenPort > 65535) throw new IllegalArgumentException();


        this.connection = connection;
        this.listenPort = listenPort;
    }

    @Override
    public void run() {
        try {
            //LOGGER.log(Level.INFO, "Entered to the slave thread");
            connection.accept(listenPort);
            //LOGGER.log(Level.INFO, "Connection accepted");
            TransportObject transportObject = new TransportObject();
            transportObject.setPlayer(Game.Player.CROSS);
            transportObject.setxPosition(Integer.valueOf(0));
            transportObject.setyPosition(Integer.valueOf(0));
            connection.write(transportObject);
            //LOGGER.log(Level.INFO, "Data has been send");
            transportObject = (TransportObject) connection.read();
            //Thread.sleep(TIMEOUT_FOR_THE_MAIN_THREAD);
            //Player player = transportObject.getPlayer();
            //System.out.println("Printed by fake server");
            //System.out.println(player);
            //int x = transportObject.getxPosition();
            //System.out.println(x);
            //int y = transportObject.getyPosition();
            //System.out.println(y);
            
            transportObject.setPlayer(Game.Player.CROSS);
            transportObject.setxPosition(Integer.valueOf(1));
            transportObject.setyPosition(Integer.valueOf(0));
            connection.write(transportObject);
            //LOGGER.log(Level.INFO, "Data has been send");
            transportObject = (TransportObject) connection.read();
            //Thread.sleep(TIMEOUT_FOR_THE_MAIN_THREAD);
            //player = transportObject.getPlayer();
            //System.out.println("Printed by fake server");
            //System.out.println(player);
            //x = transportObject.getxPosition();
            //System.out.println(x);
            //y = transportObject.getyPosition();
            //System.out.println(y);
            
            

            transportObject.setPlayer(Game.Player.CROSS);
            transportObject.setxPosition(Integer.valueOf(2));
            transportObject.setyPosition(Integer.valueOf(0));
            connection.write(transportObject);
            //LOGGER.log(Level.INFO, "Data has been send");

            connection.close();
            
        } catch (SocketConnectionException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);
            ex.printStackTrace();
        }
//        } catch (InterruptedException ex) {
//            // TODO Auto-generated catch block
//            ex.printStackTrace();
//        }
    }
    
}
