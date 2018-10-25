package cv.sunwell.permaisuriban.modules.main.home;

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

public class HomeFragment extends Fragment
{
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HomeAdapter homeAdapter;
    private ArrayList<Item>  brandItem = new ArrayList<> ();

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate (R.layout.fragment_home, container, false);
        recyclerView = view.findViewById (R.id.rvHome);
        insertItem ();
        layoutManager = new LinearLayoutManager (getActivity ());
        homeAdapter = new HomeAdapter (brandItem, getContext ());
        recyclerView.setAdapter (homeAdapter);
        recyclerView.setLayoutManager (layoutManager);

        return view;
    }


    public void insertItem ()
    {
        Item temp = new Item(4, "Standard",R.drawable.cat_passenger_suv,"The Bridgestone Group is eternally committed to serving society with superior quality. ");
        brandItem.add (temp);
        temp = new Item(5, "Race",R.drawable.cat_light_truck,"For over 120 years, Dunlop has led the way in superior driving performance and excellence in racing. ");
        brandItem.add (temp);
        temp = new Item(6, "Sport",R.drawable.cat_truck_buses,"GT Radial has an environmental and humanitarian drive that ties into all actions. ");
        brandItem.add (temp);
        temp = new Item(7, "Dirt",R.drawable.cat_others,"GT Radial has an environmental and humanitarian drive that ties into all actions. ");
        brandItem.add (temp);
    }


    public void onResume ()
    {
        super.onResume ();
        ((MainActivity) getActivity ()).setActionBarTitle (R.string.title_home);
    }
}
