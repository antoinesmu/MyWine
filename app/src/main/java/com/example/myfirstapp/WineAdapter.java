package com.example.myfirstapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class WineAdapter extends RecyclerView.Adapter<WineAdapter.ViewHolder>{

    public static OnRecyclerItemClickListener onRecyclerItemClickListener;

    public boolean searched;

    public WineAdapter(OnRecyclerItemClickListener _onRecyclerItemClickListener){
        this.onRecyclerItemClickListener = _onRecyclerItemClickListener;
        searched=false;
    }

    public WineAdapter(OnRecyclerItemClickListener _onRecyclerItemClickListener, boolean searched){
        this.onRecyclerItemClickListener = _onRecyclerItemClickListener;
        this.searched=searched;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_wine,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(!searched){
            holder.setWine(Singleton.getInstance().wines.get(position));
        }else{
            holder.setWine(Singleton.getInstance().wines_searched.get(position));
        }

    }

    @Override
    public int getItemCount() {
        if(!searched){
            return Singleton.getInstance().wines.size();
        }else{
            return Singleton.getInstance().wines_searched.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageview_wine_photo;
        private TextView textview_wine_name;
        private TextView textview_wine_date;
        private TextView textview_wine_quantity;

        public ViewHolder(View view){
            super(view);
            //set les id du layout
            imageview_wine_photo = view.findViewById(R.id.imageView_wine_list_wine_photo);
            textview_wine_name = view.findViewById(R.id.textView_wine_list_name);
            textview_wine_date = view.findViewById(R.id.textView_wine_list_date);
            textview_wine_quantity = view.findViewById(R.id.textView_wine_list_quantity);
            view.setOnClickListener(this);


        }

        public void setWine(Wine wine){
            textview_wine_name.setText(wine.wine_name);
            textview_wine_date.setText(Integer.toString(wine.wine_year_date));
            textview_wine_quantity.setText(Integer.toString(wine.wine_quantity));
            //Picasso.get().load(wine.url_avatar).into(imageview_photo);
        }

        @Override
        public void onClick(View view) {
            Singleton.log4Me("WineAdapter" ,"onClick");
            onRecyclerItemClickListener.onRecyclerViewItemClick(view,getAdapterPosition());
        }
    }
}
