package com.Day2;


class SBAccount extends Account {
String accountType; // Normal or NRI

SBAccount(double amount, String accountType) throws InvalidInputException {
   if (amount < 0)
       throw new InvalidInputException("Invalid Amount");

   this.amount = amount;
   this.accountType = accountType;
}

@Override
double calculateInterest() {
   if (accountType.equalsIgnoreCase("Normal"))
       interestRate = 4;
   else if (accountType.equalsIgnoreCase("NRI"))
       interestRate = 6;

   return (amount * interestRate) / 100;
}
}

