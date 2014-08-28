public class Question15Fall13 {
    public static void main (String[] args) {
        boolean x;
        boolean xa;
        boolean xb;
        boolean xc;
        boolean xd;
        boolean xe;
        boolean a = true;
        boolean b = true;
        boolean c = true;
        System.out.println("Actual \t Option A \t Option B \t Option C \t Option D \t Option E \t");
        for(int i = 0; i<2; i++) {
            if(i == 1){a = false;}
            for(int j = 0; j < 2; j++) {
                if(j == 1){b = false;}
                for(int k = 0; k < 2; k++) {
                    if(k == 1){c = false;}
                    if (a) xa = true; else if (b) xa = c; else xa = false;
                    x = a || b && c;
                    xb = a; if (!xb) xb = b; if (xb) xb = c;
                    xc = a; if (!xc) if (b) xc = c;
                    xd = a; if (xd) {} else if (b) xd = c;
                    xe = a; if (!xe) { xe = b; if (xe) xe = c; }
                    //xe = false;
                    System.out.printf("%b \t %b \t %b \t %b \t %b \t %b \n", x, xa, xb, xc, xd, xe);
                }
            }
        }
    }
    
}