
/**
 * A filter passes only the matching word (given via the constructor),
 * but every other time, i.e. first, third, fifth, ..., (2k+1)-st time.
 */
public class MatchEveryOtherFilter implements Filter {
    Filter inverted;
    int count = 0;
    
    public MatchEveryOtherFilter(String word) {
        inverted = new InvertedFilter(new ExceptFilter(word));
    }
    
    @Override
    public boolean pass(String s) {
        boolean temp = inverted.pass(s);
        if (temp) {
            if ((count % 2) != 0) {
                count++;
                return false;
            }
            count++;
        }
        return temp;
        
    }
}
