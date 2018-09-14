package cv.sunwell.permaisuriban.modules.main.home.category;

import android.app.ActionBar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;

public class HomeCategoryActivity extends AppCompatActivity implements SearchView.OnQueryTextListener
{
    ArrayList<Item> itemArrayList = new ArrayList<> ();
    HomeCategoryAdapter homeCategoryAdapter;
    Toolbar toolbar;
    RecyclerView recyclerView;
    String brand, description;
    int imgURL;
    ImageView ivBrand;
    TextView tvDescription;

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
        toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);


        recyclerView = (RecyclerView) findViewById (R.id.rvHome);
        homeCategoryAdapter = new HomeCategoryAdapter (this, getItemArrayList ());
        recyclerView.setAdapter (homeCategoryAdapter);
        recyclerView.setLayoutManager (new LinearLayoutManager (this));
        recyclerView.setHasFixedSize (true);

        brand = getIntent ().getStringExtra ("brand");
        imgURL = getIntent ().getIntExtra ("imgURL", 0);
        description = getIntent ().getStringExtra ("description");

        Log.d ("TEST", "Description : "+description);
        Log.d("TEST","Brand : "+brand);

        ivBrand = (ImageView) findViewById (R.id.ivHomeBrand);
        tvDescription = (TextView) findViewById (R.id.tvHomeBrandDescription);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_layout);
        Toolbar toolbar = findViewById (R.id.toolbar);
        toolbar.setTitle (brand);

        ivBrand.setImageResource (imgURL);
        tvDescription.setText (description);
    }

    ArrayList<Item> getItemArrayList ()
    {
        itemArrayList.add (new Item ("GT RADIAL", "GT Champiro GTX Pro 185", 960000, R.drawable.tire_1, 1, "Passenger"));
        itemArrayList.add (new Item ("GT RADIAL", "GT Chomporo GTX Pro 186", 860000, R.drawable.tire_1, 1, "Passenger"));
        itemArrayList.add (new Item ("DUNLOP", "CS5 Ultra Touring", 125000, R.drawable.tire_2, 1, "HighPerfomance"));
        itemArrayList.add (new Item ("BRIDGESTONE", "DURAVIS 1", 1125000, R.drawable.tire_3, 1, "Bias"));
        return itemArrayList;
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
        for (Item item : itemArrayList) {
            String name = item.getName ().toLowerCase ();
            if (name.contains (newText)) {
                newList.add (item);
            }
        }
        homeCategoryAdapter.setFilter (newList);
        return true;
    }

}
