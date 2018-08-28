package com.codeleven.vtrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeleven.vtrack.R;
import com.codeleven.vtrack.View_deal_activity;

import java.util.ArrayList;

/**
 * Created by CodelevenPC on 25-Jul-18.
 */

public class dineadabter  extends RecyclerView.Adapter<dineadabter.ViewHolder> {

    //    ArrayList<String> deallist;
    ArrayList<Integer> dealimage;
    Context context;

    public dineadabter(Context context, ArrayList<Integer> dealimage) {
        super();
        this.context = context;
//        this.deallist = deallist;
        this.dealimage = dealimage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_royal, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//        viewHolder.tvSpecies.setText(dealimage.get(i));
        viewHolder.imgThumbnail.setImageResource(dealimage.get(i));
        //  viewHolder.imgThumbnail.setImageDrawable(getResources().getDrawable(dealimage.get(i)));



        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {


                Intent c = new Intent(context, View_deal_activity.class);
                context.startActivity(c);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dealimage.size();
    }

    public Context getResources() {
        return context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ImageView imgThumbnail;
        public TextView tvSpecies;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.image_deal);
//            tvSpecies = (TextView) itemView.findViewById(R.id.txtone);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }

    public interface ItemClickListener {

        void onClick(View view, int position, boolean isLongClick);
    }
}










