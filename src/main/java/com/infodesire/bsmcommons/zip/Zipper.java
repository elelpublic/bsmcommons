// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.zip;

import com.infodesire.bsmcommons.file.Directory;
import com.infodesire.bsmcommons.file.FileDirectory;
import com.infodesire.bsmcommons.file.Index;
import com.infodesire.bsmcommons.io.Bytes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * Zips directories of files into a zip file
 *
 */
public class Zipper {
  
  private List<Directory> directories = new ArrayList<Directory>();

  public Zipper() {
  }
  
  public Zipper( File dir ) {
    add( dir );
  }
  
  public Zipper( Directory dir ) {
    add( dir );
  }
  
  
  /**
   * Add a directory to the result
   * 
   * @param dir New directory
   * 
   */
  public void add( File dir ) {
    add( new FileDirectory( dir ) );
  }

  
  /**
   * Add a directory to the result
   * 
   * @param dir New directory
   * 
   */
  public void add( Directory dir ) {
    directories.add( dir );
  }
  
  
  /**
   * Create zip file with files from all directories
   * 
   * @param outFile Target zip file
   * @throws IOException when the underlying io system fails with an error
   * 
   */
  public void zip( File outFile ) throws IOException {
    OutputStream out = new FileOutputStream( outFile );
    zip( out );
    out.close();
  }
  
  /**
   * Create zip file with files from all directories
   * 
   * @param outFileName Target zip file
   * @throws IOException when the underlying io system fails with an error
   * 
   */
  public void zip( String outFileName ) throws IOException {
    zip( new File( outFileName ) );
  }

  /**
   * Create zip file with files from all directories
   * 
   * @param out Target zip file stream
   * @throws IOException when the underlying io system fails with an error
   * 
   */
  public void zip( OutputStream out ) throws IOException {
    
    ZipOutputStream zipOut = new ZipOutputStream( out );
    for( Directory directory : directories ) {
      Index index = directory.createIndex();
      for( String entryName : index ) {
        if( index.isFile( entryName ) ) {
          InputStream content = directory.getData( entryName );
          zipFile( zipOut, entryName, content );
        }
      }
    }
    zipOut.close();
    
  }
  

  private void zipFile( ZipOutputStream zipOut, String fileName, InputStream content ) throws IOException {
    zipOut.putNextEntry( new ZipEntry( fileName ) );
    Bytes.pipe( content, zipOut );
  }


}


