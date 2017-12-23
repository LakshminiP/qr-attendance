package com.example.dell.androiddatabaseexample;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;

public class DateActivity extends ListActivity implements OnItemClickListener {
    TextView barcodeResult;
    JCGSQLiteHelper db = new JCGSQLiteHelper(this);
    List<Book> list;
    ArrayAdapter<String> myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview);

        Intent intent = getIntent();
        String date = intent.getStringExtra("book");


        list = db.getAllDateBooks(date);
        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getName());
        }


        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
     //   getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // start BookActivity with extras the book id
        Intent intent = new Intent(this, BookActivity.class);
        intent.putExtra("book", list.get(arg2).getDate());
        startActivityForResult(intent, 1);
    }


}
