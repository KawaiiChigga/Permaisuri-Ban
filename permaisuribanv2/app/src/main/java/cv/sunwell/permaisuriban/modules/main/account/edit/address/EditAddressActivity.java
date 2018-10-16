package cv.sunwell.permaisuriban.modules.main.account.edit.address;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Address;
import cv.sunwell.permaisuriban.model.Item;

import cv.sunwell.permaisuriban.modules.main.StringConverter;
import cv.sunwell.permaisuriban.networking.ApiInterface;
import cv.sunwell.permaisuriban.networking.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAddressActivity  extends AppCompatActivity {
    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private EditAddressAdapter editAddressAdapter;
    private ArrayList<Address> listAddress;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public void setActionBarTitle(String _title) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_layout);
        Toolbar toolbar = findViewById (R.id.toolbar);
        toolbar.setTitle (_title);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_edit_address);
        setTitle ("Manage Address");
        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        listAddress = new ArrayList<Address>();
        apiInterface = ApiUtils.getAPIService();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(EditAddressActivity.this);
        editor = sharedPreferences.edit();
        int userid = sharedPreferences.getInt("userid", 0);
        String token = sharedPreferences.getString("token", "");

        recyclerView = (RecyclerView) findViewById (R.id.rvAddress);
        getUserAddress(token, userid);



    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater ().inflate (R.menu.menu_add, menu);
        MenuItem menuItem = menu.findItem (R.id.action_add);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(EditAddressActivity.this, "YEE", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return true;
    }

    private void initRecycleViews(){
        layoutManager = new LinearLayoutManager(EditAddressActivity.this);
        editAddressAdapter = new EditAddressAdapter (listAddress,EditAddressActivity.this);
        recyclerView.setAdapter (editAddressAdapter);
        recyclerView.setLayoutManager (layoutManager);
    }

    private void getUserAddress(String token, int userid) {
        final ProgressDialog loading = ProgressDialog.show(EditAddressActivity.this, null, "Harap Tunggu...", true, false);
        Call<JsonObject> getCall = apiInterface.getAddress(token, userid, userid);
        getCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body().get("success").getAsBoolean()) {
                    JsonArray message = response.body().get("message").getAsJsonArray();
                    Address tempAddress;
                    for(int i=0; i<message.size();i++){
                        JsonObject tempObject = message.get(i).getAsJsonObject();
                        JsonObject regency = tempObject.get("regency").getAsJsonObject();
                        tempAddress = new Address(tempObject.get("systemid").getAsInt(), regency.get("prov_id").getAsInt(), regency.get("systemid").getAsInt(), StringConverter.removeQuotation(tempObject.get("street").getAsString()), StringConverter.removeQuotation(regency.get("name").getAsString()), "Halo");
                        listAddress.add(tempAddress);
                    }
                    loading.dismiss();
                    initRecycleViews();

                } else {
                    loading.dismiss();
                    Toast.makeText(EditAddressActivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(EditAddressActivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
