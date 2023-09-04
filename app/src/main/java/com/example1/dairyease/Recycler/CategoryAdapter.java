package com.example1.dairyease.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example1.dairyease.ModelResponse.CategoryData;
import com.example1.dairyease.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    List<CategoryData> categoryDataList;

    public CategoryAdapter(Context context, List<CategoryData> categoryDataList) {
        this.context = context;
        this.categoryDataList = categoryDataList;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_category_design,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        if (categoryDataList != null && position < categoryDataList.size()) {
            holder.bind(categoryDataList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return categoryDataList != null ? categoryDataList.size() : 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCategoryImage;
        TextView tvCategoryName;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            ivCategoryImage = itemView.findViewById(R.id.ivCategoryImage);

        }

        public void bind(@NonNull CategoryData categoryData) {

            tvCategoryName.setText(categoryData.getCategory_name());

            Glide.with(context).load(categoryData.getCategory_image_url()).into(ivCategoryImage);
        }
    }
}
