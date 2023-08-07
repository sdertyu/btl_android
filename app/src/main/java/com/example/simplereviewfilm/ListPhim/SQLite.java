package com.example.simplereviewfilm.ListPhim;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {

    private Context context;
    public static final String dbName = "dboPhim";

    public SQLite(@Nullable Context context) {
        super(context,dbName,null,1);
        this.context=context;
    }



    //hàm không trả về dữ liệu
    public void querryData(String query){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(query);
    }

    public void INSERT_querryData(String idPhim, String tenPhim, byte[] anh, String ngayChieu, String thoiLuong,
                                  String TheLoai, String daoDien, String doTuoi, String dienVien, String GioiThieu,
                                  String loaiPhim) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO tblPhim values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,idPhim);
        statement.bindString(2,tenPhim);
        statement.bindBlob(3, anh);
        statement.bindString(4,ngayChieu);
        statement.bindString(5,thoiLuong);
        statement.bindString(6,TheLoai);
        statement.bindString(7,daoDien);
        statement.bindString(8,doTuoi);
        statement.bindString(9,dienVien);
        statement.bindString(10,GioiThieu);
        statement.bindString(11,loaiPhim);

        statement.executeInsert();
    }

    public void UPDATE_querryData(String idPhim, String tenPhim, byte[] anh, String ngayChieu, String thoiLuong,
                                  String TheLoai, String daoDien, String doTuoi, String dienVien, String GioiThieu,
                                  String loaiPhim) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE tblPhim set sTenPhim = ?, sNguonAnh = ?, dNgayChieu = ? ," +
                "sThoiLuong = ?, sTheLoai = ?, sDaoDien = ?, sDoTuoi = ?, sDienVien = ? ," +
                "sGioiThieu = ?, sTheLoai = ? where idPhim = ?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(11,idPhim);
        statement.bindString(1,tenPhim);
        statement.bindBlob(2, anh);
        statement.bindString(3,ngayChieu);
        statement.bindString(4,thoiLuong);
        statement.bindString(5,TheLoai);
        statement.bindString(6,daoDien);
        statement.bindString(7,doTuoi);
        statement.bindString(8,dienVien);
        statement.bindString(9,GioiThieu);
        statement.bindString(10,loaiPhim);

        statement.executeInsert();
    }
    //Hàm trả về dữ liệu
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTblPhim(db);
        createTblTaiKhoan(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTblPhim(SQLiteDatabase db)
    {
        db.execSQL("Create table IF NOT EXISTS tblPhim(\n" +
                "\n" +
                "\tidPhim nvarchar(255) primary key,\n" +
                "\tsTenPhim Text,\n" +
                "\tsNguonAnh BLOB,\n" +
                "\tdNgayChieu date,\n" +
                "\tsThoiLuong text,\n" +
                "\tsTheLoai text,\n" +
                "\tsDaoDien text,\n" +
                "\tsDoTuoi text,\n" +
                "\tsDienVien text,\n" +
                "\tsGioiThieu text,\n" +
                "\tsLoaiPhim text" +
                ")");
    }

    public void createTblPhimComment(SQLiteDatabase db)
    {
        db.execSQL("Create table IF NOT EXISTS tblPhimComment(\n" +
                "\tidCmt INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\tidPhim nvarchar (255),\n" +
                "\tsNoiDung text,\n" +
                "\tsSdt nvarchar(255),\n" +
                "\tCONSTRAINT FK_PCM_P FOREIGN KEY (idPhim) REFERENCES tblPhim(idPhim),\n" +
                "\tCONSTRAINT FK_PCM_TK FOREIGN KEY (sSdt) REFERENCES tblTaiKhoan(sSdt)\n" +
                ")");
    }

    public void createTblTaiKhoan(SQLiteDatabase db){
        db.execSQL("Create table IF NOT EXISTS tblTaiKhoan(\n" +
                "\tsSdt nvarchar(255) primary key,\n" +
                "\tsMatKhau text,\n" +
                "\tsEmail text,\n" +
                "\tsHoTen text\n" +
                ")");
    }

    public List<userFilm> getALLPhim() {
        List<userFilm> lst = new ArrayList<>();
        Cursor data = getReadableDatabase().rawQuery("SELECT * FROM tblPhim", null);
        if (data.moveToFirst()) {
            do {
                lst.add(new userFilm(data.getString(0), data.getString(1),
                        data.getBlob(2), data.getString(3),
                        data.getString(4), data.getString(5),
                        data.getString(6), data.getString(7),
                        data.getString(8), data.getString(9),
                        data.getString(10)));
            } while (data.moveToNext());
        }
        return lst;
    }

    public List<userFilm> getListPhimSapChieu()
    {
        List<userFilm> lst = new ArrayList<>();
        Cursor data = getReadableDatabase().rawQuery("SELECT * FROM tblPhim where sLoaiPhim = 'Sắp chiếu'", null);
        if(data.moveToFirst())
        {
            do {
                lst.add(new userFilm(data.getString(0),data.getString(1),
                        data.getBlob(2), data.getString(3),
                        data.getString(4),data.getString(5),
                        data.getString(6),data.getString(7),
                        data.getString(8),data.getString(9),
                        data.getString(10)));
            }while (data.moveToNext());
        }
        return lst;
    }

    public List<userFilm> getListPhimDangChieu()
    {
        List<userFilm> lst = new ArrayList<>();
        Cursor data = getReadableDatabase().rawQuery("SELECT * FROM tblPhim where sLoaiPhim = 'Đang chiếu'", null);
        if(data.moveToFirst())
        {
            do {
                lst.add(new userFilm(data.getString(0),data.getString(1),
                        data.getBlob(2), data.getString(3),
                        data.getString(4),data.getString(5),
                        data.getString(6),data.getString(7),
                        data.getString(8),data.getString(9),
                        data.getString(10)));
            }while (data.moveToNext());
        }
        return lst;
    }
    public List<userFilm> getListSearch(String search){
        List<userFilm> lst = new ArrayList<>();
        Cursor data = getReadableDatabase().rawQuery("SELECT * FROM tblPhim where sTenPhim = '"+search+"'", null);
        if(data.moveToFirst())
        {
            do {
                lst.add(new userFilm(data.getString(0),data.getString(1),
                        data.getBlob(2), data.getString(3),
                        data.getString(4),data.getString(5),
                        data.getString(6),data.getString(7),
                        data.getString(8),data.getString(9),
                        data.getString(10)));
            }while (data.moveToNext());
        }
        return lst;

    }

    public List<userFilm> getListTop3PhimDangChieu()
    {
        List<userFilm> lst = new ArrayList<>();
        Cursor data = getReadableDatabase().rawQuery("SELECT * FROM tblPhim where sLoaiPhim = 'Đang chiếu' LIMIT 3", null);
        if(data.moveToFirst())
        {
            do {
                lst.add(new userFilm(data.getString(0),data.getString(1),
                        data.getBlob(2), data.getString(3),
                        data.getString(4),data.getString(5),
                        data.getString(6),data.getString(7),
                        data.getString(8),data.getString(9),
                        data.getString(10)));
            }while (data.moveToNext());
        }
        return lst;
    }

    public List<userFilm> getListTop3PhimSapChieu()
    {
        List<userFilm> lst = new ArrayList<>();
        Cursor data = getReadableDatabase().rawQuery("SELECT * FROM tblPhim where sLoaiPhim = 'Sắp chiếu' LIMIT 3", null);
        if(data.moveToFirst())
        {
            do {
                lst.add(new userFilm(data.getString(0),data.getString(1),
                        data.getBlob(2), data.getString(3),
                        data.getString(4),data.getString(5),
                        data.getString(6),data.getString(7),
                        data.getString(8),data.getString(9),
                        data.getString(10)));
            }while (data.moveToNext());
        }
        return lst;
    }

    public void updateTaiKhoan(String sdt, String hoten, String email, String matKhau){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("Update tblTaiKhoan set sHoTen = '"+hoten+"', sEmail = '"+email+"', sMatKhau = '"+matKhau+"' where sSdt = '"+sdt+"'");
    }

    public void xoaPhim(String idPhim){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("Delete from tblPhim where idPhim = '"+idPhim+"'");
//        List<userFilm> lst = getALLPhim();
//        ((adminActivity) context).admin(lst);
    }

//    String dateString = "2021-08-31";
//    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//try {
//        Date date = dateFormat.parse(dateString);
//        String formattedDate = dateFormat.format(date);
//        System.out.println(formattedDate); // Kết quả: 31/08/2021
//    } catch (ParseException e) {
//        e.printStackTrace();
//    }

    public List<userFilm> getListPhimTheoNgayChieu(String date){
        List<userFilm> lst = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from tblPhim", null);
        if(cursor.moveToFirst())
        {
            do{
//                Toast.makeText(context, cursor.getString(3), Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, date, Toast.LENGTH_SHORT).show();

                if(cursor.getString(3).compareTo(date) == 0)
                {
                    lst.add(new userFilm(cursor.getString(0),cursor.getString(1),
                            cursor.getBlob(2), cursor.getString(3),
                            cursor.getString(4),cursor.getString(5),
                            cursor.getString(6),cursor.getString(7),
                            cursor.getString(8),cursor.getString(9),
                            cursor.getString(10)));

                }

            }while (cursor.moveToNext());
        }

        return lst;
    }
}
