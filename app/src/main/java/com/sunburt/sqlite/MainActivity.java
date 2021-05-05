package com.sunburt.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.sunburt.sqlite.adapter.CardItemProduct;
import com.sunburt.sqlite.database.DBHelper;
import com.sunburt.sqlite.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editProductId, editName, editPrice;
    private RadioButton radSoldout, radStocking;
    private Button btnAdd;
    private RecyclerView revProduct;
    private CardItemProduct adapter = null;
    private List<Product> products = new ArrayList<>();
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getControls();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAddNewProduct();
            }
        });
    }

    public void getControls(){
        editProductId = (EditText) findViewById(R.id.IdProduct);
        editName = (EditText) findViewById(R.id.editName);
        editPrice = (EditText) findViewById(R.id.editPrice);
        radSoldout = (RadioButton) findViewById(R.id.radSoldout);
        radStocking = (RadioButton) findViewById(R.id.radStocking);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        revProduct = (RecyclerView) findViewById(R.id.revProduct);
        dbHelper = new DBHelper(this);

        adapter = new CardItemProduct(MainActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        revProduct.setAdapter(adapter);
        revProduct.setLayoutManager(manager);
    }

    public void doAddNewProduct(){
        String productId = editProductId.getText().toString();
        String name = editName.getText().toString();
        float price = Float.parseFloat(editPrice.getText().toString());
        String status = "";
        if (radStocking.isChecked())
            status += "Stocking";
        else
            status += "Sold out";
        Product product = new Product(productId, name, price, status);
        adapter.addProduct(product);
        adapter.notifyDataSetChanged();

        Boolean checkAddNew = dbHelper.addNewProduct(productId, name, price, status);
    }
}