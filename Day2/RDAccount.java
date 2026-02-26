package com.Day2;

class RDAccount extends Account {
	 int noOfMonths;
	 double monthlyAmount;
	 int age;

	 RDAccount(int noOfMonths, double monthlyAmount, int age) throws InvalidInputException {
	     if (noOfMonths < 0)
	         throw new InvalidInputException("Invalid Months");
	     if (monthlyAmount < 0)
	         throw new InvalidInputException("Invalid Monthly Amount");
	     if (age < 0)
	         throw new InvalidInputException("Invalid Age");

	     this.noOfMonths = noOfMonths;
	     this.monthlyAmount = monthlyAmount;
	     this.age = age;
	     this.amount = monthlyAmount * noOfMonths;
	 }

	 @Override
	 double calculateInterest() {

	     if (noOfMonths == 6)
	         interestRate = (age >= 60) ? 8.00 : 7.50;
	     else if (noOfMonths == 9)
	         interestRate = (age >= 60) ? 8.25 : 7.75;
	     else if (noOfMonths == 12)
	         interestRate = (age >= 60) ? 8.50 : 8.00;
	     else if (noOfMonths == 15)
	         interestRate = (age >= 60) ? 8.75 : 8.25;
	     else if (noOfMonths == 18)
	         interestRate = (age >= 60) ? 9.00 : 8.50;
	     else if (noOfMonths == 21)
	         interestRate = (age >= 60) ? 9.25 : 8.75;

	     return (amount * interestRate) / 100;
	 }
	}

