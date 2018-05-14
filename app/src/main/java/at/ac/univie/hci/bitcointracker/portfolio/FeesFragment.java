package at.ac.univie.hci.bitcointracker.portfolio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import at.ac.univie.hci.bitcointracker.AlertActivity;
import at.ac.univie.hci.bitcointracker.R;
import at.ac.univie.hci.bitcointracker.SettingsActivity;
import at.ac.univie.hci.bitcointracker.news.NewsActivity;

import java.util.ArrayList;
import java.util.List;


public class FeesFragment extends Fragment {

    private Button buyBtn;
    private Button sellBtn;
    private Integer coinMarket = 0;
    private List<Coin> coinList;
    private PortfolioMemory portfolioMemory;
    private PriceAdapter priceAdapter;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fees_fragment, container, false);
        Spinner feesMarkets = (Spinner) rootView.findViewById(R.id.marketFees);
        final TextView sellPercent = (TextView) rootView.findViewById(R.id.sellPercent);
        final TextView buyPercent = (TextView) rootView.findViewById(R.id.buyPercent);
        ListView listView = (ListView) rootView.findViewById(R.id.fees_list);
        buyBtn = (Button) rootView.findViewById(R.id.buyBtnFees);
        sellBtn = (Button) rootView.findViewById(R.id.sellBtnFees);

        pBtn = (ImageButton) rootView.findViewById(R.id.pBtn_Fee);
        wBtn = (ImageButton) rootView.findViewById(R.id.wBtn_Fee);
        fBtn = (ImageButton) rootView.findViewById(R.id.fBtn_Fee);
        nBtn = (ImageButton) rootView.findViewById(R.id.nBtn_Fee);
        aBtn = (ImageButton) rootView.findViewById(R.id.aBtn_Fee);
        sBtn = (ImageButton) rootView.findViewById(R.id.sBtn_Fee);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1,
                getResources().getStringArray(R.array.coinMarkets));

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        feesMarkets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                if (position == 0) {
                    buyPercent.setText("     " + "1,49%");
                    sellPercent.setText("     " + "1,49%");
                    coinMarket = 0;
                } else if (position == 1) {
                    buyPercent.setText("     " + "0,43%");
                    sellPercent.setText("     " + "0,43%");
                    coinMarket = 1;
                } else if (position == 2) {
                    buyPercent.setText("     " + "0,1%");
                    sellPercent.setText("     " + "0,1");
                    coinMarket = 2;
                } else if (position == 3) {
                    buyPercent.setText("     " + "0%");
                    sellPercent.setText("     " + "0,25%");
                    coinMarket = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Apply the adapter to the spinner
        feesMarkets.setAdapter(adapter);

        portfolioMemory = new PortfolioMemory();
        coinList = new ArrayList<>();
        ArrayList<Coin> favorites = portfolioMemory.getFavorites(getContext());

        if(favorites == null){

        }else {
            coinList.add(portfolioMemory.getTotal(getContext()));
            coinList.addAll(favorites);
        }

        priceAdapter = new PriceAdapter(getContext(), coinList);
        listView.setAdapter(priceAdapter);

        return rootView;
    }


    @Override
    @SuppressWarnings("Duplicates")
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        buyBtn = (Button) getView().findViewById(
                R.id.buyBtnFees);
        buyBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (coinMarket == 0) {
                    Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.coinbase.com"));
                    startActivity(openBrowser);
                } else if (coinMarket == 1) {
                    Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://bitbay.net"));
                    startActivity(openBrowser);
                } else if (coinMarket == 2) {
                    Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://binance.com"));
                    startActivity(openBrowser);
                } else if (coinMarket == 3) {
                    Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gdax.com"));
                    startActivity(openBrowser);
                }
            }
        });


        sellBtn = (Button) getView().findViewById(
                R.id.sellBtnFees);
        sellBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (coinMarket == 0) {
                    Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.coinbase.com"));
                    startActivity(openBrowser);
                } else if (coinMarket == 1) {
                    Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://bitbay.net"));
                    startActivity(openBrowser);
                } else if (coinMarket == 2) {
                    Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://binance.com"));
                    startActivity(openBrowser);
                } else if (coinMarket == 3) {
                    Intent openBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gdax.com"));
                    startActivity(openBrowser);
                }
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
