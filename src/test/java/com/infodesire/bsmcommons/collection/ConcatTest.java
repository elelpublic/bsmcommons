// (C) 1998-2016 Information Desire Software GmbH
// www.infodesire.com

package com.infodesire.bsmcommons.collection;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class ConcatTest {


  @Before
  public void setUp() throws Exception {
  }


  @After
  public void tearDown() throws Exception {
  }


  @Test
  public void test() {
    assertEquals( "", Concat.concat( new String[ 0 ], "" ) );
    assertEquals( "A", Concat.concat( new String[]{ "A" }, "" ) );
    assertEquals( "AB", Concat.concat( new String[]{ "A", "B" }, "" ) );
    assertEquals( "A B", Concat.concat( new String[]{ "A", "B" }, " " ) );
  }

}
