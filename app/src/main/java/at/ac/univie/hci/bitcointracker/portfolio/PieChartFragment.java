package at.ac.univie.hci.bitcointracker.portfolio;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import at.ac.univie.hci.bitcointracker.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PieChartFragment extends Fragment {

    private PieChart pieChart;
    private PortfolioMemory portfolioMemory;
    private List<Coin> coinList;

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
        View rootView = inflater.inflate(R.layout.pie_chart_fragment, container, false);

        portfolioMemory = new PortfolioMemory();
        coinList = portfolioMemory.getFavorites(getContext());

        pieChart = (PieChart) rootView.findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(90f);
        pieChart.setCenterText("TOTAL");
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(15f);
        pieChart.setDrawEntryLabels(true);

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        if (coinList != null) {
            for (Coin aCoinList : coinList) {
                Float amount = Float.parseFloat(aCoinList.getAmount());
                String label = aCoinList.getName();
                pieEntries.add(new PieEntry(amount, label));
            }
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "      Crypto Coins");
        dataSet.setSliceSpace(5f);
        dataSet.setSelectionShift(0f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);

        return rootView;
    }


}
