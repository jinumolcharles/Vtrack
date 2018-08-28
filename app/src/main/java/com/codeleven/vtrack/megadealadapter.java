package com.codeleven.vtrack;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeleven.vtrack.Helpers.OnItemClickHelperInterface;
import com.codeleven.vtrack.databinding.CustommegaBinding;
import com.codeleven.vtrack.model.Content;
import com.codeleven.vtrack.model.GalleryResponse;
import com.codeleven.vtrack.model.ResponseImage;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by CodelevenPC on 27-Jul-18.
 */

public class megadealadapter extends RecyclerView.Adapter<megadealadapter.ViewHolder> {


    private Context context;
    private ArrayList<GalleryResponse> galleryResponses;
    private ArrayList<Content> conte;
    OnItemClickHelperInterface helperInterface;


    public megadealadapter(Context context, ArrayList<GalleryResponse> galleryResponse,OnItemClickHelperInterface onItemClickHelperInterface) {
        this.context = context;
        this.galleryResponses = galleryResponse;
        helperInterface=onItemClickHelperInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        CustommegaBinding viewHolder = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custommega, parent, false);
////
            return new ViewHolder(viewHolder.getRoot());

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (galleryResponses.get(position).getDealType().equals("discount")) {

            holder.bindData(galleryResponses.get(position));

        }
    }

    @Override
    public int getItemCount() {
        return galleryResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CustommegaBinding customMegaDealBinding;

        public ViewHolder(View itemView) {
            super(itemView);

            customMegaDealBinding = DataBindingUtil.bind(itemView);

        }

        /**
         * bind data to adapter
         */


        void bindData(final GalleryResponse data) {

//            customViewBinding.setModel(data);
//time out also set for the requests
            String a = data.getDealType();

            if (a.equals("discount")) {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(160, TimeUnit.SECONDS)
                        .writeTimeout(160, TimeUnit.SECONDS)
                        .readTimeout(160, TimeUnit.SECONDS).build();
                Request request = new Request.Builder()
                        .url("https://ownshopz.com/api/get_img/?id=" + data.getImage())
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        Log.e("inside adapter", e.getMessage());
                        e.printStackTrace();

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        try {
//                            if (a.equals("discount")) {
                                loadImage(response, data );
//                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
            }

        private void loadImage(Response response, final GalleryResponse galleryResponse) throws IOException {
            final Content cont = null;
            String received = response.body().string();
            Log.i("response", received);
            try {

                Gson gson = new Gson();
                com.google.gson.stream.JsonReader reader = new com.google.gson.stream.JsonReader(new StringReader(received));
                reader.setLenient(true);

                final ResponseImage image = gson.fromJson(reader, ResponseImage.class);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                      if( galleryResponse.getDealType().equals("discount")){
                          customMegaDealBinding.customAllNewImage.setVisibility(View.VISIBLE);
                        Picasso.with(context)
                                .load(image.getResult())
                                .resize(450,450)
                                .into(customMegaDealBinding.customAllNewImage);
                         }

//                        Glide.with(context).load(image.getResult())
//                                .priority(Priority.IMMEDIATE)
//                                .override(400, 350)
//                                .dontAnimate()
//                                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                .into(customViewBinding.customAllNewImage);
                        customMegaDealBinding.customAllNewImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                helperInterface.onItemSelcted(image, galleryResponse.getSlug());
                                Intent c = new Intent(context, View_deal_activity.class);

                                ResponseImage responseImage = (ResponseImage) image;
//                                Content content = (Content) cont;
                                c.putExtra("discoutvalue", galleryResponse.getDiscountValue());
//                                c.putExtra("content", content.getRendered());
                                c.putExtra("brandfactory", galleryResponse.getSlug());
                                c.putExtra("img", responseImage.getResult());

                                context.startActivity(c);
//                                helperInterface.onItemSelcted(image, galleryResponse.getDiscountValue());
                            }
                        });

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
