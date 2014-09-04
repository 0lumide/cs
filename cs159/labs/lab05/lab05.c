/***************************************************************************
 *
 *  Programmers and Purdue Email Addresses:
 *	 1. oawofeso@purdue.edu
 *	 
 *  Lab #:05
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
 *  Program Description:
 *
 ***************************************************************************/
#include <stdio.h>
#include <math.h>

int getchoice();
void getInputPayment(float*, float*, float*, float*, float*);
void getInputPurchase(float*, float*, float*, float*);
void getInputBalance(float*, float*, float*, float*);
void calcPayment(float, float, float, float, float);
void calcPurchase(float, float, float);
void calcBalance(float, float, float, float);

int main()
{
  //LOCAL DECLARATION
  int choice; //This is the number of the action the user chooses to evaluate
  float val1; //This is the value of the first input entered by the user
  float val2; //This is the value of the second input entered by the user
  float val3; //This is the value of the third input entered by the user
  float val4; //This is the value of the fourth input entered by the user
  float val5; //This is the value of the fifth input entered by the user

  //EXECUTABLE STATEMENTs
  choice = getchoice();
  if (choice == 1)
  {
    getInputPayment(&val1, &val2, &val3, &val4, &val5);
    calcPayment(val1, val2, val3, val4, val5);
  }
  else if (choice == 2)
  {
    getInputPurchase(&val1, &val2, &val3, &val4);
    if (val1 >= 730)
    {
      calcPurchase(val2, val3, val4);
    }
  }
  else if (choice == 3)
  {
    getInputBalance(&val1, &val2, &val3, &val4);
    calcBalance(val1, val2, val3, val4);
  }
  return(0);

}



/***************************************************************************
*
*     Function Information
*
*     Name of Function: getChoice
*
*     Programmer's Name: Olumide Awofeso
*
*     Function Return Type: int
*
*     Parameters (list data type, name, and comment one per line):
*       1. int choice //This is the number of the choice entered by the user
*       
*     Function Description:This function gets the choice from the user
*
***************************************************************************/

int getchoice()
{
  //LOCAL: DECLARATION
  int choice; //This is the number of the action chosen by the user

  //EXECUTABLE STATEMENT
  printf("\nSelect one of the following:\n");
  printf("\n 1. Calculate monthly payment.");
  printf("\n 2. Determine maximum purchase.");
  printf("\n 3. Calculate remaining balance.\n");
  printf("\nEnter your option: ");
  scanf("%d",&choice);
  return (choice);

}


/***************************************************************************
*
*     Function Information
*
*     Name of Function:getInputPayment
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:void
*
*     Parameters (list data type, name, and comment one per line):
*       1. float* crdt_sco //This is the the credit score entered by user
*       2. float* loan_amnt //This is the loan amount entered by the user
*       3. float* val_home // This is the value of the home entered by user
*       4. float* int_rate //This is the interest rate entered by the user
*       5. float* loan_term // This is how long the loan is to paid over in years
*
*     Function Description:This function gets input to be used to calculate the amount to be paid monthly
*
***************************************************************************/

void getInputPayment(float* crdt_sco, float* loan_amnt, float* val_home, float* int_rate, float* loan_term)
{
  //EXECUTABLE STATEMENTS
  printf("\nEnter your credit score: ");
  scanf("%f",crdt_sco);
  printf("Enter the loan amount: ");
  scanf("%f",loan_amnt);
  printf("Enter the value of the home: ");
  scanf("%f",val_home);
  printf("Enter Annual interest rate: ");
  scanf("%f",int_rate);
  printf("Enter term of loan in years: ");
  scanf("%f",loan_term);

}



/***************************************************************************
*
*     Function Information
*
*     Name of Function:getInputPayment
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:void
*     Parameters (list data type, name, and comment one per line):
*       1. float* crdt_sco //This is the the credit score entered by user
*       2. float* mont_pay //This is the value of the montly payment entered by the user
*       3. float* int_rate //This is the interest rate entered by the user
*       5. float* loan_term // This is how long the loan is to paid over in years
*
*      Function Description: This function gets the values to be used to calculate the maximum loan that can be gotten
*
***************************************************************************/

