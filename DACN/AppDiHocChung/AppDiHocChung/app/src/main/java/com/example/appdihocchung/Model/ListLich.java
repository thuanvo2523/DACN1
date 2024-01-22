package com.example.appdihocchung.Model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appdihocchung.R;

import java.util.ArrayList;

public class ListLich extends ArrayAdapter<Lich> {
    public ListLich( Activity context, int idLayout, ArrayList<Lich> myList) {
        super(context, idLayout,myList);
        this.context = context;
        this.idLayout = idLayout;
        this.myList = myList;
    }

    Activity context;
    int idLayout;
    ArrayList<Lich> myList;


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myflacter = context.getLayoutInflater();
        convertView = myflacter.inflate(idLayout,null);
        Lich myLich = myList.get(position);
        TextView ngayThu = (convertView.findViewById(R.id.txtNgayVaThu));
        TextView diemHen = (convertView.findViewById(R.id.txtDiemHen));
        TextView thoiGianHen = (convertView.findViewById(R.id.txtThoiGianHen));
        TextView thongTinLienLac = (convertView.findViewById(R.id.txtSdtLienLac));
        ngayThu.setText(myLich.getNgayThu());
        diemHen.setText(myLich.getDiemHen());
        thoiGianHen.setText(myLich.getThoiGianHen());
        thongTinLienLac.setText(myLich.getThongTinLienLac());
        return convertView;
    }
}
