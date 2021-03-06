package squier.john.innerClasses;

import java.io.Closeable;

/**
 * @author John A. Squier
 */
public interface Connection extends Closeable
{
    String getIP();
    int getPort();
    Protocol getProtocol();
    String connect();
}
