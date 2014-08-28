class InterfaceExampleImplementation implements InterfaceExample {

    public void printSignature() {
        System.out.println("My name is Olumide");
    }
    public static void main(String[] args) {
    	InterfaceExampleImplementation ob1 = new InterfaceExampleImplementation();
	ob1.printSignature();
    }

}
