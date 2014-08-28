interface Doable {
    int compute(int x);
    void doit(int y);
}

class henway implements Doable {
    public int compute(int x) {
        return x + 1;
    }
    public void doit(int y) {
        System.out.println(y);
    }
}