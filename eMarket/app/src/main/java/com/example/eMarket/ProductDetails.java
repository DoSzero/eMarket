package com.example.eMarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class ProductDetails extends AppCompatActivity {

    ImageView back, img;
    TextView proName, proDesc, proPrice;

    String name, price, desc;
    int image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent i = getIntent();

        name = i.getStringExtra("name");
        image = i.getIntExtra("image", R.drawable.computer);
        price = i.getStringExtra("price");
        desc = i.getStringExtra("desc");


        proName = findViewById(R.id.NameProduct);
        proDesc = findViewById(R.id.DescriptionProduct);
        proPrice = findViewById(R.id.PriceProduct);
        img = findViewById(R.id.ImageProduct);
        back = findViewById(R.id.back2);


        proName.setText(name);
        proPrice.setText(price);
        proDesc.setText(desc);



        img.setImageResource(image);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProductDetails.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });



    }
}