package squier.john.innerClasses;

import org.junit.Before;

/**
 * Created by johnsquier on 2/2/17.
 */
public class ProtocolTest
{
    Protocol ftp, http, ssh, tcp;

    @Before
    public void setup()
    {
        ftp = Protocol.FTP;
        http = Protocol.HTTP;
        ssh = Protocol.SSH;
        tcp = Protocol.TCP;
    }
}
