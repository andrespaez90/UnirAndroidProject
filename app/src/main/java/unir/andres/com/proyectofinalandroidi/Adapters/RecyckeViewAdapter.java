package unir.andres.com.proyectofinalandroidi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import unir.andres.com.proyectofinalandroidi.Model.User;
import unir.andres.com.proyectofinalandroidi.R;

/**
 * Created by INNSO SAS on 08/06/2015.
 */

public class RecyckeViewAdapter extends RecyclerView.Adapter<RecyckeViewAdapter.DataViewHolder>
        implements  View.OnClickListener{

    private ArrayList<User> visibleUsers;
    private ArrayList<User> allItems;
    private Context mContext;


    public RecyckeViewAdapter(ArrayList<User> data, Context context){
        mContext = context;
        allItems = data;
        flushFilter();
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_list, viewGroup, false);

        itemView.setOnClickListener(this);
        DataViewHolder tvh = new DataViewHolder(itemView,mContext);
        return tvh;
    }

    @Override
    public void onBindViewHolder(DataViewHolder data, int i) {
        User item = visibleUsers.get(i);
        data.bindItem(item);
    }

    @Override
    public int getItemCount() {
        return visibleUsers.size();
    }

    public void flushFilter(){
        visibleUsers = new ArrayList<>();
        visibleUsers.addAll(allItems);
        notifyDataSetChanged();
    }

    public void setFilter(String queryText) {

        visibleUsers = new ArrayList<>();
        for (User item: allItems) {
            if (item.getName().toLowerCase().contains(queryText))
                visibleUsers.add(item);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }


    public static class DataViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView txtTitle;
        private TextView txtsubtitle;
        private int mRowHeight;
        private Context mContext;

        public DataViewHolder(View itemView, Context context) {
            super(itemView);
            mRowHeight = 100;
            mContext = context;
            imageView = (ImageView)itemView.findViewById(R.id.list_image);
            txtTitle = (TextView)itemView.findViewById(R.id.list_title);
            txtsubtitle = (TextView) itemView.findViewById(R.id.list_subtitle);
        }

        public void bindItem(User item) {
            txtTitle.setText(item.getName());
            txtsubtitle.setText(item.getUsername());
        }
    }

}
