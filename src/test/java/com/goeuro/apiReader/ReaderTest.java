package com.goeuro.apiReader;

/**
 * Unit test for Reader.
 */
public class ReaderTest {

	/**
	 * Test when location name is sent
	 */
	@org.junit.Test(expected=IllegalArgumentException.class)
	public void testNoParametersSent() {
		ApiReader.main(new String[] {});
	}
	
	
	/**
	 * Test when more than one parameters are sent
	 */
	@org.junit.Test(expected=IllegalArgumentException.class)
	public void testTwoParametersSent() {
		ApiReader.main(new String[] {"Berlin", "Cairo"});
	}
	
	
	
	/**
	 * Test in case the query API returned error
	 */
	@org.junit.Test(expected=RuntimeException.class)
	public void testQueryWithError() {
		ApiReader.main(new String[] {"?"});
	}
	
	
	/**
	 * Test when query API returns no data
	 * No exception expected
	 */
	@org.junit.Test
	public void testQueryNoData() {
		ApiReader.main(new String[] {"wrong location"});
	}
	
	
	/**
	 * Test when query API returns data
	 * No exception expected
	 */
	@org.junit.Test
	public void testQueryData() {
		ApiReader.main(new String[] {"Berlin"});
	}
}
