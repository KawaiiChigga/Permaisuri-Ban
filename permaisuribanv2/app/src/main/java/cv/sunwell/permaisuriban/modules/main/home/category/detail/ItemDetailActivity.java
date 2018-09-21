package cv.sunwell.permaisuriban.modules.main.home.category.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.main.MainActivity;
import cv.sunwell.permaisuriban.modules.main.cart.CartFragment;
import cv.sunwell.permaisuriban.modules.main.dialog.BuyItemDialogFragment;
import cv.sunwell.permaisuriban.modules.main.dialog.CartDeleteDialogFragment;

public class ItemDetailActivity extends AppCompatActivity
{
    String name;
    int price;
    int imgURL;
    String brand;
    String category;
    String description;
    int count;

    TextView tvDetailName;
    TextView tvDetailPrice;
    ImageView ivItemDetail;
    TextView tvDetailBrand;
    TextView tvDetailCategory;
    TextView tvDetailDescription;
    TextView tvDetailCount;
    Button btnItemDetailBuy;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_item_detail);
        name =  getIntent ().getStringExtra ("name");
        price =  getIntent ().getIntExtra ("price", 0);
        imgURL = getIntent().getIntExtra("imgURL", 0);
        brand = getIntent().getStringExtra("brand");
        category = getIntent().getStringExtra("category");
        description = getIntent().getStringExtra("description");
        count = getIntent().getIntExtra("count", 0);
        setTitle (name);

        tvDetailName = findViewById (R.id.tvItemDetailName);
        tvDetailPrice = findViewById (R.id.tvItemDetailPrice);
        ivItemDetail = findViewById(R.id.ivItemDetail);
        tvDetailBrand = findViewById(R.id.tvItemDetailBrand);
        tvDetailCategory = findViewById(R.id.tvItemDetailCategory);
        tvDetailDescription = findViewById(R.id.tvItemDetailDescription);
        tvDetailCount = findViewById(R.id.tvItemDetailStockCount);
        btnItemDetailBuy = findViewById(R.id.btnItemDetailBuy);

        tvDetailName.setText(name);
        tvDetailPrice.setText("Rp. " + price);
        ivItemDetail.setImageResource(imgURL);
        tvDetailBrand.setText("Brand        :     " + brand);
        tvDetailCategory.setText("Category  :     " + category);
        tvDetailCount.setText(String.valueOf(count));
        //tvDetailDescription.setText(description);
        btnItemDetailBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });
    }

    public void addSomething(){
        Toast.makeText(ItemDetailActivity.this, "Tambah", Toast.LENGTH_SHORT).show();
    }

    public void addSomethingGo(){
        Toast.makeText(ItemDetailActivity.this, "Tambah dan pergi", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("frgToLoad", 1);
        startActivity(i);
        finish();
    }

    public void showAddDialog(){
        BuyItemDialogFragment buyDialog = new BuyItemDialogFragment();
        FragmentManager fm = getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putInt("imgUrl", imgURL);
        buyDialog.setArguments(args);
        buyDialog.show(fm, "dialog");
    }
}
