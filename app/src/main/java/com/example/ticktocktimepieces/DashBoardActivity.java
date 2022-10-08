package com.example.ticktocktimepieces;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ticktocktimepieces.utils.Constants;

public class DashBoardActivity extends AppCompatActivity {
    ImageView menu;
    DrawerLayout drawerLayout;
    CardView Cart, Watches,myorder;
    TextView txtemail, txtusername;
    LinearLayout login,logout,watchelayout,aboutlayput,rate;
    View loginview,logoutview;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        rate = findViewById(R.id.rate);
        rate.setOnClickListener(v -> Toast.makeText(DashBoardActivity.this,"Sorry! Our App Not Available In PlayStore",Toast.LENGTH_LONG).show());
        aboutlayput = findViewById(R.id.layout_about) ;
        aboutlayput.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),AboutActiviti.class)));
        watchelayout = findViewById(R.id.layoutwatches);
        watchelayout.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),MainActivity.class)));
        login = findViewById(R.id.layou_login);
        logout = findViewById(R.id.layout_logout);
        loginview = findViewById(R.id.login_view);
        logoutview = findViewById(R.id.logout_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        menu = findViewById(R.id.image_menu);
        txtemail = findViewById(R.id.text_email);
        txtusername = findViewById(R.id.text_username);
        Cart = findViewById(R.id.card_cart);
        Watches = findViewById(R.id.card_watches);
        myorder = findViewById(R.id.myorders);
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MyOrdersActivity.class));
            }
        });

        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean(Constants.KEY_IS_LOGGED_IN, false);
        if (!isLoggedIn) {
            txtusername.setText(R.string.Welcome_Guest);
            txtusername.setVisibility(View.VISIBLE);
            login.setVisibility(View.VISIBLE);
            loginview.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
            logoutview.setVisibility(View.GONE);
        } else {
            txtusername.setText(preferences.getString(Constants.KEY_USRNAME,"N/A"));
            txtusername.setVisibility(View.VISIBLE);
            txtemail.setText(preferences.getString(Constants.KEY_EMAIL,"N/A"));
            txtemail.setVisibility(View.VISIBLE);
            login.setVisibility(View.GONE);
            loginview.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
            logoutview.setVisibility(View.VISIBLE);
        }
        menu.setOnClickListener(v -> drawerLayout.openDrawer(Gravity.START));
        Cart.setOnClickListener(v -> {
            Intent intent = new Intent(DashBoardActivity.this, CartActivity.class);
            startActivity(intent);
        });
        Watches.setOnClickListener(v -> {
            Intent intent = new Intent(DashBoardActivity.this, MainActivity.class);
            startActivity(intent);
        });

        logout.setOnClickListener(v -> {
            AlertDialog.Builder alert=new AlertDialog.Builder(DashBoardActivity.this);
            alert.setTitle("Alert");
            alert.setMessage("Do you Really Want to Log Out ?");
            alert.setPositiveButton("YES", (dialog, which) -> {
                SharedPreferences.Editor editor=preferences.edit();
                editor.clear();
                editor.apply();
                Toast.makeText(DashBoardActivity.this,"You Have Been Logged Out!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),DashBoardActivity.class));
               finish();
            });
            alert.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());
            alert.show();
        });
        login.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        });


    }
}