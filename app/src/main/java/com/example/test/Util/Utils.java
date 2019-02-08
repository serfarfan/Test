package com.example.test.Util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

    //Check internet connection before execute network operation
    public static boolean isConnected(Context ctx){

        ConnectivityManager connMgr =
                (ConnectivityManager) ctx.getSystemService(Activity.CONNECTIVITY_SERVICE);
        if (connMgr!= null){
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        }
        else return false;
    }
}
