package cv.sunwell.permaisuriban.modules.main.home.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;
import cv.sunwell.permaisuriban.modules.main.home.category.detail.ItemDetailActivity;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder>
{
    ArrayList<Item> horItem = new ArrayList<> ();
    Context context;

    public HorizontalAdapter (ArrayList<Item> _arrayList, Context _context)
    {
        this.horItem = _arrayList;
        this.context = _context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.activity_home_category_item, parent, false);
        return new MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, final int position)
    {
        holder.name.setText (horItem.get (position).getName ());
        holder.brand.setText (horItem.get (position).getBrand ());
        holder.price.setText ("Rp." + horItem.get (position).getPrice ());
        holder.image.setImageResource (horItem.get (position).getImgURL ());
        holder.card.setOnClickListener (new View.OnClickListener ()
        {
            @Override
            public void onClick (View v)
            {

                Intent intent = new Intent(context, ItemDetailActivity.class);
                Bundle bundle = new Bundle ();
                bundle.putString ("name",horItem.get (position).getName ());
                intent.putExtras (bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount ()
    {
        return horItem.size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        CardView card;
        TextView name, brand, price;
        ImageView image;

        public MyViewHolder (View itemView)
        {
            super (itemView);
            name = (TextView) itemView.findViewById (R.id.tvHorName);
            brand = (TextView) itemView.findViewById (R.id.tvHorBrand);
            price = (TextView) itemView.findViewById (R.id.tvHorPrice);
            image = (ImageView) itemView.findViewById (R.id.ivHorImg);
            card = (CardView) itemView.findViewById (R.id.cvHomeCategoryItem);
        }
    }
}
