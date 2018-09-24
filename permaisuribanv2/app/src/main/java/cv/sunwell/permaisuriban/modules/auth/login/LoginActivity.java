package cv.sunwell.permaisuriban.modules.auth.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.JsonObject;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.JSON.Login;
import cv.sunwell.permaisuriban.modules.auth.AuthActivity;
import cv.sunwell.permaisuriban.modules.main.MainActivity;
import cv.sunwell.permaisuriban.networking.ApiInterface;
import cv.sunwell.permaisuriban.networking.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity
{

    Context mContext;
    ApiInterface apiInterface;
    ProgressDialog loading;
    EditText etLogUsername;
    EditText etLogPassword;

    @Override
    public void onBackPressed ()
    {
        //logout prevention
        Intent intent = new Intent(LoginActivity.this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        mContext = this;
        apiInterface = ApiUtils.getAPIService();
        etLogPassword = findViewById(R.id.etLogPassword);
        etLogUsername = findViewById(R.id.etLogUsername);
    }

    public void ocLoginNormal(View view) {
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
        requestLogin();
    }

    private void requestLogin(){
        Login loginInfo = new Login(etLogUsername.getText().toString(), etLogPassword.getText().toString());
        JsonObject jsonObject = new JsonObject();
        Call<Login> call = apiInterface.loginRequest(loginInfo);
        call.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                         if (response.body().isSuccess()){
                             Toast.makeText(LoginActivity.this, "Yay! Id : " + response.body().getId(), Toast.LENGTH_SHORT).show();
                             Toast.makeText(mContext, etLogUsername.getText().toString() + "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                             intent.putExtra("frgToLoad", 0);
                             startActivity(intent);
                             loading.dismiss();
                             finish();
                         } else {
                                loading.dismiss();
                                    Toast.makeText(mContext, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                         }
                    }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error : " + t.toString(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
                });
    }
}