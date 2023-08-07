package com.example.simplereviewfilm.ListPhim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simplereviewfilm.R;

public class dangKy extends AppCompatActivity {

    private Button btn_dnn, btn_dangKy;
    private EditText edt_Hoten, edt_sdt, edt_email, edt_matKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        init();
        CLickDangNhapNgay();
        ClickDangKy();

    }

    public void init(){
        btn_dnn = findViewById(R.id.btn_dnNgay);
        btn_dangKy = findViewById(R.id.btn_dangKyTK);
        edt_Hoten = findViewById(R.id.edt_HoTen);
        edt_email = findViewById(R.id.edt_Email);
        edt_matKhau = findViewById(R.id.edt_matKhau);
        edt_sdt = findViewById(R.id.edt_SDT);
    }

    public void CLickDangNhapNgay(){
        btn_dnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ClickDangKy() {
        btn_dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_Hoten.getText().toString().compareTo("") == 0
                    || edt_email.getText().toString().compareTo("") == 0
                    || edt_sdt.getText().toString().compareTo("") == 0
                    || edt_matKhau.getText().toString().compareTo("") == 0){
                    Toast.makeText(dangKy.this, "Bạn cần nhập đầy đủ thông tin!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    SQLite sql = new SQLite(dangKy.this);
                    sql.createTblTaiKhoan(sql.getWritableDatabase());
//                    sql.querryData("Create table IF NOT EXISTS tblTaiKhoan(\n" +
//                            "\tsSdt nvarchar(255) primary key,\n" +
//                            "\tsMatKhau text,\n" +
//                            "\tsEmail text,\n" +
//                            "\tsHoTen text\n" +
//                            ")");

//                    sql.querryData("INSERT INTO tblTaiKhoan values ('"+ edt_sdt.getText().toString()+ "', '"+ edt_matKhau.getText().toString()+"', '"+edt_Hoten.getText().toString()+"', '" +
//                            edt_email.getText().toString()+"')");
//                    Log.e("ff","INSERT INTO tblTaiKhoan values ('"+ edt_sdt.getText().toString()+ "', '"+ edt_matKhau.getText().toString()+"', '"+edt_Hoten.getText().toString()+"', '" +
//                            edt_email.getText().toString()+"')");
                    ContentValues values = new ContentValues();
                    values.put("sSdt", edt_sdt.getText().toString());
                    values.put("sMatKhau", edt_matKhau.getText().toString());
                    values.put("sHoTen", edt_Hoten.getText().toString());
                    values.put("sEmail", edt_email.getText().toString());
                    long rs = sql.getWritableDatabase().insert("tblTaiKhoan",null,values);
                    Toast.makeText(dangKy.this,"Đăng ký thanh công!!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}