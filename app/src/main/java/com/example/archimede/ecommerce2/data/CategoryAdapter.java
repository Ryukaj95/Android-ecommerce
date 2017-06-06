package com.example.archimede.ecommerce2.data;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archimede.ecommerce2.R;

import java.util.List;

/**
 * Created by archimede on 06/06/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> mDataset;
    private Context context;

    public CategoryAdapter(List<Category> mDataset, Context context) {
        this.mDataset = mDataset;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView imageView;
        public TextView title;
        public TextView description;


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }



    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_row_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);

        vh.title = (TextView) v.findViewById(R.id.title);
        vh.description = (TextView) v.findViewById(R.id.description);
        vh.imageView = (ImageView) v.findViewById(R.id.imageView);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(mDataset.get(position).getTitle());
        holder.description.setText(mDataset.get(position).getDescription());
//        holder.imageView.setText(mDataset.get(position).getImage());

    }

    public int getItemCount() { return mDataset.size(); }
}
