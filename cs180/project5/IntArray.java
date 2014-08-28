
/**
 * An implementation of {@link IntArray} that uses an array.
 */
public class IntArray implements IntSequence {
    
    int[] a;
    boolean isSubSequence = false;
    IntSequence parentSequence;
    int parentIndex;
    
    public IntArray(int size) { // Note: do *not* change constructor parameters
        a = new int[size];
    }
    
    public IntArray(int size, IntSequence origSequence, int index) { // Note: do *not* change constructor parameters
        this.a = new int[size];
        isSubSequence = true;
        this.parentSequence = origSequence;
        this.parentIndex = index;
    }
    
    @Override
    public int length() {
        return this.a.length;
    }
    
    @Override
    public int get(int index) {
        return this.a[index];
    }
    
    @Override
    public void set(int index, int value) {
        if (this.isSubSequence)
            parentSequence.set(index + parentIndex, value);
        this.a[index] = value;
    }
    
    @Override
    public IntSequence subSequence(int index, int size) {
        // TODO implement
        IntSequence b = this;
        IntSequence s = new IntArray(size, b, index);
        for (int i = 0; i < size; i++)
            s.set(i, this.get(index + i));
        return s;
    }
    
    // Driver program - feel free to change it as it won't be tested
    public static void main(String[] args) {
        IntSequence a = new IntArray(5);
        a.set(0, 0);
        a.set(1, 10);
        a.set(2, 20);
        a.set(3, 30);
        a.set(4, 40);
        
        System.out.println("Initial array");
        System.out.println("size: " + a.length());
        for (int i = 0; i < 5; ++i)
            System.out.println("a[" + i + "]: " + a.get(i));
        
        System.out.println("Creating subarray (2, 2)");
        IntSequence s = a.subSequence(2, 2);
        System.out.println("s.size: " + s.length());
        
        System.out.println("Multiplying subarray's last element");
        s.set(1, s.get(1) * 10);
        System.out.println("Subarray after modification:");
        for (int i = 0; i < s.length(); ++i)
            System.out.println("s[" + i + "]: " + s.get(i));
        
        System.out.println("Array after modification:");
        for (int i = 0; i < 5; ++i)
            System.out.println("a[" + i + "]: " + a.get(i));
        
        a.subSequence(0, 1).subSequence(0, 1).subSequence(0, 1).set(0, -10);
        System.out.println("First element changed to: " + a.get(0));
        
        System.out.println("Creating subarray (1, 4)");
        IntSequence q = a.subSequence(1, 4);
        System.out.println("q.size: " + q.length());
        System.out.println("Array after modification:");
        for (int i = 0; i < q.length(); ++i)
            System.out.println("q[" + i + "]: " + q.get(i));
        
        System.out.println("Creating subarray (2, 2)");
        IntSequence r = q.subSequence(2, 2);
        
        System.out.println("r.size: " + r.length());
        System.out.println("Array after modification:");
        for (int i = 0; i < r.length(); ++i)
            System.out.println("r[" + i + "]: " + r.get(i));
        
        System.out.println("Multiplying subarray's last element");
        r.set(1, r.get(1) * 10);
        
        
        for (int i = 0; i < a.length(); ++i)
            System.out.println("a[" + i + "]: " + a.get(i));
        for (int i = 0; i < r.length(); ++i)
            System.out.println("q[" + i + "]: " + q.get(i));
        //for (int i = 0; i < r.length(); ++i)
          //  System.out.println("q[" + i + "]: " + q.get(i));
    }
}
