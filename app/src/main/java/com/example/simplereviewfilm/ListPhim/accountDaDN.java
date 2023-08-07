package com.example.simplereviewfilm.ListPhim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplereviewfilm.R;

public class accountDaDN extends Fragment {

    Button btnSuaTT,btnSangDN,btnDangXuat, btnQuanLyPhim;
    TextView txt_ten;
    private userTK user;
    String sdt ="";

    mSharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_account_da_dn,container,false);
        btnSuaTT = view.findViewById(R.id.btn_suaTT);

        sharedPreferences = new mSharedPreferences(getActivity());


        btnSangDN = view.findViewById(R.id.btn_sangDangNhap);
        btnDangXuat = view.findViewById(R.id.btn_DangXuat);
        btnSuaTT = view.findViewById(R.id.btn_suaTT);
        txt_ten = view.findViewById(R.id.txt_tenTK);
        btnQuanLyPhim = view.findViewById(R.id.btn_qly);

        btnSuaTT.setVisibility(View.INVISIBLE);
        btnDangXuat.setVisibility(View.INVISIBLE);
        btnQuanLyPhim.setVisibility(View.INVISIBLE);

        btnSangDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DangNhap.class);
                startActivity(intent);
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSuaTT.setVisibility(View.INVISIBLE);
                btnDangXuat.setVisibility(View.INVISIBLE);
                btnSangDN.setVisibility(View.VISIBLE);
                btnQuanLyPhim.setVisibility(View.INVISIBLE);
                user = null;
                txt_ten.setText("Bạn chưa đăng nhập");
                Toast.makeText(getActivity(),"Đăng xuất thành công",Toast.LENGTH_SHORT).show();
                sharedPreferences.clearShared();
            }
        });

        btnSuaTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SuaThongTin.class);
                startActivity(intent);
            }
        });

        btnQuanLyPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), adminActivity.class);
                startActivity(intent);
            }
        });


        return  view;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode,resultCode,data);
//        if(requestCode == 1){
////            Bundle bundle = getArguments();
//            user = (userTK) data.getSerializableExtra("userTK");
////            user = (userTK) bundle.getSerializable("nhanTK");
//            if(user.getSdt().compareTo("") != 0)
//            {
//                btnSangDN.setVisibility(View.INVISIBLE);
//                btnDangXuat.setVisibility(View.VISIBLE);
//                btnSuaTT.setVisibility(View.VISIBLE);
//                txt_ten.setText(user.getHoten());
//            }
//        }
//
//    }


    @Override
    public void onResume() {
        super.onResume();
        sdt = sharedPreferences.getString("sdt");
        String mk = sharedPreferences.getString("matKhau");
        String hoTen = sharedPreferences.getString("hoTen");
        if(TextUtils.isEmpty(sdt)){
            txt_ten.setText("Bạn chưa đăng nhập");
            btnSangDN.setVisibility(View.VISIBLE);
            btnDangXuat.setVisibility(View.INVISIBLE);
            btnSuaTT.setVisibility(View.INVISIBLE);
        } else if (sdt.compareTo("admin") == 0) {
            txt_ten.setText(hoTen);
            btnSangDN.setVisibility(View.INVISIBLE);
            btnDangXuat.setVisibility(View.VISIBLE);
            btnSuaTT.setVisibility(View.VISIBLE);
            btnQuanLyPhim.setVisibility(View.VISIBLE);
        } else
        {
            txt_ten.setText(hoTen);
            btnQuanLyPhim.setVisibility(View.INVISIBLE);
            btnSangDN.setVisibility(View.INVISIBLE);
            btnDangXuat.setVisibility(View.VISIBLE);
            btnSuaTT.setVisibility(View.VISIBLE);
        }
    }

    public void setData(){

//        Toast.makeText(getActivity(),"account", Toast.LENGTH_SHORT).show();



    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_account_da_dn);
//
//
//    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}