void getInputPurchase(float* crdt_sco, float* loan_term, float* int_rate, float* mont_pay)
{
  //EXECUTABLE STATEMENTS
  printf("\nEnter your credit score: ");
  scanf("%f",crdt_sco);
  if (*crdt_sco < 730)
  {
    printf("\nBased on your input you are not eligible for a loan.\n");
  }
  else
  {
    printf("Enter the term of the loan in years: ");
    scanf("%f",loan_term);
    printf("Enter the annual interest rate of the loan: ");
    scanf("%f",int_rate);
    printf("Enter desired monthly payment: ");
    scanf("%f",mont_pay);
  }
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function:getInputBalance
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:void
*     Parameters (list data type, name, and comment one per line):
*       1. float* ori_loan //This is the value of the original loan entered by the user
*       2. float* pay_made //This is the amount of payments made by the user
*       3. float* int_rate //This is the interest rate entered by the user
*       4. float* loan_term // This is how long the loan is to paid over in years
*
*      Function Description: This function gets the values to be used to calculate the remaining amount to be paid
*
***************************************************************************/

void getInputBalance(float* loan_term, float* int_rate, float* pay_made, float* ori_loan)
{

  //EXECUTABLE STATEMENTS
  printf("\nEnter the term of the loan in years: ");
  scanf("%f",loan_term);
  printf("Enter the annual interest rate of the loan: ");
  scanf("%f",int_rate);
  printf("Enter number of payments made: ");
  scanf("%f",pay_made);
  printf("Enter original loan amount: ");
  scanf("%f",ori_loan);
}



/***************************************************************************
*
*     Function Information
*
*     Name of Function:calcPayment
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:void
*
*     Parameters (list data type, name, and comment one per line):
*       1. float crdt_sco //This is the the credit score entered by user
*       2. float loan_amnt //This is the loan amount entered by the user
*       3. float val_home // This is the value of the home entered by user
*       4. float int_rate //This is the interest rate entered by the user
*       5. float loan_term // This is how long the loan is to paid over in years
*
*     Function Description:This function calculates and prints the amount to be paid monthly
*
***************************************************************************/

void calcPayment(float crdt_sco, float loan_amnt, float val_home, float int_rate, float loan_term)
{
  //LOCAL DECLARATION
  float mont_pay; //This is the amount that would be paid every month

  //EXECUTABLE STATEMENTS
  if ((crdt_sco >= 730) || ((crdt_sco > 600) && ((loan_amnt / val_home) < 0.80)))
  {
    mont_pay = ((loan_amnt * (int_rate / 1200)) / (1 - pow((1 + (int_rate / 1200)), (-loan_term * 12))));
    printf("\nMonthly Payment: $%.2f\n\n", mont_pay);
  }
  else if ((crdt_sco <= 600) && ((loan_amnt / val_home) > 0.80))
  {
    printf("\nBased on your input you are not eligible for a loan.\n\n");
  }
  else
  {
    mont_pay = (((0.2 * loan_amnt) * ((int_rate + 2) / 1200)) / (1 - pow((1 + ((int_rate + 2) / 1200)), (-(loan_term - 10) * 12)))) + (((0.8 * loan_amnt) * (int_rate / 1200)) / (1 - pow((1 + (int_rate / 1200)), (-loan_term * 12))));
    printf("\nMonthly Payment: $%.2f\n\n", mont_pay);
  }
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function:calcPurchase
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:void
*     Parameters (list data type, name, and comment one per line):
*       1. float mont_pay //This is the value of the montly payment entered by the user
*       2. float int_rate //This is the interest rate entered by the user
*       3. float loan_term // This is how long the loan is to paid over in years
*
*      Function Description: This function calculates and prints the maximum loan that can be gotten
*
***************************************************************************/
void calcPurchase(float loan_term, float int_rate, float mont_pay)
{
  //LOCAL DECLARATION
  float max_loan; //This is the maximum amount of loan that can be gotten by the user based on the values entered

  //EXECUTABLE STATEMENTS
  max_loan = (mont_pay * (1 - pow((1 + (int_rate / 1200)), (-12 * loan_term)))) / (int_rate / 1200);
  printf("\nMaximum Loan: $%.2f\n\n",max_loan);
}


/***************************************************************************
*
*     Function Information
*
*     Name of Function:calcBalance
*
*     Programmer's Name:Olumide Awofeso
*
*     Function Return Type:void
*     Parameters (list data type, name, and comment one per line):
*       1. float ori_loan //This is the value of the original loan entered by the user
*       2. float pay_made //This is the amount of payments made by the user
*       3. float int_rate //This is the interest rate entered by the user
*       4. float loan_term // This is how long the loan is to paid over in years
*
*      Function Description: This function calculates and prints the remaining amount to be paid
*
***************************************************************************/
void calcBalance(float loan_term, float int_rate, float pay_made, float ori_loan)
{
  //LOCAL DECLARATION
  float bal_rem; //This is the remaining amount to be paid by the user

  //EXECUTABLE STATEMENTS
  bal_rem = (ori_loan * (1 - pow((1 + int_rate / 1200),(-12 * (loan_term) + pay_made)))) / (1 - pow((1 + int_rate / 1200), (-12 * loan_term)));
  printf("\nRemaining Balance: $%.2f\n\n", bal_rem);
}
