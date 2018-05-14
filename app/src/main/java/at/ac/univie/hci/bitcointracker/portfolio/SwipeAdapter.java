package at.ac.univie.hci.bitcointracker.portfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.baoyz.swipemenulistview.SwipeMenuListView;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();

        switch (position){
            case 0:
                CoinPriceFragment coinPriceFragment = new CoinPriceFragment();
                coinPriceFragment.setArguments(bundle);
                return coinPriceFragment;
            case 1:
              ManagePortfolioCoinsFragment managePortfolioCoinsFragment =  new ManagePortfolioCoinsFragment();
              managePortfolioCoinsFragment.setArguments(bundle);
                return managePortfolioCoinsFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
