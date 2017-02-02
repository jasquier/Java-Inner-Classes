package squier.john.innerClasses;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author John A. Squier
 */
public class ManagedConnectionTest
{
    Connection connection1, connection2, connection3;
    ConnectionManager.ManagedConnection managedConnection;
    String ipAddress;
    int port;
    Protocol protocol;

    @Before
    public void setup()
    {
        ipAddress = "128.0.0.0";
        port = 1000;
        protocol = Protocol.FTP;

        connection1 = new ConnectionManager().new ManagedConnection(ipAddress, protocol);
        connection2 = new ConnectionManager().new ManagedConnection(ipAddress, port);
        connection3 = new ConnectionManager().new ManagedConnection(ipAddress, port, protocol);

        managedConnection = new ConnectionManager().new ManagedConnection(ipAddress, port, protocol);
    }

    @Test
    public void getIpAddressTest()
    {
        String expected = "128.0.0.0";
        String actual = connection1.getIPAddress();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPortTest()
    {
        int expected = 1000;
        int actual = connection2.getPort();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getProtocolTest()
    {
        Protocol expected = Protocol.FTP;
        Protocol actual = connection3.getProtocol();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStatusTest()
    {
        ConnectionStatus expected = ConnectionStatus.OPEN;
        ConnectionStatus actual = managedConnection.getStatus();

        Assert.assertEquals(expected, actual);
    }
}
