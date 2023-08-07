package com.example.simplereviewfilm.ListPhim;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.simplereviewfilm.R;

import java.util.ArrayList;
import java.util.List;

public class userCmtAdapter extends ArrayAdapter<userCMT> {

    Activity context;
    int img;
    ArrayList<userCMT> adapter;

    public userCmtAdapter(Activity context1, int img, ArrayList<userCMT> adapter) {
        super(context1,img,adapter);
        this.context = context1;
        this.img = img;
        this.adapter = adapter;
    }

    public userCmtAdapter(@NonNull Context context, int resource, @NonNull List<userCMT> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Tạo để chưa layout
        LayoutInflater myinflater = context.getLayoutInflater();

        //Đặt id layout để tạo thành view
        convertView = myinflater.inflate(img,null);
//        convertView = LayoutInflater.from(context).inflate(R.layout)

        //Lấy một phần tử trong mảng
        userCMT us = adapter.get(position);

        //Khai báo, tham chiếu id và hiển thị lên imageview

        TextView txt_ten = convertView.findViewById(R.id.txtTen);
        txt_ten.setText(us.getTen());

        TextView txt_cmt = convertView.findViewById(R.id.txtCmt);
        txt_cmt.setText(us.getCmt());

        return convertView;

    }
}
