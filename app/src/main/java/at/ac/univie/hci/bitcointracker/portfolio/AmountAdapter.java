package at.ac.univie.hci.bitcointracker.portfolio;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import at.ac.univie.hci.bitcointracker.AlertActivity;
import at.ac.univie.hci.bitcointracker.R;

import java.util.List;

public class AmountAdapter extends BaseAdapter {

    private List<Coin> coinArrayList;
    private Context mContext;


    public AmountAdapter(Context mContext, List<Coin> coinArrayList) {
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

    public String getItemName(int position) {return coinArrayList.get(position).getName();}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.amount_coin_template, null);

        TextView coinName = (TextView) view.findViewById(R.id.coinNameAmount);
        TextView amountText = (TextView) view.findViewById(R.id.textAmount);
        TextView amountCoin = (TextView) view.findViewById(R.id.coinAmountAdapt);
        final String name = coinArrayList.get(position).getName();
        ImageButton tvBtn = (ImageButton) view.findViewById(R.id.alarmBtnAmount);

        coinName.setText(coinArrayList.get(position).getName());
        amountCoin.setText(coinArrayList.get(position).getAmount());
        tvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mContext, AlertActivity.class);
                i.putExtra("crypto", name); //Optional parameters
                mContext.startActivity(i);            }
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
