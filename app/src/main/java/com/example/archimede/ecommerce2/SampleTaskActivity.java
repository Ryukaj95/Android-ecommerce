package com.example.archimede.ecommerce2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by archimede on 10/07/17.
 */

public class SampleTaskActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_task);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        AsyncTaskSample sample = new AsyncTaskSample();
        sample.execute("sample async task start");

    }

    public class AsyncTaskSample extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... params) {
            for (int i = 0;i<100; i++){
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d("onProgerssUpdate", "progress: " + values);
            progressBar.setProgress(values[0].intValue());
        }
    }

}
