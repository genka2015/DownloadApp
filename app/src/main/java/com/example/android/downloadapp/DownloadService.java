package com.example.android.downloadapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadService extends IntentService {


    public DownloadService() {
        super("DownloadService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String urlToDownload = intent.getStringExtra("url");
            ResultReceiver receiver = (ResultReceiver) intent.getParcelableExtra("receiver");

            Bundle myBundle = new Bundle();

            try{
                URL url = new URL(urlToDownload);
                URLConnection connection = url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                // this will be useful so that you can show a typical 0-100% progress bar
                int fileLength = connection.getContentLength();

                // download the file
                InputStream input = connection.getInputStream();


                Bitmap image = BitmapFactory.decodeStream(input);


                myBundle.putParcelable("download", image);
                receiver.send(0, myBundle);
                input.close();



            }catch (IOException e){

            }

        }
    }


}
