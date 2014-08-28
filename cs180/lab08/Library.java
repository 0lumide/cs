import java.util.Scanner;
public class Library {
 
    public static void main(String[] args) {
        // Creating objects for the class Book
        Book bookA = new Book(1, "Jurassics", "Novel");
        Book bookB = new Book(3, "Stranger", "Novel");
        Book bookC = new Book(5, "DigiChar", "Novel");
        Book bookD = new Book(7, "Physics", "Tool");
        Book bookE = new Book(9, "Java", "Tool");
        Book bookF = new Book(11, "Calculus", "Tool");
 
        Book[] booksForShelf1 = {bookA, bookB, bookC};
        Book[] booksForShelf2 = {bookD, bookE, bookF};
 
        // Creating Shelf objects
        Shelf shelf1 = new Shelf("Novel", booksForShelf1);
        Shelf shelf2 = new Shelf("Tool", booksForShelf2);
 
        // Creating Scanner object
        Scanner in = new Scanner(System.in);
        int number = 0;
        int numberOrName = 0;
        String name = "";
        String category = "";
        int answer = 0;  
        System.out.print("Welcome to the library!\n");
 
        do {
            System.out.print("Please select the action you want to take by\n");
            System.out.print("typing the corresponding number from the menu:\n");
            System.out.print("1 - Borrow a book\n");
            System.out.print("2 - Return a book\n");
            System.out.print("3 - Check the availability of a book\n");
            System.out.print("4 - Add a book (admin)\n");
            System.out.print("5 - Exit\n");
            answer = in.nextInt();
 
            if (answer == 1) {
                //lend
                System.out.print("Borrow a Book\n");
                System.out.print("By number/name (0/1):\n");
                numberOrName = in.nextInt();
                if (numberOrName == 0) {
                    System.out.print("Please enter the book number:\n");
                    number = in.nextInt();
                }
                else {
                    System.out.print("Please enter the book name:\n");
                    name = in.next();
                }
 
 
                Book target = new Book(number, name, null);
                if (shelf1.lendBook(target, numberOrName)) {
                    System.out.print("You have borrowed it from shelf1.\n");
                }
                else if (shelf2.lendBook(target, numberOrName)) {
                    System.out.print("You have borrowed it from shelf2.\n");
                }
                else {
                    System.out.print("The library does not have this book.\n");
                }
            }
            else if (answer == 2) {
                //return
                System.out.print("Return a Book\n");
                System.out.print("By number/name (0/1):\n");
                numberOrName = in.nextInt();
                if (numberOrName == 0) {
                    System.out.print("Please enter the book number:\n");
                    number = in.nextInt();
                }
                else {
                    System.out.print("Please enter the book name:\n");
                    name = in.next();
                }
 
                Book target = new Book(number, name, null);
                if (shelf1.returnBook(target, numberOrName)) {
                    System.out.print("The book was returned to shelf1.\n");
                }
                else if (shelf2.returnBook(target, numberOrName)) {
                    System.out.print("The book was returned to shelf2.\n");
                }
                else {
                    System.out.print("The library does not have this book.\n");
                }
            }
            else if (answer == 3) {
                //check availability
                System.out.print("Check Availability\n");
                System.out.print("By number/name (0/1):\n");
                numberOrName = in.nextInt();
                if (numberOrName == 0) {
                    System.out.print("Please enter the book number:\n");
                    number = in.nextInt();
                }
                else {
                    System.out.print("Please enter the book name:\n");
                    name = in.next();
                }
 
                Book target = new Book(number, name, null);
                if (shelf1.getBookDetails(target, numberOrName)) {
 
                }
                else if (shelf2.getBookDetails(target, numberOrName)) {
 
                }
                else {
                    System.out.print("The library does not have this book.\n");
                }
            }  
            else if (answer == 4) {
                //add
                System.out.print("Add a Book (admin)\n");
                System.out.print("Please enter the book number:\n");
                number = in.nextInt();
                System.out.print("Please enter the book name:\n");
                name = in.next();
                System.out.print("Please enter the book category\n");
                System.out.print("(novel or tool):\n");
                category = in.next();
                Book target = new Book(number, name, category);
                if (category.equalsIgnoreCase(shelf1.getShelfType())) {
                    shelf1.addBooks(target);
                    System.out.print("The book has been added to shelf1.\n");
                }
                else if (category.equalsIgnoreCase(shelf2.getShelfType())) {
                    shelf2.addBooks(target);
                    System.out.print("The book has been added to shelf2.\n");
                }
                else {
                    System.out.print("The library does not have such "
                      + "shelf to contain this book.\n");
                }
            }
            else if (answer != 5) {
                System.out.print("The requested service is not available.\n");
            }
        } while (answer != 5);
 
        System.out.print("Thank you for using the library services.\n");
    }
 
}