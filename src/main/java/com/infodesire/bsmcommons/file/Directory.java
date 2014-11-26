// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.file;

import java.io.IOException;
import java.io.InputStream;


/**
 * A recursive structure of files. 
 * <p>
 * 
 * This abstraction can be used to access ordinary file system directories 
 * or files in a zip archive or anything else.
 *
 */
public interface Directory {
  

  /**
   * @return All direct sub directories (no recursion)
   * 
   */
  Iterable<Directory> listDirectories();

  
  /**
   * @return All files in current directory (no recursion)
   * 
   */
  Iterable<String> listFiles();
  
  
  /**
   * Read data from file
   * 
   * @param fileName Name of file in this directory
   * @return Stream of data or null if file does not exists
   * @throws IOException if there is a problem in the underlying io system
   * 
   */
  InputStream getData( String fileName ) throws IOException;


  /**
   * @return Deep recursive index of all files and sub directories
   * 
   */
  Index createIndex();
  

}


