package com.codeleven.vtrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeleven.vtrack.Helpers.OnItemClickHelperInterface;
import com.codeleven.vtrack.R;
import com.codeleven.vtrack.databinding.CustomRowPartnerBinding;
import com.codeleven.vtrack.model.associatedatamodel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by CodelevenPC on 10-Aug-18.
 */

public class associ_adapter extends RecyclerView.Adapter<associ_adapter.ViewHolder> {


    Context context;

    private ArrayList<associatedatamodel> galleryResponses;
    OnItemClickHelperInterface helperInterface;

    public associ_adapter(Context context, ArrayList<associatedatamodel> galleryResponse, OnItemClickHelperInterface onItemClickHelperInterface) {
        this.context = context;
        this.galleryResponses = galleryResponse;
        helperInterface=onItemClickHelperInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View v = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.activity_main, viewGroup, false);
//        ViewHolder viewHolder = new ViewHolder(v);
//        return viewHolder;

        CustomRowPartnerBinding viewHolder = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_row_partner, viewGroup, false);

        return new ViewHolder(viewHolder.getRoot());


    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//        viewHolder.tvSpecies.setText(deallist.get(i));
//        viewHolder.imgThumbnail.setImageResource(dealimage.get(i));

        viewHolder.bindData(galleryResponses.get(i));
    }

    @Override
    public int getItemCount() {
        return galleryResponses.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        //        public ImageView imgThumbnail;
//        public TextView tvSpecies;
        CustomRowPartnerBinding customRowPartnerBinding;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
//            imgThumbnail = (ImageView)itemView.findViewById(R.id.image_deal);
//            imgThumbnail = (ImageView) itemView.findViewById(R.id.expandedImage);
            //tvSpecies = (TextView) itemView.findViewById(R.id.txtone);
            customRowPartnerBinding = DataBindingUtil.bind(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void bindData(final associatedatamodel assodata) {
                      final String url = assodata.getIcon();
            Picasso.with(context)
                    .load("https://ownshopz.com/ownapi/images/"+url)
//                    .load("http://192.168.1.103/ownapi/images/"+url)
                    .into(customRowPartnerBinding.expandedImage);


            customRowPartnerBinding.expandedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    helperInterface.onItemSelcted(url, datacat.getCategory());
                    final String link = assodata.getLink();
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(link));
//                    intent.setData(Uri.parse("https://play.google.com/store/apps/dev?id=com.codeleven.rabbito"));
                    context.startActivity(intent);
                }
            });


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




















