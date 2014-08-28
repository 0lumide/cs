// Define class Teacher, subclass of AcademicPerson
public class Teacher extends AcademicPerson {
    // Instance variables
    private static final int MAX_COURSES = 10; // maximum courses
    // Constructor
    public Teacher(String name, String address) {
        super(name, address);
        courses = new String[MAX_COURSES];
        numCourses = 0;
    }
    
    
    // It adds a course into the list of courses.
    // This method throws ArrayElementException when the course that is being
    // added to the list already exists in it.
    public void addCourse(String course) throws ArrayElementException {
        boolean exists = false;
        /*System.out.println(" A Beore:");
         printCourses();
         System.out.println("END");*/
        if(numCourses < MAX_COURSES) {
            for (int i = 0; i < numCourses; i++) {
                if ((courses[i]).equals(course)) {
                    exists = true;
                }
            }
            if (!exists) {
                courses[numCourses] = course;
                numCourses++;
            }
            else
                throw new ArrayElementException("Course Already in list!");
            /*System.out.println(" A After:");
             printCourses();
             System.out.println("END");*/
        }
        else
            throw new ArrayElementException("You're already at max number of courses!");
        
    }
    
    // It removes a course into the list of courses.
    // This method throws ArrayElementException when the course does not exist
    // in the list.
    public void removeCourse(String course) throws ArrayElementException {
        int pos = -1;
        for (int i = 0; i < numCourses; i++) {
            if ((courses[i]).equals(course)) {
                pos = i;
                rearrangeCourses(i);
            }
        }
        if (pos == -1)
            throw new ArrayElementException("Course not found!");
    }
        
    //This shifts the courses array up after a course is removed
    public void rearrangeCourses(int index) {
        /*System.out.println(" R Before:");
        printCourses();
        System.out.println("END");*/
        for (int i = index; i < numCourses; i++) {
             courses[i] = courses[i + 1];
        }
        courses[numCourses] = null;
        numCourses--;
        /*System.out.println(" R After:");
        printCourses();
        System.out.println("END");*/
    }
    
    // It prints all the courses in the list in each line
    @Override
    public void printCourses() {
        System.out.println("Courses teaching this semester:");
        for (int i = 0; i < numCourses; i++ ) {
            System.out.println(courses[i]);
        }
        
    }
    
    @Override
    public String toString() {
        return "Teacher: " + super.toString();
    }
    
}