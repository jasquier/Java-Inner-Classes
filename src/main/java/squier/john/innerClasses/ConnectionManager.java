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

    private static final int MAX_CONNECTIONS = 10;

    public ConnectionManager()
    {
        connectionLimit = MAX_CONNECTIONS;
        numConnections = 0;
        connectionList = new ArrayList<>();
    }

    public int getConnectionLimit() { return connectionLimit; }
    public int getNumConnections()  { return numConnections;  }
    public List<Connection>getConnectionList() { return connectionList; }

    public Connection getConnection(String ip, Protocol protocol)
    {
        return getConnection(ip, 1, protocol);
    }

    public Connection getConnection(String ip, int port)
    {
        return getConnection(ip, port, Protocol.HTTP);
    }

    public Connection getConnection(String ip, int port, Protocol protocol)
    {
        if ( aConnectionCanBeAdded() )
        {
            Connection c = new ManagedConnection(ip, port, protocol);

            addConnectionToList(c);

            return c;
        }
        else
        {
            return null;
        }
    }

    private boolean aConnectionCanBeAdded()
    {
        return numConnections < connectionLimit;
    }

    private void addConnectionToList(Connection c)
    {
        connectionList.add(c);
        numConnections++;
    }

    public class ManagedConnection implements Connection
    {
        private String ipAddress;
        private int port;
        private Protocol protocol;
        private ConnectionStatus status;

        public ManagedConnection(String ipAddress, int port, Protocol protocol)
        {
            this.ipAddress = ipAddress;
            this.port = port;
            this.protocol = protocol;
            this.status = ConnectionStatus.OPEN;
        }

        public String getIP()
        {
            if ( status.equals(ConnectionStatus.OPEN) )
            {
                return ipAddress;
            }
            else
            {
                return "CONNECTION CLOSED";
            }
        }

        public int getPort()
        {
            if ( status.equals(ConnectionStatus.OPEN) )
            {
                return port;
            }
            else
            {
                return -1;
            }
        }

        public Protocol getProtocol()
        {
            if ( status.equals(ConnectionStatus.OPEN) )
            {
                return protocol;
            }
            else
            {
                return null;
            }
        }

        public ConnectionStatus getStatus()
        {
            return status;
        }

        public String connect()
        {
            if ( status.equals(ConnectionStatus.OPEN) )
            {
                return "Connected to " + getIP() + ":" + getPort() + " via " + getProtocol().toString();
            }
            else
            {
                return "Connection closed";
            }
        }

        public void close()
        {
            ConnectionManager.this.numConnections--;
            this.status = ConnectionStatus.CLOSED;
        }
    }

}
