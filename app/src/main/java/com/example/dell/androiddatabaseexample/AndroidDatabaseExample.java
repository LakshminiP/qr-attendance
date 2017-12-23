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

public class AndroidDatabaseExample extends ListActivity implements OnItemClickListener {
    TextView barcodeResult;
    JCGSQLiteHelper db = new JCGSQLiteHelper(this);
    List<Book> list;
    ArrayAdapter<String> myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        barcodeResult=(TextView)findViewById(R.id.barcode_result);

        list = db.getAllDates();

        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getDate());
        }

        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);


        // drop this database if already exists

  //      java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd-MM-yyyy");
    //    String b=sdf.format(new java.util.Date());

      //  db.createBook(new Book(barcodeResult.getText().toString(),b));
/*
        // get all books
        list = db.getAllBooks();
        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getName());
        }

        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
*/
    }

    public void scanBarcode(View v){
        Intent intent=new Intent(this,ScanBarcodeActivity.class);
        startActivityForResult(intent,0);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // start BookActivity with extras the book id
        Intent intent = new Intent(this, DateActivity.class);
        intent.putExtra("book", list.get(arg2).getDate());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==0){
            if(resultCode== CommonStatusCodes.SUCCESS){
                if(data!=null) {
                    com.google.android.gms.vision.barcode.Barcode barcode = data.getParcelableExtra("barcode");
                    String str;
                    str= " "+barcode.displayValue;
                    barcodeResult.setText("" + str);

                    java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd-MM-yyyy");
                    String b=sdf.format(new java.util.Date());
                    db.createBook(new Book(barcodeResult.getText().toString(),b));
                }
                else {
                    barcodeResult.setText("No barcode found");
                }
            }
            list = db.getAllDates();

            List<String> listTitle = new ArrayList<String>();

            for (int i = 0; i < list.size(); i++) {
                listTitle.add(i, list.get(i).getDate());
            }

            myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
            getListView().setOnItemClickListener(this);
            setListAdapter(myAdapter);

/*
            // get all books again, because something changed
            list = db.getAllBooks();

            List<String> listTitle = new ArrayList<String>();

            for (int i = 0; i < list.size(); i++) {
                listTitle.add(i, list.get(i).getName());
            }

            myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
            getListView().setOnItemClickListener(this);
            setListAdapter(myAdapter);
  */

/*
            list = db.getAllDateBooks();

            List<String> listTitle = new ArrayList<String>();

            for (int i = 0; i < list.size(); i++) {
                listTitle.add(i, list.get(i).getName());
            }

            myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
            getListView().setOnItemClickListener(this);
            setListAdapter(myAdapter);


*/
        }
    }
}
