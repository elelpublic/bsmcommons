// (C) 1998-2016 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.net;

import java.io.IOException;
import java.net.ServerSocket;


/**
 * Network utils
 *
 */
public class Network {


  /**
   * Returns a free port number on localhost.
   * 
   * From: https://gist.github.com/vorburger/3429822
   * 
   * Heavily inspired from org.eclipse.jdt.launching.SocketUtil (to avoid a dependency to JDT just because of this). Slightly improved with close() missing in JDT. And throws exception instead of
   * returning -1.
   * 
   * @return a free port number on localhost
   * @throws IllegalStateException if unable to find a free port
   */
  public static int findFreePort() {
    ServerSocket socket = null;
    try {
      socket = new ServerSocket( 0 );
      socket.setReuseAddress( true );
      int port = socket.getLocalPort();
      try {
        socket.close();
      }
      catch( IOException e ) {
        // Ignore IOException on close()
      }
      return port;
    }
    catch( IOException e ) {
    }
    finally {
      if( socket != null ) {
        try {
          socket.close();
        }
        catch( IOException e ) {
        }
      }
    }
    throw new IllegalStateException( "Could not find a free TCP/IP port" );
  }

}
