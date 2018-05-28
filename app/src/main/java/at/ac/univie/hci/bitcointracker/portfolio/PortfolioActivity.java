package at.ac.univie.hci.bitcointracker.portfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import at.ac.univie.hci.bitcointracker.R;


/**
 * PortfolioActivity is extending FragmentActivity and it's goal is to distribute all 3 Fragments in our application
 * The first fragment, which is invoked when the application is started is the CryptoPriceFragment and layout with
 * which this fragment is working is the cryptoPrice_layout
 */
public class PortfolioActivity extends FragmentActivity {
    /**
     * When the application is started the method onCreate is called and invoking the
     * Fragment Manager. The Fragment Manager is responsible for managing all fragments
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio_frame);
        if (findViewById(R.id.container_portfolio) != null) {

            if (savedInstanceState != null) {
                return;
            }
        }

        Intent intent = getIntent();
        String fragmentToStart = intent.getExtras() != null ? intent.getExtras().getString("FragmentToOpen") : "start_fragment";

        switch (fragmentToStart) {
            case "start_fragment":

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container_portfolio, new ChartPriceFragment())
                        .add(R.id.container_portfolio, new CoinPriceFragment())
                        .commit();
                break;
            case "manage_fragment":
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container_portfolio, new ManagePortfolioCoinsFragment())
                        .commit();
                break;
            case "fee_fragment":
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container_portfolio, new FeesFragment())
                        .commit();
                break;
        }

    }

}
