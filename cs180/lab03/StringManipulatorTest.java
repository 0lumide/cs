import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class StringManipulatorTest extends TestCase {
  
  /**
   * A test method.
   * (Replace "X" with a name describing the test.  You may write as
   * many "testSomething" methods in this class as you wish, and each
   * one will be called when running JUnit over this class.)
   */
  public void testMakeUsernameBasic() {
    StringManipulator strman = new StringManipulator();
    String ret= strman.makeUsername("john doe");
    assertEquals("jdoe", ret);
  }
  public void testMakeUsernameLower() {
    StringManipulator strman = new StringManipulator();
    String ret= strman.makeUsername("JOhn dOe");
    assertEquals("jdoe", ret);
  }
  public void testMakeEmail() {
    StringManipulator strman = new StringManipulator();
    String ret= strman.makeEmail("jdoe", "PuRdue.edu");
    assertEquals("jdoe@purdue.edu", ret);
  }
  
}
