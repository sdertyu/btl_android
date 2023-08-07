package com.example.simplereviewfilm.ListPhim;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simplereviewfilm.R;


public class CTPFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ctp, container, false);
        TextView txt_ngayChieu, txt_doTuoi, txt_thoiLuong, txt_dienVien, txt_daoDien, txt_theLoai,txt_gioiThieu;
        ImageView img;
        txt_ngayChieu = view.findViewById(R.id.txt_ngayChieu);
        txt_thoiLuong = view.findViewById(R.id.txt_thoiLuong);
        txt_daoDien = view.findViewById(R.id.txt_daoDien);
        txt_theLoai = view.findViewById(R.id.txt_theLoai);
        txt_gioiThieu = view.findViewById(R.id.txt_gioiThieu);
        txt_doTuoi = view.findViewById(R.id.txt_doTuoi);
        txt_dienVien = view.findViewById(R.id.txt_dienVien);
        img = view.findViewById(R.id.anhCT);

        Bundle bundle = getArguments();
        userFilm user = (userFilm) bundle.getSerializable("nhan");
        txt_ngayChieu.setText(user.getsNgayChieu());
        txt_doTuoi.setText(user.getsDoTuoi());
        txt_thoiLuong.setText(user.getsThoiLuong());
        txt_dienVien.setText(user.getsDienVien());
        txt_daoDien.setText(user.getsDaoDien());
        txt_theLoai.setText(user.getsTheLoai());
        txt_gioiThieu.setText(user.getsGioiThieu());

        byte [] hinhanh = user.getImgSrc();

        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0,hinhanh.length);
        img.setImageBitmap(bitmap);

        return view;


    }
}