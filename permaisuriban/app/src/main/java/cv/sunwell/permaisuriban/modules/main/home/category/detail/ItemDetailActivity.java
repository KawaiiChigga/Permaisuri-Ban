package cv.sunwell.permaisuriban.modules.main.home.category.detail;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.main.MainActivity;
import cv.sunwell.permaisuriban.modules.main.home.HomeAdapter;
import cv.sunwell.permaisuriban.modules.main.home.category.HomeCategoryActivity;

public class ItemDetailActivity extends AppCompatActivity
{
    String name;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_item_detail);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);



        name =  getIntent ().getStringExtra ("name");
        setTitle (name);

    }
}
