package cv.sunwell.permaisuriban.modules.main.favourite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;
import cv.sunwell.permaisuriban.modules.main.MainActivity;
import cv.sunwell.permaisuriban.modules.main.dialog.FavoriteDialogFragment;

public class FavouriteFragment extends Fragment implements SearchView.OnQueryTextListener
{

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FavouriteAdapter favouriteAdapter;
    private ArrayList<Item> listItem = new ArrayList<> ();

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        view = inflater.inflate (R.layout.fragment_favourite, container, false);
        recyclerView = (RecyclerView) view.findViewById (R.id.rvFavourite);
        layoutManager = new GridLayoutManager ((MainActivity) getActivity (), 3);
        insertItem ();
        favouriteAdapter = new FavouriteAdapter (listItem, getActivity (), FavouriteFragment.this);
        recyclerView.setAdapter (favouriteAdapter);
        recyclerView.setLayoutManager (layoutManager);

        return view;
    }

    public void insertItem ()
    {
        Item temp = new Item (22, "DUNLOP", "BAN 1", 125000, R.drawable.tire_1, "1","Passenger");
        listItem.add (temp);
        temp = new Item (23, "BRIDGESTONE", "BAN 2", 960000, R.drawable.tire_2, "1","Passenger");
        listItem.add (temp);
        temp = new Item(24, "GT RADIAL", "BAN 3", 1750000,R.drawable.tire_3, "1","Passenger");
        listItem.add (temp);
        temp = new Item(25, "BRIDGESTONE", "BAN 4", 1750000,R.drawable.tire_3, "1","Passenger");
        listItem.add (temp);
        temp = new Item(26, "GT RADIAL", "BAN 5", 1750000,R.drawable.tire_3, "1","Passenger");
        listItem.add (temp);
        temp = new Item(27, "GT RADIAL", "BAN 6", 1750000,R.drawable.tire_3, "1","Passenger");
        listItem.add (temp);
        temp = new Item (28, "DUNLOP", "BAN 7", 125000, R.drawable.tire_1, "1","Passenger");
        listItem.add (temp);

    }

    public void onResume ()
    {
        super.onResume ();
        ((MainActivity) getActivity ()).setActionBarTitle (R.string.title_favourites);
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu (menu, inflater);
        getActivity ().getMenuInflater ().inflate (R.menu.menu_items,menu);
        MenuItem menuItem = menu.findItem (R.id.action_search);
        SearchView searchView = (SearchView)MenuItemCompat.getActionView (menuItem);
        searchView.setOnQueryTextListener (this);
    }

    @Override
    public boolean onQueryTextSubmit (String query)
    {
        return false;
    }

    @Override
    public boolean onQueryTextChange (String newText)
    {
        newText = newText.toLowerCase ();
        ArrayList<Item> newList = new ArrayList<> ();
        for(Item item : newList)
        {
            String name = item.getName ().toLowerCase ();
            if(name.contains (newText))
            {
                newList.add (item);
            }
        }

        favouriteAdapter.setFilter (newList);
        return true;
    }

    public void showFavoriteDialog(String name, int imgUrl, int position){
        FavoriteDialogFragment favDialog = new FavoriteDialogFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putInt("imgUrl", imgUrl);
        args.putInt("position", position);
        favDialog.setArguments(args);
        favDialog.setTargetFragment(FavouriteFragment.this, 1338);
        favDialog.show(fm, "dialog");

    }

    public void goItem(int pos) {
        favouriteAdapter.putSend(pos);
    }

    public void deleteItem(int pos) {
        favouriteAdapter.deleteFavItem(pos);
    }


}
