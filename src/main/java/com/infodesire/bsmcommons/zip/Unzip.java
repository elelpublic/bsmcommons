// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.zip;

import com.infodesire.bsmcommons.io.Bytes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * Unzip zip files (and jars, wars, ...)
 * 
 *
 */
public class Unzip {


  /**
   * Unzip a file
   * 
   * @param zipFile Zipped file (or jar, war, ...)
   * @param targetDir Where to unpack files to
   * @throws FileNotFoundException if zip file was not found or one file could not be created
   * @throws IOException if an error occurred in the underlying system
   * 
   */
  public static void unzip( File zipFile, File targetDir )
    throws FileNotFoundException, IOException {
    unzip( new FileInputStream( zipFile ), targetDir );
  }


  /**
   * Unzip a file
   * 
   * @param zipInput Input stream from a zip file
   * @param targetDir Where to unpack files to
   * @throws FileNotFoundException if zip file was not found or one file could not be created
   * @throws IOException if an error occurred in the underlying system
   * 
   */
  public static void unzip( InputStream zipInput, File targetDir )
    throws FileNotFoundException, IOException {

    ZipInputStream zipInputStream = new ZipInputStream( zipInput );
    //while( zipInputStream.available() > 0 ) {
    for( ZipEntry entry = zipInputStream.getNextEntry(); entry != null; entry = zipInputStream
      .getNextEntry() ) { // end of entries
      if( !entry.isDirectory() ) {
        File file = new File( targetDir, entry.getName() );
        file.getParentFile().mkdirs();
        Bytes.pipe( zipInputStream, new FileOutputStream( file ) );
      }
    }
    //}

  }


}


