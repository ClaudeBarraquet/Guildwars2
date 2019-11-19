package com.example.claude.guildwars2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Konynrith on 14/03/2018.
 */

public class Equipment extends Activity {

    protected void OnCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipment);

        Intent intent = getIntent();
        final String key = intent.getStringExtra("ApiKey");
        final String charac = intent.getStringExtra("charac");

        TextView test = (TextView) findViewById(R.id.textView2);
        test.setText(charac);
    }
}
