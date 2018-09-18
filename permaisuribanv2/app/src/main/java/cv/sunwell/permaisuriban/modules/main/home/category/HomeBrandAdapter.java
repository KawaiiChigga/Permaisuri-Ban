package cv.sunwell.permaisuriban.modules.main.home.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;



public class HomeBrandAdapter extends RecyclerView.Adapter<HomeBrandAdapter.MyViewHolder>
{

    ArrayList<Item> brandItem = new ArrayList<> ();
    Context context;
    public HomeBrandAdapter (ArrayList<Item> _alFavouriteItem, Context context)
    {
        this.brandItem = _alFavouriteItem;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeBrandAdapter.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.activity_home_category_brand, parent, false);

        return new HomeBrandAdapter.MyViewHolder (view, context);
    }

    @Override
    public void onBindViewHolder (@NonNull HomeBrandAdapter.MyViewHolder holder, int position)
    {
        holder.brandImg.setImageResource (brandItem.get (position).getImgURL ());
        holder.toggleButton.setBackgroundResource(brandItem.get (position).getImgURL ());
        holder.brandName = brandItem.get(position).getBrand();
    }

    @Override
    public int getItemCount ()
    {
        return brandItem.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener
    {
        ImageView brandImg;
        String brandName;
        ToggleButton toggleButton;
        Context context;
        public MyViewHolder (View itemView, Context context)
        {
            super (itemView);
            brandImg = itemView.findViewById (R.id.ivBrHorImg);
            toggleButton = itemView.findViewById (R.id.toggle);
            toggleButton.setOnCheckedChangeListener(this);
            this.context = context;
        }

        @Override
        public void onCheckedChanged(CompoundButton compound, boolean isChecked) {
            if(isChecked){
                if(context instanceof HomeCategoryActivity){
                    ((HomeCategoryActivity)context).FilterBrandEnable(brandName);
                }
                //Toast.makeText(context, "Checked " + brandName, Toast.LENGTH_SHORT).show();
            }
            else{
                if(context instanceof HomeCategoryActivity){
                    ((HomeCategoryActivity)context).FilterBrandDisable(brandName);
                }
                //Toast.makeText(context,"UnChecked " + brandName, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
