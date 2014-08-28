
public interface Filter {
    /**
     * Determines whether the specified string passes the filter.
     * @param s a string
     * @return <tt>true</tt> if yes, <tt>false</tt> otherwise
     */
    boolean pass(String s);
}