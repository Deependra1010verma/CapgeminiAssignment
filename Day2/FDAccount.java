package com.Day2;


class FDAccount extends Account {
 int noOfDays;
 int age;

 FDAccount(double amount, int noOfDays, int age) throws InvalidInputException {
     if (amount < 0)
         throw new InvalidInputException("Invalid Amount");
     if (noOfDays < 0)
         throw new InvalidInputException("Invalid Number of days. Please enter correct values.");
     if (age < 0)
         throw new InvalidInputException("Invalid Age");

     this.amount = amount;
     this.noOfDays = noOfDays;
     this.age = age;
 }

 @Override
 double calculateInterest() {

     if (amount < 10000000) { // Below 1 Crore
         if (noOfDays >= 7 && noOfDays <= 14)
             interestRate = (age >= 60) ? 5.00 : 4.50;
         else if (noOfDays <= 29)
             interestRate = (age >= 60) ? 5.25 : 4.75;
         else if (noOfDays <= 45)
             interestRate = (age >= 60) ? 6.00 : 5.50;
         else if (noOfDays <= 60)
             interestRate = (age >= 60) ? 7.50 : 7.00;
         else if (noOfDays <= 184)
             interestRate = (age >= 60) ? 8.00 : 7.50;
         else if (noOfDays <= 365)
             interestRate = (age >= 60) ? 8.50 : 8.00;
     } else {
         if (noOfDays >= 7 && noOfDays <= 14)
             interestRate = 6.50;
         else if (noOfDays <= 29)
             interestRate = 6.75;
         else if (noOfDays <= 45)
             interestRate = 6.75;
         else if (noOfDays <= 60)
             interestRate = 8.00;
         else if (noOfDays <= 184)
             interestRate = 8.50;
         else if (noOfDays <= 365)
             interestRate = 10.00;
     }

     return (amount * interestRate) / 100;
 }
}

