// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons;



/**
 * String handling.
 * 
 *
 */
public class BsmStrings {


  /**
   * Returns text after a prefix.
   *
   * @param text Full text
   * @param prefix Start text
   * @return Sub string of full text following the prefix or null
   *          if no such text exists.
   *
   */
  public static String after( String text, String prefix ) {

    if( !text.startsWith( prefix ) ) {

      return null;

    }

    return text.substring( prefix.length() );

  }

  
  /**
   * Returns text before a suffix.
   *
   * @param text Full text
   * @param suffix Start text
   * @return Sub string of full text before the suffix or null
   *          if no such text exists.
   *
   */
  public static String before( String text, String suffix ) {

    if( !text.endsWith( suffix ) ) {

      return null;

    }

    return text.substring( 0, text.length() - suffix.length() );

  }

  
  /**
   * The string before the first occurrence of a given separator
   * 
   * @param text Input text
   * @param separator The separator string
   * @return The string before the first occurrence of a given separator or the whole text
   *  if no separator was found
   * 
   */
  public static String beforeFirst( String text, char separator ) {
    return beforeFirst( text, "" + separator );
  }
  
  
  /**
   * The string before the first occurrence of a given separator
   * 
   * @param text Input text
   * @param separator The separator string
   * @return The string before the first occurrence of a given separator or the whole text
   *  if no separator was found
   * 
   */
  public static String beforeFirst( String text, String separator ) {

    if( text == null || separator == null ) {
      return text;
    }
    else {
      int index = text.indexOf( separator );
      if( index == -1 ) {
        return text;
      }
      else {
        return text.substring( 0, index );
      }
    }

  }
  
  
  /**
   * The string before the last occurrence of a given separator
   * 
   * @param text Input text
   * @param separator The separator string
   * @return The string before the last occurrence of a given separator or the whole text
   *  if no separator was found
   * 
   */
  public static String beforeLast( String text, char separator ) {
    return beforeLast( text, "" + separator );
  }
  
    
  /**
   * The string before the last occurrence of a given separator
   * 
   * @param text Input text
   * @param separator The separator string
   * @return The string before the last occurrence of a given separator or the whole text
   *  if no separator was found
   * 
   */
  public static String beforeLast( String text, String separator ) {

    if( text == null || separator == null ) {
      return text;
    }
    else {
      int index = text.lastIndexOf( separator );
      if( index == -1 ) {
        return text;
      }
      else {
        return text.substring( 0, index );
      }
    }

  }
  
  
  /**
   * The string after the first occurrence of a given separator
   * 
   * @param text Input text
   * @param separator The separator string
   * @return The string after the first occurrence of a given separator or null
   *  if no separator was found
   * 
   */
  public static String afterFirst( String text, char separator ) {
    return afterFirst( text, "" + separator );
  }
  
  
  /**
   * The string after the first occurrence of a given separator
   * 
   * @param text Input text
   * @param separator The separator string
   * @return The string after the first occurrence of a given separator or null
   *  if no separator was found
   * 
   */
  public static String afterFirst( String text, String separator ) {

    if( text == null || separator == null ) {
      return text;
    }
    else {
      int index = text.indexOf( separator );
      if( index == -1 ) {
        return null;
      }
      else {
        return text.substring( index + separator.length() );
      }
    }

  }
  
  
  /**
   * The string after the last occurrence of a given separator
   * 
   * @param text Input text
   * @param separator The separator string
   * @return The string after the last occurrence of a given separator or null
   *  if no separator was found
   * 
   */
  public static String afterLast( String text, char separator ) {
    return afterLast( text, "" + separator );
  }
  
  
  /**
   * The string after the last occurrence of a given separator
   * 
   * @param text Input text
   * @param separator The separator string
   * @return The string after the last occurrence of a given separator or null
   *  if no separator was found
   * 
   */
  public static String afterLast( String text, String separator ) {
    
    if( text == null || separator == null ) {
      return text;
    }
    else {
      int index = text.lastIndexOf( separator );
      if( index == -1 ) {
        return null;
      }
      else {
        return text.substring( index + separator.length() );
      }
    }
    
  }


}


