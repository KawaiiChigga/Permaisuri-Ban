package cv.sunwell.permaisuriban.modules.main.account.edit.address;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Address;

import cv.sunwell.permaisuriban.modules.main.StringConverter;
import cv.sunwell.permaisuriban.networking.ApiInterface;
import cv.sunwell.permaisuriban.networking.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageAddressActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ManageAddressAdapter manageAddressAdapter;
    private ArrayList<Address> listAddress;
    int userid;
    String token;
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
        setContentView (R.layout.activity_manage_address);
        setTitle ("Manage Address");
        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        listAddress = new ArrayList<Address>();
        apiInterface = ApiUtils.getAPIService();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ManageAddressActivity.this);
        editor = sharedPreferences.edit();
        userid = sharedPreferences.getInt("userid", 0);
        token = sharedPreferences.getString("token", "");
        recyclerView = (RecyclerView) findViewById (R.id.rvAddress);
        initRecycleViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserAddress(token,userid);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater ().inflate (R.menu.menu_add, menu);
        MenuItem menuItem = menu.findItem (R.id.action_add);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent (ManageAddressActivity.this, NewAddressActivity.class);
                startActivity(intent);
                return false;
            }
        });
        return true;
    }

    private void initRecycleViews(){
        layoutManager = new LinearLayoutManager(ManageAddressActivity.this);
        manageAddressAdapter = new ManageAddressAdapter(listAddress,ManageAddressActivity.this);
        recyclerView.setAdapter (manageAddressAdapter);
        recyclerView.setLayoutManager (layoutManager);
    }

    private void getUserAddress(String token, int userid) {
        listAddress.clear();
        final ProgressDialog loading = ProgressDialog.show(ManageAddressActivity.this, null, "Harap Tunggu...", true, false);
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
                        JsonObject province = tempObject.get("province").getAsJsonObject();
                        tempAddress = new Address(tempObject.get("systemid").getAsInt(), regency.get("prov_id").getAsInt(), regency.get("systemid").getAsInt(), StringConverter.removeQuotation(tempObject.get("street").getAsString()), StringConverter.removeQuotation(regency.get("name").getAsString()), StringConverter.removeQuotation(province.get("name").getAsString()));
                        listAddress.add(tempAddress);
                    }
                    manageAddressAdapter.notifyDataSetChanged();
                    loading.dismiss();


                } else {
                    loading.dismiss();
                    Toast.makeText(ManageAddressActivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(ManageAddressActivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    void deleteAddress(int systemid){
        final ProgressDialog loading = ProgressDialog.show(ManageAddressActivity.this, null, "Harap Tunggu...", true, false);
        Call<JsonObject> getCall = apiInterface.deleteAddress(token, userid, userid, systemid);
        getCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body().get("success").getAsBoolean()) {
                    Toast.makeText(ManageAddressActivity.this, "Alamat berhasil dihapus!", Toast.LENGTH_SHORT).show();
                    listAddress.clear();
                    getUserAddress(token,userid);
                    manageAddressAdapter.notifyDataSetChanged();
                    loading.dismiss();
                } else {
                    loading.dismiss();
                    Toast.makeText(ManageAddressActivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(ManageAddressActivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
