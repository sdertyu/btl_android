package com.example.simplereviewfilm.ListPhim;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplereviewfilm.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PrimitiveIterator;

public class HomeFragment extends Fragment {

    public static SQLite film;
    private RecyclerView Rcv1, Rcv2;
    private userFilmAdapter adapterPSC, adapterPDC;

    private List<userFilm> lstSC, lstDangC;
    private Button btn_xtp_dang_c, btn_xtpsc;

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
//        ImageButton imageButton = view.findViewById(R.id.thu);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),ChiTietPhim.class);
//                startActivity(intent);
//            }
//        });
//        sharedPreferences = getActivity().getSharedPreferences(mshareTK, Activity.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sharedPreferences.edit();
//        edit.putString("sdt", "");
//        edit.commit();

        Rcv1 = view.findViewById(R.id.rcv_psc);
        Rcv2 = view.findViewById(R.id.rcv_pdac);
        btn_xtpsc = view.findViewById(R.id.btn_full_psc);
        btn_xtp_dang_c = view.findViewById(R.id.btn_full_f_dang_c);
        film = new SQLite(getActivity());

        lstSC = new ArrayList<>();
        lstDangC = new ArrayList<>();
//
        adapterPSC = new userFilmAdapter(getActivity());
        adapterPDC = new userFilmAdapter(getActivity());
//
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        Rcv2.setLayoutManager(linearLayoutManager2);
        Rcv1.setLayoutManager(linearLayoutManager1);

        sqlFilm();

        lstSC = film.getListTop3PhimSapChieu();
        lstDangC =film.getListTop3PhimDangChieu();

        adapterPSC.setData(getActivity(),lstSC);
        adapterPDC.setData(getActivity(),lstDangC);
        Rcv1.setAdapter(adapterPSC);
        Rcv2.setAdapter(adapterPDC);
        XemThemFimSapChieu();
        XemThemFimDangChieu();
//

        return view;
    }

