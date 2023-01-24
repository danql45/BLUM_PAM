package com.example.blum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blum.model.ProductInfo;
import com.example.blum.model.ProductNew;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductsNewAdapter extends RecyclerView.Adapter<ProductsNewAdapter.ViewHolder> {

    private LayoutInflater inflater;

    private Context context;
    private List<ProductNew> productList;

    private List<Boolean> clickedIndexes;


    ProductsNewAdapter(Context context, List<ProductNew> productList){
        this.inflater = LayoutInflater.from(context);
        this.productList = productList;
        this.context = context;
        clickedIndexes = resetClickedIndexes();
    }

    public void setProductsList(List<ProductNew> productsList) {
        this.productList = productsList;
        this.clickedIndexes = resetClickedIndexes();
        notifyDataSetChanged();
    }

    private List<Boolean> resetClickedIndexes() {
        clickedIndexes = new ArrayList<>();
        for(ProductNew p : productList){
            clickedIndexes.add(false);
        }
        return clickedIndexes;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @NonNull
    @Override
    public ProductsNewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.expandable_list_element, parent, false);
        return new ProductsNewAdapter.ViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull ProductsNewAdapter.ViewHolder holder, int position) {
        ProductNew curr = productList.get(position);
        holder.title.setText(curr.getTitle());
        holder.number.setText(String.valueOf(curr.getId()));
        Picasso.get().load(curr.getImageURL()).into(holder.mainImageView);
        holder.variantCount.setText(String.valueOf(curr.getProductInfo().size()));
        if(clickedIndexes.get(position)==true){
            holder.childrenLayout.setVisibility(View.VISIBLE);
        }else{
            holder.childrenLayout.setVisibility(View.GONE);
        }
        holder.childrenLayout.removeAllViews();
        List<View> children = createChildrenViews(curr);
        for(View child : children){
            holder.childrenLayout.addView(child);
        }

        //
//        holder.expandButton.setText("BUTTON");
//        holder.expandButton.setTextColor(Color.WHITE);
//        holder.expandButton.setBackgroundColor(Color.parseColor("#162C35"));
    }

    private List<View> createChildrenViews(ProductNew curr) {
        List<View> toAdd = new ArrayList<>();
        for(ProductInfo i : curr.getProductInfo()){
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = vi.inflate(R.layout.details_view, null);
            if(i.getProductURL()!=null){
                @SuppressLint({"MissingInflatedId", "LocalSuppress"})
                ImageView imageView = (ImageView) v.findViewById(R.id.productURL);
                Picasso.get().load(i.getProductURL()).into(imageView);
                imageView.setVisibility(View.VISIBLE);
            }
            if(i.getArticleNumber()!=null){
                TextView textView = (TextView) v.findViewById(R.id.artNo);
                textView.setText("Art no: "+String.valueOf(i.getArticleNumber()));
                textView.setVisibility(View.VISIBLE);
            }
            if(i.getDistance()!=null){
                TextView textView = (TextView) v.findViewById(R.id.distance);
                textView.setText("distance: "+String.valueOf(i.getDistance()));
                textView.setVisibility(View.VISIBLE);
            }
            if(i.getHeight()!= null){
                TextView textView = (TextView) v.findViewById(R.id.height);
                textView.setText("Height: "+String.valueOf(i.getHeight()));
                textView.setVisibility(View.VISIBLE);
            }
            if(i.getColour()!= null){
                TextView textView = (TextView) v.findViewById(R.id.colour);
                textView.setText("Colour: "+String.valueOf(i.getColour()));
                textView.setVisibility(View.VISIBLE);
            }
            if(i.getFixingMethod()!=null){
                TextView textView = (TextView) v.findViewById(R.id.fixingMethod);
                textView.setText("Fixing method: "+String.valueOf(i.getFixingMethod()));
                textView.setVisibility(View.VISIBLE);
            }
            if(i.getDoorType()!= null){
                TextView textView = (TextView) v.findViewById(R.id.doorType);
                textView.setText("Door type: "+String.valueOf(i.getDoorType()));
                textView.setVisibility(View.VISIBLE);
            }
            if(i.getOpeningAngle()!= null){
                TextView textView = (TextView) v.findViewById(R.id.openingAngle);
                textView.setText("Opening angle: "+String.valueOf(i.getOpeningAngle()));
                textView.setVisibility(View.VISIBLE);
            }
            if(i.getProductSystem()!= null){
                TextView textView = (TextView) v.findViewById(R.id.productSystem);
                textView.setText("Product system: "+String.valueOf(i.getProductSystem()));
                textView.setVisibility(View.VISIBLE);
            }
            if(i.getCabinetHeight() !=null){
                TextView textView = (TextView) v.findViewById(R.id.cabinetHeight);
                textView.setText("Cabinet height: "+String.valueOf(i.getCabinetHeight()));
                textView.setVisibility(View.VISIBLE);
            }
            if(i.getCabinetMinDepth()!= null){
                TextView textView = (TextView) v.findViewById(R.id.cabinetMinDepth);
                textView.setText("min depth: "+String.valueOf(i.getCabinetMinDepth()));
                textView.setVisibility(View.VISIBLE);
            }
            if(i.getPowerFactorLF()!=null){
                TextView textView = (TextView) v.findViewById(R.id.powerFactorLF);
                textView.setText("Power factor: "+String.valueOf(i.getPowerFactorLF()));
                textView.setVisibility(View.VISIBLE);
            }
            toAdd.add(v);
        }

        return toAdd;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        
        TextView number;
        TextView variantCount;
        Button expandButton;
        ImageView mainImageView;
        LinearLayout childrenLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.nameContent);
            variantCount = itemView.findViewById(R.id.variantsContent);
            mainImageView = itemView.findViewById(R.id.main_image);
            number = itemView.findViewById(R.id.idContent);
            expandButton = itemView.findViewById(R.id.expandButton);
            childrenLayout = itemView.findViewById(R.id.childrenContainer);
            expandButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean current = clickedIndexes.get(getAdapterPosition());
                    clickedIndexes.set(getAdapterPosition(),!current);
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
