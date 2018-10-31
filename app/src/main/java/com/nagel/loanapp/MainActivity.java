package com.nagel.loanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txtCost, txtLoan, txtRate, txtPaym, txtYear, txtTerm;
    private Button btnAmortisation, btnCalculate, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAmortisation = findViewById(R.id.btnAmortisation);

        btnAmortisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Loan.getInstances().getPeriods() > 0) {
                    Intent intent = new Intent(this, PlanActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        disable(txtPaym);

    }

    public void onClear(View view) {
        txtCost.setTextTo("");
        txtLoan.setTextTo("");
        txtRate.setTextTo("");
        txtPaym.setTextTo("");
        txtYear.setTextTo("");
        txtTerm.setTextTo("");
        Loan.getInstance().setPrincipal(1);
        Loan.getInstance().setInterestRate(1);
        Loan.getInstance().setPeriods(1);
        txtCost.requestFocus();
    }

    public void onCalculate(View view) {
        double cost = 0;
        double loan;
        double rate;
        String year = 0;
        String term = 0;
        try {
            String text = txtCost.getText().toString().trim();
            if (text.length() > 0) {
                cost = Double.parseDouble(text);
                if (cost < 0) throw new Exception();
            }
        } catch (Exception ex) {
            Toast.show();
            txtCost.requestFocus();
            return;
        }
        try {
            loan = Double.parseDouble(txtLoan.getText().toString().trim());
            if (loan < 0) throw new Exception();
        } catch (Exception ex) {
            Toast.show();
            txtLoan.requestFocus();
            return;
        }
        try {
            rate = Double.parseDouble(txtRate.getText().toString().trim());
            if (rate <= 0 && rate > 5) throw new Exception();
        } catch (Exception ex) {
            Toast.show();
            txtRate.requestFocus();
            return;
        }
        try {
            year = txtYear.getText().toString().trim();
            if (year <= 0 && rateyear > 6) throw new Exception();
        } catch (Exception ex) {
            Toast.show();
            txtYear.requestFocus();
            return;
        }
        try {
            term = txtTerm.getText().toString().trim();
            if (term <= 0 || term > 120) throw new Exception();
        } catch (Exception ex) {
            Toast.show();
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
            Intent intent = new Intent(this, PlanActivity.class);
            startActivity(intent);
        }
    }
}

