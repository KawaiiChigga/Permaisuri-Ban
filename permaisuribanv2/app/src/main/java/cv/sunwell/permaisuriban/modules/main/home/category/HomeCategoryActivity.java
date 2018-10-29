package cv.sunwell.permaisuriban.modules.main.home.category;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;
import cv.sunwell.permaisuriban.networking.ApiInterface;
import cv.sunwell.permaisuriban.networking.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeCategoryActivity extends AppCompatActivity implements SearchView.OnQueryTextListener
{
    ArrayList<Item> itemArrayList = new ArrayList<> ();
    ArrayList<Item> brandList = new ArrayList<> ();
    ArrayList<Item> filterList = new ArrayList<> ();
    SearchView searchView;
    HomeCategoryAdapter homeCategoryAdapter;
    HomeBrandAdapter homeBrandAdapter;
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    ApiInterface apiInterface;
    private RecyclerView.LayoutManager layoutManager;
    SharedPreferences sharedPreferences;
    String token;
    int userid;
    String kategori;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_home_category);
        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        apiInterface = ApiUtils.getAPIService();
        recyclerView2 = findViewById (R.id.rvBrands);
        homeBrandAdapter = new HomeBrandAdapter(getBrandList(), this);
        recyclerView2.setAdapter (homeBrandAdapter);
        recyclerView2.setLayoutManager (new LinearLayoutManager  (this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setHasFixedSize (true);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(HomeCategoryActivity.this);
        userid = sharedPreferences.getInt("userid", 0);
        token = sharedPreferences.getString("token", "");
        recyclerView = findViewById (R.id.rvHome);
        layoutManager = new GridLayoutManager(this, 3);
        homeCategoryAdapter = new HomeCategoryAdapter(itemArrayList, this);
        recyclerView.setAdapter (homeCategoryAdapter);
        recyclerView.setLayoutManager (layoutManager);
        recyclerView.setHasFixedSize (true);
        kategori = HomeCategoryActivity.this.getIntent().getStringExtra("namakat");

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_layout);
        Toolbar toolbar = findViewById (R.id.toolbar);
        toolbar.setTitle (kategori);

        getItems(token, userid);
    }


    ArrayList<Item> getBrandList ()
    {
        brandList.add (new Item (1, "GT Radial", R.drawable.gtlogo, "abc"));
        brandList.add (new Item (2, "Bridgestone", R.drawable.bridgestonelogo, "abc"));
        brandList.add (new Item (3, "Michelin", R.drawable.michelinlogo, "abc"));
        brandList.add (new Item (4, "Pirelli", R.drawable.pirellilogo, "abc"));
        return brandList;
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater ().inflate (R.menu.menu_items, menu);
        MenuItem menuItem = menu.findItem (R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView (menuItem);
        searchView.setOnQueryTextListener (this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit (String query)
    {
        return false;
    }

    @Override
    public boolean onQueryTextChange (String newText)
    {
        SetSearch(newText);
        return true;
    }

    public void SetSearch (String newText)
    {
        newText = newText.toLowerCase ();
        ArrayList<Item> newList = new ArrayList<> ();
        for (Item item : filterList) {
            String name = item.getName ().toLowerCase ();
            if (name.contains (newText)) {
                newList.add (item);
            }
        }
        homeCategoryAdapter.setFilter (newList);
    }

    public boolean FilterBrandEnable (String newText)
    {
        newText = newText.toLowerCase ();

        for (Item item : itemArrayList) {
            String brand = item.getBrand ().toLowerCase ();
            String name = item.getName().toLowerCase();
            if (brand.contains (newText) && name.contains(searchView.getQuery().toString())) {
                filterList.add (item);
            }
        }

        Collections.sort(filterList);
        homeCategoryAdapter.setFilter (filterList);
        return true;
    }

    public boolean FilterBrandDisable (String newText)
    {
        newText = newText.toLowerCase ();
        for (Item item : itemArrayList) {
            String brand = item.getBrand ().toLowerCase ();
            String name = item.getName().toLowerCase();
            if (brand.contains (newText) && name.contains(searchView.getQuery().toString())) {
                filterList.remove (item);
            }
        }
        Collections.sort(filterList);
        homeCategoryAdapter.setFilter (filterList);
        return true;
    }

    private void getItems(String token, int userid) {
        final ProgressDialog loading = ProgressDialog.show(HomeCategoryActivity.this, null, "Memuat barang..", true, false);
        Call<JsonObject> getCall = apiInterface.getItem(token, userid);
        getCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body().get("success").getAsBoolean()) {
                    JsonArray message = response.body().get("message").getAsJsonArray();
                    Item tempItems;
                    for(int i=0; i<message.size();i++){
                        JsonObject tempObject = message.get(i).getAsJsonObject();
                        JsonArray categories = tempObject.get("categories").getAsJsonArray();
                        JsonObject category = categories.get(0).getAsJsonObject();
                        JsonObject merk = tempObject.get("merk").getAsJsonObject();
                        tempItems = new Item(tempObject.get("systemid").getAsInt(), merk.get("name").getAsString(), tempObject.get("name").getAsString(), Integer.parseInt(tempObject.get("sellprice").getAsString()), R.drawable.tire_1, category.get("name").getAsString());
                        if(kategori.equalsIgnoreCase(category.get("name").getAsString())){
                            itemArrayList.add(tempItems);
                        }
                    }
                    Collections.sort(itemArrayList);
                    filterList.addAll(itemArrayList);
                    homeCategoryAdapter.notifyDataSetChanged();
                    loading.dismiss();

                } else {
                    loading.dismiss();
                    Toast.makeText(HomeCategoryActivity.this, "Failed to get item data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(HomeCategoryActivity.this, "Failed to get item data", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
