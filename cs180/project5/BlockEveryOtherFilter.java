
/**
 * A filter that blocks the first time, third time,
 * fifth time, ..., (2k+1)-st time, e.g.:
 * <pre>
 * Filter f = new BlocksEveryOtherFilter();
 * f.pass(""); // returns false
 * f.pass(""); // returns true
 * f.pass("foo"); // returns false
 * f.pass("bar"); // returns true
 * </pre>
 */
class BlockEveryOtherFilter implements Filter {
    boolean ret = true;
    
    @Override
    public boolean pass(String s) {
        return ret ^= true;
    }
}