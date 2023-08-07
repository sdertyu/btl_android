package com.example.simplereviewfilm.ListPhim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.simplereviewfilm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class adminActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<userFilm> lst;

    private userFilmAdapterQLY adapterQLY;
    private SQLite sqLite;

    private FloatingActionButton FABtn;

    private boolean b= false;

    public adminActivity(){}
    public void admin(List<userFilm> lst){
        this.lst = lst;
        adapterQLY.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(adminActivity.this, "fgsg", Toast.LENGTH_SHORT).show();
        lst = sqLite.getALLPhim();
        adapterQLY.setData(adminActivity.this, lst);
        adapterQLY.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        FABtn = findViewById(R.id.flBtn_Them);
        sqLite = new SQLite(adminActivity.this);

        FABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminActivity.this, QlyPhim.class);
                intent.putExtra("chucNang", "them");
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.rcv_sua);
        getRCV();
    }

    public void getRCV()
    {
        lst = sqLite.getALLPhim();

        Collections.sort(lst, new Comparator<userFilm>() {
            @Override
            public int compare(userFilm o1, userFilm o2) {
                return o2.getsNgayChieu().compareTo(o1.getsNgayChieu());
            }
        });

        adapterQLY = new userFilmAdapterQLY(adminActivity.this);
        adapterQLY.setData(adminActivity.this, lst);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(adminActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapterQLY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_search, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search_ac).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterQLY.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterQLY.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}