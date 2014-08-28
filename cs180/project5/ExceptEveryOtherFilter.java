
/**
 * An ExceptFilter that passes everything except the specified word,
 * but blocks the specified word when it appears for the
 * first, third, fifth, ..., (2k+1)-st time, e.g.:
 * <pre>
 * Filter f = new ExactFilter("target");
 * f.pass("foo"); // returns true
 * f.pass("target"); // returns false (1st appearance)
 * f.pass("bar"); // returns true
 * f.pass("target"); // returns true (2nd appearance)
 * f.pass("target"); // returns false (3rd appearance)
 * </pre>
 * The specified word is given as the only argument to the constructor.
 */
public class ExceptEveryOtherFilter implements Filter {
    Filter matchEveryOther;
    
    public ExceptEveryOtherFilter(String word) {
        matchEveryOther = new MatchEveryOtherFilter(word);
    }
    
    @Override
    public boolean pass(String s) {
        Filter temp = new InvertedFilter(matchEveryOther);
        return temp.pass(s);
    }
}
