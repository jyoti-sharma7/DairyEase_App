package com.example1.dairyease.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example1.dairyease.ModelResponse.ProductList;
import com.example1.dairyease.R;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    Context context;
    List<ProductList> productLists;

    public ProductListAdapter(Context context, List<ProductList> productLists) {
        this.context = context;
        this.productLists = productLists;
    }

    @NonNull
    @Override
    public ProductListAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(context).inflate(R.layout.item_product_recycler,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ProductViewHolder holder, int position) {
       // holder.bind(productLists.get(position));

        if (productLists != null && position < productLists.size()) {
            holder.bind(productLists.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return productLists != null ? productLists.size() : 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imgproduct;
        TextView productName,tvQuantity,tvPerPrice;
        LinearLayout rvllProduct;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            rvllProduct = itemView.findViewById(R.id.rvllProduct);
            imgproduct = itemView.findViewById(R.id.imgproduct);
            productName = itemView.findViewById(R.id.productName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvPerPrice = itemView.findViewById(R.id.tvPerPrice);



        }


        public  void bind( ProductList productList ){

            productName.setText(productList.getName());
            tvQuantity.setText(productList.getQuantity()+"");
            tvPerPrice.setText(productList.getPrice());

            Glide.with(context).load(productList.getProduct_image_url()).into(imgproduct);



        }


    }
}
