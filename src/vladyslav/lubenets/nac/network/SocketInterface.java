package vladyslav.lubenets.nac.network;

public interface SocketInterface {
    public enum State {
        OPEN, CLOSED;
    }

    public enum Result {
        SUCCESS, FAIL, ERROR
    }
    
    public static final String INPUT_ERROR = "Input error!";
    public static final int TIMEOUT = 2000;
    public static final String INTERNET_ADDRESS = "127.0.0.1";
    public static final int PORT = 1234;
    
    
    
    public void creatingClientOrServer(String clientOrServer);
    public Result clientConnect();
    public Result clientAccept();
    public String readFromSocket();
    public void writeToSocket(String whatToWrite);
    public void closeSocket();
    
}
