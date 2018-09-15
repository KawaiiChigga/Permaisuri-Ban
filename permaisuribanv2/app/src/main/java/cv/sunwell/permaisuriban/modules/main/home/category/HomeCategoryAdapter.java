package cv.sunwell.permaisuriban.modules.main.home.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.MyViewHolder>
{

    ArrayList<Item> alCategoryItem = new ArrayList<> ();
    Context context;

    public HomeCategoryAdapter (ArrayList<Item> _alCategoryItem, Context _context)
    {
        this.alCategoryItem = _alCategoryItem;
        this.context = _context;
    }

    @NonNull
    @Override
    public HomeCategoryAdapter.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.activity_home_category_item, parent, false);
        return new HomeCategoryAdapter.MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder (@NonNull HomeCategoryAdapter.MyViewHolder holder, int position)
    {
        Log.d ("TEST", "Name : " + alCategoryItem.get (position).getName ());
        Log.d ("TEST", "Brand : " + alCategoryItem.get (position).getBrand ());
        Log.d ("TEST", "Price : " + alCategoryItem.get (position).getPrice ());
        Log.d ("TEST", "URL : " + alCategoryItem.get (position).getImgURL ());
        holder.tvCatItemName.setText (alCategoryItem.get (position).getName ());
        holder.tvCatItemBrand.setText (alCategoryItem.get (position).getBrand ());
        holder.tvCatItemPrice.setText ("Rp. " + alCategoryItem.get (position).getPrice ());
        holder.ivCatItemImg.setImageResource (alCategoryItem.get (position).getImgURL ());

    }

    @Override
    public int getItemCount ()
    {
        return alCategoryItem.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvCatItemBrand;
        TextView tvCatItemName;
        TextView tvCatItemPrice;
        ImageView ivCatItemImg;

        public MyViewHolder (View itemView)
        {
            super (itemView);
            tvCatItemBrand = itemView.findViewById (R.id.tvHorBrand);
            tvCatItemName = itemView.findViewById (R.id.tvHorName);
            tvCatItemPrice = itemView.findViewById (R.id.tvHorPrice);
            ivCatItemImg = itemView.findViewById (R.id.ivHorImg);

        }
    }

    public void setFilter (ArrayList<Item> _alFavouriteItem)
    {
        alCategoryItem = new ArrayList<> ();
        alCategoryItem.addAll (_alFavouriteItem);
        notifyDataSetChanged ();
    }
}
