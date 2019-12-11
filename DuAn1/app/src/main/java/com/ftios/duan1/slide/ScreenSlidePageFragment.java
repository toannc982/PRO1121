package com.ftios.duan1.slide;


import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ftios.duan1.R;
import com.ftios.duan1.model.Cauhoi;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {

    ArrayList<Cauhoi> arr_cauhoi;
    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSWER = "checkAnswer";
    private int mPageNumber;    //số trang hiện tại
    public  int checkAns;       //biến kiểm tra

    TextView txtsocau, txtcauhoi;
    RadioGroup radioGroup;
    RadioButton traloiA, traloiB, traloiC, traloiD;
    ImageView imgIcon;

    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        txtsocau = (TextView) rootView.findViewById(R.id.txtSoCau);
        txtcauhoi = (TextView) rootView.findViewById(R.id.txtCauHoi);
        traloiA = (RadioButton) rootView.findViewById(R.id.traloi_A);
        traloiB = (RadioButton) rootView.findViewById(R.id.traloi_B);
        traloiC = (RadioButton) rootView.findViewById(R.id.traloi_C);
        traloiD = (RadioButton) rootView.findViewById(R.id.traloi_D);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radGroup);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // lấy data từ ScreenSlideActivity
        arr_cauhoi = new ArrayList<Cauhoi>();
        ScreenSlideActivity slideActivity = (ScreenSlideActivity) getActivity();
        arr_cauhoi = slideActivity.getDuLieu();

        mPageNumber = getArguments().getInt(ARG_PAGE);  // lấy số trang hiện tại
        checkAns = getArguments().getInt(ARG_CHECKANSWER);
    }

    public static Fragment create(int pageNumber, int checkAnswer) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();                    //gói dữ liệu để gửi đi
        args.putInt(ARG_PAGE, pageNumber);             // gửi số trang hiện tại
        args.putInt(ARG_CHECKANSWER, checkAnswer);     // gửi giá trị kiểm tra câu trả lời
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtsocau.setText("Câu " + (mPageNumber + 1));
        txtcauhoi.setText(getItem(mPageNumber).getCauhoi());
        traloiA.setText(getItem(mPageNumber).getTrloi_a());
        traloiB.setText(getItem(mPageNumber).getTrloi_b());
        traloiC.setText(getItem(mPageNumber).getTrloi_c());
        traloiD.setText(getItem(mPageNumber).getTrloi_d());


        // imgIcon.setImageResource(getResources().getIdentifier(getItem(mPageNumber).getHinhanh()+"","drawable", MainActivity.PACKAGE_NAME));


        if(checkAns != 0){
            traloiA.setClickable(false);
            traloiB.setClickable(false);
            traloiC.setClickable(false);
            traloiD.setClickable(false);
            getCheckAns(getItem(mPageNumber).getDapan().toString());
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                getItem(mPageNumber).luachonID = checkedId;     // lấy ID khi click câu trả lời
                getItem(mPageNumber).setTraloi(laygiatrituID(checkedId));

                //Toast.makeText(getActivity(), "Đây là đáp án "+ checkedId, Toast.LENGTH_SHORT).show();


            }
        });
    }



    //lấy giá trị (vị trí) radiogroup chuyển thành đáp án A,B,C,D
    private String laygiatrituID(int ID) {
        if (ID == R.id.traloi_A) {
            return "A";
        } else if (ID == R.id.traloi_B) {
            return "B";
        } else if (ID == R.id.traloi_C) {
            return "C";
        } else if (ID == R.id.traloi_D) {
            return "D";
        } else return "";
    }

    //lấy Item khi biet vi tri
    public Cauhoi getItem(int vitri){
        return arr_cauhoi.get(vitri);
    }

    //hàm kiểm tra câu trả lời, nếu câu đúng thì đổi màu background radiobutton tương ứng
    private  void getCheckAns(String ans) {
        if(ans.equals("A") == true){
            traloiA.setBackgroundColor(Color.GREEN);
        }else if(ans.equals("B") == true){
            traloiB.setBackgroundColor(Color.GREEN);
        }else if(ans.equals("C") == true){
            traloiC.setBackgroundColor(Color.GREEN);
        }else if(ans.equals("D") == true){
            traloiD.setBackgroundColor(Color.GREEN);
        }else ;
    }

}
