public class PrimeSum {
    public static void main (String[] args) {
        PrimeSum psum = new PrimeSum();
        int count = 1;
        int num = 3; 
        int sum = 2;
        while (count < 1000) {
            if (psum.ifPrime(num)) {
                count++; 
                sum += num;
            }  
            num += 2; 
        }
        System.out.print(sum);
//        System.out.print(count);
    }
    public boolean ifPrime(int num) {
        boolean returnVal = true;
        int numroot = (int)Math.sqrt(num);
        for (int i = 2; i <= numroot; i++) {
            if ((num % i) == 0) {
                returnVal = false;
                break;
            }
        }
        return(returnVal);
    }
}
