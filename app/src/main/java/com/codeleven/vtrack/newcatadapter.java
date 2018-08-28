package com.codeleven.vtrack;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeleven.vtrack.activity.categories_Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodelevenPC on 30-Jul-18.
 */

public class newcatadapter extends RecyclerView.Adapter<newcatadapter.ViewHolder> {

    ArrayList<String> alName;
    ArrayList<Integer> alImage;
    private List<mainmodel> catlist;

    Context context;
    //
//    public MainAdapter(Context context,List<mainmodel> caticonlist) {
//        super();
//        this.context = context;
////        this.alName = alName;
//        this.catlist = caticonlist;
//    }
    public newcatadapter(Context context, ArrayList<String> alName, ArrayList<Integer> alImage) {
        super();
        this.context = context;
        this.alName = alName;
        this.alImage = alImage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_activity, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//        viewHolder.tvSpecies.setText(catlist.get(i).getImagename());
        viewHolder.tvSpecies.setText(alName.get(i));
        viewHolder.imgThumbnail.setImageResource(alImage.get(i));

//        String imgurl ="http://www.lumiya.co.in/Lumya/images/";
//
//        String url=imgurl+catlist.get(i).getImage();
//        Context context = viewHolder.imgThumbnail.getContext();
//
//        Picasso.with(context).load(url).into(viewHolder.imgThumbnail);
//        Picasso.with(context)
//                .load(catlist.get(i).getImage())
//                .into(  viewHolder.imgThumbnail);


        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                String a= alName.get(position);

                Intent c = new Intent(context, categories_Activity.class);
                c.putExtra("Cat", a);
//                c.putExtra("img", "https://ownshopz.com/wp-content/uploads/2018/07/5d84ef15c47229ed33758985f45411e3.jpg");
                context.startActivity(c);

            }
        });
    }

    @Override
    public int getItemCount() {
        return alName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ImageView imgThumbnail;
        public TextView tvSpecies;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvSpecies = (TextView) itemView.findViewById(R.id.tv_species);
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
