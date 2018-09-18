package cv.sunwell.permaisuriban.modules.main.home.category;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;
import cv.sunwell.permaisuriban.modules.main.home.category.detail.ItemDetailActivity;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.MyViewHolder>
{

    public ArrayList<Item> alCategoryItem = new ArrayList<> ();
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

    public class MyViewHolder extends RecyclerView.ViewHolder
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

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();

                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){
                        putSend(pos);
                    }
                }
            });
        }
    }

    public void setFilter (ArrayList<Item> _alFavouriteItem)
    {
        alCategoryItem = new ArrayList<> ();
        alCategoryItem.addAll (_alFavouriteItem);
        notifyDataSetChanged ();
    }

    public void putSend(int pos){
        Item clickedDataItem = alCategoryItem.get(pos);
        //Toast.makeText(context, "You clicked " + clickedDataItem.getCount(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, ItemDetailActivity.class);

        intent.putExtra ("brand",clickedDataItem.getBrand());
        intent.putExtra ("imgURL",clickedDataItem.getImgURL());
        intent.putExtra ("description",clickedDataItem.getDescription());
        intent.putExtra ("name",clickedDataItem.getName());
        intent.putExtra("price", clickedDataItem.getPrice());
        intent.putExtra("category", clickedDataItem.getCategory());
        intent.putExtra("count", clickedDataItem.getCount());

        context.startActivity(intent);
    }

}
