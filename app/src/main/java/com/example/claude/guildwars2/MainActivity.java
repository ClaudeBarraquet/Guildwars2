package com.example.claude.guildwars2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;


/*Cette classe correspond à l'écran de connexion, l'endroit est est saisie la clé API */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button BtnValider = (Button) findViewById(R.id.btnValider);

        BtnValider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText editText = (EditText)findViewById(R.id.APIText);
                String key;

                key = editText.getText().toString();

                Intent i = new Intent(MainActivity.this,CharacterActivity.class);
                i.putExtra("ApiKey",key);
                startActivity(i);
                //editText.setText("");
            }
        });

        TextView text = (TextView)findViewById(R.id.recupKeyText);
        text.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Uri uri = Uri.parse("https://account.arena.net/applications");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }


}
