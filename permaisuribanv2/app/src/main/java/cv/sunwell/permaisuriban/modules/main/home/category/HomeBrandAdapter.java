package cv.sunwell.permaisuriban.modules.main.home.category;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;



public class HomeBrandAdapter extends RecyclerView.Adapter<HomeBrandAdapter.MyViewHolder>
{

    ArrayList<Item> brandItem = new ArrayList<> ();

    public HomeBrandAdapter (ArrayList<Item> _alFavouriteItem)
    {
        this.brandItem = _alFavouriteItem;
    }

    @NonNull
    @Override
    public HomeBrandAdapter.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.activity_home_category_brand, parent, false);
        return new HomeBrandAdapter.MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder (@NonNull HomeBrandAdapter.MyViewHolder holder, int position)
    {
        holder.brandImg.setImageResource (brandItem.get (position).getImgURL ());

    }

    @Override
    public int getItemCount ()
    {
        return brandItem.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView brandImg;

        public MyViewHolder (View itemView)
        {
            super (itemView);
            brandImg = itemView.findViewById (R.id.ivBrHorImg);

        }
    }
}
