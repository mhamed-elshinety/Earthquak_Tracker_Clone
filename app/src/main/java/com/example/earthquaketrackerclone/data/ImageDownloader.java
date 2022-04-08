package com.example.earthquaketrackerclone.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.earthquaketrackerclone.listeners.OnImageDownloadListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloader extends AsyncTask<String,Void, Bitmap> {

    OnImageDownloadListener listener;
    public ImageDownloader (OnImageDownloadListener listener){
        this.listener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... strings){
        Bitmap result = null;
        HttpURLConnection urlConnection = null;
        InputStream is = null;
        try {
            URL imgURL = new URL(strings[0]);
            urlConnection= (HttpURLConnection) imgURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            Log.e(Constants.LOG_KEY_MY_APP,"Image Response Code" + responseCode );
            if(responseCode==200) {
                is = urlConnection.getInputStream();
                result = BitmapFactory.decodeStream(is);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        listener.onImageDownload(bitmap);

    }
}
