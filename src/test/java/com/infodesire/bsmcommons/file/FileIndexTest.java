// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.file;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FileIndexTest {


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
    Files.touch( new File( baseDir, "t1.txt" ) );
    Files.touch( new File( baseDir, "t2.txt" ) );

    File sub1 = new File( baseDir, "sub1" );
    sub1.mkdirs();
    Files.touch( new File( sub1, "t1.txt" ) );
    Files.touch( new File( sub1, "t2.txt" ) );

    File sub2 = new File( baseDir, "sub2" );
    sub2.mkdirs();
    Files.touch( new File( sub2, "t1.txt" ) );
    Files.touch( new File( sub2, "t2.txt" ) );
  
    File sub11 = new File( sub1, "sub1" );
    sub11.mkdirs();
    Files.touch( new File( sub11, "t1.txt" ) );
    Files.touch( new File( sub11, "t2.txt" ) );
    
    FileIndex index = new FileIndex( baseDir );
    
    assertFolder( index, "" );
    assertFolder( index, "sub1" );
    assertFolder( index, "sub2" );
    assertFolder( index, "sub1/sub1" );

    assertFile( index, "t1.txt" );
    assertFile( index, "t2.txt" );
    assertFile( index, "sub1/t1.txt" );
    assertFile( index, "sub1/t2.txt" );
    assertFile( index, "sub2/t1.txt" );
    assertFile( index, "sub2/t2.txt" );
    assertFile( index, "sub1/sub1/t1.txt" );
    assertFile( index, "sub1/sub1/t2.txt" );
    
    assertFalse( index.exists( "t3.txt" ) );
    assertFalse( index.exists( "sub3" ) );
    
    Set<String> entries = new HashSet<String>();
    for( String entry : index ) {
      entries.add( entry );
    }
    
    assertTrue( entries.contains( "" ) );
    assertTrue( entries.contains( "sub1" ) );
    assertTrue( entries.contains( "sub2" ) );
    assertTrue( entries.contains( "sub1/sub1" ) );
    
    assertTrue( entries.contains( "t1.txt" ) );
    assertTrue( entries.contains( "t2.txt" ) );
    assertTrue( entries.contains( "sub1/t1.txt" ) );
    assertTrue( entries.contains( "sub1/t2.txt" ) );
    assertTrue( entries.contains( "sub2/t1.txt" ) );
    assertTrue( entries.contains( "sub2/t2.txt" ) );
    assertTrue( entries.contains( "sub1/sub1/t1.txt" ) );
    assertTrue( entries.contains( "sub1/sub1/t2.txt" ) );
    
    assertEquals( 12, entries.size() );
    assertEquals( 12, index.size() );
    
  }


  private void assertFile( FileIndex index, String path ) {
    assertTrue( index.exists( path ) );
    assertTrue( index.isFile( path ) );
  }

  
  private void assertFolder( FileIndex index, String path ) {
    assertTrue( index.exists( path ) );
    assertFalse( index.isFile( path ) );
  }
  

}

