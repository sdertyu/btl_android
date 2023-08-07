package com.example.simplereviewfilm.ListPhim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplereviewfilm.R;

public class SuaThongTin extends AppCompatActivity {

    private LinearLayout layout;
    private EditText edtSuaHT, edtSuaEmail, edtSuaMaKhau, edtXacNhanMK;
    private TextView txtSuaSDT;
    private Button btnXacNhan, btnSuaTTTK;
    private mSharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin);

        layout = findViewById(R.id.layout_suaTT);
        edtSuaHT = findViewById(R.id.edt_suaHoTen);
        edtSuaEmail = findViewById(R.id.edt_suaEmail);
        edtSuaMaKhau = findViewById(R.id.edt_suaMatKhau);
        edtXacNhanMK = findViewById(R.id.edt_xacNhanMK);
        btnXacNhan = findViewById(R.id.btn_xacNhanMK);
        btnSuaTTTK = findViewById( R.id.btn_suaTTTK);
        txtSuaSDT = findViewById(R.id.txt_suaSDT);

        sharedPreferences = new mSharedPreferences(SuaThongTin.this);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtXacNhanMK.getText().toString().compareTo(sharedPreferences.getString("matKhau")) == 0){
                    layout.setVisibility(View.VISIBLE);
                    edtSuaHT.setText(sharedPreferences.getString("hoTen"));
                    edtSuaEmail.setText(sharedPreferences.getString("email"));
                    edtSuaMaKhau.setText(sharedPreferences.getString("matKhau"));
                    txtSuaSDT.setText(sharedPreferences.getString("sdt"));
                }
                else {
                    Toast.makeText(SuaThongTin.this, "Mật khẩu xác nhận không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSuaTTTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sharedPreferences.putString("sdt", );
                if(TextUtils.isEmpty(edtSuaHT.getText().toString()) ||
                        TextUtils.isEmpty(edtSuaEmail.getText().toString()) ||
                        TextUtils.isEmpty(edtSuaMaKhau.getText().toString()))
                {
                    Toast.makeText(SuaThongTin.this, "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    sharedPreferences.putString("matKhau",edtSuaMaKhau.getText().toString());
                    sharedPreferences.putString("email", edtSuaEmail.getText().toString());
                    sharedPreferences.putString("hoTen",edtSuaHT.getText().toString());
                    SQLite sqLite = new SQLite(SuaThongTin.this);
                    sqLite.updateTaiKhoan(sharedPreferences.getString("sdt"),
                            sharedPreferences.getString("hoTen"),
                            sharedPreferences.getString("email"),
                            sharedPreferences.getString("matKhau"));
                    finish();
                }
            }
        });


    }
}