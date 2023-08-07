package com.example.simplereviewfilm.ListPhim;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.simplereviewfilm.R;

public class DangNhap extends AppCompatActivity {
    private Button btn_dangky,btn_dangNhap;
    EditText edt_sdtDN, edt_mkDN;

    mSharedPreferences sharedPreferences;

    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        btn_dangky = findViewById(R.id.btn_dangky);
        btn_dangNhap = findViewById(R.id.btn_dangnhap);
        edt_sdtDN = findViewById(R.id.edt_sdtDN);
        edt_mkDN = findViewById(R.id.edt_matKhauDN);
        setBtn_dangky();
        setBtn_dangNhap();
    }


    public void init(){
        btn_dangky = findViewById(R.id.btn_dangky);
    }

    public void setBtn_dangky(){
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, dangKy.class);
                startActivity(intent);

            }

        });
    }

    public void setBtn_dangNhap(){
        btn_dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                SQLite sqLite = new SQLite(DangNhap.this);
                boolean dd = false;

                Cursor cursor = sqLite.getData("SELECT * FROM tblTaiKhoan");
                if(cursor.moveToFirst())
                {
                    do {
                        if(edt_sdtDN.getText().toString().compareTo(cursor.getString(0)) == 0
                                && edt_mkDN.getText().toString().compareTo(cursor.getString(1))==0){
                            dd=true;

                            Toast.makeText(DangNhap.this, "Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
//                            Bundle bundle = new Bundle();
//                            userTK utk = new userTK(cursor.getString(0),
//                                    cursor.getString(1), cursor.getString(2), cursor.getString(3));
//                            bundle.putSerializable("userTK",utk);
//                            intent.putExtra("userTK",utk);
//                            setResult(Activity.RESULT_OK,intent);
                            sharedPreferences = new mSharedPreferences(DangNhap.this);
                            sharedPreferences.putString("sdt", cursor.getString(0));
                            sharedPreferences.putString("matKhau",cursor.getString(1));
                            sharedPreferences.putString("email", cursor.getString(2));
                            sharedPreferences.putString("hoTen",cursor.getString(3));
                            finish();
                        }
                    }while (cursor.moveToNext());
                }

                if(dd == false){
                    Toast.makeText(DangNhap.this, "Tài khoản hoặc mật khẩu không chính xác!",Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}

// uuuu uuuu uu uuuuu uuuu
// ooo ooo