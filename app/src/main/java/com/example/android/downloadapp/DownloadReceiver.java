package com.example.android.downloadapp;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by Android on 6/3/2017.
 */

class DownloadReceiver extends ResultReceiver {

private MainActivity mainActivity;

    public DownloadReceiver(Handler handler, MainActivity mainActivity) {

        super(handler);
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        mainActivity.showImage((Bitmap) resultData.getParcelable("download"));
    }
}
