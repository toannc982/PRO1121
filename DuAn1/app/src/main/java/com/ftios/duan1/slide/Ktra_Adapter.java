package com.ftios.duan1.slide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ftios.duan1.R;
import com.ftios.duan1.model.Cauhoi;

import java.util.ArrayList;

/**
 * Created by T-420 on 28/05/2017.
 */

public class Ktra_Adapter extends BaseAdapter{

    ArrayList lsDuLieu;
    LayoutInflater inflater;

    public Ktra_Adapter(ArrayList lsDuLieu, Context context) {
        this.lsDuLieu = lsDuLieu;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lsDuLieu.size();
    }

    @Override
    public Object getItem(int position) {
        return lsDuLieu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cauhoi dulieu = (Cauhoi)getItem(position);
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_gridview_list_answer, null);
            holder.txtsocautraloi = (TextView)convertView.findViewById(R.id.txtSoCauTraLoi);
            holder.txtcautraloi = (TextView)convertView.findViewById(R.id.txtCauTraLoi);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        int i = position + 1;

        holder.txtsocautraloi.setText("Câu "+i+": ");
        holder.txtcautraloi.setText(dulieu.getTraloi());
        return convertView;
    }

    private  static  class  ViewHolder{
        TextView txtsocautraloi, txtcautraloi;

    }
}
