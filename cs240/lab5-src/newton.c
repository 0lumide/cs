#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define TOLERANCE 1E-6
#define MAX_ITER 12

double f(const char* funname, double x);
double fPrime(const char* funname, double x);
void printFunction();

/* Add global variables and helper functions here */

int main(int argc, char** argv)
{
	/* Add your implementation here */

	return 0;
}

/* Prints the function in readable form */
void printFunction(const char * funname)
{
        char * func;

	if(strcmp(funname, "poly1") == 0)
	  func = "y = x^2 - 4 = 0";
	else if(strcmp(funname, "sin") == 0)
	  func = "y = sin(x)-.5 = 0";
	else if(strcmp(funname, "xsin") == 0)
	  func = "y = x*sin(x)-10 = 0";
	else if(strcmp(funname, "poly2") == 0)
	  func = "y = x^3+3*x^2+4*x-1 = 0";
	else if(strcmp(funname, "imaginary") == 0)
	  func = "y = x^2+1 = 0";
	else
	{
	  printf("Error: %s is not a valid function name\n", funname);
		exit(1);
	}

        printf("Function: %s\n", func);
}

/* Evaluates f(x) */
double f(const char* funname, double x)
{
	if(strcmp(funname, "poly1") == 0)
		return (x*x - 4);
	else if(strcmp(funname, "sin") == 0)
	  return (sin(x)-.5);
	else if(strcmp(funname, "xsin") == 0)
	  return (x*sin(x)-10);
	else if(strcmp(funname, "poly2") == 0)
	  return (x*x*x+3*x*x+4*x-1);
	else if(strcmp(funname, "imaginary") == 0)
	  return (x*x+1);
	else
	{
	  printf("Error: %s is not a valid function name\n", funname);
		exit(1);
	}
}

/* Evaluates f'(x) */
double fPrime(const char* funname, double x)
{
	if(strcmp(funname, "poly1") == 0)
		return (2*x);
	else if(strcmp(funname, "sin") == 0)
	  return (cos(x));
	else if(strcmp(funname, "xsin") == 0)
	  return (sin(x)+x*cos(x));
	else if(strcmp(funname, "poly2") == 0)
	  return (3*x*x+6*x+4);
	else if(strcmp(funname, "imaginary") == 0)
	  return (3*x*x+6*x+4);
	else
	{
	  printf("Error: %s is not a valid function name\n", funname);
		exit(1);
	}
}
