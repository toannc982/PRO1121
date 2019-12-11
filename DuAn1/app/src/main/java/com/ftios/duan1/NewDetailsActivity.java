package com.ftios.duan1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ftios.duan1.model.News;

import java.util.List;

public class NewDetailsActivity extends AppCompatActivity {

    TextView tvTitleDetails, tvDescDetails;

    Context context;

    private final String htmlText = "<body><h1>Heading Text</h1><p>This tutorial " +
            "explains how to display " +
            "<strong>HTML </strong>text in android text view.&nbsp;</p>" +
            "<img src=\"hughjackman.jpg\">" +
            "<blockquote>Example from <a href=\"www.stacktips.com\">" +
            "stacktips.com<a></blockquote></body>";


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_details);

        tvTitleDetails = findViewById(R.id.tvTitleDetails);
        tvDescDetails = findViewById(R.id.tvDescDetails);

        context = getApplicationContext();


        Bundle bundle = getIntent().getExtras();
        int key = bundle.getInt("key_1");
        String key2 = bundle.getString("key_2");
        String key3 = bundle.getString("key_3");


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bài viết");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DB4437")));

        tvTitleDetails.setText(Html.fromHtml(key2, Html.FROM_HTML_MODE_LEGACY));

        tvDescDetails.setText(Html.fromHtml(key3, Html.FROM_HTML_MODE_LEGACY));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }


}
