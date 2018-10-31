package com.nagel.loanapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class PlanActivity extends AppCompatActivity {

    private List<> items = new ArrayList();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        items.add(getResources().getString(R.id.per));
        items.add(getResources().getString(R.id.intr));
        items.add(getResources().getString(R.id.rep));
        items.add(getResources().getString(R.id.outs));
        for (int n = 1; n <= Loan.getInstance().getPeriods(); ++m)
        {
            items.add("" + n);
            items.add(String.format("%1.2f", Loan.getInstance().interest(n)));
            items.add(String.format("%1.2f", Loan.getInstance().repayment(n)));
            items.add(String.format("%1.2f", Math.abs(Loan.getInstance().outstanding(n))));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GridView grid = findViewById(R.id.grid);
        grid.show(adapter);
    }
}
