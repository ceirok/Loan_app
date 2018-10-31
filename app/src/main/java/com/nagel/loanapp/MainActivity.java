package com.nagel.loanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Issue 14: EditText and Button elements should not be set as private if we modify them
    public EditText txtCost, txtLoan, txtRate, txtPaym, txtYear, txtTerm;
    public Button btnAmortisation, btnCalculate, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAmortisation = findViewById(R.id.btnAmortisation);

        btnAmortisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Loan.getInstance().getPeriods() > 0) {
                    // Issue 6: new Intent has to point to the current Intent first (MainActivity.this)
                    Intent intent = new Intent(MainActivity.this, PlanActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        // Issue 18: all EditText elements have to be linked to the activity
        txtCost = findViewById(R.id.txtCost);
        txtLoan = findViewById(R.id.txtLoan);
        txtRate = findViewById(R.id.txtRate);
        txtPaym = findViewById(R.id.txtPaym);
        txtYear = findViewById(R.id.txtYear);
        txtTerm = findViewById(R.id.txtTerm);
        disable(txtPaym);
    }

    // Issue 8: correct setText method doesn't contain To (setTextTo() -> setText())
    public void onClear(View view) {
        txtCost.setText("");
        txtLoan.setText("");
        txtRate.setText("");
        txtPaym.setText("");
        txtYear.setText("");
        txtTerm.setText("");
        Loan.getInstance().setPrincipal(1);
        Loan.getInstance().setInterestRate(1);
        Loan.getInstance().setPeriods(1);
        txtCost.requestFocus();
        // Issue 7: disable() method was not defined
    }

    private void disable(EditText view) {
        view.setKeyListener(null);
        view.setEnabled(false);
    }

    public void onCalculate(View view) {
        double cost = 0;
        double loan;
        double rate;
        int year = 0;
        int term = 0;
        try {
            String text = txtCost.getText().toString().trim();
            if (text.length() > 0) {
                cost = Double.parseDouble(text);
                if (cost < 0) throw new Exception();
            }
        } catch (Exception ex) {
            // Issue 9: All Toast messages were were missing the Toast body (makeText())
            Toast.makeText(this, ex + "", Toast.LENGTH_SHORT).show();
            txtCost.requestFocus();
            return;
        }
        try {
            loan = Double.parseDouble(txtLoan.getText().toString().trim());
            if (loan < 0) throw new Exception();
        } catch (Exception ex) {
            Toast.makeText(this, ex + "", Toast.LENGTH_SHORT).show();
            txtLoan.requestFocus();
            return;
        }
        try {
            rate = Double.parseDouble(txtRate.getText().toString().trim());
            // Issue 16: rate must be an integer between 0
            if (rate <= 0 || rate > 50) throw new Exception();
        } catch (Exception ex) {
            Toast.makeText(this, ex + "", Toast.LENGTH_SHORT).show();
            txtRate.requestFocus();
            return;
        }
        try {
            // Issue 10: year nad term are int type variables which have to be parsed before assigning
            year = Integer.parseInt(txtYear.getText().toString().trim());
            // Issue 11: there is no such variable defined as 'rateyear'
            // Issue 15: year must be an integer between 1 and 60
            if (year <= 0 || year >= 60) throw new Exception();
        } catch (Exception ex) {
            Toast.makeText(this, ex + "", Toast.LENGTH_SHORT).show();
            txtYear.requestFocus();
            return;
        }
        try {
            term = Integer.parseInt(txtTerm.getText().toString().trim());
            // Issue 16: term must be an integer between 1 and 12
            if (term <= 0 || term > 12) throw new Exception();
        } catch (Exception ex) {
            Toast.makeText(this, ex + "", Toast.LENGTH_SHORT).show();
            txtTerm.requestFocus();
            return;
        }
        Loan.getInstance().setPrincipal(loan + cost);
        Loan.getInstance().setInterestRate(rate / 100 / term);
        Loan.getInstance().setPeriods(year * term);
        txtPaym.setText(String.format("%1.2f", Loan.getInstance().payment()));
    }

    public void onAmort(View view) {
        if (Loan.getInstance().getPeriods() > 0) {
            // Issue 17: new Intent must first declare current activity
            Intent intent = new Intent(this, PlanActivity.class);
            startActivity(intent);
        }
    }
}

