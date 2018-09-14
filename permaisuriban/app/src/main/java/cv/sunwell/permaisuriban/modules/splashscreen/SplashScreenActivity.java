package cv.sunwell.permaisuriban.modules.splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.auth.AuthActivity;
import cv.sunwell.permaisuriban.modules.main.MainActivity;
import cv.sunwell.permaisuriban.modules.main.home.category.HomeCategoryActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    SharedPreferences sharedPreferences;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                sharedPreferences = getSharedPreferences ("loginPref", Context.MODE_PRIVATE);
//                username = sharedPreferences.getString ("userName","");
//                if(username.length () != 0)
//                {
//                    Intent intent = new Intent(SplashScreenActivity.this, LoginResultActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                else
//                {
//                    Intent intent = new Intent (SplashScreenActivity.this, LoginActivity.class);
//                    startActivity (intent);
//                    finish ();
//                }

                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        },SPLASH_TIME_OUT);

    }
}
