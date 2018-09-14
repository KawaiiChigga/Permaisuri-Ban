package cv.sunwell.permaisuriban.modules.main.cart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;
import cv.sunwell.permaisuriban.modules.main.MainActivity;

public class CartFragment extends Fragment
{

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CartAdapter cartAdapter;
    private ArrayList<Item> listItem = new ArrayList<> ();

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate (R.layout.fragment_cart, container, false);
        recyclerView = (RecyclerView) view.findViewById (R.id.rvCart);

        layoutManager = new LinearLayoutManager ((MainActivity) getActivity ());
        insertItem ();
        cartAdapter = new CartAdapter (listItem);
        recyclerView.setAdapter (cartAdapter);
        recyclerView.setLayoutManager (layoutManager);

        return view;
    }

    public void insertItem ()
    {
        Item temp = new Item ("DUNLOP", "CS5 Ultra Touring", 1250000, R.drawable.tire_dummy, 3, "Passenger");
        listItem.add (temp);
        temp = new Item ("DUNLOP", "GT Champiro GTX Pro 185/65R15", 960000, R.drawable.tire_dummy2, 4, "Passenger");
        listItem.add (temp);
        temp = new Item ("DUNLOP", "Full OFFROAD", 850000, R.drawable.tire_dummy3, 3, "Passenger");
        listItem.add (temp);
        temp = new Item ("DUNLOP", "Ban Super Baik", 1900000, R.drawable.tire_dummy, 3, "Passenger");
        listItem.add (temp);
        //maks nama 25 karakter
    }


    public void onResume ()
    {
        super.onResume ();
        ((MainActivity) getActivity ()).setActionBarTitle (R.string.title_cart);
    }
}
