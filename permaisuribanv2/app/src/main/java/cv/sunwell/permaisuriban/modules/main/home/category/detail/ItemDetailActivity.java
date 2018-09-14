package cv.sunwell.permaisuriban.modules.main.home.category.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cv.sunwell.permaisuriban.R;

public class ItemDetailActivity extends AppCompatActivity
{
    String name;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_item_detail);
        name =  getIntent ().getStringExtra ("name");
        setTitle (name);

    }
}
