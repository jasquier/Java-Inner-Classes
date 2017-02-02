package squier.john.innerClasses;

import org.junit.Before;

/**
 * Created by johnsquier on 2/2/17.
 */
public class ConnectionStatusTest
{
    ConnectionStatus open, closed;

    @Before
    public void setup()
    {
        open = ConnectionStatus.OPEN;
        closed = ConnectionStatus.CLOSED;
    }
}
