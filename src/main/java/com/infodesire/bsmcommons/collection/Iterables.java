// (C) 1998-2016 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.collection;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;


/**
 * Iterate elements.
 * 
 *
 */
public class Iterables {
  
  
  /**
   * Make an enumeration iterable. Please note that each enumeration can be used only once.
   * 
   * @param enumeration Enumeration
   * @return Iterable
   * 
   */
  public static <T> Iterable<T> iterable( Enumeration<T> enumeration ) {
    return new EnumerationIterable<T>( enumeration );
  }
  

  /**
   * Make an enumeration iterable. Please note that each enumeration can be used only once.
   * 
   * @param enumeration Enumeration
   * @return Iterable
   * 
   */
  public static <T> Iterable<T> iterable( T[] elements ) {
    return Arrays.asList( elements );
  }
  
  
  /**
   * Create an iterator from an enumeration. Please note that each enumeration can be used only once.
   * 
   * @param enumeration Enumeration
   * @return Iterator
   * 
   */
  public static <T> Iterator<T> iterator( Enumeration<T> enumeration ) {
    return new EnumerationIterator<T>( enumeration );
  }
  
  
}


