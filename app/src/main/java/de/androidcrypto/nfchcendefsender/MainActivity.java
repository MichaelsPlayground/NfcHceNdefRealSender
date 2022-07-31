package de.androidcrypto.nfchcendefsender;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // AID is setup in apduservice.xml
    // original AID: F0394148148100

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button setNdef = (Button) findViewById(R.id.set_ndef_button);
        setNdef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                // Technically, if this is past our byte limit,
                // it will cause issues.
                //
                // TODO: add validation
                //
                TextView getNdefString = (TextView) findViewById(R.id.ndef_text);
                String test = getNdefString.getText().toString() + " on " +
                        getTimestamp();

                Intent intent = new Intent(view.getContext(), MyHostApduService.class);
                intent.putExtra("ndefMessage", test);
                System.out.println("*** start ***");
                startService(intent);
            }
        });

    }

    public static String getTimestamp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return ZonedDateTime
                    .now(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern("uuuu.MM.dd HH:mm:ss"));
        } else {
            return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}