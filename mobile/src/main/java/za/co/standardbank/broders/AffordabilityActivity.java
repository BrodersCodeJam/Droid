package za.co.standardbank.broders;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;


public class AffordabilityActivity extends Activity {

    public static final String TAG = AffordabilityActivity.class.getSimpleName();
    private Double requiredLoanAmount = Double.MAX_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affordability);
        String price = getIntent().getExtras().getString("LOAN_AMOUNT");
        Log.d(TAG, "Price from Bundle: " + price);
        requiredLoanAmount = Double.parseDouble(price);
        String formattedRequiredLoanAmount =  NumberFormat.getCurrencyInstance(new Locale("en","ZA")).format(requiredLoanAmount);
        ((TextView) findViewById(R.id.required_text_view)).setText(formattedRequiredLoanAmount);
        calculateLoanAmount();

        ((EditText) findViewById(R.id.income_text_view)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateLoanAmount();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_affordability, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void calculateLoanAmount() {
        String rateTV = ((EditText)findViewById(R.id.rate_text_view)).getText().toString();
        String termTV = ((EditText) findViewById(R.id.term_text_view)).getText().toString();
        String incomeTV = ((EditText) findViewById(R.id.income_text_view)).getText().toString();

        double rate =  Double.parseDouble(rateTV);
        int term = Integer.parseInt(termTV);
        double income =  Double.parseDouble(incomeTV);

        double potentialLoanAmount = calculateLoanAmount(rate, term, income);

        String formattedAmount = NumberFormat.getCurrencyInstance(new Locale("en","ZA")).format(potentialLoanAmount);

        ((EditText)findViewById(R.id.potential_text_view)).setText(formattedAmount);

        if (potentialLoanAmount >= requiredLoanAmount) {
            (findViewById(R.id.button)).setVisibility(View.VISIBLE);
        } else {
            (findViewById(R.id.button)).setVisibility(View.GONE);
        }
    }

    public void appleGo(View view) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // set title
            alertDialogBuilder.setTitle("Homeloan Application Started");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("You Are 1 Step Closer")
                            .setCancelable(false)
                            .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    AffordabilityActivity.this.finish();
                                }
                            });

        // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
    }

    private double calculateLoanAmount(double rate, int term, double income) {
        double moneyFactor = rate/100.0/12;
        double potential = income *  0.3  * (1 - Math.pow(1 + moneyFactor, 0 - term)) / moneyFactor;
        return potential;
    }
}
