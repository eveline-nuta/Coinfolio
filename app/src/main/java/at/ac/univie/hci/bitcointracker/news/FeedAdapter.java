package at.ac.univie.hci.bitcointracker.news;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import at.ac.univie.hci.bitcointracker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    ArrayList<TestArtikel>feedItems;
    Context context;
    public FeedAdapter(Context context, ArrayList<TestArtikel>feedItems){
        this.feedItems=feedItems;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_layout,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        TestArtikel current=feedItems.get(position);
        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.Date.setText(current.getDate());
        //picasso für thumbnail/ media integration
        Picasso.with(context).load(current.getThumbnailUrl()).into(holder.Thumbnail);

    }



    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title,Description,Date;
        ImageView Thumbnail;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.title_text);
            Description= (TextView) itemView.findViewById(R.id.description_text);
            Date= (TextView) itemView.findViewById(R.id.date_text);
            Thumbnail= (ImageView) itemView.findViewById(R.id.thumb_img);
            cardView= (CardView) itemView.findViewById(R.id.cardview);
        }
    }
}
