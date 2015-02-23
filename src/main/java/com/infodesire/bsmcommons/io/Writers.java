// (C) 1998-2016 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.io;

import java.io.PrintWriter;
import java.io.Writer;


/**
 * Utils for print writer
 *
 */
public class Writers {
  
  
  /**
   * Create print writer from writer. Keep original if possible.
   * 
   * @param writer Writer
   * @return PrintWriter
   * 
   */
  public static PrintWriter printWriter( Writer writer ) {
    return writer instanceof PrintWriter ? (PrintWriter) ( writer ) : new PrintWriter( writer );
  }
  

}


