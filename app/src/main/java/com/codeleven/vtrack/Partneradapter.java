package com.codeleven.vtrack;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by CodelevenPC on 10-Jul-18.
 */

public class Partneradapter extends RecyclerView.Adapter<Partneradapter.ViewHolder> {

    ArrayList<String> deallist;
    ArrayList<Integer> dealimage;
    Context context;

    public Partneradapter(Context context, ArrayList<Integer> dealimage) {
        super();
        this.context = context;
//        this.deallist = deallist;
        this.dealimage = dealimage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_row_partner, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//        viewHolder.tvSpecies.setText(deallist.get(i));

            viewHolder.imgThumbnail.setImageResource(dealimage.get(i));



        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                if(position==0) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.codeleven.rabbito"));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dealimage.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ImageView imgThumbnail;
        LinearLayout rootLayout;
        //        public TextView tvSpecies;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            rootLayout = (LinearLayout) itemView.findViewById(R.id.top_layout);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.expandedImage);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

//            tvSpecies = (TextView) itemView.findViewById(R.id.txtone);
//            itemView.setOnClickListener(this);
//            itemView.setOnLongClickListener(this);
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


