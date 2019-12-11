package com.ftios.duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ftios.duan1.model.Cauhoi;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class KiemtraActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideAdapter myadapter;

    private cauhoi_controller cauhoi_controller;
    ArrayList<Cauhoi> arr_cauhoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiemtra);

//        getSupportActionBar().setTitle("Kiểm tra 03:00");
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DB4437")));

        getSupportActionBar().hide();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myadapter = new SlideAdapter(this);
        viewPager.setAdapter(myadapter);

        cauhoi_controller = new cauhoi_controller(this);    // this: màn hình hiện tại
        arr_cauhoi = new ArrayList<Cauhoi>();
        //arr_cauhoi = cauhoi_ctrler.getCauHoi(sode,monhoc);

        arr_cauhoi = cauhoi_controller.getCauhoi(1, "toan");


        Toast.makeText(this, arr_cauhoi + "", Toast.LENGTH_SHORT).show();

        final TextView bButton = (TextView) findViewById(R.id.true_button);

        new CountDownTimer(3*60*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                bButton.setText(countTime); //SetText cho textview hiển thị thời gian.
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                bButton.setText("done!");
            }

        }.start();



    }

    public void btnKetThucThi(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bạn chắc chắn muốn kết thúc ?");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(KiemtraActivity.this, "Kết thúc bài thi thành công", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(KiemtraActivity.this, DiemActivity.class);  //your class
                startActivity(i);
                finish();

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    // sự kiện nút quay lại
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(KiemtraActivity.this);
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
            //super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
}
