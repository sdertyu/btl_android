package com.example.simplereviewfilm.ListPhim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.simplereviewfilm.R;

import java.util.ArrayList;
import java.util.List;

public class Full_PhimDangChieu extends AppCompatActivity {

    private List<userFilm> userFilms;
    private userFilmAdapter adapter;
    private RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_phim_dang_chieu);

        SQLite sqLite = new SQLite(Full_PhimDangChieu.this);
        rcv = findViewById(R.id.rcv_full_pdangc);
        adapter = new userFilmAdapter(Full_PhimDangChieu.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Full_PhimDangChieu.this, 2);
        rcv.setLayoutManager(gridLayoutManager);

        adapter.setData(Full_PhimDangChieu.this,sqLite.getListPhimDangChieu());
        rcv.setAdapter(adapter);
    }

}