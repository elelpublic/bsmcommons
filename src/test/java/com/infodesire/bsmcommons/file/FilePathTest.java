// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.file;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class FilePathTest {


  @Before
  public void setUp() throws Exception {
  }


  @After
  public void tearDown() throws Exception {
  }


  @Test
  public void test() {
    
    assertTrue( FilePath.parse( "" ).isRelative() );
    assertTrue( FilePath.parse( "" ).isBase() );
    assertFalse( FilePath.parse( "/" ).isRelative() );
    assertTrue( FilePath.parse( "/" ).isBase() );

    assertTrue( FilePath.parse( " " ).isRelative() );
    assertTrue( FilePath.parse( " " ).isBase() );
    assertFalse( FilePath.parse( " /" ).isRelative() );
    assertTrue( FilePath.parse( " /" ).isBase() );
    
    assertFalse( FilePath.parse( " / " ).isRelative() );
    assertTrue( FilePath.parse( " / " ).isBase() );
    
    assertNull( FilePath.parse( "" ).getParent() );
    assertNull( FilePath.parse( "/" ).getParent() );
    
    FilePath root = FilePath.parse( "/" );
    FilePath base = FilePath.parse( "" );
    
    assertEquals( base, FilePath.parse( "abc" ).getParent() );
    assertEquals( root, FilePath.parse( "/abc" ).getParent() );
    assertEquals( base, FilePath.parse( "/abc" ).removeFirst() );
    
    assertEquals( "/", FilePath.normalize( "//" ) );
    assertEquals( "/", FilePath.normalize( " / " ) );
    assertEquals( "abc", FilePath.normalize( " abc " ) );
    assertEquals( "/abc", FilePath.normalize( " / abc " ) );
    assertEquals( "abc/def/ghi", FilePath.normalize( " abc / def /ghi/" ) );
    assertEquals( "abc/def/ghi", FilePath.normalize( "abc//def///ghi//" ) );
    
  }


  /**
   * Ticket 23005: concurrent rest requests caused RestappsFilter to return wrong uri paths
   */
  @Test
  public void testConcurrency() throws InterruptedException {

    class MyThread extends Thread {

      Error error;

      public void run() {
        for( int i = 0; i < 100; i++ ) {
          try {
            Thread.sleep( (long) ( Math.random() * 10 ) );
          }
          catch( InterruptedException e ) {
          }
          try {
            FilePath path = FilePath.parse( "/projectile/rest/flyer/abc" );
            assertEquals( "1", "/projectile/rest/flyer/abc", path.toString() );
            path = path.removeFirst();
            assertEquals( "2", "rest/flyer/abc", path.toString() );
            path = path.removeFirst();
            assertEquals( "3", "flyer/abc", path.toString() );
            path = path.removeFirst();
            assertEquals( "4", "abc", path.toString() );
          }
          catch( Error ex ) {
            error = ex;
          }
        }
      }
    }

    List<MyThread> threads = new ArrayList<MyThread>();

    for( int i = 0; i < 10; i++ ) {
      threads.add( new MyThread() );
    }

    for( MyThread t : threads ) {
      t.start();
    }

    boolean ok = true;
    for( MyThread t : threads ) {
      t.join();
      if( t.error != null ) {
        ok = false;
        t.error.printStackTrace();
      }
    }
    if( !ok ) {
      fail();
    }

  }


}