//    private List<userFilm> getList() {
//        List<userFilm> lst = new ArrayList<>();
//        lst.add(new userFilm(R.drawable.anh5,"Phim 1"));
//        lst.add(new userFilm(R.drawable.anh3,"Phim đàih jdkfh jdfks jkdsf kjdssjdfkdsf jsdkhf kjdshf k"));
//        lst.add(new userFilm(R.drawable.anh4,"Phim 3"));
//        lst.add(new userFilm(R.drawable.anh1,"Phim 4"));
//        lst.add(new userFilm(R.drawable.anh2,"Phim 5"));
//        return lst;
//    }

    public void sqlFilm(){

//        int res2 = R.drawable.anh2;
//        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),res2);
//        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
//        bitmap2.compress(Bitmap.CompressFormat.JPEG,50,stream2);
//        byte[] anh2 = stream2.toByteArray();
//        String adu = haha.toString();
//        Log.e("dd",adu);




//        film.querryData("Create table IF NOT EXISTS tblPhim(\n" +
//                "\n" +
//                "\tidPhim nvarchar(255) primary key,\n" +
//                "\tsTenPhim Text,\n" +
//                "\tsNguonAnh text,\n" +
//                "\tdNgayChieu date,\n" +
//                "\tsThoiLuong text,\n" +
//                "\tsTheLoai text,\n" +
//                "\tsDaoDien text,\n" +
//                "\tsDoTuoi text,\n" +
//                "\tsDienVien text,\n" +
//                "\tsGioiThieu text,\n" +
//                "\tsLoaiPhim text" +
//                ")");

//        film.querryData("Create table IF NOT EXISTS tblPhimComment(\n" +
//                "\tidPhim nvarchar (255),\n" +
//                "\tsNoiDung text,\n" +
//                "\tsSdt nvarchar(255),\n" +
//                "\tCONSTRAINT FK_PCM_P FOREIGN KEY (idPhim) REFERENCES tblPhim(idPhim),\n" +
//                "\tCONSTRAINT FK_PCM_TK FOREIGN KEY (sSdt) REFERENCES tblTaiKhoan(sSdt)\n" +
//                ")");
//
//        film.querryData("Create table IF NOT EXISTS tblTaiKhoan(\n" +
//                "\tsSdt nvarchar(255) primary key,\n" +
//                "\tsMatKhau text,\n" +
//                "\tsEmail text,\n" +
//                "\tsHoTen text\n" +
//                ")");

//        film.INSERT_querryData("P1","Transformer: Quái thú trỗi dậy", anh(R.drawable.transformer) , "09/06/2023",
//                "138 phút", "Giả tưởng", "Bay", "C16", "Optimus Prime, Bumble Bee",
//                "Bộ phim kể về thời kỳ khởi nguyên của các autobot với sự tham gia của đội quân Maximal để chống " +
//                        "lại Omicron chiếm đoạt chìa khóa không gian di chuyen giữa các vũ trụ để nuốt chửng các hành tinh",
//                "Đang chiếu");
//
//        film.INSERT_querryData("P2","Xứ sở các nguyên tố", anh(R.drawable.elemental) , "23/06/2023",
//                "109 phút", "Hoạt hình, Gia đình", "Peter Sohn", "P", "Leah Lewis, Mamoudou Athie",
//                "Xứ Sở Các Nguyên Tố từ Disney và Pixar lấy bối cảnh tại thành phố các nguyên tố, nơi lửa, nước, đất và không khí " +
//                        "cùng chung sống với nhau. Câu chuyện xoay quanh nhân vật Ember, một cô nàng cá tính, thông minh, mạnh mẽ và đầy sức hút. " +
//                        "Tuy nhiên, mối quan hệ của cô với Wade - một anh chàng hài hước, luôn thuận thế đẩy dòng - khiến Ember cảm thấy ngờ vực với " +
//                        "thế giới này. Được đạo diễn bởi Peter Sohn, sản xuất bởi Denise Ream, và lồng tiếng bởi Leah Lewis (Ember) và Mamoudou Athie (Wade)",
//                "Đang chiếu");
//
//        film.INSERT_querryData("P3","Người Nhện: Du Hành Vũ Trụ Nhện", anh(R.drawable.nguoinhen) , "01/06/2023",
//                "140 phút", "Hành động, Phiêu lưu", "Joaquim Dos Santos, Justin K. Thompson, Kemp Powers", "C13", "Shameik Moore",
//                "Miles Morales trở lại trong chương tiếp theo của câu chuyện Người Nhện, một cuộc phiêu lưu sử thi sẽ đưa Người Nhện thân thiện, toàn thời gian " +
//                        "của Brooklyn đi khắp Đa vũ trụ để hợp lực với Gwen Stacy và một nhóm Người Nhện mới để đối đầu với một nhân vật phản diện mạnh hơn bất cứ thứ gì họ từng gặp.",
//                "Đang chiếu");
////
//        film.INSERT_querryData("P4","Phim Điện Ảnh Doraemon: Nobita Và Vùng Đất Lý Tưởng Trên Bầu Trời", anh(R.drawable.doraemon) , "26/05/2023",
//                "108 phút", "Hoạt hình, Phiêu lưu", "Takumi Doyama", "P", "Wasabi Mizuta, Megumi Oohara, Yumi Kakazu, Subaru Kimura, Tomokazu Seki",
//                "câu chuyện khi Nobita tìm thấy một hòn đảo hình lưỡi liềm trên trời mây. Ở nơi đó, tất cả đều hoàn hảo… đến mức cậu nhóc Nobita mê ngủ ngày cũng " +
//                        "có thể trở thành một thần đồng toán học, một siêu sao thể thao. Cả hội Doraemon cùng sử dụng một món bảo bối độc đáo chưa từng xuất hiện trước đây " +
//                        "để đến với vương quốc tuyệt vời này. Cùng với những người bạn ở đây, đặc biệt là chàng robot mèo Sonya, cả hội đã có chuyến hành trình tới vương quốc " +
//                        "trên mây tuyệt vời… cho đến khi những bí mật đằng sau vùng đất lý tưởng này được hé lộ.",
//                "Đang chiếu");
////
//        film.INSERT_querryData("P5","Nhiệm Vụ: Bất Khả Thi Nghiệp Báo - Phần Một", anh(R.drawable.mission) , "14/07/2023",
//                "164 phút", "Hành động, Phiêu lưu", "Christopher McQuarrie", "C16", "Tom Cruise, Vanessa Kirby, Indira Varma, Rebecca Ferguson",
//                "Phần phim thứ 7 của loạt phim Nhiệm Vụ Bất Khả Thi",
//                "Sắp chiếu");
////
//        film.INSERT_querryData("P6","Tà Chú Cấm", anh(R.drawable.tachucam) , "23/06/2023",
//                "122 phút", "Kinh dị, Tâm lý", "Sophon Sakdaphisit", "C18", "Nittha Jirayungyurn, Sukollawat Kanaros, Penpak Sirikul",
//                "Phim kể về cặp vợ chồng Ning, Kwin và cô con gái 7 tuổi với tên gọi Ing. Vì khó khăn về tài chính, hai vợ chồng quyết định cho thuê ngôi nhà đang " +
//                        "ở và chuyển đến một căn hộ chung cư giá rẻ để sinh sống. Sau khi những người thuê nhà chuyển đến, Ning nhận thấy chồng bắt đầu có những hành vi bất thường. " +
//                        "Anh ta trở nên bí mật và thường biến mất khỏi căn hộ và lúc 4 giờ sáng. Kwin thậm chí còn có một hình xăm kỳ lạ ở ngực tựa như ký hiệu đặc biệt của một hội tà " +
//                        "giáo bí ẩn. Khi hành vi của chồng ngày càng trở nên đáng lo ngại, cũng là lúc Ning nhận ra cô con gái của mình đang gặp hiểm nguy. Bí mật nào đang được che giấu " +
//                        "trong ngôi nhà cho thuê của họ?",
//                "Đang chiếu");
////
//        film.INSERT_querryData("P7","Flash", anh(R.drawable.flash) , "16/07/2023",
//                "144 phút", "Hành động, Phiêu lưu", "Andy Muschietti", "C16", "Ben Affleck, Michael Shannon, Michael Keaton",
//                "Được cho là về cốt truyện của truyện tranh Flashpoint, nơi Barry Allen (còn gọi là The Flash) thức dậy trong một thế giới không thể nhận ra. " +
//                        "Anh ta không còn sức mạnh siêu tốc nữa và Justice League không tồn tại.",
//                "Sắp chiếu");
////
//        film.INSERT_querryData("P8","Quỷ Quyệt: Cửa Đỏ Vô Định", anh(R.drawable.quyquyet) , "14/07/2023",
//                "107 phút", "Kinh dị, Hồi Hộp", "Patrick Wilson", "C16", "Ty Simpkins, Patrick Wilson, Rose Byrne",
//                "\"Quỷ quyệt: Cửa Đỏ Vô Định\" là bộ phim kinh dị siêu nhiên của Mỹ ra mắt năm 2023 được đạo diễn bởi Patrick Wilson, đây đồng " +
//                        "thời cũng là bộ phim đạo diễn đầu tay của anh, với kịch bản dựa trên câu chuyện từ nhà sáng tạo của loạt phim Leigh Whannell và " +
//                        "được chấp bút bởi Scott Teems.",
//                "Sắp chiếu");
////
//        film.INSERT_querryData("P9","Phim Điện Ảnh Thám Tử Lừng Danh Conan: Tàu Ngầm Sắt Màu Đen", anh(R.drawable.conan) , "21/07/2023",
//                "110 phút", "Hoạt hình, Trinh thám", "Yuzuru Tachikawa", "C16", "Ayumu Murase, Hayashibara Megumi, Yukitoshi Hori, ...",
//                "\"Thám Tử Lừng Danh Conan: Tàu Ngầm Sắt Màu Đen\" lấy bối cảnh tại Pacific Buoy - một trụ sở hàng hải của Interpol có nhiệm vụ kết nối các camera " +
//                        "an ninh trên toàn thế giới. Nhóm của Conan, theo lời mời của Sonoko, cũng đến Hachijojima để xem cá voi. Tại đây, Conan nhận được thông tin về một nhân " +
//                        "viên Europol bị ám sát. Cùng với đó, tính mạng Haibara bị đe dọa, phải chăng thân phận của cô đã bị bại lộ trước Gin…",
//                "Sắp chiếu");
//
//        film.INSERT_querryData("P1","Transformer: Quái thú trỗi dậy", anh1 , "04/27/2023",
//                "138 phút", "Giả tưởng", "Bay", "C16", "Optimus Prime, Bumble Bee",
//                "Bộ phim kể về thời kỳ khởi nguyên của các autobot với sự tham gia của đội quân Maximal để chống " +
//                        "lại Omicron chiếm đoạt chìa khóa không gian di chuyen giữa các vũ trụ để nuốt chửng các hành tinh",
//                "Đang chiếu");

//        film.querryData("INSERT INTO tblPhim values ('P1','Transformer: Quái thú trỗi dậy', '"+anh1+"', '04/27/2023', '138 phút', " +
//                              "'Giả tưởng', 'Bay', 'C16', 'Optimus Prime, Bumble Bee', 'Bộ phim kể về thời kỳ khởi nguyên của các autobot với sự tham gia của đội quân" +
//                "Maximal để chống lại Omicron chiếm đoạt chìa khóa không gian di chuyen giữa các vũ trụ để nuốt chửng các hành tinh','Đang chiếu')");
////
//        film.querryData("INSERT INTO tblPhim values ('P2','Xứ sở các nguyên tố', '"+anh1+"', '06/12/2023', '119 phút', " +
//                "'Hoạt hình', 'Peter Sohn', 'C13', 'Leah Lewis, Mamoudou Athie', 'Lấy bối cảnh tại thành phố các nguyên tố, nơi nước, đất, lửa sống chung với nhau. " +
//                "Câu chuyện xoay quanh nhân vật Ember, một cô nàng cá tính, thông minh, mạnh mẽ và đầy sức hút.','Đang chiếu')");

//        film.querryData("INSERT INTO tblPhim values ('P3','Người Nhện: Du hành vũ trụ nhện', '"+anh1+"', '06/22/2023', '160 phút', " +
//                "'Hành động, phiêu lưu', 'Shameik Moore', 'C13', 'Joaquim Dos Santos, Justin K...', 'Miles Morales trở lại trong chương trình tiếp theo" +
//                "của câu chuyện người nhện, một cuộc phưu lưu sử thi sẽ đưa người nhện du hành khắp vũ trụ để hợp lực với Gwen đối đầu với phản diện Spot','Sắp chiếu')");
//        film.querryData("INSERT INTO tblCTPhim values ('P1', '09/25/2023', '120 phút', 'Hài hước', 'Bay', 'C13', 'Tom Cruise', 'Hay vãi nồi')");
//        Log.e("ds","INSERT INTO tblHomePhim values ('Avatar 2: Dòng chảy của nước', '" + adu +"')");
//        film.querryData("INSERT INTO tblHomePhim values (null, 'Tranformer: Quái thú trỗi dậy', 'R.drawable.anh4')");
//        film.querryData("INSERT INTO tblHomePhim values (null, 'Tranformer: Quái thú trỗi dậy', 'R.drawable.anh4')");


//        Cursor data = film.getData("SELECT * FROM tblPhim where sLoaiPhim = 'Sắp chiếu'");
//                if (data.moveToFirst())
//                {
//                    do {
////                        byte[] result = data.getString(1).getBytes();
//                        lstSC.add(new userFilm(
//                                data.getString(0),data.getString(1),
//                                data.getString(2).getBytes(), data.getString(3),
//                                data.getString(4),data.getString(5),
//                                data.getString(6),data.getString(7),
//                                data.getString(8),data.getString(9),
//                                data.getString(10)
//                        ));
//
//
//                    } while (data.moveToNext());
//                }

//        Cursor data1 = film.getData("SELECT * FROM tblPhim where sLoaiPhim = 'Đang chiếu'");
//        if (data1.moveToFirst())
//        {
//            do {
////                byte[] result = data.getString(1).getBytes();
//                lstDangC.add(new userFilm(
//                        data1.getString(0),data1.getString(1),
//                        data1.getString(2).getBytes(), data1.getString(3),
//                        data1.getString(4),data1.getString(5),
//                        data1.getString(6),data1.getString(7),
//                        data1.getString(8),data1.getString(9),
//                        data1.getString(10)
//                ));
//
//
//            } while (data1.moveToNext());
//        }




    }

    public void clickItemListPhim(){
    }

    public void XemThemFimSapChieu(){
        btn_xtpsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Full_PhimSapChieu.class);
                startActivity(intent);
            }
        });
    }

    public void XemThemFimDangChieu()
    {
        btn_xtp_dang_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Full_PhimDangChieu.class);
                startActivity(intent);
            }
        });
    }

//    public void loadData(){
//        Toast.makeText(getActivity(),"fdsfdsf",Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public byte[] anh(int anh)
    {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),anh);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,stream);
        return stream.toByteArray();
    }
}
