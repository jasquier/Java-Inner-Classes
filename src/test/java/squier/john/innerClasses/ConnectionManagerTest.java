package squier.john.innerClasses;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by johnsquier on 2/2/17.
 */
public class ConnectionManagerTest
{
    ConnectionManager connectionManager;
    String ipAddress = "128.0.0.0";
    Protocol protocol = Protocol.FTP;
    int port;

    @Before
    public void setup()
    {
        connectionManager = new ConnectionManager();
        ipAddress = "128.0.0.0";
        protocol = Protocol.FTP;
        port = 8000;
    }

    @Test
    public void getConnectionLimitTest()
    {
        int expected = 100;
        int actual = connectionManager.getConnectionLimit();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNumConnectionsTest()
    {
        connectionManager.getConnection(ipAddress, port, protocol);

        int expected = 1;
        int actual = connectionManager.getNumConnections();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getConnectionsListTest()
    {
        connectionManager.getConnection(ipAddress, port, protocol);

        List<Connection> expected = connectionManager.getConnectionList();

        Assert.assertTrue( expected.size() == 1
                            && expected.get(0).getIPAddress().equals(ipAddress)
                            && expected.get(0).getPort() == port
                            && expected.get(0).getProtocol().equals(protocol));
    }

    @Test
    public void getConnectionWithIPAndProtocolTest()
    {
        Connection actual = connectionManager.getConnection(ipAddress, protocol);

        Assert.assertTrue(actual instanceof  Connection
                            && actual.getIPAddress().equals(ipAddress)
                            && actual.getPort() == 1
                            && actual.getProtocol().equals(protocol));
    }

    @Test
    public void getConnectionWithIPAndPortTest()
    {
        Connection actual = connectionManager.getConnection(ipAddress, port);

        Assert.assertTrue(actual instanceof  Connection
                            && actual.getIPAddress().equals(ipAddress)
                            && actual.getPort() == port
                            && actual.getProtocol().equals(Protocol.HTTP));
    }

    @Test
    public void getConnectionWithIPAndPortAndProtocol()
    {
        Connection actual = connectionManager.getConnection(ipAddress, port, protocol);

        Assert.assertTrue(actual instanceof Connection
                            && actual.getIPAddress().equals(ipAddress)
                            && actual.getPort() == port
                            && actual.getProtocol().equals(protocol));
    }

    @Test
    public void getConnectionWhenMaxConnectionsAlreadyExist()
    {
        // fill up the connection list with max connections
        for ( int i = 0; i < connectionManager.getConnectionLimit(); i++ )
        {
            connectionManager.getConnection(ipAddress, port, protocol);
        }

        // try to get another connection
        Connection expected = connectionManager.getConnection(ipAddress, port);

        Assert.assertNull(expected);
    }
}
