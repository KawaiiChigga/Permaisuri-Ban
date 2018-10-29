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
import cv.sunwell.permaisuriban.model.Kategori;
import cv.sunwell.permaisuriban.modules.main.MainActivity;

public class HomeFragment extends Fragment
{
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HomeAdapter homeAdapter;
    private ArrayList<Kategori> merks = new ArrayList<> ();

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate (R.layout.fragment_home, container, false);
        recyclerView = view.findViewById (R.id.rvHome);
        insertKategori ();
        layoutManager = new LinearLayoutManager (getActivity ());
        homeAdapter = new HomeAdapter (merks, getContext ());
        recyclerView.setAdapter (homeAdapter);
        recyclerView.setLayoutManager (layoutManager);

        return view;
    }


    public void insertKategori ()
    {
        Kategori temp = new Kategori(1, "Standard",R.drawable.cat_passenger_suv);
        merks.add (temp);
        temp = new Kategori(2, "Race",R.drawable.cat_light_truck);
        merks.add (temp);
        temp = new Kategori(3, "Sport",R.drawable.cat_truck_buses);
        merks.add (temp);
        temp = new Kategori(4, "Dirt",R.drawable.cat_others);
        merks.add (temp);
    }


    public void onResume ()
    {
        super.onResume ();
        ((MainActivity) getActivity ()).setActionBarTitle (R.string.title_home);
    }
}
