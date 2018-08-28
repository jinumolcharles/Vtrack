package com.codeleven.vtrack;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeleven.vtrack.Helpers.OnItemClickHelperInterface;
import com.codeleven.vtrack.databinding.RoyalCustomBinding;
import com.codeleven.vtrack.model.royaldata;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by CodelevenPC on 03-Jul-18.
 */

public class adabterclass extends RecyclerView.Adapter<adabterclass.ViewHolder> {

    ArrayList<String> deallist;
    ArrayList<Integer> dealimage;
    Context context;

    private ArrayList<royaldata> galleryResponses;
    OnItemClickHelperInterface helperInterface;

//    public adabterclass(Context context,  ArrayList<Integer> dealimage) {
//        super();
//        this.context = context;
//        this.dealimage = dealimage;
//    }

    public adabterclass(Context context, ArrayList<royaldata> galleryResponse, OnItemClickHelperInterface onItemClickHelperInterface) {
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

        RoyalCustomBinding viewHolder = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.royal_custom, viewGroup, false);

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
        RoyalCustomBinding royalCustomBinding;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
//            imgThumbnail = (ImageView)itemView.findViewById(R.id.image_deal);
//            imgThumbnail = (ImageView) itemView.findViewById(R.id.expandedImage);
            //tvSpecies = (TextView) itemView.findViewById(R.id.txtone);
            royalCustomBinding = DataBindingUtil.bind(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void bindData(final royaldata datacat) {
            final String url = datacat.getIcon();
            Picasso.with(context)
                    .load("https://ownshopz.com/ownapi/images/"+url)
                    .into(royalCustomBinding.imageDeal);


            royalCustomBinding.imageDeal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    helperInterface.onItemSelcted(url, datacat.getCategory());
                    final String link = datacat.getLink();
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




















//
//        extends RecyclerView.Adapter<adabterclass.ViewHolder> {
//    private ArrayList<model> android_versions;
//    ArrayList<Integer> dealimage;
//    private Context context;
//
////    public adabterclass(Context context,ArrayList<model> android_versions) {
////        this.context = context;
////        this.android_versions = android_versions;
////
////    }
//
//    public adabterclass(Context context,  ArrayList<Integer> dealimage) {
//        super();
//        this.context = context;
////        this.deallist = deallist;
//        this.dealimage = dealimage;
//    }
//
//    @Override
//    public adabterclass.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main, viewGroup, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//
//
//        viewHolder.img_android.setImageResource(dealimage.get(i));
////        viewHolder.tv_android.setText(android_versions.get(i).getAndroid_version_name());
////        viewHolder.img_android.setImageResource(Integer.parseInt(android_versions.get(i).getImage()));
////        Picasso.with(context).load(android_versions.get(i).getImage()).resize(120, 60).into(viewHolder.img_android);
//
////        Glide.with(context)
////                .load(android_versions.get(i).getImage())
////                .diskCacheStrategy(DiskCacheStrategy.ALL)
////                .into(viewHolder.img_android);
////        String imgUrl = android_versions.get(i).getImage();
////        //Picasso
////        Picasso.with(context).load(imgUrl).into(viewHolder.img_android);
////
////        //Glide
////        Glide.with(context).load(imgUrl).into(viewHolder.img_android);
//
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return dealimage.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
////        TextView tv_android;
//        ImageView img_android;
//        public ViewHolder(View view) {
//            super(view);
//
//            img_android = (ImageView)view.findViewById(R.id.image_deal);
//        }
//
//
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
