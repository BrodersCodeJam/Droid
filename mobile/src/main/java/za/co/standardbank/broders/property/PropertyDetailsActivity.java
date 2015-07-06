package za.co.standardbank.broders.property;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.springframework.http.ResponseEntity;

import java.text.Format;
import java.text.NumberFormat;
import java.util.Formatter;
import java.util.Locale;
import java.util.Objects;

import za.co.standardbank.broders.AffordabilityActivity;
import za.co.standardbank.broders.R;
import za.co.standardbank.broders.services.Service;

public class PropertyDetailsActivity extends Activity {

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        UserLoginTask userLoginTask = new UserLoginTask();
        userLoginTask.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_property_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_affordability) {
            Intent propertyDetailsIntent = new Intent(this, AffordabilityActivity.class);
            startActivity(propertyDetailsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class UserLoginTask extends AsyncTask<Void, Void, PropertyInfo> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected PropertyInfo doInBackground(Void... params) {
            try {
                ResponseEntity<PropertyInfo> response = new Service().GET("https://broders.azure-mobile.net/api/PropertyInfo/1", PropertyInfo.class);
                return response.getBody();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("REGISTER", "REGISTER Failed", e);
                return new PropertyInfo(2,new Double(123456.25),"Chatten","Kyalami","Johannesburg","Gauteng","http://m.pamgolding.co.za/4-bedroom-house-for-sale-in-helderfontein-estate-103008315", new Object());
            }
        }

        @Override
        protected void onPostExecute(final PropertyInfo property) {
            spinner.setVisibility(View.GONE);
            (findViewById(R.id.property_details_table)).setVisibility(View.VISIBLE);
            populateProperty(property);
        }
    }

    private void populateProperty(PropertyInfo property) {
        String price =  property.getAmount() == null ? "Not Availiable" : NumberFormat.getCurrencyInstance(new Locale("en","ZA")).format(property.getAmount());
        ((TextView) findViewById(R.id.price_text_view)).setText(price);
        ((TextView) findViewById(R.id.street_text_view)).setText(property.getStreet());
        ((TextView) findViewById(R.id.suburb_text_view)).setText(property.getSuburb());
        ((TextView) findViewById(R.id.city_text_view)).setText(property.getCity());
        ((TextView) findViewById(R.id.province_text_view)).setText(property.getProvince());
        ((TextView) findViewById(R.id.website_text_view)).setText(property.getUrl());
    }
}
