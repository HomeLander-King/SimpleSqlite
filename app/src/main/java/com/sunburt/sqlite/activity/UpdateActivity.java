package com.sunburt.sqlite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sunburt.sqlite.MainActivity;
import com.sunburt.sqlite.R;

public class UpdateActivity extends AppCompatActivity {

    private TextView txtProductId;
    private EditText editName, editPrice;
    private RadioButton radStocking, radSoldout;
    private Button btnUpdate, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getControls();

        reciveData();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBack();
            }
        });

    }

    private void doBack() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("productId", txtProductId.getText().toString());
        intent.putExtra("name", editName.getText().toString());
        intent.putExtra("price", Float.parseFloat(editPrice.getText().toString()));
        String status = "";
        if (radSoldout.isChecked())
            status = "Sold out";
        else
            status = "Stocking";
        intent.putExtra("status", status);
        startActivity(intent);
    }

    private void updateData() {
        String name = editName.getText().toString();
        float price = Float.parseFloat(editPrice.getText().toString());
        String status = "";
        if (radSoldout.isChecked())
            status = "Sold out";
        else
            status = "Stocking";
        editName.setText(name);
        editPrice.setText(price+"");
        Toast.makeText(UpdateActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
    }

    private void reciveData() {
        Bundle bundle = getIntent().getExtras();
        String productId = bundle.getString("productId");
        txtProductId.setText(productId);
        String name = bundle.getString("name");
        editName.setText(name);
        float price = bundle.getFloat("price");
        editPrice.setText(price + "");
        String status = bundle.getString("status");
        if (status.equals("Stocking"))
            radStocking.setChecked(true);
        else
            radStocking.setChecked(false);
        if (status.equals("Sold out"))
            radSoldout.setChecked(true);
        else
            radSoldout.setChecked(false);
    }

    private void getControls() {
        txtProductId = (TextView) findViewById(R.id.txtId);
        editName = (EditText) findViewById(R.id.editName);
        editPrice = (EditText) findViewById(R.id.editPrice);
        radSoldout = (RadioButton) findViewById(R.id.radSoldout);
        radStocking = (RadioButton) findViewById(R.id.radStocking);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
    }
}