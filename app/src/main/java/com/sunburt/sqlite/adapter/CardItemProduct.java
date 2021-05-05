package com.sunburt.sqlite.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunburt.sqlite.R;
import com.sunburt.sqlite.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CardItemProduct extends RecyclerView.Adapter<CardItemProduct.CardViewHolder> {

    private List<Product> products;
    private Activity activity;

    public CardItemProduct(Activity activity){
        activity = activity;
        products = new ArrayList<Product>();
    }

    public List<Product> getListProduct(){
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_product,parent,
                false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        final Product product = products.get(position);
        if (product!=null){
            holder.txtProductId.setText(product.getIdProduct());
            holder.txtName.setText(product.getName());
            holder.txtPrice.setText(product.getPrice() + "");
            if (product.getStatus().equals("Stocking")){
                holder.imgStatus.setImageResource(R.drawable.ic_check);
            }
            else
                holder.imgStatus.setImageResource(R.drawable.ic_warnning);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgStatus;
        private TextView txtProductId, txtName, txtPrice;
        private ImageButton btnDelete;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imgStatus = (ImageView) itemView.findViewById(R.id.imgStatus);
            txtProductId = (TextView) itemView.findViewById(R.id.txtIdProduct);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            btnDelete = (ImageButton) itemView.findViewById(R.id.btnDelete);
        }
    }
}
