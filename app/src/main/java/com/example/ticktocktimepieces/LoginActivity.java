package com.example.ticktocktimepieces;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ticktocktimepieces.models.LoginResponseModel;
import com.example.ticktocktimepieces.network.NetworkClient;
import com.example.ticktocktimepieces.network.NetworkServices;
import com.example.ticktocktimepieces.utils.Constants;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ticktocktimepieces.utils.Constants.KEY_IS_LOGGED_IN;

public class LoginActivity extends AppCompatActivity {

    TextView textCreateAccount;
    AutoCompleteTextView inputEmail, inputPassword;
    TextView buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textCreateAccount = findViewById(R.id.tvSignIn);
        textCreateAccount.setPaintFlags(textCreateAccount.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textCreateAccount.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));

        inputEmail = findViewById(R.id.atvEmailLog);
        inputPassword = findViewById(R.id.atvPasswordLog);
       ImageView skip = findViewById(R.id.skipimg);
        skip.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),DashBoardActivity.class));
            finish();
        });

        buttonLogin = findViewById(R.id.btnSignIn);

        buttonLogin.setOnClickListener(v -> {
            if (inputEmail.getText().toString()==null &&inputEmail.getText().toString().equals("")) {
                Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
            } else if (inputPassword.getText().toString().equals("")) {
                Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
            } else {
                login();
            }

        });
    }

    private void login() {
        AlertDialog alertDialog = new SpotsDialog(LoginActivity.this, R.style.Custom3);
        alertDialog.show();

        NetworkServices networkServices = NetworkClient.getClient().create(NetworkServices.class);
        Call<LoginResponseModel> login = networkServices.login(inputEmail.getText().toString(), inputPassword.getText().toString());
        login.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                LoginResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        SharedPreferences preferences=getSharedPreferences(Constants.PREFERENCE_NAME,MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(KEY_IS_LOGGED_IN,true);
                        editor.putString(Constants.KEY_USRNAME,responseBody.getUserDetailsObject().getUser_Details().get(0).getFirst_name()+ " " +responseBody.getUserDetailsObject().getUser_Details().get(0).getLast_name());
                        editor.putString(Constants.KEY_EMAIL,responseBody.getUserDetailsObject().getUser_Details().get(0).getEmail());
                        editor.apply();
                        Toast.makeText(LoginActivity.this, responseBody.getMassage(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this,DashBoardActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, responseBody.getMassage(), Toast.LENGTH_LONG).show();
                    }
                }
                alertDialog.cancel();
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "in onFailure", Toast.LENGTH_SHORT).show();
                alertDialog.cancel();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LoginActivity.this,DashBoardActivity.class));
    }
}




