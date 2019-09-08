package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Console;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public Context mContext;
    public String Article[];
    List<RssViewModel> mRssFeedModels;

    List<String> titlelist, descriptionlist, linklist;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView,titleview;
        public ImageView imageview;

        public MyViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.articletext);
            imageview = view.findViewById(R.id.imageView2);
           titleview=view.findViewById(R.id.titleview);

        }

    }

    public MyAdapter(List<RssViewModel> FeedModel) {

        mRssFeedModels = FeedModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RssViewModel rssViewModel = mRssFeedModels.get(position);


            holder.textView.setText(rssViewModel.description);
            holder.titleview.setText(rssViewModel.title);

       //- holder.imageview.setImageResource(R.drawable.download);
      Picasso.get().load(rssViewModel.imgsrc).into(holder.imageview);



    }

    @Override
    public int getItemCount() {
      return   mRssFeedModels.size();

    }
}


