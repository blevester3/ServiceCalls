package junittests;

import junit.framework.TestCase;

import org.json.JSONException;
import org.junit.Test;

import stats.WebCalls;

public class TestWebCalls extends TestCase {
	WebCalls webCalls;

	@Test
	public void testGoodEntry() throws JSONException {
		webCalls = new WebCalls("2014-08-27");
		try {
			String testString = webCalls.getStats();
			assertEquals(testString, "{\"games\": [{\"team\":{\"site\":\"H\",\"opponent\":\"Abilene Christian \",\"defense\":{\"rush\":{\"att\":\"36 \",\"yds\":\"95 \"},\"turnovers\":{\"int\":\"1 \",\"fumbles\":\"0 \"},\"pts\":\"37 \",\"pass\":{\"comp\":\"30 \",\"att\":\"40 \",\"yds\":\"403 \"}},\"name\":\"Georgia State \",\"offense\":{\"rush\":{\"att\":\"33 \",\"yds\":\"153 \"},\"turnovers\":{\"int\":\"2 \",\"fumbles\":\"1 \"},\"pts\":\"38 \",\"pass\":{\"comp\":\"31 \",\"att\":\"49 \",\"yds\":\"413 \"}}},\"date\":\"2014-08-27\"}]}");
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception");
		}
	}
	
	@Test
	public void testBadEntry(){
		webCalls = new WebCalls("2014-27");
		try {
			String testString = webCalls.getStats();
			assertEquals("{\"team\":\"none\"}", testString);
		}
		catch (Exception e){
			e.printStackTrace();
			fail("Exception");
		}
	}
	
	@Test
	public void testNoEntry(){
		webCalls = new WebCalls("");
		try{
			String testString = webCalls.getStats();
			assertEquals("{\"team\":\"none\"}", testString);
		}
		catch (Exception e){
			e.printStackTrace();
			fail("Exception");
		}
	}
}
