package cv.sunwell.permaisuriban.modules.main.favourite;

import android.content.Context;
import android.content.Intent;
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
import cv.sunwell.permaisuriban.modules.main.home.category.detail.ItemDetailActivity;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>
{

    ArrayList<Item> alFavouriteItem = new ArrayList<> ();
    Context context;
    public FavouriteFragment favouriteFragmente;

    public FavouriteAdapter (ArrayList<Item> _alFavouriteItem, Context _context, FavouriteFragment _favouriteFragmente)
    {
        this.alFavouriteItem = _alFavouriteItem;
        this.context = _context;
        this.favouriteFragmente = _favouriteFragmente;
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
        holder.favouriteFragment = favouriteFragmente;
        holder.tvFavItemName.setText (alFavouriteItem.get (position).getName ());
        holder.tvFavItemBrand.setText (alFavouriteItem.get (position).getBrand ());
        holder.tvFavItemPrice.setText ("Rp. " + alFavouriteItem.get (position).getPrice ());
        holder.ivFavItemImg.setImageResource (alFavouriteItem.get (position).getImgURL ());
        holder.favItemName = alFavouriteItem.get (position).getName ();
        holder.imgUrl = alFavouriteItem.get (position).getImgURL ();
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
        FavouriteFragment favouriteFragment;
        String favItemName;
        int imgUrl;

        public MyViewHolder (View itemView)
        {
            super (itemView);
            tvFavItemBrand = itemView.findViewById (R.id.tvFavBrand);
            tvFavItemName = itemView.findViewById (R.id.tvFavName);
            tvFavItemPrice = itemView.findViewById (R.id.tvFavPrice);
            ivFavItemImg = itemView.findViewById (R.id.ivFavImage);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();

                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){
                        favouriteFragment.showFavoriteDialog(favItemName, imgUrl, pos);
                    }
                }
            });
        }
    }

    public void setFilter (ArrayList<Item> _alFavouriteItem)
    {
        alFavouriteItem = new ArrayList<> ();
        alFavouriteItem.addAll (_alFavouriteItem);
        notifyDataSetChanged ();
    }

    public void deleteFavItem(int position){
        alFavouriteItem.remove(position);
        notifyDataSetChanged ();
    }

    public void putSend(int pos) {
        Item clickedDataItem = alFavouriteItem.get(pos);
        //Toast.makeText(context, "You clicked " + clickedDataItem.getCount(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, ItemDetailActivity.class);

        intent.putExtra("brand", clickedDataItem.getBrand());
        intent.putExtra("imgURL", clickedDataItem.getImgURL());
        intent.putExtra("description", clickedDataItem.getDescription());
        intent.putExtra("name", clickedDataItem.getName());
        intent.putExtra("price", clickedDataItem.getPrice());
        intent.putExtra("category", clickedDataItem.getCategory());
        intent.putExtra("count", clickedDataItem.getCount());

        context.startActivity(intent);

    }
}
