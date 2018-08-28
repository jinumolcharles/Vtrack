package com.codeleven.vtrack.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codeleven.vtrack.R;
import com.codeleven.vtrack.View_deal_activity;
import com.codeleven.vtrack.mainmodel;

import java.util.List;

/**
 * Created by CodelevenPC on 16-Jul-18.
 */

public class viewalladapter  extends RecyclerView.Adapter<viewalladapter.ViewHolder> {


    Context context;
    private List<mainmodel>images;
    private Intent intent;


    public viewalladapter(Context context,List<mainmodel> caticonlist) {
        super();
        this.context = context;
        this.images = caticonlist;
        this.intent = intent;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_all_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

            String url = images.get(i).getImage();
//            Intent intent = getIntent();
//            final String brand = intent.getExtras().getString("brandfactory");

//        Picasso picasso = Picasso.with(context);
//        Picasso.setSingletonInstance(picasso);
//        picasso.with(context)
//                .load(url)
//                .error(R.drawable.adidas)
//                .resize(100,150)
//                .into( viewHolder.imgThumbnail);

            Glide.with(context)
                    .load(url)
                    .fitCenter()
                    .into(viewHolder.imgThumbnail);

        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

               String a= images.get(position).getImagename();

                     Intent c = new Intent(context, View_deal_activity.class);
//                    c.putExtra("brandfactory", brand);
                    c.putExtra("img", "https://ownshopz.com/wp-content/uploads/2018/07/5d84ef15c47229ed33758985f45411e3.jpg");
                    context.startActivity(c);





            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ImageView imgThumbnail;
//        public TextView tvSpecies;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.expandedImage);
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


