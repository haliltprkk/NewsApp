package com.example.android.theguardianapplication;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.theguardianapplication.adapters.Adapter;
import com.example.android.theguardianapplication.models.Guardian;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Guardian>> {
    public String apiUrl;
    TextView textView;
    ListView newsListView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.main_menu)
        {
            Intent open=new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(open);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsListView = (ListView) findViewById(R.id.list);


    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String result=prefs.getString("example_list", "1");
        String resulOfPagesize=prefs.getString("example_text","5");
        Uri.Builder builder = new Uri.Builder();
        if(result.equals("1"))
        {
            builder.scheme("https")
                    .authority("content.guardianapis.com")
                    .appendPath("search")
                    .appendQueryParameter("api-key", "ad9e0ba0-30de-473e-9901-8e3d1d8888bd")
                    .appendQueryParameter("q", "economy")
                    .appendQueryParameter("page-size", resulOfPagesize);

        }
        else if(result.equals("0"))
        {
            builder.scheme("https")
                    .authority("content.guardianapis.com")
                    .appendPath("search")
                    .appendQueryParameter("api-key", "ad9e0ba0-30de-473e-9901-8e3d1d8888bd")
                    .appendQueryParameter("q", "education")
                    .appendQueryParameter("page-size", resulOfPagesize);

        }
        else if(result.equals("-1"))
        {
            builder.scheme("https")
                    .authority("content.guardianapis.com")
                    .appendPath("search")
                    .appendQueryParameter("api-key", "ad9e0ba0-30de-473e-9901-8e3d1d8888bd")
                    .appendQueryParameter("q", "politics")
                    .appendQueryParameter("page-size", resulOfPagesize);
        }

        apiUrl = builder.build().toString();
        getLoaderManager().restartLoader(0,null,this);
        checkConnection();
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void checkConnection() {
        textView = (TextView) findViewById(R.id.problem_text_view);
        if (isOnline()) {
            getLoaderManager().initLoader(0, null, MainActivity.this).forceLoad();
            textView.setVisibility(View.GONE);
            newsListView.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, "You are connected to Internet", Toast.LENGTH_SHORT).show();
        } else {

            textView.setVisibility(View.VISIBLE);
            newsListView.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public Loader<ArrayList<Guardian>> onCreateLoader(int id, Bundle args) {
        return new TheGuardianLoader(MainActivity.this, apiUrl);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Guardian>> loader, ArrayList<Guardian> news) {
        updateUI(news);

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Guardian>> loader) {
        Log.v("reset", "Reset has worked");

    }

    public void updateUI(final ArrayList<Guardian> news) {
        Adapter adapter = new Adapter(MainActivity.this, news);
        newsListView.setAdapter(adapter);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent send = new Intent(Intent.ACTION_VIEW, Uri.parse(news.get(position).getWebUrl()));
                startActivity(send);
            }
        });
    }


}