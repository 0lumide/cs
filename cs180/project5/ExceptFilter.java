
/**
 * A filter that passes everything except the specified word.
 */
class ExceptFilter implements Filter {
    String word;
    
    public ExceptFilter(String word) {
        this.word = word;
    }
    
    @Override
    public boolean pass(String s) {
        return !(s == null ? word == null : s.equals(word));
    }
}