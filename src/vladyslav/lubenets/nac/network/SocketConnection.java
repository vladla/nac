package vladyslav.lubenets.nac.network;

import java.io.Serializable;

public interface SocketConnection {

    public static class SocketConnectionException extends Exception {
        private static final long serialVersionUID = -1363147170370424388L;

        public SocketConnectionException() {
            super();
        }

        public SocketConnectionException(String message, Throwable cause) {
            super(message, cause);
        }

        public SocketConnectionException(String message) {
            super(message);
        }

        public SocketConnectionException(Throwable cause) {
            super(cause);
        }
    }

    /**
     * Tries to connect to remote host. <br>
     * Does not return until connection will be established.
     * 
     * @throws SocketConnectionException
     *             if there are any connection problems (timeout etc)
     */
    void connect(String host, int port) throws SocketConnectionException;

    /**
     * Opens the connection for <b>port</b>. Tries to accept remote client. <br>
     * Does not return until connection will be established.
     * 
     * @throws SocketConnectionException
     *             if there are any connection problems (timeout etc)
     */
    void accept(int port) throws SocketConnectionException;

    /**
     * Tries to read {@link Serializable} object from established connection.
     * 
     * @return never returns <b>null</b>
     * @throws SocketConnectionException
     *             if there are any problems
     */
    Serializable read() throws SocketConnectionException;

    /**
     * Tries to write {@link Serializable} object to established connection.
     * 
     * @param data
     *            not <b>null</b>
     * @throws SocketConnectionException
     * @throws NullPointerException
     *             if <b>data<b> is <b>null</b>
     */
    void write(Serializable data) throws SocketConnectionException;

    /**
     * Close current connection. This-object can be used in future for new
     * connections. Never throws.
     */
    void close();

}
