package com.ftios.duan1.ui.notifications;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ftios.duan1.R;
import com.ftios.duan1.adapter.NewsAdapter;
import com.ftios.duan1.model.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private ProgressDialog progress;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private AlertDialog mAlertDialog;

    News news;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);

        progress = new ProgressDialog(getActivity());
        progress.setMessage("Retrieving data. Please wait...");
        progress.setCancelable(false);
        progress.show();

        // khoi tao thread moi bang AsyncTask de request du lieu
        GetDataTask getDataTask = new GetDataTask();
        // truyen tham so url vao thread va run thread
        getDataTask.execute("https://vietnamnet.vn/rss/oto-xe-may.rss");

        return root;
    }

    class GetDataTask extends AsyncTask<String, Long, List<News>> {

        @Override
        protected List<News> doInBackground(String... strings) {
            // boc tach xml tai day

            // khai bao 1 list rong~ de chua du lieu
            List<News> newsList = new ArrayList<>();

            // khai bao try catch de bat loi~
            try {

                URL url = new URL(strings[0]);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();


                // khoi tao doi tuong xmlpullparser
                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                xmlPullParserFactory.setNamespaceAware(false);

                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();

                // truyen du lieu vao xmlpullparser tien hanh boc tach xml
                xmlPullParser.setInput(inputStream, "utf-8");

                int eventType = xmlPullParser.getEventType();

                String text = "";
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String name = xmlPullParser.getName();
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if (name.equals("item")) {
                                news = new News();
                            }
                            break;

                        case XmlPullParser.TEXT:
                            text = xmlPullParser.getText();
                            break;

                        case XmlPullParser.END_TAG:
                            if (news != null && name.equalsIgnoreCase("title")) {
                                news.setTitle(text);
                            } else if (news != null && name.equalsIgnoreCase("description")) {
                                news.setDescription(text);
                            } else if (news != null && name.equalsIgnoreCase("pubdate")) {
                                news.setPubDate(text);
                            } else if (news != null && name.equalsIgnoreCase("link")) {
                                news.setLink(text);
                            } else if (news != null && name.equalsIgnoreCase("guiid")) {
                                news.setGuiid(text);
                            } else if (news != null && name.equalsIgnoreCase("image")) {
                                news.setImage(text);
                            } else if (news != null && name.equalsIgnoreCase("content:encoded")) {
                                news.setContent(text);
                            }
                            else if (name.equalsIgnoreCase("item")) {
                                newsList.add(news);
                            }
                            break;

                    }
                    // di chuyen toi tag ke tiep
                    eventType = xmlPullParser.next(); //move to next element
                }
            } catch (Exception e) {
                Log.e("Exception", e.getMessage());
            }

            return newsList;
        }

        @Override
        protected void onPostExecute(List<News> news) {
            super.onPostExecute(news);

            // tuong tac voi layout, view tai day

            // tat loading
            progress.dismiss();

            if (news.size() > 0) {
                newsAdapter = new NewsAdapter(news, getActivity());

                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(newsAdapter);

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Không có dữ liệu !!!");
                builder.setCancelable(false);
//            builder.setPositiveButton("OK", null);
                mAlertDialog = builder.create();
                mAlertDialog.show();
                // dismiss dialog in TIME_OUT ms
                mHandler.sendEmptyMessageDelayed(0, 3000);
            }
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0)
                if (mAlertDialog != null && mAlertDialog.isShowing()) {
                    mAlertDialog.dismiss();
                }
        }
    };
}