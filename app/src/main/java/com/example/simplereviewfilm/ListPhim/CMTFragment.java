package com.example.simplereviewfilm.ListPhim;


import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.simplereviewfilm.R;

import java.util.ArrayList;
import java.util.List;

public class CMTFragment extends Fragment {

//    String thongtin[] ={"keke", "haha", "clm", "huhu","jiji"};
//    int image[] = {R.drawable.anh2, R.drawable.anh1, R.drawable.anh3, R.drawable.anh4,R.drawable.anh5};
    private TextView txt_tenPhim;
    private ArrayList<userCMT> mylist;
    private userCmtAdapter myadapter;

    private ListView mlist;

    private View view;

    private EditText edt_nhapCmt;

    private mSharedPreferences sharedPreferences;

    private Button btnCMT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cmt, container, false);
        Bundle bundle = getArguments();
        userFilm user = (userFilm) bundle.getSerializable("nhan");
        txt_tenPhim = view.findViewById(R.id.txtTenPhim);
        txt_tenPhim.setText(user.getTenPhim());


        btnCMT = view.findViewById(R.id.btnCMT);
        edt_nhapCmt = view.findViewById(R.id.edt_nhapCmt);
        mlist = view.findViewById(R.id.list_comment);
        sharedPreferences = new mSharedPreferences(getActivity());
        SQLite sqLite = new SQLite(getActivity());
        sqLite.createTblPhimComment(sqLite.getWritableDatabase());
        mylist = new ArrayList<userCMT>();



        Cursor cursor = sqLite.getData("Select t.sHoTen, p.sNoiDung from tblPhimComment as p\n" +
                "inner join tblTaiKhoan as t on t.sSdt = p.sSdt\n" +
                "where p.idPhim = '"+user.getIdPhim()+"'");
        Log.e("hfhfh","Select * from tblPhimComment where idPhim = '"+user.getIdPhim()+"'");
        if(cursor.moveToFirst()){
            do {
                mylist.add(new userCMT(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
//        myadapter.notifyDataSetChanged();
//        mylist.add(new userCMT("ngoo minh phuong", "tam duoc"));

        myadapter = new userCmtAdapter(getActivity(),R.layout.item_comment,mylist);

        mlist.setAdapter(myadapter);

        btnCMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edt_nhapCmt.getText().toString()))
                {
                    Toast.makeText(getActivity(),"Bạn cần nhập comment của bạn",Toast.LENGTH_SHORT).show();
                }
                else {
                    sqLite.querryData("INSERT INTO tblPhimComment values (null,'"+user.getIdPhim()+"', '"+edt_nhapCmt.getText().toString()+"', '"+sharedPreferences.getString("sdt")+"')");
                    Toast.makeText(getActivity(),"Comment thành công!!", Toast.LENGTH_SHORT).show();
                    edt_nhapCmt.setText("");

                    Cursor cursor1 = sqLite.getData("Select t.sHoTen, p.sNoiDung from tblPhimComment as p\n" +
                            "inner join tblTaiKhoan as t on t.sSdt = p.sSdt\n" +
                            "ORDER BY p.idCmt DESC LIMIT 1");
//                    Cursor cursor1 =sqLite.getData("SELECT * from tblPhimComment ORDER BY idCmt DESC LIMIT 1");
                    if(cursor1.moveToFirst()){
                        do {
                            mylist.add(new userCMT(cursor1.getString(0),cursor1.getString(1)));
                        }while (cursor1.moveToNext());
                    }
                    myadapter.notifyDataSetChanged();
                }
            }
        });

        return view;


    }

}