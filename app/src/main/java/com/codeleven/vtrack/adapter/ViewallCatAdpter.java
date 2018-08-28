package com.codeleven.vtrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeleven.vtrack.Helpers.OnItemClickHelperInterface;
import com.codeleven.vtrack.MainActivity;
import com.codeleven.vtrack.R;
import com.codeleven.vtrack.databinding.CustomAllCatBinding;
import com.codeleven.vtrack.model.datacat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by CodelevenPC on 16-Jul-18.
 */

public class ViewallCatAdpter extends RecyclerView.Adapter<ViewallCatAdpter.ViewHolder> {


    private Context context;
    private ArrayList<datacat> galleryResponses;
    OnItemClickHelperInterface helperInterface;

    //
    public ViewallCatAdpter(Context context, ArrayList<datacat> galleryResponse, OnItemClickHelperInterface onItemClickHelperInterface) {
        this.context = context;
        this.galleryResponses = galleryResponse;
        helperInterface=onItemClickHelperInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CustomAllCatBinding viewHolder = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_all_cat, parent, false);

        return new ViewHolder(viewHolder.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.bindData(galleryResponses.get(position));
        holder.bindData(galleryResponses.get(position));

    }




    @Override
    public int getItemCount() {
        return galleryResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        CustomAllCatBinding customAllCatBinding;
        private ItemClickListener clickListener;


        public ViewHolder(View itemView) {
            super(itemView);
            customAllCatBinding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void bindData(final datacat datacat) {
            final String url = datacat.getIcon();

            Picasso.with(context)
                    .load("https://ownshopz.com/ownapi/images/"+url)
                    .into(customAllCatBinding.imgThumbnail);
            customAllCatBinding.tvSpecies.setText(datacat.getCategory());

            customAllCatBinding.topLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                        Intent c = new Intent(context, MainActivity.class);
                        context.startActivity(c);
                        helperInterface.onItemSelcted(url, datacat.getCategory());



                }
            });

        }



        public void setOnClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }


    }
    public interface ItemClickListener {

        void onClick(View view, int position, boolean isLongClick);

    }



}
