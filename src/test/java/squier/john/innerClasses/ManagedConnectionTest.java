package squier.john.innerClasses;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author John A. Squier
 */
public class ManagedConnectionTest
{
    Connection connection;
    ConnectionManager.ManagedConnection managedConnection1, managedConnectionClosed;
    String ipAddress;
    int port;
    Protocol protocol;

    @Before
    public void setup()
    {
        ipAddress = "128.0.0.0";
        port = 1000;
        protocol = Protocol.FTP;

        connection = new ConnectionManager().new ManagedConnection(ipAddress, port, protocol);

        managedConnection1 = new ConnectionManager().new ManagedConnection(ipAddress, port, protocol);
        managedConnectionClosed = new ConnectionManager().new ManagedConnection(ipAddress, port, protocol);

        try
        {
            managedConnectionClosed.close();
        }
        catch ( Exception e )
        {

        }
    }

    @Test
    public void getIpAddressConnectionOpenTest()
    {
        String expected = "128.0.0.0";
        String actual = connection.getIP();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getIpAddressConnectionClosedTest()
    {
        Assert.assertTrue(managedConnectionClosed.getIP().equals("CONNECTION CLOSED"));
    }

    @Test
    public void getPortConnectionOpenTest()
    {
        int expected = 1000;
        int actual = connection.getPort();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPortConnectionClosedTest()
    {
        Assert.assertTrue(managedConnectionClosed.getPort() == -1);
    }

    @Test
    public void getProtocolConnectionOpenTest()
    {
        Protocol expected = Protocol.FTP;
        Protocol actual = connection.getProtocol();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getProtocolConnectionClosedTest()
    {
        Assert.assertNull(managedConnectionClosed.getProtocol());
    }

    @Test
    public void getStatusTest()
    {
        ConnectionStatus expected = ConnectionStatus.OPEN;
        ConnectionStatus actual = managedConnection1.getStatus();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void connectWhenConnectionOpenTest()
    {
        String expected = "Connected to 128.0.0.0:1000 via FTP";

        String actual = connection.connect();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void connectWhenConnectionClosedTest()
    {
        String expected = "Connection closed";

        String actual = managedConnectionClosed.connect();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void closeTest()
    {
        Assert.assertTrue(managedConnectionClosed.getStatus().equals(ConnectionStatus.CLOSED));
    }
}
