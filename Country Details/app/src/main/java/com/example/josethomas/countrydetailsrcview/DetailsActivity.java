package com.example.josethomas.countrydetailsrcview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Locale;
import java.util.Map;

/**
 * Created by JoseThomas on 3/22/2016.
 */
public class DetailsActivity extends AppCompatActivity {

    Handler handler;
    String countryName;
    private ProgressBar mProgress;
    private ImageView flagImage;
    private TextView countryField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProgress = (ProgressBar)findViewById(R.id.progressBar);
        flagImage = (ImageView)findViewById(R.id.flag);
        countryField = (TextView)findViewById(R.id.countryName);

        countryField.setText(countryName);

        //recieving intent

        Intent intent = getIntent();

        if((intent != null) && (intent.getExtras()!= null)){
            countryName= intent.getExtras().getString("COUNTRY_NAME");
            if(null != countryName){
                fetchCountryDetails(countryName);

                String countryCode = getCountryCode(countryName);
                String flagUrl = "http://flagpedia.net/data/flags/normal/"+countryCode+".png";
                DownloadImageTaskWithURL downloadTask = new DownloadImageTaskWithURL(flagImage);
                downloadTask.execute(flagUrl);
            }
        }
    }

    public DetailsActivity(){
        handler = new Handler();
    }

    private void fetchCountryDetails(final String countryName){

        new Thread() {
            public void run(){

                    final Map<String,String> details = RemoteFetchCDetails.getCountryDetails(countryName);

                    if(details == null){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(DetailsActivity.this,
                                        DetailsActivity.this.getString(R.string.retrieval_error),
                                        Toast.LENGTH_LONG).show();

                                TextView countryName = (TextView)findViewById(R.id.countryName);
                                TextView capital = (TextView)findViewById(R.id.capital);
                                TextView currentTime = (TextView)findViewById(R.id.currentTime);

                                countryName.setText("");
                                capital.setText("");
                                currentTime.setText("");
                                currentTime.setText("No information found. Try again...");
                                currentTime.setTextColor(Color.RED);

                            }
                        });
                    }else{
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                renderCountryDetails(details);
                            }
                        });
                    }
                }
        }.start();
    }

    private void renderCountryDetails ( Map<String,String>  detailsMap){


        TextView capitalField = (TextView)findViewById(R.id.capital);
        TextView currencyField = (TextView)findViewById(R.id.currency);
        TextView populationField = (TextView)findViewById(R.id.population);

        TextView timeTextField = (TextView)findViewById(R.id.currentTimeText);
        TextView timeField = (TextView)findViewById(R.id.currentTime);
        TextView dateField = (TextView)findViewById(R.id.currentDate);

        countryField.setText(countryName);
        capitalField.setText(detailsMap.get("Capital"));
        currencyField.setText(detailsMap.get("Currency"));
        populationField.setText(detailsMap.get("Population"));

        timeTextField.setText(detailsMap.get("TimeText"));
        timeField.setText(detailsMap.get("Time"));
        dateField.setText(detailsMap.get("Date"));

        //hiding the progress bar
        mProgress.setVisibility(View.GONE);

    }

    private String getCountryCode (String selectedCountry){

        String retCode = "";

        String[] isoCountryCodes = Locale.getISOCountries();
        for (String countryCode : isoCountryCodes) {
            Locale locale = new Locale("", countryCode);
            String cName = locale.getDisplayCountry();
            if(cName.equals(selectedCountry)){
                retCode = countryCode.toLowerCase();
                break;
            }
        }
        return retCode;
    }

    private class DownloadImageTaskWithURL extends AsyncTask<String,Void,Bitmap>{

        ImageView bmImage;

        public DownloadImageTaskWithURL(ImageView bmImage){
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {

            String pathToFile = urls[0];
            Bitmap bitmap = null;

            try{
                InputStream in = new java.net.URL(pathToFile).openStream();
                bitmap = BitmapFactory.decodeStream(in);

            }catch (Exception e){
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
