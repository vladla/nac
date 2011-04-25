package vladyslav.lubenets.nac;

import java.util.logging.Level;
import java.util.logging.Logger;

import vladyslav.lubenets.nac.game.Game;
import vladyslav.lubenets.nac.network.FakeServerThread;
import vladyslav.lubenets.nac.network.SocketConnection;
import vladyslav.lubenets.nac.network.SocketConnection.SocketConnectionException;
import vladyslav.lubenets.nac.network.TransportObject;

public class FakeClientThreadSimpleGameScenario extends Thread {
    private static final Logger LOGGER = Logger.getLogger(FakeServerThread.class.getSimpleName());

    private static final int TIMEOUT_FOR_THE_MAIN_THREAD = 2000;
    private final SocketConnection connection;
    private final int listenPort;
    private final String host;

    public FakeClientThreadSimpleGameScenario (SocketConnection connection, int listenPort, String host) {
        if (connection == null) throw new NullPointerException();
        if (listenPort < 1 || listenPort > 65535) throw new IllegalArgumentException();


        this.connection = connection;
        this.listenPort = listenPort;
        this.host = host;
    }

    @Override
    public void run() {
        try {
            //LOGGER.log(Level.INFO, "Entered to the slave thread");
            Thread.sleep(TIMEOUT_FOR_THE_MAIN_THREAD);
            connection.connect(host, listenPort);
            TransportObject transportObject = (TransportObject) connection.read();
//            Player player = transportObject.getPlayer();
//            System.out.println("Printed by fake server");
//            System.out.println(player);
//            int x = transportObject.getxPosition().intValue();
//            System.out.println(x);
//            int y = transportObject.getyPosition().intValue();
//            System.out.println(y);
            //LOGGER.log(Level.INFO, "Connection accepted");
            transportObject.setPlayer(Game.Player.NOUGHT);
            transportObject.setxPosition(Integer.valueOf(0));
            transportObject.setyPosition(Integer.valueOf(1));
            connection.write(transportObject);
            //LOGGER.log(Level.INFO, "Data has been send");

            transportObject = (TransportObject) connection.read();
            //Thread.sleep(TIMEOUT_FOR_THE_MAIN_THREAD);
//            player = transportObject.getPlayer();
//            System.out.println("Printed by fake server");
//            System.out.println(player);
//            x = transportObject.getxPosition().intValue();
//            System.out.println(x);
 //           y = transportObject.getyPosition().intValue();
//            System.out.println(y);
            //LOGGER.log(Level.INFO, "Connection accepted");
            transportObject.setPlayer(Game.Player.NOUGHT);
            transportObject.setxPosition(Integer.valueOf(1));
            transportObject.setyPosition(Integer.valueOf(1));
            connection.write(transportObject);
            //LOGGER.log(Level.INFO, "Data has been send");
            
            transportObject = (TransportObject) connection.read();
            //Thread.sleep(TIMEOUT_FOR_THE_MAIN_THREAD);
//            player = transportObject.getPlayer();
//            System.out.println("Printed by fake server");
//            System.out.println(player);
//            x = transportObject.getxPosition().intValue();
//            System.out.println(x);
//            y = transportObject.getyPosition().intValue();
//            System.out.println(y);
            //LOGGER.log(Level.INFO, "Connection accepted");

            connection.close();
            
        } catch (SocketConnectionException ex) {
            LOGGER.log(Level.SEVERE, "ERROR", ex);
            ex.printStackTrace();
        }
//        } catch (InterruptedException ex) {
//            // TODO Auto-generated catch block
//            ex.printStackTrace();
//        }
 catch (InterruptedException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }
    
}
