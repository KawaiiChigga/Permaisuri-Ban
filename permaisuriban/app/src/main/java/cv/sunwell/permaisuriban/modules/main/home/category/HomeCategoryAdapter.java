package cv.sunwell.permaisuriban.modules.main.home.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;

public class HomeCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    private ArrayList<Item> allItem;
    private ArrayList<Item> passengerItem = new ArrayList<> ();
    private ArrayList<Item> highItem = new ArrayList<> ();
    private ArrayList<Item> biasItem = new ArrayList<> ();
    private final int HORIZONTAL = 2;
    private static int count = 0;

    public HomeCategoryAdapter (Context _context, ArrayList<Item> _allItem)
    {
        count = 0;
        this.context = _context;
        this.allItem = _allItem;
        getPassenger ();
        getHighPerfomance ();
        getBias ();
    }

    public  ArrayList<Item> getPassenger ()
    {
        for (Item item : allItem) {
            if(item.getCategory ().toLowerCase ().equals ("passenger"))
            {
                Log.d("TEST","Data : "+item.getName ());
                passengerItem.add (item);
            }
        }
        return passengerItem;
    }

    public  ArrayList<Item> getHighPerfomance ()
    {
        for (Item item : allItem) {
            if(item.getCategory ().toLowerCase ().equals ("highperfomance"))
            {
                Log.d("TEST","Data : "+item.getName ());
                highItem.add (item);
            }
        }
        return highItem;
    }

    public  ArrayList<Item> getBias ()
    {
        for (Item item : allItem) {
            if (item.getCategory ().toLowerCase ().equals ("bias")) {
                Log.d ("TEST", "Data : " + item.getName ());
                biasItem.add (item);
            }
        }
        return biasItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        String s_category = null;
        LayoutInflater inflater = LayoutInflater.from (context);
        View view;
        RecyclerView.ViewHolder holder;
        TextView category;

        view = inflater.inflate (R.layout.activity_home_category_horizontal, parent, false);
        category = view.findViewById (R.id.tvCategoryName);
        switch (count) {
            case 0:
                s_category = "Passenger";
                break;
            case 1:
                s_category = "High Perfomance";
                break;
            case 2:
                s_category = "Bias";
                break;
        }
        category.setText (s_category);
        holder = new HorizontalViewHolder (view);
        return holder;
    }

    @Override
    public void onBindViewHolder (@NonNull RecyclerView.ViewHolder holder, int position)
    {
        if (holder.getItemViewType () == HORIZONTAL) {
            horizontalView ((HorizontalViewHolder) holder);
        }
    }

    public void horizontalView (HorizontalViewHolder _holder)
    {
        HorizontalAdapter tempAdapter = null;
        switch (count) {
            case 0:
                tempAdapter = new HorizontalAdapter (passengerItem, context);
                break;
            case 1:
                tempAdapter = new HorizontalAdapter (highItem, context);
                break;
            case 2:
                tempAdapter = new HorizontalAdapter (biasItem, context);
                break;
        }

        _holder.recyclerView.setLayoutManager (new LinearLayoutManager (context, LinearLayoutManager.HORIZONTAL, false));
        _holder.recyclerView.setAdapter (tempAdapter);
        if (count == 3) {
            count = 0;
        } else {
            count++;
        }

    }

    @Override
    public int getItemCount ()
    {
        return allItem.size ();
    }


    @Override
    public int getItemViewType (int position)
    {
        return HORIZONTAL;
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder
    {
        RecyclerView recyclerView;

        HorizontalViewHolder (View _view)
        {
            super (_view);
            recyclerView = (RecyclerView) _view.findViewById (R.id.rvHorizontal);
        }
    }

    public void setFilter (ArrayList<Item> _arrayList)
    {
        count = 0;
        allItem = new ArrayList<> ();
        allItem.addAll (_arrayList);
        notifyDataSetChanged ();
    }




}
