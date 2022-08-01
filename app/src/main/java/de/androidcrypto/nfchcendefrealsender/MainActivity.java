package de.androidcrypto.nfchcendefrealsender;

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
    // here we are using the official NFC tag type 4 AID: D2760000850101

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

        Button setNdef100 = (Button) findViewById(R.id.set_ndef100_button);
        setNdef100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                // Technically, if this is past our byte limit,
                // it will cause issues.
                //
                // TODO: add validation
                //
                String characters66 = "";
                for (int i = 0; i < 66; i++) {
                    characters66 = characters66 + "R";
                }
                String test = characters66 + " on " +
                        getTimestamp();
                Intent intent = new Intent(view.getContext(), MyHostApduService.class);
                intent.putExtra("ndefMessage", test);
                System.out.println("*** start ***");
                startService(intent);
            }
        });

        Button setNdef500 = (Button) findViewById(R.id.set_ndef500_button);
        setNdef500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                // Technically, if this is past our byte limit,
                // it will cause issues.
                //
                // TODO: add validation
                //
                String characters66 = "";
                for (int i = 0; i < 466; i++) {
                    characters66 = characters66 + "A";
                }
                String test = characters66 + " on " +
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