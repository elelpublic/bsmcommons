// (C) 1998-2013 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons;

import com.infodesire.bsmcommons.collection.Concat;

import java.util.ArrayList;


/**
 * Clean replacement of ItemString: create a list of strings with separators
 * 
 */
public class StringList extends ArrayList<CharSequence> {


	/**
	 * Serialization id
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static String toString( Iterable<?> entries, String separator ) {
	  return Concat.concat( entries, separator );
	}


	public static String toString( Object[] entries, String separator ) {
	  return Concat.concat( entries, separator );
	}
	
	
	public static String toString( Iterable<?> entries, String separator, boolean skipNulls ) {
	  return new StringList( entries, separator, skipNulls ).toString();
	}
	
	
	public static String toString( Object[] entries, String separator, boolean skipNulls ) {
	  return new StringList( entries, separator, skipNulls ).toString();
	}
	
	
	/**
	 * Separator between strings in toString() method
	 * 
	 */
	private String separator;


	/**
	 * Create string list
	 * 
	 * @param separator Separator between strings in toString() method
	 * 
	 */
	public StringList( String separator ) {
		super();
		this.separator = separator;
	}


	/**
	 * Create string list
	 * 
	 * @param data Prefilled data
	 * @param separator Separator between strings in toString() method
	 * 
	 */
	public StringList( Iterable<?> data, String separator ) {
	  this( data, separator, false );
	}
	
	
  /**
   * Create string list
   * 
   * @param data Prefilled data
   * @param separator Separator between strings in toString() method
   * 
   */
  public StringList( Iterable<?> data, String separator, boolean skipNulls ) {
	  super();
	  for( Object object : data ) {
	    if( object == null ) {
	      if( !skipNulls ) {
	        add( "null" );
	      }
	    }
	    else {
	      add( object.toString() );
	    }
	  }
	  this.separator = separator;
	}
	
	
	/**
	 * Create string list
	 * 
	 * @param data Prefilled data
	 * @param separator Separator between strings in toString() method
	 * 
	 */
	public StringList( Object[] data, String separator ) {
	  this( data, separator, false );
	}


	/**
   * Create string list
   * 
   * @param data Prefilled data
   * @param separator Separator between strings in toString() method
   * 
   */
  public StringList( Object[] data, String separator, boolean skipNulls ) {
	  super();
	  for( Object object : data ) {
      if( object == null ) {
        if( !skipNulls ) {
          add( "null" );
        }
      }
      else {
        add( object.toString() );
      }
	  }
	  this.separator = separator;
	}
	
	
	/**
	 * List of strings with separators
	 * 
	 * @return List of strings with separators
	 * 
	 */
	public String toString() {
		return toStringBuffer().toString();
	}


	/**
	 * List of strings with separators
	 * 
	 * @return List of strings with separators
	 * 
	 */
	public StringBuffer toStringBuffer() {
	  
	  StringBuffer result = new StringBuffer();
	  String sep = null;
	  for( CharSequence entry : this ) {
	    if( sep != null ) {
	      result.append( sep );
	    }
	    else {
	      sep = separator;
	    }
	    result.append( entry );
	  }
	  return result;
	  
	}


  public String getString( int i ) {
    return get( i ).toString();
  }
	
	
}


