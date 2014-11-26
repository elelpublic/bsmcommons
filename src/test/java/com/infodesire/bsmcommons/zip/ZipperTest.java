// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.zip;

import static org.junit.Assert.*;

import com.infodesire.bsmcommons.file.FileIndex;
import com.infodesire.bsmcommons.file.Files;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class ZipperTest {


  private File tmp;


  @Before
  public void setUp() throws Exception {
    tmp = Files.createTempDir();
  }


  @After
  public void tearDown() throws Exception {
    FileUtils.deleteDirectory( tmp );
  }


  @Test
  public void test() throws IOException {
    
    File baseDir = Files.createTempDir( tmp );

    File sub1 = new File( baseDir, "sub1" );
    sub1.mkdirs();
    Files.touch( new File( sub1, "t11.txt" ) );
    Files.touch( new File( sub1, "t12.txt" ) );

    File sub2 = new File( baseDir, "sub2" );
    sub2.mkdirs();
    Files.touch( new File( sub2, "t21.txt" ) );
    Files.touch( new File( sub2, "t22.txt" ) );
  
    File sub11 = new File( sub1, "sub1" );
    sub11.mkdirs();
    Files.touch( new File( sub11, "t111.txt" ) );
    Files.touch( new File( sub11, "t112.txt" ) );

    Zipper zipper = new Zipper( sub1 );
    zipper.add( sub2 );
    File zipFile = new File( tmp, "zipper.zip" );
    zipper.zip( zipFile );
    
    File targetDir = Files.createTempDir( tmp );
    Unzip.unzip( zipFile, targetDir );
    
    FileIndex targetIndex = new FileIndex( targetDir );
    assertEquals( 8, targetIndex.size() );
    
    assertFolder( targetDir );
    assertFolder( new File( targetDir, "sub1" ) );
    
    assertFile( new File( targetDir, "t11.txt" ) );
    assertFile( new File( targetDir, "t12.txt" ) );
    assertFile( new File( new File( targetDir, "sub1" ), "t111.txt" ) );
    assertFile( new File( new File( targetDir, "sub1" ), "t112.txt" ) );
    assertFile( new File( targetDir, "t21.txt" ) );
    assertFile( new File( targetDir, "t22.txt" ) );
    
  }


  private void assertFile(File file ) {
    assertTrue( file.exists() );
    assertTrue( file.isFile() );
  }

  
  private void assertFolder(File file ) {
    assertTrue( file.exists() );
    assertTrue( file.isDirectory() );
  }
  

}


