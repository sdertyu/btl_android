package com.example.simplereviewfilm.ListPhim;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplereviewfilm.R;

import java.util.List;

public class userFilmAdapter extends RecyclerView.Adapter<userFilmAdapter.UserViewHolder>{

    private Context context;
    private List<userFilm> mlist;

    public userFilmAdapter(Context context) {
        this.context = context;
    }

    public void setData(Context context,List<userFilm> mlist){
        this.context= context;
        this.mlist = mlist;
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
        Intent intent = new Intent(context, ChiTietPhim.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("userFilm", user);

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

}
