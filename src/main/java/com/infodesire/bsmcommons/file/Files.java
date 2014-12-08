// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


/**
 * Utilities for working with files.
 *
 */
public class Files {


  /**
   * Filter which returns only directories
   * 
   */
  public static final FileFilter DIRS = new FileFilter() {


    @Override
    public boolean accept( File pathname ) {
      return pathname.isDirectory();
    }
  };


  /**
   * Filter which returns only directories
   * 
   */
  public static final FileFilter FILES = new FileFilter() {


    @Override
    public boolean accept( File pathname ) {
      return pathname.isFile();
    }
  };


  /**
   * Create a temporary directory
   * 
   * @return Temporary directory
   * @throws IOException if creating directory fails
   * 
   */
  public static File createTempDir() throws IOException {
    return createTempDir( "tmp", null );
  }


  /**
   * Create a temporary directory
   * 
   * @param prefix Dir name prefix
   * @param suffix Dir name suffix
   * @return Temporary directory
   * @throws IOException if creating directory fails
   * 
   */
  public static File createTempDir( String prefix, String suffix )
    throws IOException {

    File tmpFile = File.createTempFile( prefix, suffix );
    File tmpDir = new File( tmpFile.getParentFile(), tmpFile.getName() );
    tmpFile.delete();
    tmpDir.mkdirs();
    return tmpDir;

  }


  /**
   * Create a temporary directory
   * 
   * @param baseDir Base for temporary dit
   * @return Temporary directory
   * @throws IOException if creating directory fails
   * 
   */
  public static File createTempDir( File baseDir ) throws IOException {
    return createTempDir( baseDir, "tmp", null );
  }


  /**
   * Create a temporary directory
   * 
   * @param baseDir Base for temporary dit
   * @param prefix Dir name prefix
   * @param suffix Dir name suffix
   * @return Temporary directory
   * @throws IOException if creating directory fails
   * 
   */
  public static File createTempDir( File baseDir, String prefix, String suffix )
    throws IOException {

    File tmpFile = File.createTempFile( prefix, suffix, baseDir );
    File tmpDir = new File( tmpFile.getParentFile(), tmpFile.getName() );
    tmpFile.delete();
    tmpDir.mkdirs();
    return tmpDir;

  }


  /**
   * Simulate unix 'touch command'. Creates empty file, if it does not exist. Set timestamp to current time.
   * 
   * @param file File to be touched
   * @throws IOException Underlying file system problem
   * 
   */
  public static void touch( File file ) throws IOException {
    long timestamp = System.currentTimeMillis();
    touch( file, timestamp );
  }


  /**
   * Simulate unix 'touch command'. Creates empty file, if it does not exist.
   * 
   * @param file File to be touched
   * @param timestamp New timestamp for file
   * @throws IOException Underlying file system problem
   * 
   */
  public static void touch( File file, long timestamp ) throws IOException {
    if( !file.exists() ) {
      new FileOutputStream( file ).close();
    }
    file.setLastModified( timestamp );
  }


  /**
   * Test if a file or jar url points to a directory
   * 
   * @param url Tested url
   * @return Points to a directory
   * @throws IOException
   * 
   */
  public static boolean isDirectory( URL url ) throws IOException {
    
    String protocol = url.getProtocol();
    
    if( protocol.equals( "file" ) ) {
      return new File( url.getFile() ).isDirectory();
    }
    
    if( protocol.equals( "jar" ) ) {
      String file = url.getFile();
      int bangIndex = file.indexOf( '!' );
      String jarPath = file.substring( bangIndex + 2 );
      file = new URL( file.substring( 0, bangIndex ) ).getFile();
      ZipFile zip = new ZipFile( file );
      try {
        ZipEntry entry = zip.getEntry( jarPath );
        boolean isDirectory = entry.isDirectory();
        if( !isDirectory ) {
          InputStream input = zip.getInputStream( entry );
          isDirectory = input == null;
          if( input != null )
            input.close();
        }
        return isDirectory;
      }
      finally {
        try {
          zip.close();
        }
        catch( Exception ex ) {}
      }
    }
    
    return false;
    
  }

}
