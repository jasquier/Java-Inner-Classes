package squier.john.innerClasses;

import java.io.Closeable;

/**
 * @author John A. Squier
 */
public interface Connection extends Closeable
{
    String getIPAddress();
    int getPort();
    Protocol getProtocol();
    String connect();
}
