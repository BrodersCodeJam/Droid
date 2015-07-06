package za.co.standardbank.broders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import za.co.standardbank.broders.messaging.NotificationHubHelper;
import za.co.standardbank.broders.property.PropertyDetailsActivity;
import za.co.standardbank.broders.scanning.ScanActivity;


public class MainActivity extends Activity {

    private EditText mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailView = (EditText) findViewById(R.id.username);
        mEmailView.setText("customer@standardbank.com");

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setText("Password@1");

        NotificationHubHelper.init(this);
    }


    public void scan(View view) {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        attemptLogin();

    }

    public void attemptLogin() {
//        if (mAuthTask != null) {
//            return;
//        }

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (validateForm(email, password)) {
            Intent propertyDetailsIntent = new Intent(this, PropertyDetailsActivity.class);
            startActivity(propertyDetailsIntent);
//            showProgress(true);
//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public boolean validateForm(String email, String password) {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        }

        return !cancel;
    }
}
