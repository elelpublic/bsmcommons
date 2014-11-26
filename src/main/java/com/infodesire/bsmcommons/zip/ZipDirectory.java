// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.zip;

import com.infodesire.bsmcommons.file.Directory;
import com.infodesire.bsmcommons.file.FilePath;
import com.infodesire.bsmcommons.file.Index;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;



/**
 * Represents the files in a zip archive as a directory structure.
 *
 */
public class ZipDirectory implements Directory {
  

  private ZipIndex index;
  private FilePath basePath;
  private ZipFile zipFile;


  /**
   * Read zip file, create index
   * 
   * @param file Zip file
   * @throws IOException if an error occured accessing or decoding the zip file 
   * 
   */
  public ZipDirectory( File file ) throws IOException {
    this( new ZipIndex( new FileInputStream( file ), true, true ), new FilePath( true ) );
    this.zipFile = new ZipFile( file );
  }
  
  
  private ZipDirectory( ZipIndex index, FilePath basePath ) {
    this.basePath = basePath;
  }
  
  
  @Override
  public Iterable<Directory> listDirectories() {
    List<Directory> result = new ArrayList<Directory>();
    for( FilePath path : index.listFolders( basePath ) ) {
      result.add( new ZipDirectory( index, path ) );
    }
    return result;
  }


  @Override
  public Iterable<String> listFiles() {
    List<String> result = new ArrayList<String>();
    for( FilePath path : index.listFiles( basePath ) ) {
      result.add( path.getLast() );
    }
    return result;
  }


  @Override
  public InputStream getData( String fileName ) throws IOException {
    ZipEntry entry = zipFile.getEntry( new FilePath( basePath, fileName ).toString() );
    if( entry == null ) {
      return zipFile.getInputStream( entry );
    }
    else {
      return null;
    }
  }


  @Override
  public Index createIndex() {
    return index;
  }
  
  
}


