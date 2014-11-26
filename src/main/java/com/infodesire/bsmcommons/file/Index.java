// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.file;


/**
 * Deep recursive index of all files and folders in a directory
 *
 */
public interface Index extends Iterable<String> {

  
  /**
   * @param path Path to entry
   * @return File or folder exists
   * 
   */
  boolean exists( String path );
  
  
  
  /**
   * @param path Path to entry
   * @return True: entry is a file (false: folder)
   * 
   */
  boolean isFile( String path );
  
  
  /**
   * @return Number of entries (files and folders)
   * 
   */
  int size();
  
  
}


