package com.example.simplereviewfilm.ListPhim;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplereviewfilm.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class QlyPhim extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private EditText edt_tenPhim, edt_ngayChieu, edt_doTuoi, edt_thoiLuong, edt_dienVien, edt_daoDien, edt_theLoai, edt_gioiThieu, edt_loaiPhim, edt_id;
    private ImageView img;
    private userFilm user;

    private Button btnCapNhat, btnXoa, btnThem;

    private SQLite sqLite;

    public QlyPhim() {
    }

    private static final int PICK_IMAGE_REQUEST = 1;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qly_phim);


        edt_ngayChieu = findViewById(R.id.edt_ngayChieu);
        edt_thoiLuong = findViewById(R.id.edt_thoiLuong);
        edt_daoDien = findViewById(R.id.edt_daoDien);
        edt_theLoai = findViewById(R.id.edt_theLoai);
        edt_gioiThieu = findViewById(R.id.edt_gioiThieu);
        edt_doTuoi = findViewById(R.id.edt_doTuoi);
        edt_dienVien = findViewById(R.id.edt_dienVien);
        edt_tenPhim = findViewById(R.id.edt_TenPhim);
        edt_id = findViewById(R.id.edt_id);
        edt_loaiPhim = findViewById(R.id.edt_loaiPhim);
        img = findViewById(R.id.anhCTQL);

        btnThem = findViewById(R.id.btn_themPhim);
        btnCapNhat = findViewById(R.id.btn_capNhat);
        btnXoa = findViewById(R.id.btn_XoaPhim);
        btnThem.setEnabled(false);
        sqLite = new SQLite(QlyPhim.this);

        Intent intent = getIntent();

        if (intent.getStringExtra("chucNang").compareTo("them") == 0) {
            edt_ngayChieu.setText("");
            edt_doTuoi.setText("");
            edt_thoiLuong.setText("");
            edt_dienVien.setText("");
            edt_daoDien.setText("");
            edt_theLoai.setText("");
            edt_gioiThieu.setText("");
            edt_tenPhim.setText("");
            btnThem.setVisibility(View.VISIBLE);
            btnCapNhat.setVisibility(View.INVISIBLE);
            btnXoa.setVisibility(View.INVISIBLE);
            img.setOnClickListener(QlyPhim.this);
            btnThem.setOnClickListener(QlyPhim.this);
        } else {
            btnThem.setVisibility(View.INVISIBLE);
            btnCapNhat.setVisibility(View.VISIBLE);
            btnXoa.setVisibility(View.VISIBLE);

            Bundle bundle = intent.getExtras();
            user = (userFilm) bundle.getSerializable("userFilm");
            edt_ngayChieu.setText(user.getsNgayChieu());
            edt_doTuoi.setText(user.getsDoTuoi());
            edt_thoiLuong.setText(user.getsThoiLuong());
            edt_dienVien.setText(user.getsDienVien());
            edt_daoDien.setText(user.getsDaoDien());
            edt_theLoai.setText(user.getsTheLoai());
            edt_gioiThieu.setText(user.getsGioiThieu());
            edt_tenPhim.setText(user.getTenPhim());
            edt_id.setText(user.getIdPhim());
            edt_loaiPhim.setText(user.getsLoaiPhim());

            byte[] hinhanh = user.getImgSrc();

            Bitmap bitmap1 = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            img.setImageBitmap(bitmap1);
            btnXoa.setOnClickListener(QlyPhim.this);
            img.setOnClickListener(QlyPhim.this);
            btnCapNhat.setOnClickListener(QlyPhim.this);
        }

