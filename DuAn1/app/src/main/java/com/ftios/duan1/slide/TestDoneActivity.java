package com.ftios.duan1.slide;



import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ftios.duan1.R;
import com.ftios.duan1.model.Cauhoi;

import java.util.ArrayList;

public class TestDoneActivity extends AppCompatActivity {

    ArrayList<Cauhoi> arr_cauhoiBD = new ArrayList<Cauhoi>();
    int traloi_dung = 0;
    int traloi_sai = 0;
    int traloi_khong = 0;
    int tongdiem = 0;



    TextView txtcaudung, txtcausai, txtcauchuatl, txttongdiem;
    Button btnlamlai, btnthoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);

        getSupportActionBar().setTitle("Kết thúc");

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DB4437")));

        // lấy list câu hỏi
        final Intent intent = getIntent();
        arr_cauhoiBD = (ArrayList<Cauhoi>) intent.getExtras().getSerializable("arr_cauhoi");

        txtcauchuatl = (TextView) findViewById(R.id.txtCauChuaTL);
        txtcausai = (TextView) findViewById(R.id.txtCauSai);
        txtcaudung = (TextView) findViewById(R.id.txtCauDung);
        txttongdiem = (TextView) findViewById(R.id.txtTongDiem);
        btnlamlai = (Button) findViewById(R.id.btnLamLai);
        btnthoat = (Button) findViewById(R.id.btnThoat);


        ktra_ketqua();
        tongdiem = traloi_dung * 10;
        txtcauchuatl.setText("" + traloi_khong);
        txtcaudung.setText("" + traloi_dung);
        txtcausai.setText("" + traloi_sai);
        txttongdiem.setText("" + tongdiem + "/100");

        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát hay không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        btnlamlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HomeFragment homeFragment = new HomeFragment();
//                FragmentManager manager = getSupportFragmentManager();
//                manager.beginTransaction().replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();
                refresh();
                finish();
                Intent intent2 = new Intent(TestDoneActivity.this, ScreenSlideActivity.class);
                intent2.putExtra("arr_cauhoi", arr_cauhoiBD);
                intent2.putExtra("test","no");
                startActivity(intent2);
            }
        });
    }

    @Override
    // sự kiện nút quay lại
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn thoát hay không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    // làm mới mảng câu hỏi
    public void  refresh(){
        for(int i = 0; i < arr_cauhoiBD.size(); i++){
            arr_cauhoiBD.get(i).setTraloi("");
        }
    }


    //hàm check kết quả
    public void ktra_ketqua() {
        for (int i = 0; i < arr_cauhoiBD.size(); i++) {
            if (arr_cauhoiBD.get(i).getTraloi().equals("") == true) {
                traloi_khong++;
            } else if (arr_cauhoiBD.get(i).getDapan().equals(arr_cauhoiBD.get(i).getTraloi()) == true) {
                traloi_dung++;
            } else
                traloi_sai++;
        }
    }
}
