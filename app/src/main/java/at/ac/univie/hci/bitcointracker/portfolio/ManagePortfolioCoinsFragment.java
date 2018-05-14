package at.ac.univie.hci.bitcointracker.portfolio;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import at.ac.univie.hci.bitcointracker.AlertActivity;
import at.ac.univie.hci.bitcointracker.DrawerActivity;
import at.ac.univie.hci.bitcointracker.R;
import at.ac.univie.hci.bitcointracker.SettingsActivity;
import at.ac.univie.hci.bitcointracker.news.NewsActivity;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

public class ManagePortfolioCoinsFragment extends Fragment {

    private SwipeMenuListView listView;
    private PriceAdapter priceAdapter;
    private AmountAdapter amountAdapter;
    private List<Coin> coinList;
    private ArrayList<String> cryptoList;
    private Button addCoinBtn;
    private EditText coinName;
    private EditText coinAmount;
    private ImageButton pBtn;
    private ImageButton wBtn;
    private ImageButton fBtn;
    private ImageButton nBtn;
    private ImageButton aBtn;
    private ImageButton sBtn;


    private Double sum = 0.0;
    private Double amnt = 0.0;

    private PortfolioMemory portfolioMemory;

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
        View rootView = inflater.inflate(R.layout.manage_coin_fragment, container, false);

        portfolioMemory = new PortfolioMemory();

        coinName = (EditText) rootView.findViewById(R.id.coinNameReal);
        coinAmount = (EditText) rootView.findViewById(R.id.coinAmountReal);
        addCoinBtn = (Button) rootView.findViewById(R.id.saveCoinBtn);
        listView = (SwipeMenuListView) rootView.findViewById(R.id.listViewCoin);
        pBtn = (ImageButton) rootView.findViewById(R.id.pBtn_Manage);
        wBtn = (ImageButton) rootView.findViewById(R.id.wBtn_Manage);
        fBtn = (ImageButton) rootView.findViewById(R.id.fBtn_Manage);
        nBtn = (ImageButton) rootView.findViewById(R.id.nBtn_feed);
        aBtn = (ImageButton) rootView.findViewById(R.id.aBtn_Mnage);
        sBtn = (ImageButton) rootView.findViewById(R.id.sBtn_Manage);
        coinList = new ArrayList<>();
        cryptoList = new ArrayList<>();

        //cryptoList contains all coins, which are allowed to track from our application
        cryptoList.add("BTC");
        cryptoList.add("LTC");
        cryptoList.add("ETH");
        cryptoList.add("ABC");

        //gets the portfolio coins from sharedPreferences
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
        amountAdapter = new AmountAdapter(getContext(), coinList);
//        listView.setAdapter(priceAdapter);
        listView.setAdapter(amountAdapter);

        /**
         * SwipeMenu Creator is used in order to swipe from right to left on one item from the list view and delete it
         *  or do something else
         * */
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getContext());
//                // set item background
//                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
//                        0xCE)));
//                // set item width
//                openItem.setWidth(170);
//                // set item title
//                openItem.setTitle("DELTE");
//                // set item title fontsize
//                openItem.setTitleSize(18);
//                // set item title font color
//                openItem.setTitleColor(Color.WHITE);
//                // add to menu
//                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.trash_btn);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        String coinName =  amountAdapter.getItemName(position);
                        for(Coin aCoinList: coinList){
                            if(aCoinList.getName().equals(coinName)){
//                                coinList.remove(aCoinList);
//                                amountAdapter = new AmountAdapter(getContext(), coinList);
//                                listView.setAdapter(amountAdapter);
                            }
                        }
                        break;
                    case 1:
                        Toast.makeText(getContext(), "ITEM CLICKED2 ==" + position, Toast.LENGTH_LONG).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addCoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getCoin = coinName.getText().toString();
                String getAmount = coinAmount.getText().toString();

                ArrayList<String> savedCoins = new ArrayList<>();

                for (Coin aCoinList : coinList) {
                    savedCoins.add(aCoinList.getName());
                }

                if (getCoin == null || getCoin.trim().equals("") || coinAmount.getText().toString() == null
                        || coinAmount.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext(), "One or more Input Fields are Empty", Toast.LENGTH_LONG).show();
                } else if (!(cryptoList.contains(getCoin))) {
                    Toast.makeText(getContext(), "Unknown Coin", Toast.LENGTH_LONG).show();
                } else {

                    if (savedCoins.contains(getCoin)) {
                        updateCoinAmount(getCoin, getAmount);
                    } else renderCoinInput(getCoin, getAmount);
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


    private void updateCoinAmount(String name, String amount) {
        String updatedAmount = "";
        for (Coin aCoinList : coinList) {
            if (aCoinList.getName().equals(name)) {
                String previousAmount = aCoinList.getAmount();
                Double value = Double.parseDouble(previousAmount) + Double.parseDouble(amount);
                updatedAmount = String.valueOf(value);
                aCoinList.setAmount(updatedAmount);
                portfolioMemory.updateFavorites(getContext(), aCoinList);
                //TODO FIX updateTotal to view instant on change
                portfolioMemory.updateTotal(getContext());
                priceAdapter.updateAdapter(coinList);
                calculatePriceAmount(name,updatedAmount, aCoinList);
                amountAdapter.updateAdapter(coinList);
//                listView.setAdapter(priceAdapter);
                  listView.setAdapter(amountAdapter);
            }
        }

    }

    private void renderCoinInput(String name, String amount) {
        Coin coin = new Coin();
        coin.setName(name);
        coin.setAmount(amount);
        coin.setCurrency("$");
        coin.setPrice("0");

        coinList.add(coin);
        portfolioMemory.addFavorite(getContext(), coin);
        portfolioMemory.updateTotal(getContext());
        amountAdapter.updateAdapter(coinList);
        calculatePriceAmount(name, amount, coin);
        priceAdapter.updateAdapter(coinList);
        amountAdapter.updateAdapter(coinList);
//        listView.setAdapter(priceAdapter);
          listView.setAdapter(amountAdapter);
    }

    private void calculatePriceAmount(String name, String amount, Coin coin){
            switch (name) {
                case "BTC": {
                    int btcPrice = 9948;
                    Double amout = Double.parseDouble(amount);
                    Double priceAmount = btcPrice * amout;
                    coin.setPrice(String.valueOf(priceAmount));
                    portfolioMemory.updateFavorites(getContext(), coin);
//                    Toast.makeText(getContext(),"MY PRICE IS == " + coin.getPrice(), Toast.LENGTH_LONG).show();
                    break;
                }
                case "LTC": {
                    int ltcPrice = 174;
                    Double amout = Double.parseDouble(amount);
                    Double priceAmount = ltcPrice * amout;
                    coin.setPrice(String.valueOf(priceAmount));
                    portfolioMemory.updateFavorites(getContext(), coin);
                    break;
                }
                case "ETH": {
                    int ethPrice = 815;
                    Double amout = Double.parseDouble(amount);
                    Double priceAmount = ethPrice * amout;
                    coin.setPrice(String.valueOf(priceAmount));
                    portfolioMemory.updateFavorites(getContext(), coin);
                    break;
                }
                case "ABC": {
                    int abcPrice = 250;
                    Double amout = Double.parseDouble(amount);
                    Double priceAmount = abcPrice * amout;
                    coin.setPrice(String.valueOf(priceAmount));
                    portfolioMemory.updateFavorites(getContext(), coin);
                    break;
                }
                default:
                    Toast.makeText(getContext(), "Problem in calculating PriceAmount", Toast.LENGTH_LONG).show();
                    break;
            }
    }
}
