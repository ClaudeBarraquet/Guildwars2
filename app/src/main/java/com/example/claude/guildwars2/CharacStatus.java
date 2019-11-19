package com.example.claude.guildwars2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by Konynrith on 17/02/2018.
 */

public class CharacStatus extends Activity {


    String UrlImg;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personnage);

        ImageButton BtnEquip = (ImageButton) findViewById(R.id.imageButtonEquipment);

        Intent intent = getIntent();
        final String key = intent.getStringExtra("ApiKey");
        final String charac = intent.getStringExtra("charac");
        final String profe = intent.getStringExtra("profe");
        final String level = intent.getStringExtra("level");
        final String race = intent.getStringExtra("race");
        //final Bitmap logo = intent.getParcelableExtra("logo");

        img = (ImageView) findViewById(R.id.imageView2);

        TextView CharacName = (TextView) findViewById(R.id.textViewNameCharac);
        CharacName.setText(charac);

        TextView CharacProfe = (TextView) findViewById(R.id.textViewProfe);
        CharacProfe.setText(profe);

        TextView CharacLevel = (TextView) findViewById(R.id.textViewLevel);
        CharacLevel.setText(level);

        TextView CharacRace = (TextView) findViewById(R.id.textViewRace);
        CharacRace.setText(race);

        //url = "https://api.guildwars2.com/v2/characters/" + charac + "?access_token=" + key;

        new GetUrlImg().execute("https://api.guildwars2.com/v2/professions/"+profe);


        BtnEquip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(CharacStatus.this, Equipment.class);
                i.putExtra("ApiKey", key);
                i.putExtra("charac", charac);

            }

    });
}

    private class GetUrlImg extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            return JsonReader.GET(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                Object Json = new JSONTokener(result).nextValue();
                if (Json instanceof JSONObject) {
                    JSONObject json = new JSONObject(result);
                    UrlImg = json.getString("icon_big");
                }
                new DownloadImageTask().execute(UrlImg);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        Bitmap d;

        protected Bitmap doInBackground(String... urls) {

            try {
                InputStream is = (InputStream) new URL(UrlImg).getContent();
                d = BitmapFactory.decodeStream(is);
                is.close();
                return d;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(Bitmap result) {
            img.setImageBitmap(d);
        }
    }




}


