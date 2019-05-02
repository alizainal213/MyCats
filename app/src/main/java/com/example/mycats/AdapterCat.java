package com.example.mycats;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mycats.retrofit.ApiServiceCat;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class AdapterCat extends RecyclerView.Adapter<AdapterCat.CatHolder> {

    Context context;
    LayoutInflater inflater;
    private List<ApiServiceCat> catList;
    String image_url = "https://api.thecatapi.com/images/";

    public AdapterCat(MainActivity activity, List<ApiServiceCat> catList) {
        this.context = activity;
        this.catList = catList;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public CatHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.cat_item, viewGroup, false);
        CatHolder holder = new CatHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatHolder catHolder, int position) {
        catHolder.txtNama.setText(catList.get(position).getName());
        catHolder.txtOri.setText(catList.get(position).getOrigins());
        catHolder.txtDesc.setText(catList.get(position).getDescription());

        //Picasso
        Picasso
                .with(context)
                .load(image_url + catList.get(position).getImages())
                .transform(new RoundedCornersTransformation(8, 5))
                .placeholder(R.drawable.kucingproject)
                .error(R.drawable.kucingproject)
                .into(catHolder.imgCat);

    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public class CatHolder extends RecyclerView.ViewHolder {
        View catView;
        ImageView imgCat;
        TextView txtNama, txtOri, txtDesc;
        public CatHolder(@NonNull View itemView) {
            super(itemView);
            catView = itemView;
            txtNama = itemView.findViewById(R.id.CatName);
            txtOri = itemView.findViewById(R.id.CatOrigin);
            txtDesc = itemView.findViewById(R.id.CatDesc);
            imgCat = itemView.findViewById(R.id.CatImage);
        }
    }
}
