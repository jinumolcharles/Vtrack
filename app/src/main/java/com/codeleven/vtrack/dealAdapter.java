package com.codeleven.vtrack;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeleven.vtrack.Helpers.OnItemClickHelperInterface;
import com.codeleven.vtrack.databinding.CustomRowBinding;
import com.codeleven.vtrack.model.PreData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by CodelevenPC on 03-Jul-18.
 */

public class dealAdapter extends RecyclerView.Adapter<dealAdapter.ViewHolder> {

    ArrayList<String> deallist;
    ArrayList<Integer> dealimage;
    private Context mcontext;
    private ArrayList<PreData> galleryResponses;
    OnItemClickHelperInterface helperInterface;


//    public dealAdapter(Context context, ArrayList<String> deallist, ArrayList<Integer> dealimage) {
//        super();
//        this.context = context;
//        this.deallist = deallist;
//        this.dealimage = dealimage;
//    }
    public dealAdapter(Context context, ArrayList<PreData> galleryResponse, OnItemClickHelperInterface onItemClickHelperInterface) {
        this.mcontext = context;
        this.galleryResponses = galleryResponse;
        helperInterface=onItemClickHelperInterface;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View v = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.custom_row, viewGroup, false);
//        ViewHolder viewHolder = new ViewHolder(v);
//        return viewHolder;
        CustomRowBinding viewHolder = DataBindingUtil.inflate(LayoutInflater.from(mcontext), R.layout.custom_row, viewGroup, false);

        return new ViewHolder(viewHolder.getRoot());

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.bindData(galleryResponses.get(i));

    }

    @Override
    public int getItemCount() {
        return galleryResponses.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

//        public ImageView imgThumbnail;
//        public TextView tvSpecies;

        CustomRowBinding customRowBinding;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
//            imgThumbnail = (ImageView) itemView.findViewById(R.id.expandedImage);
//            tvSpecies = (TextView) itemView.findViewById(R.id.txtone);
            customRowBinding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void bindData(final PreData data) {
            final String url = data.getImageurl();
            Picasso.with(mcontext)
                    .load(url)
                    .resize(300,350)
                    .into(customRowBinding.expandedImage);

            customRowBinding.expandedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String  brand = data.getSlug();
                    Intent c = new Intent(mcontext, View_deal_activity.class);
                    c.putExtra("brandfactory",brand);
                    c.putExtra("img",url);
                    mcontext.startActivity(c);
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


