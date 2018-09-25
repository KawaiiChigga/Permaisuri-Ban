package cv.sunwell.permaisuriban.modules.main;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.helper.BottomNavigationHelper;
import cv.sunwell.permaisuriban.modules.auth.AuthActivity;
import cv.sunwell.permaisuriban.modules.main.account.AccountFragment;
import cv.sunwell.permaisuriban.modules.main.cart.CartFragment;
import cv.sunwell.permaisuriban.modules.main.favourite.FavouriteFragment;
import cv.sunwell.permaisuriban.modules.main.home.HomeFragment;
import cv.sunwell.permaisuriban.modules.main.transaction.TransactionFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    private android.support.v7.app.ActionBar actionBar;

    public void setActionBarTitle(int title) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        TextView textView = findViewById (R.id.tvActionBarTitle);
        textView.setText (title);
    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;

    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_favourite:
                fragment = new FavouriteFragment ();
                break;

            case R.id.navigation_cart:
                fragment = new CartFragment ();
                break;

            case R.id.navigation_transaction:
                fragment = new TransactionFragment ();
                break;

            case R.id.navigation_account:
                fragment = new AccountFragment ();
                break;

        }

        return loadFragment(fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView)findViewById(R.id.navigation);
        BottomNavigationHelper.disableShiftMode (navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        actionBar = getSupportActionBar ();
        actionBar.setBackgroundDrawable (new ColorDrawable (Color.parseColor ("#EF1724")));
        int intentFragment = getIntent().getExtras().getInt("frgToLoad", 0);

        switch (intentFragment){
            case 0 : loadFragment(new HomeFragment ()); break;
            case 1 : loadFragment(new CartFragment ()); navigation.setSelectedItemId(R.id.navigation_cart); break;
            case 4 : loadFragment(new AccountFragment ()); navigation.setSelectedItemId(R.id.navigation_account); break;
        }
    }

    public void onLogout(){
        Intent intent = new Intent(MainActivity.this, AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
