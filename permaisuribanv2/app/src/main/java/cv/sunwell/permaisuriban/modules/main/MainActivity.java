package cv.sunwell.permaisuriban.modules.main;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.helper.BottomNavigationHelper;
import cv.sunwell.permaisuriban.modules.auth.AuthActivity;
import cv.sunwell.permaisuriban.modules.auth.login.LoginActivity;
import cv.sunwell.permaisuriban.modules.main.account.AccountFragment;
import cv.sunwell.permaisuriban.modules.main.cart.CartFragment;
import cv.sunwell.permaisuriban.modules.main.favourite.FavouriteFragment;
import cv.sunwell.permaisuriban.modules.main.home.HomeFragment;
import cv.sunwell.permaisuriban.modules.main.transaction.TransactionFragment;
import cv.sunwell.permaisuriban.networking.ApiInterface;
import cv.sunwell.permaisuriban.networking.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    ApiInterface apiInterface;
    private android.support.v7.app.ActionBar actionBar;
    private int userid;
    private String token;
    HomeFragment homeFragment = new HomeFragment();
    CartFragment cartFragment = new CartFragment();
    AccountFragment accountFragment = new AccountFragment();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

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
        apiInterface = ApiUtils.getAPIService();
        BottomNavigationView navigation = (BottomNavigationView)findViewById(R.id.navigation);
        BottomNavigationHelper.disableShiftMode (navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        actionBar = getSupportActionBar ();
        actionBar.setBackgroundDrawable (new ColorDrawable (Color.parseColor ("#EF1724")));
        int intentFragment = getIntent().getExtras().getInt("frgToLoad", 0);
        userid = getIntent().getExtras().getInt("userid", 0);
        token = getIntent().getExtras().getString("token");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
        switch (intentFragment){
            case 0 : getLoginUser(token,userid); loadFragment(new HomeFragment ()); break;
            case 1 : loadFragment(cartFragment); navigation.setSelectedItemId(R.id.navigation_cart); break;
            case 4 : loadFragment(accountFragment); navigation.setSelectedItemId(R.id.navigation_account); break;
        }


    }

    public void onLogout(){
        editor.remove("token");
        Intent intent = new Intent(MainActivity.this, AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void getLoginUser(String token, int userid) {
        final ProgressDialog loading = ProgressDialog.show(MainActivity.this, null, "Harap Tunggu...", true, false);
        Call<JsonObject> getCall = apiInterface.getUser(token, userid, userid);
        getCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body().get("success").getAsBoolean()) {
                    JsonObject message = response.body().get("message").getAsJsonObject();
                    loading.dismiss();
                    String firstname = message.get("firstname").toString();
                    String lastname = message.get("lastname").toString();
                    editor.putString("name",StringConverter.removeQuotation(firstname + " " + lastname));
                    editor.putString("email", StringConverter.removeQuotation(message.get("email").toString()));
                    editor.apply();
                } else {
                    loading.dismiss();
                    Toast.makeText(MainActivity.this, "Error : " + response.body().get("message"), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(MainActivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*private void prepareData(){
        accountFragment
    }*/
}
