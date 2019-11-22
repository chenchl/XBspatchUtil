package com.ccl.view.myapplication;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ccl.bspatch.XBspatchUtil;

import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(BuildConfig.VERSION_NAME);
        Button btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Executors.newFixedThreadPool(1).execute(new Runnable() {
                    @Override
                    public void run() {
                        XBspatchUtil.getNewApk(getApplicationContext(),
                                Environment.getExternalStorageDirectory().getAbsolutePath() + "/xbspatch",
                                Environment.getExternalStorageDirectory().getAbsolutePath() + "/newApk.apk");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"合成完毕",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }
}
