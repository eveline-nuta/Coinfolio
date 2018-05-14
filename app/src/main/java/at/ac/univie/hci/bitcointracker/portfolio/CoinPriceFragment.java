package at.ac.univie.hci.bitcointracker.portfolio;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import at.ac.univie.hci.bitcointracker.AlertActivity;
import at.ac.univie.hci.bitcointracker.R;
import at.ac.univie.hci.bitcointracker.SettingsActivity;
import at.ac.univie.hci.bitcointracker.news.NewsActivity;

import java.util.ArrayList;
import java.util.List;

public class CoinPriceFragment extends Fragment {

    private ListView listView;
    private Button addCoin;
    private PortfolioMemory portfolioMemory;
    private PriceAdapter priceAdapter;
    private List<Coin> coinList;

    private ImageButton pBtn;
    private ImageButton wBtn;
    private ImageButton fBtn;
    private ImageButton nBtn;
    private ImageButton aBtn;
    private ImageButton sBtn;

    /**
     * onCreateView is invoked when this fragment is created and here I am initialising my TextViews
     * need for my layout
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    @Override
    @SuppressWarnings("Duplicates")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.coin_price_fragment, container, false);
        portfolioMemory = new PortfolioMemory();

        addCoin = (Button) rootView.findViewById(R.id.addCoinPriceBtn);
        listView = (ListView) rootView.findViewById(R.id.viewListCoinPrice);

        pBtn = (ImageButton) rootView.findViewById(R.id.pBtn_Por);
        wBtn = (ImageButton) rootView.findViewById(R.id.wBtn_Por);
        fBtn = (ImageButton) rootView.findViewById(R.id.fBtn_Por);
        nBtn = (ImageButton) rootView.findViewById(R.id.nBtn_Por);
        aBtn = (ImageButton) rootView.findViewById(R.id.aBtn_Por);
        sBtn = (ImageButton) rootView.findViewById(R.id.sBtn_Por);

        coinList = new ArrayList<>();
        ArrayList<Coin> favorites = portfolioMemory.getFavorites(getContext());

        if(favorites == null){
            Coin total = new Coin();
            total.setCurrency("$");
            total.setPrice("0");
            total.setAmount("0");
            total.setName("TOTAL");
            portfolioMemory.saveTotal(getContext(),total);
            coinList.add(portfolioMemory.getTotal(getContext()));

        }else {
            coinList.add(portfolioMemory.getTotal(getContext()));
            coinList.addAll(favorites);
        }

        priceAdapter = new PriceAdapter(getContext(), coinList);
        listView.setAdapter(priceAdapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addCoin = (Button) getView().findViewById(
                R.id.addCoinPriceBtn);
        addCoin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction = getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_portfolio, new ManagePortfolioCoinsFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        pBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PortfolioActivity.class);
                intent.putExtra("FragmentToOpen", "start_fragment");
                startActivity(intent);
            }
        });
        wBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PortfolioActivity.class);
                intent.putExtra("FragmentToOpen", "manage_fragment");
                startActivity(intent);
            }
        });
        fBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PortfolioActivity.class);
                intent.putExtra("FragmentToOpen", "fee_fragment");
                startActivity(intent);
            }
        });
        nBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewsActivity.class);
                startActivity(intent);
            }
        });
        aBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AlertActivity.class);
                startActivity(intent);
            }
        });
        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

    }

}
