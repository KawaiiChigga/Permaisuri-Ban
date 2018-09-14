package cv.sunwell.permaisuriban.modules.main.favourite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>
{

    ArrayList<Item> alFavouriteItem = new ArrayList<> ();
    Context context;

    public FavouriteAdapter (ArrayList<Item> _alFavouriteItem, Context _context)
    {
        this.alFavouriteItem = _alFavouriteItem;
        this.context = _context;
    }

    @NonNull
    @Override
    public FavouriteAdapter.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.fragment_favourite_item, parent, false);
        return new FavouriteAdapter.MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder (@NonNull FavouriteAdapter.MyViewHolder holder, int position)
    {
        Log.d ("TEST", "Name : " + alFavouriteItem.get (position).getName ());
        Log.d ("TEST", "Brand : " + alFavouriteItem.get (position).getBrand ());
        Log.d ("TEST", "Price : " + alFavouriteItem.get (position).getPrice ());
        Log.d ("TEST", "URL : " + alFavouriteItem.get (position).getImgURL ());
        holder.tvFavItemName.setText (alFavouriteItem.get (position).getName ());
        holder.tvFavItemBrand.setText (alFavouriteItem.get (position).getBrand ());
        holder.tvFavItemPrice.setText ("Rp. " + alFavouriteItem.get (position).getPrice ());
        holder.ivFavItemImg.setImageResource (alFavouriteItem.get (position).getImgURL ());

    }

    @Override
    public int getItemCount ()
    {
        return alFavouriteItem.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvFavItemBrand;
        TextView tvFavItemName;
        TextView tvFavItemPrice;
        ImageView ivFavItemImg;
        Button btnFavBuy;

        public MyViewHolder (View itemView)
        {
            super (itemView);
            tvFavItemBrand = (TextView) itemView.findViewById (R.id.tvFavItemBrand);
            tvFavItemName = (TextView) itemView.findViewById (R.id.tvFavItemName);
            tvFavItemPrice = (TextView) itemView.findViewById (R.id.tvFavItemPrice);
            ivFavItemImg = (ImageView) itemView.findViewById (R.id.ivFavItemImage);
            btnFavBuy = (Button) itemView.findViewById (R.id.btnFavBuy);

        }
    }

    public void setFilter (ArrayList<Item> _alFavouriteItem)
    {
        alFavouriteItem = new ArrayList<> ();
        alFavouriteItem.addAll (_alFavouriteItem);
        notifyDataSetChanged ();
    }
}
