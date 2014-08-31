
/***************************************************************************
*
*  Programmer and Purdue Email Address:
*  1.Olumide Awofeso oawofeso@purdue.edu
*
*  Homework #:02
*
*  Academic Integrity Statement:
*
*       I have not used source code obtained from
*       any other unauthorized source, either modified
*       or unmodified.  Neither have I provided access
*       to my code to another. The project I am submitting
*       is my own original work.
*
*  Lab Division and Section:0101
*
*  Program Description:This program calculates the grade letter for students exams using the inputed exam scores
*
***************************************************************************/
#include <stdio.h>

int getInput(int);
int calcGrade(int);
void printGrade(int,int, char);

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
  
  //EXECUTABLE STATEMENTS
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
*     Programmer's Name: Olumide Awofeso
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
  char pound_key; // This is the character for the pound key to be used later in this function
  int exscore; // This stores the exam score inputed by the user

  //EXECUTABLE STATEMENTS
  pound_key = 35;
  printf("Enter score for exam %c%d: ", pound_key, ex_num);
  scanf("%d", &exscore);
  return(exscore);
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function: calcGrade
*
*     Programmer's Name: Olumide Awofeso
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

int calcGrade(int exam)
{
  //LOCAL DECLARATION
  int letter_grade; //This is the char value for the letter grade for the exam received from main
  

  //EXECUTABLE STATEMENTS
  letter_grade = 70 - (2 * ((exam / 55) - ((exam - 55) / 55)) + ((exam / 65) - ((exam - 65) / 65)) + ((exam / 75) - ((exam - 75) / 75)) + ((exam / 85) - ((exam - 85) / 85)));
  return(letter_grade);
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function: printGrade
*
*     Programmer's Name:Olumide Awofeso
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
