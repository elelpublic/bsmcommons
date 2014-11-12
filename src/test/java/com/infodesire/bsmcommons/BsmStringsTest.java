// (C) 1998-2015 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class BsmStringsTest {


  @Before
  public void setUp() throws Exception {
  }


  @After
  public void tearDown() throws Exception {
  }


  @Test
  public void testAfter() {
    
    assertEquals( "national", BsmStrings.after( "International", "Inter" ) );
    assertNull( BsmStrings.after( "International", "Interview" ) );
    assertEquals( "national", BsmStrings.after( "national", "" ) );
    assertNull( BsmStrings.after( "", "abc" ) );
    
    try {
      assertEquals( "national", BsmStrings.after( "national", null ) );
      fail( "Expected NullPointerException" );
    }
    catch( NullPointerException ex ) {}
    
    try {
      assertEquals( "national", BsmStrings.after( null, "nat" ) );
      fail( "Expected NullPointerException" );
    }
    catch( NullPointerException ex ) {}

    try {
      assertEquals( "national", BsmStrings.after( null, null ) );
      fail( "Expected NullPointerException" );
    }
    catch( NullPointerException ex ) {}
    
  }

}
