// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.collection;

import com.infodesire.bsmcommons.io.PrintStringWriter;
import com.infodesire.bsmcommons.io.Writers;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Enumeration;


/**
 * Concatenate elements of a collection
 *
 */
public class Concat {
  
  
  /**
   * Concatenate strings
   * 
   * @param entries Entries (each one will be called with toString())
   * @param writer Target writer
   * @param separator Separator between entries
   * 
   */
  public static void concat( Iterable<?> entries, Writer writer, String separator ) {
    
    PrintWriter print = Writers.printWriter( writer );
    
    String sep = "";
    for( Object entry : entries ) {
      print.print( sep );
      print.print( entry );
      sep = separator;
    }
    
  }
  

  /**
   * Concatenate strings
   * 
   * @param entries Entries (each one will be called with toString())
   * @param separator Separator between entries
   * @return Concatenated entries in a string
   * 
   */
  public static String concat( Iterable<?> entries, String separator ) {
    
    PrintStringWriter print = new PrintStringWriter();
    concat( entries, print, separator );
    return print.toString();
    
  }
  
  
  /**
   * Concatenate strings
   * 
   * @param entries Entries (each one will be called with toString())
   * @param separator Separator between entries
   * @return Concatenated entries in a string
   * 
   */
  public static String concat( Enumeration<?> entries, String separator ) {
    
    PrintStringWriter print = new PrintStringWriter();
    concat( Iterables.iterable( entries ), print, separator );
    return print.toString();
    
  }
  
  
  /**
   * Concatenate strings
   * 
   * @param entries Entries (each one will be called with toString())
   * @param separator Separator between entries
   * @return Concatenated entries in a string
   * 
   */
  public static <T> String concat( T[] entries, String separator ) {
    
    PrintStringWriter print = new PrintStringWriter();
    concat( Iterables.iterable( entries ), print, separator );
    return print.toString();
    
  }
  
  
}


