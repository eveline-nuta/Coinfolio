package at.ac.univie.hci.bitcointracker.news;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

//Platzhalter für bessere übersichtlichkeit zwischen einträgen
public class Platzhalter extends RecyclerView.ItemDecoration{

    int Space;
    public Platzhalter(int Space){
        this.Space=Space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=Space;
        outRect.bottom=Space;
        outRect.right=Space;
        if (parent.getChildLayoutPosition(view)==0){
            outRect.top=Space;
        }
    }
}
