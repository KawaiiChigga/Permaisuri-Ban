package cv.sunwell.permaisuriban.modules.main.cart;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;
import cv.sunwell.permaisuriban.modules.auth.AuthActivity;
import cv.sunwell.permaisuriban.modules.main.MainActivity;
import cv.sunwell.permaisuriban.modules.main.account.edit.address.ManageAddressActivity;
import cv.sunwell.permaisuriban.modules.main.dialog.CartDeleteDialogFragment;
import cv.sunwell.permaisuriban.modules.main.home.category.HomeCategoryActivity;
import cv.sunwell.permaisuriban.networking.ApiInterface;
import cv.sunwell.permaisuriban.networking.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment
{

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CartAdapter cartAdapter;
    private ArrayList<Item> listItem = new ArrayList<> ();
    private Context context;
    private TextView tvTotal;
    ApiInterface apiInterface;
    int userid;
    String token;
    int total;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        context = (MainActivity) getActivity ();
        view = inflater.inflate (R.layout.fragment_cart, container, false);
        recyclerView = (RecyclerView) view.findViewById (R.id.rvCart);
        apiInterface = ApiUtils.getAPIService();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
        tvTotal = view.findViewById(R.id.tvCartTotalNominal);
        userid = sharedPreferences.getInt("userid", 0);
        token = sharedPreferences.getString("token", "");
        layoutManager = new LinearLayoutManager (context);
        cartAdapter = new CartAdapter (listItem, CartFragment.this);
        recyclerView.setAdapter (cartAdapter);
        recyclerView.setLayoutManager (layoutManager);
        getCartItems(token, userid);
        return view;
    }


    public void deleteItem(int id){
        deleteCartItem(token, userid, id);
    }

    public void onResume ()
    {
        super.onResume ();
        ((MainActivity) getActivity ()).setActionBarTitle (R.string.title_cart);
    }

    public void showDeleteDialog(String name, int imgUrl, int id){
        CartDeleteDialogFragment deleteDialog = new CartDeleteDialogFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putInt("imgUrl", imgUrl);
        args.putInt("itemId", id);
        deleteDialog.setArguments(args);
        deleteDialog.setTargetFragment(CartFragment.this, 1337);
        deleteDialog.show(fm, "dialog");

    }

    private void getCartItems(String token, int userid) {
        listItem.clear();
        total = 0;
        final ProgressDialog loading = ProgressDialog.show(context, null, "Memuat isi keranjang...", true, false);
        Call<JsonObject> getCall = apiInterface.getCart(token, userid, userid);
        getCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body().get("success").getAsBoolean()) {
                    JsonArray message = response.body().get("message").getAsJsonArray();
                    Item tempItem;
                    for(int i=0; i<message.size();i++){
                        JsonObject tempObject = message.get(i).getAsJsonObject();
                        JsonObject pivot = tempObject.get("pivot").getAsJsonObject();
                        tempItem = new Item(tempObject.get("systemid").getAsInt(), tempObject.get("name").getAsString(), tempObject.get("price").getAsInt(), R.drawable.tire_1, pivot.get("reqnote").getAsString(),  pivot.get("qty").getAsString());
                        listItem.add(tempItem);
                        Log.e("HELLO", tempItem.toString());
                        total += tempObject.get("price").getAsInt();
                    }
                    cartAdapter.notifyDataSetChanged();
                    tvTotal.setText("Rp. " + total);
                    loading.dismiss();


                } else {
                    loading.dismiss();
                    Toast.makeText(context, "Failed to get user data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(context, "Failed to get user data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteCartItem(String token, int userid, int id) {
        listItem.clear();
        final ProgressDialog loading = ProgressDialog.show(context, null, "Memproses...", true, false);
        Call<JsonObject> getCall = apiInterface.deleteAddress(token, userid, userid, id);
        getCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body().get("success").getAsBoolean()) {
                    Toast.makeText(view.getContext(), "Item telah dihapus dari keranjang", Toast.LENGTH_SHORT).show();
                    cartAdapter.notifyDataSetChanged();
                    loading.dismiss();
                } else {
                    loading.dismiss();
                    editor.remove("token");
                    editor.apply();
                    Intent intent = new Intent(context, AuthActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(context, "Gagal menghapus item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
