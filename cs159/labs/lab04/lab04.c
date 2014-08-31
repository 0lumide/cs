
/***************************************************************************
*
*  Programmers and Purdue Email Addresses:
*	 1. saudi@purdue.edu
*	 2. oawofeso@purdue.edu
*	 3. ncosselm@purdue.edu (delete line if no third partner)
*
*  Lab #:4
*
*  Academic Integrity Statement:
*
*       We have not used source code obtained from
*       any other unauthorized source, either modified
*       or unmodified.  Neither have we provided access 
*       to our code to another. The project we are submitting
*       is our own original work.
*
*  Lab Division and Section:0101
*
*  Program Description: Calculates the grades for the exam scores entered and also counts the total number of times  each grade letter is earned
*
***************************************************************************/


#include <stdio.h>

int getInput(int);  //Inputs the grade entered by the user into the program
char calcGrade(int);  //Calculates the grades of the inputted exam scores
void printGrade(int,int, char); //displays the calculated letter grades along with the inputted exam scores
void countGrade(int*,int*,int*,int*,int*,char); //counts the number of times each letter grade appears
int main()
{
  //LOCAL DECLARATION
  int exam1; //This holds the score for the first exam
  int exam2; //This holds the score for the second exam
  int exam3; //This holds the score for the third exam
  int exam4; //This holds the score for the fourth exam
  int exam_num; // This holds the exam number
  int total_score; //This is the sum of all four exams
  int total_percent;//This is the percentage of the total exam score
  int exam4_percent; //This is the percentage of the fourth exam
  int A_count; //holds the place for numbers of A grades
  int B_count;  //holds the place for numbers of B grades
  int D_count;  //holds the place for numbers of D grades
  int F_count;  //holds the place for numbers of F grades
  int C_count;  //holds the place for numbers of C grades


  //EXECUTABLE STATEMENTS
  A_count = 0; 
  B_count = 0; 
  C_count = 0; 
  D_count = 0;  
  F_count = 0;  

  exam_num = 1;
  printf("\n");
  exam1 = getInput(exam_num++);
  exam2 = getInput(exam_num++);
  exam3 = getInput(exam_num++);
  exam4 = getInput(exam_num);

  printf("\nExam Score Grade");
  printf("\n----------------\n");
  total_score = exam1 + exam2 + exam3 + exam4;
  total_percent = ((total_score * 100) / 450);
  exam4_percent = (exam4 * 100) / 150;
  exam_num = 1;
  printGrade(exam_num++, exam1, calcGrade(exam1));
  printGrade(exam_num++, exam2, calcGrade(exam2));
  printGrade(exam_num++, exam3, calcGrade(exam3));
  printGrade(exam_num, exam4, calcGrade(exam4_percent));
  printf("----------------\n");
  total_score = exam1 + exam2 + exam3 + exam4;
  total_percent = ((total_score * 100) / 450);

  countGrade(&A_count,&B_count,&C_count,&D_count,&F_count,calcGrade(exam1));
  countGrade(&A_count,&B_count,&C_count,&D_count,&F_count,calcGrade(exam2));
  countGrade(&A_count,&B_count,&C_count,&D_count,&F_count,calcGrade(exam3));
  countGrade(&A_count,&B_count,&C_count,&D_count,&F_count,calcGrade(exam4_percent));
  printf("Exam Letter Grades\n");
  printf("A: %d B: %d C: %d D: %d F: %d", A_count, B_count, C_count, D_count, F_count);
  printf("\n----------------\n");


  printf("Total:       %3d", total_score);
  printf("\nGrade:         %c\n\n\n",calcGrade(total_percent));


  return (0);
}


/***************************************************************************
 *
 *     Function Information
 *
 *     Name of Function: getInput
 *
 *     Programmer's Name: Olumide Awofeso, Shadi Audi, Nick cosselman
 *
 *     Function Return Type: int
 *
 *     Parameters (list data type, name, and comment one per line):
 *       1. int ex_num This is the number of the exam which is being worked upon
 *
 *     Function Description: It gets input from the user and returns it to main
 *
 ***************************************************************************/

int getInput(int ex_num)
{
  //LOCAL DECLARATION
  int exscore; // This stores the exam score inputed by the user

  //EXECUTABLE STATEMENTS
  printf("Enter score for exam #%d: ", ex_num);
  scanf("%d", &exscore);
  return(exscore);
}


/***************************************************************************
 *
 *     Function Information
 *
 *     Name of Function: calcGrade
 *
 *     Programmer's Name: Olumide Awofeso, Shadi Audi, Nick Cosselman
 *
 *     Function Return Type: int
 *
 *     Parameters (list data type, name, and comment one per line):
 *       1. int exam //This is the exam score entered by the user
 *  
 *       
 *
 *     Function Description:It gets the exam score percentage from main and calculates the integer value of the corresponding grade char
 *
 ***************************************************************************/

char calcGrade(int exam)
{
  //LOCAL DECLARATION
  int letter_grade; //This is the char value for the letter grade for the exam received from main
  

  //EXECUTABLE STATEMENTS
  letter_grade = 70 - (exam/55)*(2 * ((exam / 55) - ((exam - 55) / 55)) + ((exam / 65) - ((exam - 65) / 65)) + ((exam / 75) - ((exam - 75) / 75)) + ((exam / 85) - ((exam - 85) / 85)));
  return(letter_grade);
}


/***************************************************************************
 *
 *     Function Information
 *
 *     Name of Function: printGrade
 *
 *     Programmer's Name:Olumide Awofeso, Shadi Audi, Nick Cosselman
 *
 *     Function Return Type: void
 *
 *     Parameters (list data type, name, and comment one per line):
 *       1. int exam_num //This is the number of the exam
 *       2. int exam //This is th exam score 
 *       3. char letter_grade //This is the letter grade for each exam score
 *
 *     Function Description:It receives the exam number, exam score, and integer value of the corresponding grade letter character, and then prints them in the format specified by the himework question 
 *
 ***************************************************************************/

void printGrade(int exam_num, int exam, char letter_grade)
{
  //EXECUTABLE STATEMENTS
  printf("%3d   %3d     %c\n", exam_num, exam, letter_grade);
}



/***************************************************************************
*
*     Function Information
*
*     Name of Function:countGrade
*
*     Programmer's Name:Olumide Awofeso, Shadi Audi, Nick Cosselman
*
*     Function Return Type:void
*
*     Parameters (list data type, name, and comment one per line):
*       1.int* a_count This is the location of the number of times an A has been gotten
*       2.int* b_count This is the location of the number of times an B has been
 gotten
*       3.int* c_count This is the location of the number of times an C has been
 gotten
*       4.int* d_count This is the location of the number of times an D has been
 gotten
*       5.int* f_count This is the location of the number of times an F has been
 gotten
*       6.char letter This is the char value of the grade letter of each exam

*
*     Function Description: It counts the amount of time each grade letter is gotten for the four exams
*
***************************************************************************/

void countGrade(int* a_count, int* b_count, int* c_count, int* d_count, int* f_count, char letter)
{
  
  //EXECUTABLE STATEMENTS
  *a_count = *a_count + ((letter / 65) * (65 / letter)); 
  *b_count = *b_count + ((letter / 66) * (66 / letter));
  *c_count = *c_count + ((letter / 67) * (67 / letter));
  *d_count = *d_count + ((letter / 68) * (68 / letter));
  *f_count = *f_count + ((letter / 70) * (70 / letter));
  
  
}
