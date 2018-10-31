package com.nagel.loanapp;

public class Loan {

    static Loan instance = null;
    double principal = 0;     // the amount borrowed inc. costs
    double interestRate = 0;  // interest rate as a number between 0 and 1 that indicates the rate for 1 period
    int periods = 0;          // the number of periods and thus number of payments

    public Loan() {
    }
    // Issue 1: Loan getInstance() must be a static method
    // Issue 2: Missing getters and setters for principal, interestRate and periods
    public static Loan getInstance() {
        if(instance == null)
        {
            synchronized (Loan.class)
            {
                if(instance == null) instance = new Loan();
            }
        }
        return instance;
    }

    public double payment()
    {
        return principal * interestRate / (1 - Math.pow(1 + interestRate, -periods));
    }

    public void setPrincipal(double num) {
        this.principal = num;
    }

    public void setInstance(Loan instance) {
        this.instance = instance;
    }

    public void setInterestRate(double interest) {
        this.interestRate = interest;
    }

    public int getPeriods() {
        return this.periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public double outstanding(int n)
    {
        return principal * Math.pow(1 + interestRate, n) - payment() * (Math.pow(1 + interestRate, n) - 1) / interestRate;
    }

    public double interest(int n)
    {
        return outstanding(n - 1) * interestRate;
    }

    public double repayment(int n)
    {
        return payment() - interest(n);
    }
}
