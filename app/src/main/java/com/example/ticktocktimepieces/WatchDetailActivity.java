package com.example.ticktocktimepieces;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.asura.library.posters.Poster;
import com.asura.library.posters.RemoteImage;
import com.asura.library.views.PosterSlider;
import com.example.ticktocktimepieces.databse.DatabaseHandler;
import com.example.ticktocktimepieces.models.WatchModel;

import java.util.ArrayList;
import java.util.List;

public class WatchDetailActivity extends AppCompatActivity {
    ImageView imageaddtocar;
    ImageView imageback;
    TextView txtdescription, txtcompany, txtpublishyear, txtprice, textToolbarTitle;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_detail);


        imageaddtocar = (ImageView) findViewById(R.id.add_to_cart);
        txtcompany = (TextView) findViewById(R.id.text_comapny);
        txtpublishyear = (TextView) findViewById(R.id.text_Published_year);
        txtdescription = (TextView) findViewById(R.id.text_description);
        txtprice = (TextView) findViewById(R.id.text_price);
        textToolbarTitle = (TextView) findViewById(R.id.text_toolbar_title);
        imageback = (ImageView) findViewById(R.id.image_back);
        imageback.setOnClickListener(v -> onBackPressed());

        txtdescription.setText(getIntent().getStringExtra("description"));
        txtpublishyear.setText("Published :" + getIntent().getStringExtra("published_year"));
        txtcompany.setText("By :" + getIntent().getStringExtra("Company"));
        textToolbarTitle.setText(getIntent().getStringExtra("name"));
        txtprice.setText("\u20B9" + getIntent().getStringExtra("price"));

        try {
            PosterSlider posterSlider = (PosterSlider) findViewById(R.id.poster_slider);
            List<Poster> posters = new ArrayList<>();
            //add poster using remote url
            posters.add(new RemoteImage(getIntent().getStringExtra("image")));
            posters.add(new RemoteImage(getIntent().getStringExtra("image_2")));
            posters.add(new RemoteImage(getIntent().getStringExtra("image_3")));
            posters.add(new RemoteImage(getIntent().getStringExtra("image_4")));
            posterSlider.setPosters(posters);

        } catch (Exception ignored) {
        }

        imageaddtocar.setOnClickListener(v -> {
            WatchModel watchModel = new WatchModel();
            watchModel.setId(getIntent().getStringExtra("id"));
            watchModel.setCategory(getIntent().getStringExtra("category"));
            watchModel.setName(getIntent().getStringExtra("name"));
            watchModel.setCOmpany(getIntent().getStringExtra("Company"));
            watchModel.setDescription(getIntent().getStringExtra("description"));
            watchModel.setImage(getIntent().getStringExtra("image"));
            watchModel.setPublishedYear(getIntent().getStringExtra("published_year"));
            watchModel.setPrice(getIntent().getStringExtra("price"));

            DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
            databaseHandler.AddToCart(watchModel);

            Toast.makeText(WatchDetailActivity.this, "Added To Cart !", Toast.LENGTH_SHORT).show();

        });

    }

}
