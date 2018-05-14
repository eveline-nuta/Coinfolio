package at.ac.univie.hci.bitcointracker;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import at.ac.univie.hci.bitcointracker.MainActivity;
import at.ac.univie.hci.bitcointracker.R;
import at.ac.univie.hci.bitcointracker.news.NewsActivity;
import at.ac.univie.hci.bitcointracker.portfolio.PortfolioActivity;

public class AlertActivity extends Activity
{
    public Integer position = 0;
    private String selectedCurrency;
    private ArrayList<Map<String, String>> list;
    private ListView listview;
    private String crypto;

    private ImageButton pBtn;
    private ImageButton wBtn;
    private ImageButton fBtn;
    private ImageButton nBtn;
    private ImageButton aBtn;
    private ImageButton sBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alerts);
        Intent intent = getIntent();
        crypto = intent.getStringExtra("crypto");
        Spinner currencies=findViewById(R.id.currencies);
        Spinner cryptoCurrencies=findViewById(R.id.cryptoCurrencies);
        TextView addAlert = findViewById(R.id.addAlert);
        pBtn = (ImageButton) findViewById(R.id.pBtn_A);
        wBtn = (ImageButton) findViewById(R.id.wBtn_A);
        fBtn = (ImageButton) findViewById(R.id.fBtn_A);
        nBtn = (ImageButton) findViewById(R.id.nBtn_A);
        aBtn = (ImageButton) findViewById(R.id.aBtn_A);
        sBtn = (ImageButton) findViewById(R.id.sBtn_A);

        if(crypto==null)
        {
            addAlert.setText("Add alert for ");
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,
                    getResources().getStringArray(R.array.cryptoCurrencies));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cryptoCurrencies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
                {

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }

            });
            cryptoCurrencies.setAdapter(adapter);
        }
        else {
            addAlert.setText("Add alert for " + crypto);
            cryptoCurrencies.setVisibility(View.GONE);
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,
                getResources().getStringArray(R.array.currencies));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        currencies.setAdapter(adapter);


        listview = (ListView) findViewById(R.id.tableAlerts);

        String[] from = { "name", "purpose" };
        int[] to = { R.id.coin_label, R.id.coin_value };

        list = buildData();
        SimpleAdapter adapter1 = new SimpleAdapter(this,list,
                R.layout.currency_table, from, to);

        listview.setAdapter(adapter1);


        pBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PortfolioActivity.class);
                intent.putExtra("FragmentToOpen", "start_fragment");
                startActivity(intent);
            }
        });
        wBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PortfolioActivity.class);
                intent.putExtra("FragmentToOpen", "manage_fragment");
                startActivity(intent);
            }
        });
        fBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PortfolioActivity.class);
                intent.putExtra("FragmentToOpen", "fee_fragment");
                startActivity(intent);
            }
        });
        nBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                startActivity(intent);
            }
        });
        aBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AlertActivity.class);
                startActivity(intent);
            }
        });
        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

    }

    private ArrayList<Map<String, String>> buildData() {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        return list;
    }

    private HashMap<String, String> putData(String name, String purpose) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("name", name);
        item.put("purpose", purpose);
        return item;
    }
//AICI
    public void onDeleteButtonClick(View view) {

        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int pos = listView.getPositionForView(parentRow);
        //Integer pos = view.getTag() != null ? (Integer)view.getTag() : 0;
        Map<String, String> item = list.get(pos);
        list.remove(item);
        Toast.makeText(getApplicationContext(), "Item deleted", Toast.LENGTH_SHORT).show();
        final Adapter adapter = listview.getAdapter();
        if (adapter instanceof BaseAdapter) {
            ((BaseAdapter)adapter).notifyDataSetChanged();
        } else {
            throw new RuntimeException("Unexpected adapter");
        }
    }
    public void onButtonClick(View view)
    {
        Spinner currencies=findViewById(R.id.currencies);
        Spinner cryptoCurrencies=findViewById(R.id.cryptoCurrencies);
        EditText threshold=findViewById(R.id.threshold);
        if(cryptoCurrencies.getVisibility() != View.GONE) {
            crypto = cryptoCurrencies.getSelectedItem() != null ? cryptoCurrencies.getSelectedItem().toString() : "BTC";
        }
        String currency = currencies.getSelectedItem() != null ? currencies.getSelectedItem().toString() : "USD";
        String thresholdText = threshold.getText().toString();
        list.add(putData(crypto,thresholdText + " " + currency));
        Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();
        final Adapter adapter = listview.getAdapter();
        if (adapter instanceof BaseAdapter) {
            ((BaseAdapter)adapter).notifyDataSetChanged();
        } else {
            throw new RuntimeException("Unexpected adapter");
        }
    }

}
