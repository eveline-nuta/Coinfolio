package at.ac.univie.hci.bitcointracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import at.ac.univie.hci.bitcointracker.news.NewsActivity;

import at.ac.univie.hci.bitcointracker.portfolio.ManagePortfolioCoinsFragment;
import at.ac.univie.hci.bitcointracker.portfolio.PortfolioActivity;


public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.burger_frame_up, new ManagePortfolioCoinsFragment());
        ft.commit();

        navigationView.setCheckedItem(R.id.manage_menu);
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.burger_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.portfolio_menu) {
            Intent intent = new Intent(DrawerActivity.this, PortfolioActivity.class);
            intent.putExtra("FragmentToOpen", "start_fragment");
            startActivity(intent);

        } else if (id == R.id.manage_menu) {
            Intent intent = new Intent(DrawerActivity.this, PortfolioActivity.class);
            intent.putExtra("FragmentToOpen", "manage_fragment");
            startActivity(intent);
        } else if (id == R.id.fees_menu) {
            Intent intent = new Intent(DrawerActivity.this, PortfolioActivity.class);
            intent.putExtra("FragmentToOpen", "fee_fragment");
            startActivity(intent);
        } else if (id == R.id.news_menu) {
            Intent intent = new Intent(DrawerActivity.this, NewsActivity.class);
            startActivity(intent);

        } else if (id == R.id.aler_menu) {
            Intent intent = new Intent(DrawerActivity.this, AlertActivity.class);
            startActivity(intent);

        } else if (id == R.id.settings_menu) {
            Intent intent = new Intent(DrawerActivity.this, SettingsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
