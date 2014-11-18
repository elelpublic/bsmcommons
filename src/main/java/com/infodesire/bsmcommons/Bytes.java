// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Byte operations
 *
 */
public class Bytes {
  
  
  /**
   * Copies all bytes, don't flush.
   *
   * @param source the input stream to read from
   * @param target the output stream to write to
   * @return Number of bytes copied
   * @throws IOException if an I/O error occurs
   */
  public static long pipe( InputStream source, OutputStream target )
    throws IOException {

    byte[] buf = new byte[ 0x1000 ];
    long bytes = 0;
    while( true ) {
      int r = source.read( buf );
      if( r == -1 ) {
        break;
      }
      target.write( buf, 0, r );
      bytes += r;
    }
    
    return bytes;
    
  }


}
