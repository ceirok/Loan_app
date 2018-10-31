# Task: 
You had a client who wanted a loan application, further details about the application design and work principles are below. Unfortunately your colleague Helen did some really bad work.
It is now your job to fix this app, so that it will work the way the client wants it to.
While fixing the issues add a detailed report about what was wrong and how you fixed it in your github readme, in the application code add comments that trace back to the readme.

Example in code: // Issue 1

Example in readme: Issue 1: method pay was missing, added it because the application won't calculate sums without it.

# Found issues
Issue 1: Loan getInstance() must be a static method
Issue 2: Missing getters and setters for principal, interestRate and periods
Issue 3: LinearLayout must contain all elements (GridView was out of LinearLayout)
Issue 4: .PlanActivity had a typo (.PlansActivity)
Issue 5: android:text @string variables had a typo (@strings/)
Issue 6: new Intent has to point to the current Intent first (MainActivity.this)
Issue 7: disable() method was not defined
Issue 8: correct setText method doesn't contain To (setTextTo() -> setText())
Issue 9: All Toast messages were were missing the Toast body (makeText())
Issue 10: year nad term are int type variables which have to be parsed before assigning
Issue 11: there is no such variable defined as 'rateyear'
Issue 12: R.id.someVariable doesnt exist -> string variables come from R.string.someVariable
Issue 13: for loop contained wrong variable (++m)
Issue 14: EditText and Button elements should not be set as private if we modify them
Issue 15: year must be an integer between 1 and 60
Issue 16: term must be an integer between 1 and 12
Issue 17: new Intent must first declare current activity
Issue 18: all EditText elements have to be linked to the activity
Issue 19: PlanActivity was missing in Manifest.xml
Issue 20: numColumns should not be 44 columns

# Application description:
This application lets user calculate the payment on a loan. Every time the loan is an annuity loan.
In the top two fields one must be able to enter the borrowing costs and the loan principal respectively, while in the next two fields able to enter the number of years for the repayment of the loan and the number of terms per year.
In the last field user must be ableto enter the interest rate, which is entered as the interest rate in percent per year.
When clicking Calculate button, the program must calculate the term payment and show it in the field after text Payment, which is a read-only EditText.

Before calculating the program must validate data:
1. The cost must not be negative
2. The loan must be positive
3. The number of years must be an integer between 1 and 60 (both inclusive)
4. The number of terms per years must be an integer between 1 and 12 (both inclusive)
5. The interest rate must be positive and less than 50

In the case of an error, the program must display an error message in the form of a simple Toast. 

![screenshot_1540977348](https://user-images.githubusercontent.com/31770163/47778146-dd747180-dcfe-11e8-8281-8d2cd37db338.png)

If user clicks on Amortization, the program must open another activity that shows an amortization plan:

![screenshot_1540977362](https://user-images.githubusercontent.com/31770163/47778132-d8172700-dcfe-11e8-8899-95470cb370d0.png)


____________________________________________________________________________________________________________________________________

# May the odds be ever in your favour
