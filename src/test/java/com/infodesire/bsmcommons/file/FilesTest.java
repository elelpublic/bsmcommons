// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.file;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.infodesire.bsmcommons.zip.Zipper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class FilesTest {


  private File tempDir;
  private File targetDir;
  private File helloTxt;
  private File zipFile;


  @Before
  public void setUp() throws Exception {
    tempDir = Files.createTempDir();
    targetDir = Files.createTempDir();
    helloTxt = new File( tempDir, "hello.txt" );
    Files.touch( helloTxt );
    new File( tempDir, "sub" ).mkdir();
    Files.touch( new File( tempDir, "sub/subtest.txt" ) );
    zipFile = new File( targetDir, "data.zip" );
  }


  @After
  public void tearDown() throws Exception {
    FileUtils.deleteDirectory( tempDir );
  }


  @Test
  public void testIsDirectory() throws MalformedURLException, IOException {
    
    assertTrue( Files.isDirectory( tempDir.toURI().toURL() ) );
    assertFalse( Files.isDirectory( helloTxt.toURI().toURL() ) );
    
    Zipper zipper = new Zipper( tempDir );
    zipper.zip( zipFile );
    
    String zipFileUrl = zipFile.toURI().toURL().toString();
    assertTrue( Files.isDirectory( new URL( "jar:" + zipFileUrl + "!/sub" ) ) );
    assertFalse( Files.isDirectory( new URL( "jar:" + zipFileUrl + "!/hello.txt" ) ) );
    assertFalse( Files.isDirectory( new URL( "jar:" + zipFileUrl + "!/sub/subtest.txt" ) ) );
    
  }


}


