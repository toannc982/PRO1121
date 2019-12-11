package com.ftios.duan1.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ftios.duan1.NewDetailsActivity;
import com.ftios.duan1.R;
import com.ftios.duan1.model.News;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    //Dữ liệu hiện thị là danh sách sinh viên
    private List mNews;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;

    public NewsAdapter(List _student, Context mContext) {
        this.mNews = _student;
        this.mContext = mContext;

    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử sinh viên
        View studentView =
                inflater.inflate(R.layout.item_list_news, parent, false);

        NewsAdapter.ViewHolder viewHolder = new NewsAdapter.ViewHolder(studentView);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final News news = (News) mNews.get(position);

        holder.tvTitle.setText(news.getTitle());
        holder.tvDescription.setText(news.getDescription());

        Glide.with(mContext)
                .load(news.getImage())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgIcon);


        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getLink()));
//                mContext.startActivity(browserIntent);


                // this: là activity hiện tại
                Intent intent = new Intent(mContext, NewDetailsActivity.class);
                Bundle bundle = new Bundle();
                // đóng gói kiểu dữ liệu String, Boolean
                bundle.putInt("key_1", position);
                bundle.putString("key_2", news.getTitle());
                bundle.putString("key_3", news.getContent());
                // đóng gói bundle vào intent
                intent.putExtras(bundle);
                // start SecondActivity
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public void updateList(List<News> dbList) {
        dbList.clear();
        dbList.addAll(dbList);
        notifyDataSetChanged();
    }

    /**
     * Lớp nắm giữ cấu trúc view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle, tvDescription;
        public ImageView imgIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imgIcon = itemView.findViewById(R.id.imgIcon);

        }

    }


}