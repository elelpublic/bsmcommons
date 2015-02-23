// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.collection;

import java.util.Enumeration;
import java.util.Iterator;


/**
 * Iterator over elements of an enumeration
 *
 */
public class EnumerationIterator<T> implements Iterator<T> {

  private Enumeration<T> enumeration;

  public EnumerationIterator( Enumeration<T> enumeration ) {
    this.enumeration = enumeration;
  }

  @Override
  public boolean hasNext() {
    return enumeration.hasMoreElements();
  }

  @Override
  public T next() {
    return enumeration.nextElement();
  }

  public void remove() {
    throw new UnsupportedOperationException("remove");
  }

}
