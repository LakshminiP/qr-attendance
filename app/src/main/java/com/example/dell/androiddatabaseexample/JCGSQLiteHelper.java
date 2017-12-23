package com.example.dell.androiddatabaseexample;

        import java.util.LinkedList;
        import java.util.List;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class JCGSQLiteHelper extends SQLiteOpenHelper {

    // database version
    private static final int database_VERSION = 1;
    // database name
    private static final String database_NAME = "BookDB";
    private static final String table_BOOKS = "books";
    private static final String book_ID = "id";
    private static final String book_NAME = "name";
    private static final String book_DATE = "date";

    private static final String[] COLUMNS = {  book_NAME };

    public JCGSQLiteHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_BOOK_TABLE = "CREATE TABLE books ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, " + "date TEXT )";
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop books table if already exists
        db.execSQL("DROP TABLE IF EXISTS books");
        onCreate(db);
    }

    public void createBook(Book book) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(book_NAME, book.getName());
        values.put(book_DATE, book.getDate());

        // insert book
        db.insert(table_BOOKS, null, values);

        // close database transaction
        db.close();
    }

    public Book readBook(int id) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getReadableDatabase();

        // get book query
        Cursor cursor = db.query(table_BOOKS, // a. table
                COLUMNS, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);

        // if results !=null, parse the first one
        if (cursor != null)
            cursor.moveToFirst();

        Book book = new Book();
        book.setId(Integer.parseInt(cursor.getString(0)));
        book.setName(cursor.getString(1));
        book.setDate(cursor.getString(2));

        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<Book>();

        // select book query
        String query = "SELECT  * FROM " + table_BOOKS;

        // get reference of the BookDB database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setName(cursor.getString(1));
                book.setDate(cursor.getString(2));

                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }
        return books;
    }

    public List<Book> getAllDateBooks(String date) {
        List<Book> books = new LinkedList<Book>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table_BOOKS, // a. table
                COLUMNS, " date = ?", new String[] { String.valueOf(date) }, null, null, null, null);


        // select book query
        //String query = "SELECT  name FROM " + table_BOOKS ;

        // get reference of the BookDB database

       // Cursor cursor = db.rawQuery(query, null);

        // parse all results
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setName(cursor.getString(0));

                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }
        return books;
    }
    public List<Book> getAllDates() {
        List<Book> books = new LinkedList<Book>();

        // select book query
        String query = "SELECT  distinct date FROM " + table_BOOKS;

        // get reference of the BookDB database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setDate(cursor.getString(0));

                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }
        return books;
    }
    public int updateBook(Book book) {

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put("name", book.getName()); // get title
        values.put("date", book.getDate()); // get author

        // update
        int i = db.update(table_BOOKS, values, book_ID + " = ?", new String[] { String.valueOf(book.getId()) });

        db.close();
        return i;
    }



}
