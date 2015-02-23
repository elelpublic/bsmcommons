// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.collection;

import java.util.Enumeration;
import java.util.Iterator;


/**
 * Creates an iterable from an enumeration
 * 
 * @param <T> Type of entries
 *
 */
public class EnumerationIterable<T> implements Iterable<T> {


  private Enumeration<T> enumeration;

  public EnumerationIterable( Enumeration<T> enumeration ) {
    this.enumeration = enumeration;
  }

  @Override
  public Iterator<T> iterator() {
    return new EnumerationIterator<T>( enumeration );
    
  }

}
