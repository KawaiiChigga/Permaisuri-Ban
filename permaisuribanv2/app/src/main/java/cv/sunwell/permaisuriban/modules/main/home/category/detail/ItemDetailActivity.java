package cv.sunwell.permaisuriban.modules.main.home.category.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import cv.sunwell.permaisuriban.R;

public class ItemDetailActivity extends AppCompatActivity
{
    String name;
    String price;
    TextView tvDetailName;
    TextView tvDetailPrice;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_item_detail);
        name =  getIntent ().getStringExtra ("name");
        price =  getIntent ().getStringExtra ("price");
        setTitle (name);

        tvDetailName = findViewById (R.id.tvItemDetailName);
        tvDetailPrice = findViewById (R.id.tvItemDetailPrice);
        tvDetailName.setText(name);
        tvDetailPrice.setText(price);
    }
}
