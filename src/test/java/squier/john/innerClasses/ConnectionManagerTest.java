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
        int expected = 10;
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
        Connection c2 = expected.get(0);

        Assert.assertTrue( expected.size() == 1
                            && c2.getIP().equals(ipAddress)
                            && c2.getPort() == port
                            && c2.getProtocol().equals(protocol));
    }

    @Test
    public void getConnectionWithIPAndProtocolTest()
    {
        Connection actual = connectionManager.getConnection(ipAddress, protocol);

        Assert.assertTrue(actual instanceof  Connection
                            && actual.getIP().equals(ipAddress)
                            && actual.getPort() == 1
                            && actual.getProtocol().equals(protocol));
    }

    @Test
    public void getConnectionWithIPAndPortTest()
    {
        Connection actual = connectionManager.getConnection(ipAddress, port);

        Assert.assertTrue(actual instanceof  Connection
                            && actual.getIP().equals(ipAddress)
                            && actual.getPort() == port
                            && actual.getProtocol().equals(Protocol.HTTP));
    }

    @Test
    public void getConnectionWithIPAndPortAndProtocol()
    {
        Connection actual = connectionManager.getConnection(ipAddress, port, protocol);

        Assert.assertTrue(actual instanceof Connection
                            && actual.getIP().equals(ipAddress)
                            && actual.getPort() == port
                            && actual.getProtocol().equals(protocol));
    }

    @Test
    public void getConnectionWhenMaxConnectionsAlreadyExist()
    {
        // fill up the connection1 list with max connections
        for ( int i = 0; i < connectionManager.getConnectionLimit(); i++ )
        {
            connectionManager.getConnection(ipAddress, port, protocol);
        }

        // try to get another connection1
        Connection expected = connectionManager.getConnection(ipAddress, port);

        Assert.assertNull(expected);
    }

    @Test
    public void numConnectionsTests()
    {
        Connection c1 = connectionManager.getConnection(ipAddress, port, protocol);
        Connection c2 = connectionManager.getConnection(ipAddress, port, protocol);

        int expectedAfterOpen = 2;
        int actualAfterOpen = connectionManager.getNumConnections();

        try
        {
            c1.close();
        }
        catch ( Exception e ) {}

        int expectedAfterClose = 1;
        int actualAfterClose = connectionManager.getNumConnections();

        Assert.assertTrue(expectedAfterOpen == actualAfterOpen
                            && expectedAfterClose == actualAfterClose);
    }
}
