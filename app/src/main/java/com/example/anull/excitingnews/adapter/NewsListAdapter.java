package com.example.anull.excitingnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anull.excitingnews.R;
import com.example.anull.excitingnews.bean.NewsList;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by null on 2016/7/18.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<NewsList.StoriesBean> newsList;
    private OnItemClickListener onItemClickListener;



    public NewsListAdapter(Context context, List<NewsList.StoriesBean> newsList) {
        this.context = context;
        this.newsList = newsList;
        this.inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsList.StoriesBean newsItem = newsList.get(position);
        if(newsItem.getImages().isEmpty()){
            Picasso.with(context).load(R.drawable.error).into(holder.newsImg);
        }else{
            Picasso.with(context).load(newsItem.getImages().get(0)).into(holder.newsImg);
        }
        holder.newsTitle.setText(newsItem.getTitle());
        holder.position = position;
    }

    public void refresh(List<NewsList.StoriesBean> newsList){
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    public void loadMore(List<NewsList.StoriesBean> newsList){
        this.newsList.addAll(newsList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @InjectView(R.id.newsImg)
        CircleImageView newsImg;
        @InjectView(R.id.newsTitle)
        TextView newsTitle;
        int position;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.click(v, newsList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void click(View v, NewsList.StoriesBean item);
    }
}
