package cv.sunwell.permaisuriban.modules.main.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Kategori;
import cv.sunwell.permaisuriban.modules.main.home.category.HomeCategoryActivity;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
{
    ArrayList<Kategori> brandItem = new ArrayList<> ();
    Context context;

    public HomeAdapter (ArrayList<Kategori> _brandItem, Context _context)
    {

        this.brandItem = _brandItem;
        this.context = _context;
    }

    @NonNull
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.fragment_home_item,parent,false);
        return new HomeAdapter.MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder (@NonNull final HomeAdapter.MyViewHolder holder, final int position)
    {
        holder.ivHomeItemImage.setBackgroundResource (brandItem.get (position).getImgURL ());

        holder.ivHomeItemImage.setOnClickListener (new View.OnClickListener ()
        {
            @Override
            public void onClick (View v)
            {

                Intent intent = new Intent(context, HomeCategoryActivity.class);
                intent.putExtra ("idkat",brandItem.get (position).getId ());
                intent.putExtra ("namakat",brandItem.get (position).getNamamerk ());

                context.startActivity(intent);
            }
        });



        holder.ivHomeItemImage.setOnLongClickListener (new View.OnLongClickListener ()
        {
            @Override
            public boolean onLongClick (View v)
            {
                //Intent intent = new Intent(context, ItemDetailActivity.class);
                //context.startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public int getItemCount ()
    {
        return brandItem.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        Button ivHomeItemImage;

        public MyViewHolder (View itemView)
        {
            super (itemView);
            ivHomeItemImage = (Button) itemView.findViewById (R.id.ivHomeItemImage);
        }
    }

}
