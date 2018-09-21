package cv.sunwell.permaisuriban.modules.main.cart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;
import cv.sunwell.permaisuriban.modules.main.MainActivity;
import cv.sunwell.permaisuriban.modules.main.dialog.CartDeleteDialogFragment;

public class CartFragment extends Fragment
{

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CartAdapter cartAdapter;
    private ArrayList<Item> listItem = new ArrayList<> ();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate (R.layout.fragment_cart, container, false);
        recyclerView = (RecyclerView) view.findViewById (R.id.rvCart);

        layoutManager = new LinearLayoutManager ((MainActivity) getActivity ());
        insertItem ();
        cartAdapter = new CartAdapter (listItem, CartFragment.this);
        recyclerView.setAdapter (cartAdapter);
        recyclerView.setLayoutManager (layoutManager);

        return view;
    }

    public void insertItem ()
    {
        Item temp = new Item (55, "DUNLOP", "CS5 Ultra Touring", 1250000, R.drawable.tire_dummy, 3, "Passenger");
        listItem.add (temp);
        temp = new Item (56, "DUNLOP", "GT Champiro GTX Pro 185/65R15", 960000, R.drawable.tire_dummy2, 4, "Passenger");
        listItem.add (temp);
        temp = new Item (57, "DUNLOP", "Full OFFROAD", 850000, R.drawable.tire_dummy3, 3, "Passenger");
        listItem.add (temp);
        temp = new Item (58, "DUNLOP", "Ban Super Baik", 1900000, R.drawable.tire_dummy, 3, "Passenger");
        listItem.add (temp);
        temp = new Item (59, "DUNLOP", "Ban Super Jahat", 1900000, R.drawable.tire_dummy, 3, "Passenger");
        listItem.add (temp);
        //maks nama 25 karakter
    }

    public void deleteItem(int position){
        cartAdapter.deleteCartItem(position);
    }

    public void onResume ()
    {
        super.onResume ();
        ((MainActivity) getActivity ()).setActionBarTitle (R.string.title_cart);
    }

    public void showDeleteDialog(String name, int imgUrl, int position){
        CartDeleteDialogFragment deleteDialog = new CartDeleteDialogFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putInt("imgUrl", imgUrl);
        args.putInt("position", position);
        deleteDialog.setArguments(args);
        deleteDialog.setTargetFragment(CartFragment.this, 1337);
        deleteDialog.show(fm, "dialog");

    }
}
