package squier.john.innerClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John A. Squier
 */
public class ConnectionManager
{
    private int connectionLimit;
    private int numConnections;
    private List<Connection> connectionList;

    private static final int MAX_CONNECTIONS = 100;

    public ConnectionManager()
    {
        connectionLimit = MAX_CONNECTIONS;
        numConnections = 0;
        connectionList = new ArrayList<>();
    }

    public int getConnectionLimit() { return connectionLimit; }
    public int getNumConnections()  { return numConnections;  }
    public List<Connection>getConnectionList() { return connectionList; }

    // these create connections (factory) and add to the list
    public Connection getConnection(String ip, Protocol protocol)
    {
        Connection c = new ManagedConnection(ip, protocol);
        connectionList.add()
    }

    public Connection getConnection(String ip, int port)
    {
        return new ManagedConnection(ip, p)
    }

    public Connection getConnection(String ip, int port, Protocol protocol)
    {
        return null;
    }

    public class ManagedConnection implements Connection
    {
        private String ipAddress;
        private int port;
        private Protocol protocol;
        private ConnectionStatus status;

        public ManagedConnection(String ipAddress, Protocol protocol)
        {
            this.ipAddress = ipAddress;
            this.port = 1;
            this.protocol = protocol;
            this.status = ConnectionStatus.OPEN;
        }

        public ManagedConnection(String ipAddress, int port)
        {
            this.ipAddress = ipAddress;
            this.port = port;
            this.protocol = Protocol.HTTP;
        }

        public ManagedConnection(String ipAddress, int port, Protocol protocol)
        {
            this.ipAddress = ipAddress;
            this.port = port;
            this.protocol = protocol;
        }

        public String getIPAddress()
        {
            return ipAddress;
        }

        public int getPort()
        {
            return port;
        }

        public Protocol getProtocol()
        {
            return protocol;
        }

        public ConnectionStatus getStatus()
        {
            return status;
        }

        public String connect()
        {
            return null;
        }

        public void close()
        {
            return;
        }
    }

}
