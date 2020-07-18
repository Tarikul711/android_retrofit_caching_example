package com.tos.retrofitokhttpcaching.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.tos.retrofitokhttpcaching.R;
import com.tos.retrofitokhttpcaching.webapi.post.PostData;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private Context context;
    private List<PostData> items;

    public PhotoAdapter(Context context, List<PostData> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostData item = items.get(position);

        holder.tvText.setText(item.getTitle());
       /* Glide.with(context)
                .load(item.getUrl())
                .into(holder.ivImage);*/
        holder.ivImage.setTag(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvText;
        AppCompatImageView ivImage;

        ViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvText = itemView.findViewById(R.id.tvText);

        }

    }
}
