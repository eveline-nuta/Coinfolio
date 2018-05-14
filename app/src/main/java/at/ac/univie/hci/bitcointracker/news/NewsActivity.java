package at.ac.univie.hci.bitcointracker.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import at.ac.univie.hci.bitcointracker.R;

public class NewsActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //ruft RSS feed auf
        RSSparser readRss = new RSSparser(this, recyclerView);
        readRss.execute();
    }
}
