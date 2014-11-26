// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Directory on the file system
 *
 */
public class FileDirectory implements Directory {
  
  
  private File baseDir;

  public FileDirectory( File baseDir ) {
    this.baseDir = baseDir;
  }

  @Override
  public Iterable<Directory> listDirectories() {
    List<Directory> result = new ArrayList<Directory>();
    for( File file : baseDir.listFiles( Files.DIRS ) ) {
      result.add( new FileDirectory( file ) );
    }
    return result;
  }

  @Override
  public Iterable<String> listFiles() {
    List<String> result = new ArrayList<String>();
    for( File file : baseDir.listFiles( Files.FILES ) ) {
      result.add( file.getName() );
    }
    return result;
  }

  @Override
  public InputStream getData( String fileName ) {
    try {
      return new FileInputStream( new File( baseDir, fileName ) );
    }
    catch( FileNotFoundException ex ) {
      return null;
    }
  }

  @Override
  public Index createIndex() {
    return new FileIndex( baseDir );
  }


}


