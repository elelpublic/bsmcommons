// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.io;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * Convenience for creating a print writer as a string writer.
 *
 */
public class PrintStringWriter extends PrintWriter {

  public PrintStringWriter() {
    super( new StringWriter() );
  }

  public String toString() {
    return ( (StringWriter) out ).toString();
  }

}
