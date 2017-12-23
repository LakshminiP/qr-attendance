package com.example.dell.androiddatabaseexample;

        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class BookActivity extends Activity {
    /*TextView sname;
    TextView tdate;
    Book selectedBook;
    JCGSQLiteHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book);

        sname = (TextView) findViewById(R.id.titleEdit);
        tdate = (TextView) findViewById(R.id.authorEdit);

        // get the intent that we have passed from AndroidDatabaseExample
        Intent intent = getIntent();
        int id = intent.getIntExtra("book", -1);

        // open the database of the application context
        db = new JCGSQLiteHelper(getApplicationContext());

        // read the book with "id" from the database
        selectedBook = db.readBook(id);

        initializeViews();
    }

    public void initializeViews() {
        sname.setText(selectedBook.getName());
        //tdate.setText(selectedBook.getDate());
    }
/*
    @SuppressLint("WrongViewCast")
    public void update(View v) {
        Toast.makeText(getApplicationContext(), "Student is recorded.", Toast.LENGTH_SHORT).show();
        selectedBook.setName(((EditText) findViewById(R.id.titleEdit)).getText().toString());
        selectedBook.setDate(((EditText) findViewById(R.id.authorEdit)).getText().toString());

        // update book with changes
        db.updateBook(selectedBook);
        finish();
    }
*/
}
