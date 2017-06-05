package com.example.android.downloadapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DownloadReceiver downloadReceiver;
    private Button loadBt;
    private TextView result;
    private ImageView resultIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadBt = (Button)findViewById(R.id.downloadBt);
        loadBt.setOnClickListener(this);
        result = (TextView)findViewById(R.id.resultTV);
        resultIV = (ImageView) findViewById(R.id.myView);
        downloadReceiver = new DownloadReceiver(new Handler(Looper.getMainLooper()), this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.downloadBt:
                // download
                // initialize the progress dialog like in the first example

                // this is how you fire the downloader
                Intent intent = new Intent(this, DownloadService.class);
                intent.putExtra("url", "http://www.vanishingtattoo.com/tds/images/bird/bird_large/raven_005.jpg");
                intent.putExtra("receiver", downloadReceiver);
                startService(intent);
                break;
            default:
                break;
        }
    }

    private Intent getServiceIntent(){

        return new Intent(this,DownloadService.class);

    }

    public void showImage(Bitmap bmp){
        result.setText("Download complete");
        resultIV.setImageBitmap(bmp);
    }

}
