public class Book {
    private int number;
    private String name;
    private String category;
    private boolean availability = true;
    
    /**
     * This method is called by the searchBook() 
     * It checks if the book being searched for is same as 
     * the book object being referred. 
     * @parameter byAttributes determine which fields to check; 0 is number, 1 is name;
     * @parameter newBook a book object that used for search
     * If yes, it returns a true. Else false 
     */
    //public boolean equals(Book newBook, int byAttributes)
    
    /**
     * This method is called by the lendBook() in Shelf 
     * It changes the value of the privatete variable availability 
     * @parameter newValue the value availability is changed to 
     */
    public void changeAvailability(boolean newValue) {
        this.availability = newValue;
    }
    public String returnAttribute(int attributeNum) {
        String returnValue = null;
        switch (attributeNum) {
            case 0:
                returnValue = "" + this.number;
                break;
            case 1:
                returnValue = "" + this.name;
                break;
            case 2:
                returnValue = "" + this.category;
                break;
            case 3:
                returnValue = "" + this.availability;
                break;
        }
        return returnValue;
    }
    Book(int number, String name, String category) {
        this.number = number;
        this.name = name;
        this.category = category;
    }
    
    public static void main(String[] args) {
        
    }
    
}