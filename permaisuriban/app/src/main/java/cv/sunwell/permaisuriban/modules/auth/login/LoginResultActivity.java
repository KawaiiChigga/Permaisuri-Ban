package cv.sunwell.permaisuriban.modules.auth.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.splashscreen.SplashScreenActivity;

public class LoginResultActivity extends AppCompatActivity
{

    String username,email;
    TextView tvUsername, tvEmail;
    Button btnLogout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AlertDialog alertDialog;

    @Override
    public void onBackPressed ()
    {

    }

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login_result);

        tvUsername = (TextView) findViewById (R.id.tvResultUsername);
        tvEmail = (TextView) findViewById (R.id.tvResultEmail);
        btnLogout = (Button) findViewById (R.id.btnLogout);
        sharedPreferences = getSharedPreferences ("loginPref", Context.MODE_PRIVATE);
        username = sharedPreferences.getString ("userName","");

        if(username.length () == 0)
        {
            Intent intent = new Intent(LoginResultActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        btnLogout.setOnClickListener (new View.OnClickListener ()
        {
            @Override
            public void onClick (View v)
            {
                alertDialog = new AlertDialog.Builder (LoginResultActivity.this).create ();
                alertDialog.setTitle ("Logout");
                alertDialog.setMessage ("Are you sure you want to logout ?");
                alertDialog.setCancelable (false);
                alertDialog.setButton (DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener ()
                {
                    @Override
                    public void onClick (DialogInterface dialog, int which)
                    {
                        alertDialog.dismiss ();
                    }
                });

                alertDialog.setButton (DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener ()
                {
                    @Override
                    public void onClick (DialogInterface dialog, int which)
                    {
                        editor = sharedPreferences.edit ();
                        editor.remove ("userName");
                        editor.clear ();
                        editor.commit ();
                        Intent intent = new Intent (LoginResultActivity.this, LoginActivity.class);
                        startActivity (intent);
                    }
                });
                alertDialog.show ();
            }
        });


        username = sharedPreferences.getString ("userName","");
        email = sharedPreferences.getString ("email","");

        tvUsername.setText (username);
        tvEmail.setText (email);

    }
}
