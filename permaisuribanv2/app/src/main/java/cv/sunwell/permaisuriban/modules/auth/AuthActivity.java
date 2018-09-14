package cv.sunwell.permaisuriban.modules.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.auth.login.LoginActivity;
import cv.sunwell.permaisuriban.modules.auth.register.RegisterActivity;
import cv.sunwell.permaisuriban.modules.main.MainActivity;
import cv.sunwell.permaisuriban.modules.splashscreen.SplashScreenActivity;

public class AuthActivity extends AppCompatActivity
{

    Button authLogin, authSignUp;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_auth);
        authLogin = findViewById (R.id.btnAuthLogin);
        authSignUp = findViewById (R.id.btnAuthSignUp);

        authLogin.setOnClickListener (new View.OnClickListener ()
        {

            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent (AuthActivity.this, LoginActivity.class);
                startActivity (intent);
                finish ();
            }
        });
        authSignUp.setOnClickListener (new View.OnClickListener ()
        {

            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent (AuthActivity.this, RegisterActivity.class);
                startActivity (intent);
                finish ();
            }
        });

    }
}
