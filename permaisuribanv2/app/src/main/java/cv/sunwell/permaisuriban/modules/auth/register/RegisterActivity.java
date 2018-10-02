package cv.sunwell.permaisuriban.modules.auth.register;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.JsonObject;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.auth.AuthActivity;
import cv.sunwell.permaisuriban.networking.ApiInterface;
import cv.sunwell.permaisuriban.networking.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity
{

    Button btnRegister;
    ApiInterface apiInterface;
    EditText etRegUsername, etRegPassword, etRegIdNo, etRegEmail, etRegFirstname;
    String username, password, idno, email, firstname;
    ProgressDialog loading;

    @Override
    public void onBackPressed ()
    {
        super.onBackPressed ();
        Intent intent = new Intent(RegisterActivity.this, AuthActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onCreate (final Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);

        apiInterface = ApiUtils.getAPIService();
        btnRegister = findViewById(R.id.btnRegister);
        etRegUsername = findViewById(R.id.etRegUsername);
        etRegFirstname = findViewById(R.id.etRegFirstname);
        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPassword = findViewById(R.id.etRegPassword);
        etRegIdNo = findViewById(R.id.etRegIdNo);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register(){
        if(checkInputs()){
            loading = ProgressDialog.show(RegisterActivity.this, null, "Harap Tunggu...", true, false);
            username = etRegUsername.getText().toString();
            password = etRegPassword.getText().toString();
            email = etRegEmail.getText().toString();
            firstname = etRegFirstname.getText().toString();
            idno = etRegIdNo.getText().toString();

            JsonObject joCred = new JsonObject();
            joCred.addProperty("username", username);
            joCred.addProperty("password", password);
            joCred.addProperty("email", email);
            joCred.addProperty("firstname", firstname);
            joCred.addProperty("citizenid", idno);

            Call<JsonObject> call = apiInterface.registerUser(joCred);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.body().get("success").getAsBoolean()) {
                        Toast.makeText(RegisterActivity.this, "Register berhasil!" , Toast.LENGTH_SHORT).show();
                        //Toast.makeText(mContext, response.body().get("message").toString(), Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                        Intent intent = new Intent(RegisterActivity.this, AuthActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        loading.dismiss();
                        Toast.makeText(RegisterActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Error : " + t.toString(), Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            });
        }
        else{
            Toast.makeText(RegisterActivity.this, "Please fill out all fields!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkInputs(){
        return !etRegIdNo.getText().toString().equals("") && !etRegFirstname.getText().toString().equals("") && !etRegEmail.getText().toString().equals("") && !etRegPassword.getText().toString().equals("") && !etRegUsername.getText().toString().equals("");
    }
}
