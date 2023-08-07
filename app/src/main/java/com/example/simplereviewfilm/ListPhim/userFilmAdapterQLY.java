package com.example.simplereviewfilm.ListPhim;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplereviewfilm.R;

import java.util.ArrayList;
import java.util.List;

public class userFilmAdapterQLY extends RecyclerView.Adapter<userFilmAdapterQLY.UserViewHolder> implements Filterable {

    private Context context;
    private List<userFilm> mlist;
    private List<userFilm> mlistOld;

    public userFilmAdapterQLY(Context context) {
        this.context = context;
    }

    public void setData(Context context,List<userFilm> mlist){
        this.context= context;
        this.mlist = mlist;
        this.mlistOld = mlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phimdangchieu,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        userFilm user = mlist.get(position);
        if(user == null)
            return;

        byte [] hinhanh = user.getImgSrc();

        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0,hinhanh.length);
        holder.img.setImageBitmap(bitmap);
        holder.nameF.setText(user.getTenPhim());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickToCTP(user);
            }
        });

    }

    public void onClickToCTP(userFilm user){
        Intent intent = new Intent(context, QlyPhim.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userFilm", user);
        intent.putExtra("chucNang", "sua");

        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(mlist != null){
            return mlist.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView nameF;
        private CardView cardView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.layout_item);
            img = itemView.findViewById(R.id.img_anhF);
            nameF = itemView.findViewById(R.id.txt_tenF);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if(search.isEmpty()){
                    mlist = mlistOld;
                }
                else {
                    List<userFilm> lst = new ArrayList<>();
                    for(userFilm user : mlistOld){
                        if(user.getTenPhim().toLowerCase().contains(search.toLowerCase())){
                            lst.add(user);
                        }
                    }
                    mlist = lst;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mlist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mlist = (List<userFilm>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
