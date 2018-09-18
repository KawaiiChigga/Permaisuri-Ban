package cv.sunwell.permaisuriban.modules.main.home.category;

import android.app.ActionBar;
import android.os.Bundle;
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

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;

public class HomeCategoryActivity extends AppCompatActivity implements SearchView.OnQueryTextListener
{
    ArrayList<Item> itemArrayList = new ArrayList<> ();
    ArrayList<Item> brandList = new ArrayList<> ();
    ArrayList<Item> filterList = new ArrayList<> ();
    HomeCategoryAdapter homeCategoryAdapter;
    HomeBrandAdapter homeBrandAdapter;
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    String brand, description;
    int imgURL;
    private RecyclerView.LayoutManager layoutManager;

    public void setActionBarTitle(String _title) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_layout);
        Toolbar toolbar = findViewById (R.id.toolbar);
        toolbar.setTitle (_title);
    }

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_home_category);
        toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);

        recyclerView2 = findViewById (R.id.rvBrands);
        homeBrandAdapter = new HomeBrandAdapter(getBrandList(), this);
        recyclerView2.setAdapter (homeBrandAdapter);
        recyclerView2.setLayoutManager (new LinearLayoutManager  (this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setHasFixedSize (true);

        recyclerView = findViewById (R.id.rvHome);
        layoutManager = new GridLayoutManager(this, 3);
        homeCategoryAdapter = new HomeCategoryAdapter(getItemArrayList(), this);
        recyclerView.setAdapter (homeCategoryAdapter);
        recyclerView.setLayoutManager (layoutManager);
        recyclerView.setHasFixedSize (true);


        brand = getIntent ().getStringExtra ("brand");
        imgURL = getIntent ().getIntExtra ("imgURL", 0);
        description = getIntent ().getStringExtra ("description");

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_layout);
        Toolbar toolbar = findViewById (R.id.toolbar);
        toolbar.setTitle (brand);

        filterList.addAll(itemArrayList);
    }

    ArrayList<Item> getItemArrayList ()
    {
        itemArrayList.add (new Item ("GT RADIAL", "GT Champiro GTX Pro 185", 960000, R.drawable.tire_1, 500, "Passenger"));
        itemArrayList.add (new Item ("GT RADIAL", "GT Chomporo GTX Pro 186", 860000, R.drawable.tire_1, 250, "Passenger"));
        itemArrayList.add (new Item ("DUNLOP", "CS5 Ultra Touring", 125000, R.drawable.tire_2, 300, "High Perfomance"));
        itemArrayList.add (new Item ("BRIDGESTONE", "DURAVIS 1", 1125000, R.drawable.tire_3, 400, "Bias"));
        return itemArrayList;
    }

    ArrayList<Item> getBrandList ()
    {
        brandList.add (new Item ("GT", R.drawable.gtlogo, "abc"));
        brandList.add (new Item ("Bridgestone", R.drawable.bridgestonelogo, "abc"));
        brandList.add (new Item ("Dunlop", R.drawable.dunloplogo, "abc"));
        return brandList;
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater ().inflate (R.menu.menu_items, menu);
        MenuItem menuItem = menu.findItem (R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView (menuItem);
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
        newText = newText.toLowerCase ();
        ArrayList<Item> newList = new ArrayList<> ();
        for (Item item : filterList) {
            String name = item.getName ().toLowerCase ();
            if (name.contains (newText)) {
                newList.add (item);
            }
        }
        homeCategoryAdapter.setFilter (newList);
        return true;
    }

    public boolean FilterBrandEnable (String newText)
    {
        newText = newText.toLowerCase ();
        for (Item item : itemArrayList) {
            String brand = item.getBrand ().toLowerCase ();
            if (brand.contains (newText)) {
                filterList.add (item);
            }
        }
        homeCategoryAdapter.setFilter (filterList);
        return true;
    }

    public boolean FilterBrandDisable (String newText)
    {
        newText = newText.toLowerCase ();
        for (Item item : itemArrayList) {
            String brand = item.getBrand ().toLowerCase ();
            if (brand.contains (newText)) {
                filterList.remove (item);
            }
        }
        homeCategoryAdapter.setFilter (filterList);
        return true;
    }
}
