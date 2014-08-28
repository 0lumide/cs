public interface IntSequence {
    /**
     * Returns the number of elements.
     * @return a non-negative integer
     */
    int length();
    
    /**
     * Retrieves the index-th element (starting from 0).
     * @param index a non-negative integer
     * @return the element at position index
     */
    int get(int index);
    
    /**
     * Sets the index-th element (starting from 0) to the specified value.
     * @param index a non-negative integer
     * @param value the desired value
     */
    void set(int index, int value);
    
    /**
     * Returns a contiguous subsequence of size "size" which starts from
     * the index "index" and is backed by the sequence;
     * that is, changing it through {@link IntSequence#set(int, int)}
     * affects the original sequence as well.
     * @param index the starting position of the subsequence
     * @param size the subsequence size
     * @return a sequence of ints
     */
    IntSequence subSequence(int index, int size);
}