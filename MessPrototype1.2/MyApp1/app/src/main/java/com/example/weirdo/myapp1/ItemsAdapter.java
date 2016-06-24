package com.example.weirdo.myapp1;

/**
 * Created by Weirdo on 28-04-2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private List<Item> itemlist;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvdate,tvtime,tvname, tvprice;

        public MyViewHolder(View view) {
            super(view);
            tvdate = (TextView) view.findViewById(R.id.idate);
            tvtime = (TextView) view.findViewById(R.id.itime);
            tvname = (TextView) view.findViewById(R.id.iname);
            tvprice = (TextView) view.findViewById(R.id.iprice);

        }
    }


    public ItemsAdapter(List<Item> itemList) {
        this.itemlist = itemList;
    }



    @Override
    public ItemsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.MyViewHolder holder, int position) {
        Item item = itemlist.get(position);
        holder.tvname.setText(item.getItem());
        holder.tvprice.setText(item.getPrice());
        holder.tvdate.setText(item.getDate());
        holder.tvtime.setText(item.getTime());


    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }
}
