package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Console;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public Context mContext;
    public String Article[];
    List<RssViewModel> mRssFeedModels;

    List<String> titlelist, descriptionlist, linklist;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageview;

        public MyViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.articletext);
            imageview = view.findViewById(R.id.imageView2);


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


            holder.textView.setText(rssViewModel.title);
            holder.imageview.setImageResource(R.drawable.download);




    }

    @Override
    public int getItemCount() {
      return   mRssFeedModels.size();

    }
}


