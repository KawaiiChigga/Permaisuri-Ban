package cv.sunwell.permaisuriban.modules.main.home.category.detail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;
import cv.sunwell.permaisuriban.modules.auth.AuthActivity;
import cv.sunwell.permaisuriban.modules.auth.register.RegisterActivity;
import cv.sunwell.permaisuriban.modules.main.MainActivity;
import cv.sunwell.permaisuriban.modules.main.StringConverter;
import cv.sunwell.permaisuriban.modules.main.cart.CartFragment;
import cv.sunwell.permaisuriban.modules.main.dialog.BuyItemDialogFragment;
import cv.sunwell.permaisuriban.modules.main.dialog.CartDeleteDialogFragment;
import cv.sunwell.permaisuriban.modules.main.home.category.HomeCategoryActivity;
import cv.sunwell.permaisuriban.networking.ApiInterface;
import cv.sunwell.permaisuriban.networking.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailActivity extends AppCompatActivity
{
    String name;
    int price;
    int imgURL;
    String brand;
    String category;
    String description;
    String count;
    int id;
    int userid;
    String token;
    ApiInterface apiInterface;
    TextView tvDetailName;
    TextView tvDetailPrice;
    ImageView ivItemDetail;
    TextView tvDetailBrand;
    TextView tvDetailCategory;
    TextView tvDetailDescription;
    TextView tvDetailCount;
    Button btnItemDetailBuy;
    Button btnItemDetailFav;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressDialog loading;

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
        count = getIntent().getStringExtra("count");
        id = getIntent().getIntExtra("id", 0);
        setTitle (name);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ItemDetailActivity.this);
        userid = sharedPreferences.getInt("userid", 0);
        token = sharedPreferences.getString("token", "");
        editor = sharedPreferences.edit();
        apiInterface = ApiUtils.getAPIService();

        tvDetailName = findViewById (R.id.tvItemDetailName);
        tvDetailPrice = findViewById (R.id.tvItemDetailPrice);
        ivItemDetail = findViewById(R.id.ivItemDetail);
        tvDetailBrand = findViewById(R.id.tvItemDetailBrand);
        tvDetailCategory = findViewById(R.id.tvItemDetailCategory);
        tvDetailDescription = findViewById(R.id.tvItemDetailDescription);
        tvDetailCount = findViewById(R.id.tvItemDetailStockCount);
        btnItemDetailBuy = findViewById(R.id.btnItemDetailBuy);
        btnItemDetailFav = findViewById(R.id.btnItemDetailFav);

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
        btnItemDetailFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFavorite();
            }
        });
    }

    public void addSomething(int count){
        loading = ProgressDialog.show(ItemDetailActivity.this, null, "Harap Tunggu...", true, false);

        JsonObject joCred = new JsonObject();
        joCred.addProperty("qty", count);
        joCred.addProperty("reqnote", "");
        joCred.addProperty("cust", userid);
        joCred.addProperty("product", id);

        Call<JsonObject> call = apiInterface.addCartItem(token, userid, joCred);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body().get("success").getAsBoolean()) {
                    Toast.makeText(ItemDetailActivity.this, "Barang berhasil ditambahkan ke keranjang!" , Toast.LENGTH_SHORT).show();
                    //Toast.makeText(mContext, response.body().get("message").toString(), Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
                else {
                    loading.dismiss();
                    Toast.makeText(ItemDetailActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(ItemDetailActivity.this, "Error : " + t.toString(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

    public void addToFavorite(){
        Toast.makeText(ItemDetailActivity.this, "Ditambah ke favorit!", Toast.LENGTH_SHORT).show();
    }

    public void addSomethingGo(int count){
        loading = ProgressDialog.show(ItemDetailActivity.this, null, "Harap Tunggu...", true, false);

        JsonObject joCred = new JsonObject();
        joCred.addProperty("qty", count);
        joCred.addProperty("reqnote", "");
        joCred.addProperty("cust", userid);
        joCred.addProperty("product", id);

        Call<JsonObject> call = apiInterface.addCartItem(token, userid, joCred);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (StringConverter.removeQuotation(response.body().get("success").getAsString()).equalsIgnoreCase("Cart Detail successfully created!")) {
                    Toast.makeText(ItemDetailActivity.this, "Barang berhasil ditambahkan ke keranjang!" , Toast.LENGTH_SHORT).show();
                    //Toast.makeText(mContext, response.body().get("message").toString(), Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                    Intent i = new Intent(ItemDetailActivity.this, MainActivity.class);
                    i.putExtra("frgToLoad", 1);
                    startActivity(i);
                    finish();
                }
                else {
                    loading.dismiss();
                    Toast.makeText(ItemDetailActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(ItemDetailActivity.this, "Error : " + t.toString(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });

    }

    public void showAddDialog(){
        BuyItemDialogFragment buyDialog = new BuyItemDialogFragment();
        FragmentManager fm = getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putInt("imgUrl", imgURL);
        args.putInt("price", price);
        buyDialog.setArguments(args);
        buyDialog.show(fm, "dialog");
    }

}
