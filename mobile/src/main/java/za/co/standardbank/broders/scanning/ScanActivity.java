package za.co.standardbank.broders.scanning;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import za.co.standardbank.broders.R;
import za.co.standardbank.broders.property.PropertyDetailsActivity;

/**
 * Created by a159937 on 2015-07-06.
 */
public class ScanActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        IntentIntegrator.initiateScan(this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            Intent propertyDetailsIntent = new Intent(this, PropertyDetailsActivity.class);
            startActivity(propertyDetailsIntent);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
