package com.sunburt.sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.sunburt.sqlite.model.Product;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname = "product.db";

    public DBHelper(@Nullable Context context) {
        super(context, DBname, null, 1);
        SQLiteDatabase MyDB = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE tbl_product(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "productId TEXT, name TEXT, price FLOAT, status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS tbl_product");
    }

    // add new product
    public boolean addNewProduct(String productId, String name, float price,
                                 String status){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", productId);
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("status", status);

        long result = MyDB.insert("tbl_product", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    // get all book
    public ArrayList<Product> getAllProduct(){
        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase MyDB = getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM  tbl_product", null);
        while (cursor.moveToNext()){
            String productId = cursor.getString(1);
            String name = cursor.getString(2);
            float price = cursor.getFloat(3);
            String status = cursor.getString(4);

            Product product = new Product(productId, name, price, status);
            products.add(product);
        }
        return products;
    }
}
