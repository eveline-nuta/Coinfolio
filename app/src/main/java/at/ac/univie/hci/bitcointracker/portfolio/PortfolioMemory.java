package at.ac.univie.hci.bitcointracker.portfolio;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class PortfolioMemory {


    public static final String PREFS_NAME = "Bitcoin_Tracker";
    public static final String COINS = "PortfolioCoins";

    public PortfolioMemory() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<Coin> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(COINS, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, Coin coin) {
        List<Coin> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Coin>();
        favorites.add(coin);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, Coin coin) {
        ArrayList<Coin> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(coin);
            saveFavorites(context, favorites);
        }
    }

    public void updateFavorites(Context context, Coin coin) {
        ArrayList<Coin> favorites = getFavorites(context);
        if (favorites != null) {

            for (Coin favorite : favorites) {
                if (favorite.getName().equals(coin.getName())) {
                    favorite.setAmount(coin.getAmount());
                    favorite.setPrice(coin.getPrice());
                    favorite.setCurrency(coin.getCurrency());
                }
            }
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<Coin> getFavorites(Context context) {
        SharedPreferences settings;
        List<Coin> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                MODE_PRIVATE);

        if (settings.contains(COINS)) {
            String jsonFavorites = settings.getString(COINS, null);
            Gson gson = new Gson();
            Coin[] favoriteItems = gson.fromJson(jsonFavorites,
                    Coin[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Coin>(favorites);
        } else
            return null;

        return (ArrayList<Coin>) favorites;
    }


    public void saveTotal(Context context, Coin coin){
        SharedPreferences  totalPrefs = context.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor  totalEditor = totalPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(coin);
        totalEditor.putString("Total", json);
        totalEditor.commit();
    }


    public Coin getTotal(Context context){
        SharedPreferences  totalPrefs = context.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        Gson gson = new Gson();
        String json = totalPrefs.getString("Total","");
        Coin total = gson.fromJson(json,Coin.class);
        return total;
    }

    public void updateTotal(Context context){

        Coin oldTotal = getTotal(context);
        ArrayList<Coin> coins = getFavorites(context);
        Double coinAmount = 0.0;
        Double coinPrice = 0.0;

        if(oldTotal != null || coins != null){
            for (Coin coin : coins) {
                coinAmount += Double.parseDouble(coin.getAmount());
                coinPrice += Double.parseDouble(coin.getPrice());
            }

            oldTotal.setAmount(String.valueOf(coinAmount));
            oldTotal.setPrice(String.valueOf(coinPrice));
        }
        saveTotal(context, oldTotal);
    }

}
