package za.co.standardbank.broders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import za.co.standardbank.broders.messaging.NotificationHubHelper;
import za.co.standardbank.broders.property.PropertyDetailsActivity;
import za.co.standardbank.broders.scanning.ScanActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationHubHelper.init(this);
    }


    public void scan(View view) {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent propertyDetailsIntent = new Intent(this, PropertyDetailsActivity.class);
        startActivity(propertyDetailsIntent);
    }
}
