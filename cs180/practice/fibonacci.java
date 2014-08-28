public class fibonacci {
  int limit = 0;
  fibonacci(int limit) {
   this.limit = limit; 
  }
  public boolean hasNext(int currentCount){
    if (currentCount < this.limit )
      return true;
    return false;
  }
  public int next(int num1, int num2) {
    return(num1 + num2);
  }
  public static void main (String[] args) {
    int prevNum = 0;
    int num = 1;
    int count = 1;
    int temp = 0;
    fibonacci f = new fibonacci(5);
    while (f.hasNext(count)) {
      System.out.printf("%d, ",num);
      temp = num;
      num = f.next(prevNum, num);
      prevNum = temp;
      count++;
      //System.out.printf("%d, ", num);
    }
  }
}
