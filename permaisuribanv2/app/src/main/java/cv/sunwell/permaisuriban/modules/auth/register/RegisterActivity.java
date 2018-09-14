package cv.sunwell.permaisuriban.modules.auth.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.auth.AuthActivity;

public class RegisterActivity extends AppCompatActivity
{
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
    }
}
