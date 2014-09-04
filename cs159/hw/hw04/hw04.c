
/***************************************************************************
 *
 *  Programmer and Purdue Email Address:
 *  1.oawofeso@purdue.edu
 *
 *  Homework #:4
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
 *  Program Description:This program calculates the monthly payment and creates a table showing the monthly payment and other detail for each month till payment is complete
 *
 ***************************************************************************/

#include <stdio.h>
#include <math.h>

void getInput(float*, float*, int*);
float calcNewBal(float, float, float, int);
float monthPay(float, float, int);
void disp_Output(int, float, float, float);

int main()
{
  //LOCAL DECLARATION
  int term; //This is term of loan in years
  float amount; //This is the amount of money borrowed
  float int_rate; //This is the rate at which interest would be paid
  float month_pay; //This is the amount paid every month
  float old_balance; //This is the amount of money present before any money is paid each term
  float bal_rem; //This is the amount that is left after each payment is maid
  int count; //This counts counts the amount of months

  //EXECUTABLE STATEMENT
  getInput(&amount, &int_rate, &term);
  term *= 12; //This changes the term from years to months
  month_pay = monthPay(amount, int_rate, term);
  old_balance = amount;
  count = 1;

  do
  {
    bal_rem = calcNewBal(amount, int_rate, month_pay, --term);
    disp_Output(count, bal_rem, old_balance, month_pay);
    old_balance = bal_rem;
    count++;
  }while (term > 0);

  return(0);
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function:
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:void
*
*     Parameters (list data type, name, and comment one per line):
*       1.&float amount // This is the location of the amount that the user borrows
*       2.&float int_rate // This is the location of the interest rate
*       3.&int term // This is the location of the term of the loan
*
*     Function Description: This function gets input from the user
*
***************************************************************************/

void getInput(float* amount, float* int_rate, int* term)
{
  //EXECUTABLE STATEMENT
  printf("\nEnter the amount to borrow: ");
  scanf("%f",amount);
  printf("Enter the annual interest rate: ");
  scanf("%f", int_rate);
  printf("Enter ther term of the loan in years: ");
  scanf("%d", term);
}



/***************************************************************************
*
*     Function Information
*
*     Name of Function:
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:float
*
*     Parameters (list data type, name, and comment one per line):
*       1.float amount //This is the amount borrowed by the user
*       2.float in_rate //This is the interest rate of the loan
*       3.int term //This is how long the loan is to be paid over
*
*     Function Description:This calculates the amount to be paid monthly
*
***************************************************************************/

float monthPay(float amount, float int_rate, int term)
{
  //LOCAL DECLARATION
  float month_pay; //This is the amount paid monthly

  //EXECUTABLE STATEMENTS
  month_pay = (amount * (int_rate / 1200)) / (1 - pow((1 + (int_rate /1200)), (-term)));
  return(month_pay);
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function:calcNewBal
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:float
*
*     Parameters (list data type, name, and comment one per line):
*       1.float amount //This is the original amount of money borrowed
*       2.float in_rate //This is the interest rate
*       3.float month_pay //This is the amount o money to be payed each month
*       4.int term //This is the term of the loan in months
*     Function Description:This function calculates the amount of money remaining after each payment
*
***************************************************************************/

float calcNewBal(float amount, float int_rate, float month_pay, int term)
{
  //LOCAL DECLARATION
  float bal_rem; //This is the remaining amount to be paid  

  //EXECUTABLE STATEMENT
  bal_rem = (month_pay * 1200 * (1 - pow((1 + int_rate / 1200), -term))) / int_rate;
  return(bal_rem);
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function:disp_Outpt
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:void
*
*     Parameters (list data type, name, and comment one per line):
*       1.int count //This counts the amount of times the principal has been calculated
        2.float bal_rem // This holds the remaining balance for each month
        3.float old_balance //This holds the balance before payment is made every month
        4.float month_pay //This is the amount to be paid every month
*       
*
*     Function Description: This function prints the required output in the required way
*
***************************************************************************/

void  disp_Output(int count, float bal_rem, float old_balance, float month_pay)
{
  //LOCAL DECLARATION 
  char spce; //This holds the char value for space
  float principal; //This holds the value of the principal paid
  float int_paid; //This holds the value of the interest paid

  //EXECUATBLE STATEMENT
  spce = 0;
  if (count == 1)
  {
    printf("\nMonth %2c Old %3c Monthly Interest Principal %3c New\n %5c Balance  Payment %2c Paid %4c Paid %4c Balance\n",spce,spce,spce,spce,spce,spce,spce);
  }
  principal = old_balance - bal_rem;
  int_paid = month_pay - principal;
  printf("%3d %9.2f% 9.2f% 9.2f% 10.2f% 9.2f\n",count,old_balance, month_pay, int_paid, principal, bal_rem);
}

