public class Student extends AcademicPerson {
    
    // Instance variables
    private int[] grades; // grade for the corresponding course
    static final int MAX_COURSES = 30; // maximum number of courses
    
    // Constructor
    public Student(String name, String address) {
        super(name, address);
        courses = new String[MAX_COURSES];
        grades = new int[MAX_COURSES];
        numCourses = 0;
    }
    
    // It adds a course and corresponding grade to the lists.
    // Student can take the same course couple of times. If a course that
    // already exists in the list is given as the input of the method you need
    // to compare the input grade with the one that is saved in the Grades list,
    // the higher grade needs to be saved in the Grades list.
    public void addCourseGrade(String course, int grade){
        
        if(numCourses < MAX_COURSES) {
            boolean exists = false;
            int pos = -1;
            /*System.out.println(" A Beore:");
             printCourses();
             System.out.println("END");*/
            for (int i = 0; i < numCourses; i++) {
                if ((courses[i]).equals(course)) {
                    exists = true;
                    pos = i;
                }
            }
            if (exists) {
                if (grades[pos] < grade) 
                    grades[pos] = grade;
            }
            else {
                courses[numCourses] = course;
                grades[numCourses] = grade;
                numCourses++;   
            }
            
            /*System.out.println(" A After:");
             printCourses();
             System.out.println("END");*/
        }
        
    }
    
    // This method prints the student's average grade for all the courses.
    // This method throws IllegalDivisionByZero exception, when there is no
    // courses in the list.
    public void getAverageGrade() throws IllegalDivisionByZero {
        int sum = 0;
        if (numCourses == 0)
            throw new IllegalDivisionByZero("Division by zero!");
        else {
            for (int i = 0; i < numCourses; i++) {
                sum = sum + grades[i];
            }
            float average = (float)sum / (float)numCourses;
            System.out.printf("Average is: %.1f\n", average);
        }
    }
    
    // It prints all the courses with the corresponding grades in each line.
    @Override
    public void printCourses() {
        
        System.out.println("CourseName \t CourseGrade");
        for (int i = 0; i < numCourses; i++ ) {
            System.out.println(courses[i] + "\t\t" + grades[i]);
        }
    }
    
    public int[] getGrades() {
        return grades;
    }
    
    public void setGrades(int[] grades) {
        this.grades = grades;
    }
    
    @Override
    public String toString() {
        return "Student: " + super.toString();
    }
}