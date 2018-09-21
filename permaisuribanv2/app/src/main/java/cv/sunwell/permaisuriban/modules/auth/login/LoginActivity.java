package cv.sunwell.permaisuriban.modules.auth.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.auth.AuthActivity;
import cv.sunwell.permaisuriban.modules.main.MainActivity;

public class LoginActivity extends AppCompatActivity
{
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
    }

    public void ocLoginNormal(View view) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("frgToLoad", 0);
        startActivity(intent);
        finish();
    }
}