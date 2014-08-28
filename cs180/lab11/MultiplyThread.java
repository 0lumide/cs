
public class MultiplyThread implements Runnable {
    
    private double[][] a, b, ans;
    private int start, end;
    
    public MultiplyThread(double[][] a, double[][] b, double[][] ans, int start,
            int end) {
        // TODO: Complete this constructor
        this.a = a;
        this.b = b;
        this.ans = ans;
        this.start = start;
        this.end = end;
        
    }
    
    
    /**
     * This method is essentially the "main" method of the new thread. It is
     * where the new thread begins execution when the start() method is called.
     * 
     * You should implement matrix multiplication starting at row 'start' and
     * Ending at row 'end'
     */
    @Override
    public void run() {
        // TODO: Complete this method
        for (int i = this.start; i <= this.end; i++) {//range start to end
            for (int k = 0; k < b[0].length; k++) {
                ans[i][k] = 0;
                for (int q = 0; q < a[0].length; q++) {
                    ans[i][k] += a[i][q] * b[q][k]; 
                    System.out.println("i :" + i + ", k: " + k + ", q: " + q);
                }
            }
        }
    }
}
