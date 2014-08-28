
/**
 * An infinite sequence defined by the pair (a, d) as follows:
 * a, a + d, a + 2*d, ..., a + 2*n+1, ...
 * <br>
 * It also acts as a filter that filters only elements that belong
 * to the arithmetic sequence.
 * <br>
 * Note: this sequence is immutable and set() has no effect.
 */
public class ArithmeticSequence implements IntSequence, Filter {
    
    int a; // first term
    int d; // common difference between successive terms
    
    public ArithmeticSequence(int a, int d) {
        this.a = a;
        this.d = d;
    }
    
    @Override
    public int length() {
        return -1;
    }
    
    @Override
    public int get(int index) {
        return a + index * d;
    }
    
    @Override
    public void set(int index, int value) {
        // (not supported / ignored)
    }
    
    @Override
    public IntSequence subSequence(int index, int size) {
        IntSequence s = new ArithmeticSequence(this.get(index), this.d);
        return s;
    }
    
    /**
     * Checks whether an integer represented by the specified string
     * belongs to the arithmetic sequence.
     * @returns true if so, false otherwise
     */
    @Override
    public boolean pass(String s) {
        // TODO implement
        int number = Integer.parseInt(s);
        if (((this.d > 0) && (number >= this.a)) || ((this.d < 0) && (number <= this.a))) {
            if (((number - this.a) % this.d) == 0)
                return true;
        }
        return false;
    }
}
