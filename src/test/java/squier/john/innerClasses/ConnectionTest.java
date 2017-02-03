package squier.john.innerClasses;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author John A. Squier
 */
public class ConnectionTest
{
    Connection connection1, connection2, connectionClosed;
    Closeable connection3;
    String ipAddress;
    int port;
    Protocol protocol;

    @Before
    public void setup()
    {
        ipAddress = "128.0.0.0";
        port = 1000;
        protocol = Protocol.FTP;

        connection1 = new ConnectionManager().getConnection(ipAddress, port, protocol);
        connection2 = new ConnectionManager().getConnection(ipAddress, port, protocol);
        connectionClosed = new ConnectionManager().getConnection(ipAddress, port, protocol);

        connection3 = new ConnectionManager().getConnection(ipAddress, port, protocol);

        try
        {
            connectionClosed.close();
        }
        catch ( IOException e )
        {

        }
    }

    @Test
    public void getIpAddressConnectionOpenTest()
    {
        String expected = "128.0.0.0";
        String actual = connection1.getIP();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getIpAddressConnectionClosedTest()
    {
        Assert.assertTrue(connectionClosed.getIP().equals("CONNECTION CLOSED"));
    }

    @Test
    public void getPortConnectionOpenTest()
    {
        int expected = 1000;
        int actual = connection1.getPort();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPortConnectionClosedTest()
    {
        Assert.assertTrue(connectionClosed.getPort() == -1);
    }

    @Test
    public void getProtocolConnectionOpenTest()
    {
        Protocol expected = Protocol.FTP;
        Protocol actual = connection1.getProtocol();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getProtocolConnectionClosedTest()
    {
        Assert.assertNull(connectionClosed.getProtocol());
    }

    @Test
    public void getStatusTest()
    {
        ConnectionStatus expected = ConnectionStatus.OPEN;
        ConnectionStatus actual = connection2.getStatus();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void connectWhenConnectionOpenTest()
    {
        String expected = "Connected to 128.0.0.0:1000 via FTP";

        String actual = connection1.connect();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void connectWhenConnectionClosedTest()
    {
        String expected = "Connection closed";

        String actual = connectionClosed.connect();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void closeTest()
    {
        Assert.assertTrue(connectionClosed.getStatus().equals(ConnectionStatus.CLOSED));
    }
}
