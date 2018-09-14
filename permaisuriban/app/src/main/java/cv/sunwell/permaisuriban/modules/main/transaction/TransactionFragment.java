package cv.sunwell.permaisuriban.modules.main.transaction;

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

public class TransactionFragment extends Fragment
{

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TransactionAdapter transactionAdapter;
    private ArrayList<Item> listItem = new ArrayList<> ();

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate (R.layout.fragment_transaction, container, false);
        recyclerView = (RecyclerView) view.findViewById (R.id.rvTransaction);

        layoutManager = new LinearLayoutManager ((MainActivity) getActivity ());
        insertItem ();
        transactionAdapter = new TransactionAdapter (listItem,getActivity ());
        recyclerView.setAdapter (transactionAdapter);
        recyclerView.setLayoutManager (layoutManager);

        return view;
    }


    public void insertItem ()
    {
        Item temp = new Item ("DUNLOP","CS5 Ultra Touring", 125000, R.drawable.tire_dummy,1,"Passenger");
        listItem.add (temp);
        temp = new Item ("BRIDGESTONE","GT Champiro GTX Pro 185/65R15 88H", 960000, R.drawable.tire_dummy2,1,"Passenger");
        listItem.add (temp);

    }

    public void onResume ()
    {
        super.onResume ();
        ((MainActivity) getActivity ()).setActionBarTitle (R.string.title_transaction);
    }
}
