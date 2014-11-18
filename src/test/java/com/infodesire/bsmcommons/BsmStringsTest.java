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
    
    assertEquals( "national", Strings.after( "International", "Inter" ) );
    assertNull( Strings.after( "International", "Interview" ) );
    assertEquals( "national", Strings.after( "national", "" ) );
    assertNull( Strings.after( "", "abc" ) );
    
    try {
      assertEquals( "national", Strings.after( "national", null ) );
      fail( "Expected NullPointerException" );
    }
    catch( NullPointerException ex ) {}
    
    try {
      assertEquals( "national", Strings.after( null, "nat" ) );
      fail( "Expected NullPointerException" );
    }
    catch( NullPointerException ex ) {}

    try {
      assertEquals( "national", Strings.after( null, null ) );
      fail( "Expected NullPointerException" );
    }
    catch( NullPointerException ex ) {}
    
  }

}