//        if(edt_id.getText().toString().compareTo("") == 0 ||
//                edt_tenPhim.getText().toString().compareTo("") == 0 ||
//                edt_loaiPhim.getText().toString().compareTo("") == 0 ||
//                edt_gioiThieu.getText().toString().compareTo("") == 0 ||
//                edt_theLoai.getText().toString().compareTo("") == 0 ||
//                edt_doTuoi.getText().toString().compareTo("") == 0 ||
//                edt_ngayChieu.getText().toString().compareTo("") == 0 ||
//                edt_daoDien.getText().toString().compareTo("") == 0 ||
//                edt_dienVien.getText().toString().compareTo("") == 0 ||
//                bitmap == null )

        edt_id.addTextChangedListener(QlyPhim.this);
        edt_tenPhim.addTextChangedListener(QlyPhim.this);
        edt_theLoai.addTextChangedListener(QlyPhim.this);
        edt_doTuoi.addTextChangedListener(QlyPhim.this);
        edt_daoDien.addTextChangedListener(QlyPhim.this);
        edt_dienVien.addTextChangedListener(QlyPhim.this);
        edt_ngayChieu.addTextChangedListener(QlyPhim.this);
        edt_loaiPhim.addTextChangedListener(QlyPhim.this);
        edt_gioiThieu.addTextChangedListener(QlyPhim.this);
        edt_thoiLuong.addTextChangedListener(QlyPhim.this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void onClick(View v) {

        byte[] hinhanh;
        if (v.getId() == R.id.anhCTQL) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        }
        if (v.getId() == R.id.btn_XoaPhim) {
            sqLite.xoaPhim(user.getIdPhim());
            Toast.makeText(QlyPhim.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//            List<userFilm> lst = sqLite.getALLPhim();
//            ((adminActivity) QlyPhim.this).admin(lst);
            finish();
//            Intent intent = new Intent(QlyPhim.this, adminActivity.class);
        }

        if (v.getId() == R.id.btn_themPhim) {
            boolean check = true;
            if (bitmap == null) {
                Toast.makeText(QlyPhim.this, "Bạn chưa chọn ảnh", Toast.LENGTH_SHORT).show();
            } else {
                List<userFilm> listThu = sqLite.getALLPhim();
                for (userFilm user : listThu) {
                    if (user.getIdPhim().compareTo(edt_id.getText().toString()) == 0) {
                        Toast.makeText(QlyPhim.this, "ID phim đã tồn tại", Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                }

                if (check == true) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                    hinhanh = stream.toByteArray();

                    sqLite.INSERT_querryData(edt_id.getText().toString(), edt_tenPhim.getText().toString(), hinhanh, edt_ngayChieu.getText().toString(),
                            edt_thoiLuong.getText().toString(), edt_theLoai.getText().toString(), edt_daoDien.getText().toString(),
                            edt_doTuoi.getText().toString(), edt_dienVien.getText().toString(), edt_gioiThieu.getText().toString(), edt_loaiPhim.getText().toString());
                    Toast.makeText(QlyPhim.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
//            else{
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG,50,stream);
//                hinhanh = stream.toByteArray();
//
//                sqLite.INSERT_querryData(edt_id.getText().toString(), edt_tenPhim.getText().toString(), hinhanh , edt_ngayChieu.getText().toString(),
//                        edt_thoiLuong.getText().toString(), edt_theLoai.getText().toString(), edt_daoDien.getText().toString(),
//                        edt_doTuoi.getText().toString(), edt_dienVien.getText().toString(), edt_gioiThieu.getText().toString(), edt_loaiPhim.getText().toString());
//                Toast.makeText(QlyPhim.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//                finish();
//            }

        }

        if (v.getId() == R.id.btn_capNhat) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            hinhanh = stream.toByteArray();
            sqLite.UPDATE_querryData(edt_id.getText().toString(), edt_tenPhim.getText().toString(), hinhanh, edt_ngayChieu.getText().toString(),
                    edt_thoiLuong.getText().toString(), edt_theLoai.getText().toString(), edt_daoDien.getText().toString(),
                    edt_doTuoi.getText().toString(), edt_dienVien.getText().toString(), edt_gioiThieu.getText().toString(), edt_loaiPhim.getText().toString());
            finish();
        }
    }

    public byte[] anh(int anh) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), anh);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        return stream.toByteArray();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (edt_id.getText().toString().compareTo("") == 0 ||
                edt_tenPhim.getText().toString().compareTo("") == 0 ||
                edt_loaiPhim.getText().toString().compareTo("") == 0 ||
                edt_gioiThieu.getText().toString().compareTo("") == 0 ||
                edt_theLoai.getText().toString().compareTo("") == 0 ||
                edt_doTuoi.getText().toString().compareTo("") == 0 ||
                edt_ngayChieu.getText().toString().compareTo("") == 0 ||
                edt_daoDien.getText().toString().compareTo("") == 0 ||
                edt_dienVien.getText().toString().compareTo("") == 0 ||
                edt_thoiLuong.getText().toString().compareTo("") == 0) {
            btnThem.setEnabled(false);
        } else
            btnThem.setEnabled(true);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}