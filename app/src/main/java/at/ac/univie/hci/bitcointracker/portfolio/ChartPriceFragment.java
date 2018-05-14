package at.ac.univie.hci.bitcointracker.portfolio;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import at.ac.univie.hci.bitcointracker.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class ChartPriceFragment extends Fragment {


    private static final String TAG = "PortfolioActivity";
    private LineChart lineChart;

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
        View rootView = inflater.inflate(R.layout.chart_price_fragment, container, false);
        lineChart = (LineChart) rootView.findViewById(R.id.LineChart);

        ArrayList<Entry> bitcoinList = new ArrayList<>();
        bitcoinList.add(new Entry(0, 1908f));
        bitcoinList.add(new Entry(1, 1701f));
        bitcoinList.add(new Entry(2, 1156f));
        bitcoinList.add(new Entry(3, 1147f));
        bitcoinList.add(new Entry(4, 893f));
        bitcoinList.add(new Entry(5, 973f));

        ArrayList<Entry> litecoinList = new ArrayList<>();
        litecoinList.add(new Entry(0, 359f));
        litecoinList.add(new Entry(1, 296f));
        litecoinList.add(new Entry(2, 230f));
        litecoinList.add(new Entry(3, 213f));
        litecoinList.add(new Entry(4, 165f));
        litecoinList.add(new Entry(5, 151f));

        ArrayList<Entry> ethereumList = new ArrayList<>();
        ethereumList.add(new Entry(0, 793f));
        ethereumList.add(new Entry(1, 1397f));
        ethereumList.add(new Entry(2, 1161f));
        ethereumList.add(new Entry(3, 870.37f));
        ethereumList.add(new Entry(4, 707f));
        ethereumList.add(new Entry(5, 674f));

        LimitLine upper_limit = new LimitLine(1800f, " HIGH DANGER");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f, 10f, 0);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);

        LimitLine lower_limit = new LimitLine(300f, "LOW DANGER");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f, 10f, 0);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(10f);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaximum(2000F);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0);
        leftAxis.setDrawLimitLinesBehindData(true);

        lineChart.getAxisRight().setEnabled(false);

        LineDataSet bitcoin = new LineDataSet(bitcoinList, "BITCOIN");
        bitcoin.setFillAlpha(110);
        bitcoin.setColor(Color.GREEN);
        bitcoin.setLineWidth(2f);
        bitcoin.setValueTextSize(10f);

        LineDataSet litecoin = new LineDataSet(litecoinList, "LITECOIN");
        litecoin.setFillAlpha(110);
        litecoin.setColor(Color.BLUE);
        litecoin.setLineWidth(2f);
        litecoin.setValueTextSize(10f);

        LineDataSet etheruem = new LineDataSet(ethereumList, "ETHEREUM");
        etheruem.setFillAlpha(110);
        etheruem.setColor(Color.RED);
        etheruem.setLineWidth(2f);
        etheruem.setValueTextSize(10f);

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(bitcoin);
        lineDataSets.add(litecoin);
        lineDataSets.add(etheruem);

        LineData data = new LineData(lineDataSets);
        lineChart.setData(data);

        String[] months = new String[]{"Dec", "Jan", "Feb", "Mar", "Apr", "May"};

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new MyAxisValueFormatter(months));
        xAxis.setGranularity(1f);

        return rootView;
    }

    public class MyAxisValueFormatter implements IAxisValueFormatter {
        private String[] values;

        MyAxisValueFormatter(String[] values) {
            this.values = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return values[(int) value];
        }
    }

}
