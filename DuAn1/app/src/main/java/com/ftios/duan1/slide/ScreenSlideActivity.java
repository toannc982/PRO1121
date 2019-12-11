package com.ftios.duan1.slide;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ftios.duan1.R;
import com.ftios.duan1.cauhoi_controller;
import com.ftios.duan1.model.Cauhoi;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ScreenSlideActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 10;    //số Page

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    TextView txtkiemtra, txtthoigian, txtxemdiem;
    public int checkAns = 0;

    //CSDL
    cauhoi_controller cauhoi_ctrler;
    ArrayList<Cauhoi> arr_cauhoi;
    CounterClass thoigian;
    String monhoc;
    int sode;
    int tongthoigian;

    String test = "";

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiemtra);

        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mPager.setPageTransformer(true, new DepthPageTransformer());

        // lấy giá trị gửi về từ Fragment
        Intent intent = getIntent();
        monhoc = intent.getStringExtra("monhoc");
        sode = intent.getIntExtra("sode",0);
        test = intent.getStringExtra("test");

        cauhoi_ctrler = new cauhoi_controller(this);    // this: màn hình hiện tại
        arr_cauhoi = new ArrayList<Cauhoi>();
        //arr_cauhoi = cauhoi_ctrler.getCauHoi(sode,monhoc);


        if(test.equals("yes") == true){
            arr_cauhoi = cauhoi_ctrler.getCauhoi(1, monhoc);
        }else {
            arr_cauhoi = (ArrayList<Cauhoi>) intent.getExtras().getSerializable("arr_cauhoi");
        }


        tongthoigian = 5;
        thoigian = new CounterClass(tongthoigian*60*1000, 1000);

        txtthoigian = (TextView)findViewById(R.id.true_button);
        txtthoigian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txtxemdiem = (TextView)findViewById(R.id.txtXemDiem);

        txtkiemtra = (TextView)findViewById(R.id.txtKiemTra);
        txtkiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ktra_cautraloi();
            }
        });

        txtxemdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent1 = new Intent(ScreenSlideActivity.this, TestDoneActivity.class);
                // gửi list câu hỏi
                intent1.putExtra("arr_cauhoi", arr_cauhoi);
                startActivity(intent1);
            }
        });

        thoigian.start();


    }

    public  ArrayList<Cauhoi> getDuLieu(){
          return arr_cauhoi;
    }

    @Override
    // sự kiện nút quay lại
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            dialogExit();
            //super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public  void dialogExit(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ScreenSlideActivity.this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn thoát hay không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                thoigian.cancel();
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

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            //gọi phương thức bên ScreenSlidePageFragment
            // gửi số trang hiện tại
            return ScreenSlidePageFragment.create(position, checkAns);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    // hàm kiểm tra câu trả lời
    public  void ktra_cautraloi(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_answer_dialog);    //gán layout
        dialog.setTitle("Danh sách câu trả lời");

        Ktra_Adapter ktra_adapter = new Ktra_Adapter(arr_cauhoi, this);
        GridView gvcautraloi = (GridView)dialog.findViewById(R.id.gvCauTraLoi);
        gvcautraloi.setAdapter(ktra_adapter);

        gvcautraloi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPager.setCurrentItem(position);    //set ViewPage tại vị trí click câu trả lời
                dialog.dismiss();   // tắt Dialog
            }
        });

        Button btndong, btnketthuc;
        btndong = (Button)dialog.findViewById(R.id.btnDong);
        btnketthuc = (Button)dialog.findViewById(R.id.btnKetThuc);
        btndong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnketthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thoigian.cancel();
                ketqua();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public  void ketqua(){
        checkAns = 1;

        // chuyển trang để hiện trạng thái check câu trả lời
        if(mPager.getCurrentItem() >= 4)
            mPager.setCurrentItem(mPager.getCurrentItem()-4);
        else if(mPager.getCurrentItem() <= 4)
            mPager.setCurrentItem(mPager.getCurrentItem()+4);

        txtxemdiem.setVisibility(View.VISIBLE);
        txtkiemtra.setVisibility(View.GONE);
        Toast.makeText(this, "Kiểm Tra Kết Thúc", Toast.LENGTH_LONG).show();
    }

    // hàm đếm thời gian
    public class CounterClass extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */


        //millisInFuture: 60*1000    - số thời gian đếm: 60s
        //countDownInterval:  1000   - số bước nhảy: 1s
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            txtthoigian.setText(countTime); //SetText cho textview hiển thị thời gian.
        }

        @Override
        public void onFinish() {
            txtthoigian.setText("00:00");  //SetText cho textview hiển thị thời gian.
            ketqua();

        }
    }
}
