package com.example.claude.guildwars2;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * Created by Claude on 09/09/2017.
 */

public class CharacterActivity extends Activity {

    String url;
    ListView v;
    List<Character> str = new ArrayList<>();
    CustomAdapterCharac adapter;
    String key;
    Bitmap bite;
    Bitmap bmImage;
    Character character;
    View test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character);
        test = findViewById(R.id.waitingbar);
        v=(ListView) findViewById(R.id.list);

        test.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        key = intent.getStringExtra("ApiKey");

        url = "https://api.guildwars2.com/v2/characters?access_token="+key;

        new HttpAsyncTask().execute(url);

        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Character info = (Character) v.getItemAtPosition(position);

                String charac = info.getName();
                String profe = info.getProfe();
                String level = info.getLevel();
                String race = info.getRace();
                Bitmap logo = info.getLogo();
                //String charac = v.getItemAtPosition(position).toString();

                Intent intent = new Intent(CharacterActivity.this, CharacStatus.class);
                intent.putExtra("ApiKey", key);
                intent.putExtra("charac", charac);
                intent.putExtra("profe", profe);
                intent.putExtra("level", level);
                intent.putExtra("race", race);
                intent.putExtra("logo",logo);
                startActivity(intent);
            }
        });

    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return JsonReader.GET(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            try {
                Object Json = new JSONTokener(result).nextValue();
                if (Json instanceof JSONObject) {
                    //Toast.makeText(getBaseContext(), "orange", Toast.LENGTH_LONG).show();
                    JSONObject json = new JSONObject(result);

                    if(json.has("icon_big")){
                        String url =json.getString("icon_big");
                        bite = new DownloadImageTask().execute(url).get();
                        //character.setLogo(bite);
                    }
                    else {
                        String Profe = json.getString("profession");
                        String Level = json.getString("level");
                        String Name = json.getString("name");
                        String Race = json.getString("race");
                        new HttpAsyncTask().execute("https://api.guildwars2.com/v2/professions/" + Profe).get();
                        str.add(new Character(Name, Profe, Level,bite,Race));
                        //character.setLogo(bmImage);
                    }
                }
                else if (Json instanceof JSONArray) {
                    JSONArray json = new JSONArray(result);
                    int len=json.length();
                    for(int i=0;i<len;i++)
                    {
                        new HttpAsyncTask().execute("https://api.guildwars2.com/v2/characters/" + json.get(i).toString() + "?access_token=" + key).get();
                    }
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                adapter =new CustomAdapterCharac(CharacterActivity.this,str);
                v.setAdapter(adapter);
                test.setVisibility(View.GONE);
            }
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        //Bitmap bmImage;

        public DownloadImageTask() {
            //this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                //Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage = result;
        }
    }
}
