package at.ac.univie.hci.bitcointracker.portfolio;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import at.ac.univie.hci.bitcointracker.AlertActivity;
import at.ac.univie.hci.bitcointracker.R;


import java.util.List;

public class PriceAdapter extends BaseAdapter {

    private List<Coin> coinArrayList;
    private Context mContext;


    public PriceAdapter(Context mContext, List<Coin> coinArrayList) {
        this.mContext = mContext;
        this.coinArrayList = coinArrayList;
    }

    @Override
    public int getCount() {
        return coinArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return coinArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.coin_price_template, null);

        TextView tvName = (TextView) view.findViewById(R.id.nameTest);
        TextView tvCurrency = (TextView) view.findViewById(R.id.coinCurrencyTest);
        TextView tvPrice = (TextView) view.findViewById(R.id.coinPriceTest);
        ImageButton tvBtn = (ImageButton) view.findViewById(R.id.alarmBtnTemp);
        final String name = coinArrayList.get(position).getName();
        tvName.setText(coinArrayList.get(position).getName());
        tvCurrency.setText(coinArrayList.get(position).getCurrency());
        tvPrice.setText(coinArrayList.get(position).getPrice());
        tvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mContext, AlertActivity.class);
                i.putExtra("crypto", name); //Optional parameters
                mContext.startActivity(i);
            }
        });

        view.setTag(coinArrayList.get(position).getName());

        return view;
    }

    public void updateAdapter(List<Coin> tmp) {

        this.coinArrayList = tmp;

        //and call notifyDataSetChanged
        notifyDataSetChanged();
    }

}
