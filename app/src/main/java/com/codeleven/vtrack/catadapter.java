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
import com.codeleven.vtrack.databinding.CustomAllViewNewBinding;
import com.codeleven.vtrack.model.Datum;
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
 * Created by CodelevenPC on 29-Jul-18.
 */

public class catadapter extends RecyclerView.Adapter<catadapter.ViewHolder> {


    private Context context;
    private ArrayList<Datum> galleryResponses;
    OnItemClickHelperInterface helperInterface;

//
    public catadapter(Context context, ArrayList<Datum> galleryResponse, OnItemClickHelperInterface onItemClickHelperInterface) {
        this.context = context;
        this.galleryResponses = galleryResponse;
        helperInterface=onItemClickHelperInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CustomAllViewNewBinding viewHolder = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_all_view_new, parent, false);

        return new ViewHolder(viewHolder.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bindData(galleryResponses.get(position));
    }

    @Override
    public int getItemCount() {
        return galleryResponses.size();
    }


    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        CustomAllViewNewBinding customViewBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            customViewBinding = DataBindingUtil.bind(itemView);
        }

        /**
         * bind data to adapter
         * @param data
         */
        void bindData(final Datum data) {

//            customViewBinding.setModel(data);


//time out also set for the requests
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(160, TimeUnit.SECONDS)
                    .writeTimeout(160, TimeUnit.SECONDS)
                    .readTimeout(160, TimeUnit.SECONDS).build();
            Request request = new Request.Builder()
                    .url("https://ownshopz.com/api/get_img/?id=" + data.getImageid())
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
                        loadImage(response,data);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

        }

        private void loadImage(Response response, final Datum galleryResponse) throws IOException {
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

                        Picasso.with(context)
                                .load(image.getResult())
                                .resize(300,350)
                                .into(customViewBinding.customAllNewImage);
//                        Glide.with(context).load(image.getResult())
//                                .priority(Priority.IMMEDIATE)
//                                .override(350, 350)
//                                .dontAnimate()
//                                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                .into(customViewBinding.customAllNewImage);
                        customViewBinding.customAllNewImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                helperInterface.onItemSelcted(image,galleryResponse.getSlug());

                                Intent c = new Intent(context, View_deal_activity.class);

                                ResponseImage responseImage = (ResponseImage) image;
//                                Content content = (Content) cont;
//                                c.putExtra("discoutvalue", galleryResponse.());
//                                c.putExtra("content", content.getRendered());
                                c.putExtra("brandfactory", galleryResponse.getSlug());
                                c.putExtra("img", responseImage.getResult());

                                context.startActivity(c);
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
