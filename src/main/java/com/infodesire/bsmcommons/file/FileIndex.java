// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.file;

import com.infodesire.bsmcommons.io.PrintStringWriter;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Index for a file directory
 *
 */
public class FileIndex implements Index {


  private File baseDir;
  private List<String> index;


  public FileIndex( File baseDir ) {
    this.baseDir = baseDir;
  }


  @Override
  public Iterator<String> iterator() {
    return getIndex().iterator();
  }
  

  private List<String> getIndex() {
    if( index == null ) {
      index = buildIndex( new FilePath( true ) );
    }
    return index;
  }


  private List<String> buildIndex( FilePath prefix ) {
    List<String> result = new ArrayList<String>();
    result.add( prefix.toString() );
    for( File file : baseDir.listFiles( Files.FILES ) ) {
      result.add( new FilePath( prefix, file.getName() ).toString() );
    }
    for( File file : baseDir.listFiles( Files.DIRS ) ) {
      String name = file.getName();
      // recursion:
      result.addAll( new FileIndex( file ).buildIndex( new FilePath( prefix,
        name ) ) );
    }
    return result;
  }


  @Override
  public boolean exists( String path ) {
    return new File( baseDir, path ).exists();
  }


  @Override
  public boolean isFile( String path ) {
    return new File( baseDir, path ).isFile();
  }


  @Override
  public int size() {
    return getIndex().size();
  }


  /**
   * @return Full listing
   * 
   */
  public String createListing() {
    PrintStringWriter s = new PrintStringWriter();
    createListing( s, "\n" );
    return s.toString();
  }
  
  
  /**
   * Generate full listing
   * @param out Print target
   * 
   */
  public void createListing( PrintWriter out, String lineSeparator ) {
    String sep = "";
    for( String path : index ) {
      out.print( sep + path + ( !isFile( path ) ? "/" : "" ) );
      sep = lineSeparator;
    }
  }


}
