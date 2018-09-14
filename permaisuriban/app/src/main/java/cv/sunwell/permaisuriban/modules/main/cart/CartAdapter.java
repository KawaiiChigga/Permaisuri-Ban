package cv.sunwell.permaisuriban.modules.main.cart;

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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>
{
    ArrayList<Item> alCartItem = new ArrayList<> ();

    public CartAdapter (ArrayList<Item> _alCartItem)
    {
        alCartItem = _alCartItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.fragment_cart_item, parent, false);
        return new CartAdapter.MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, int position)
    {
        int price, count, subtotal;
        price = alCartItem.get (position).getPrice ();
        count = alCartItem.get (position).getCount ();
        subtotal = price * count;
        Log.d ("LOG", "Price : " + price);
        Log.d ("LOG", "Count : " + count);
        Log.d ("LOG", "Subtotal : " + subtotal);
        holder.tvCartItemName.setText (alCartItem.get (position).getName ());
        holder.tvCartItemPrice.setText ("Rp." + price);
        holder.tvCartSubtotalNominal.setText ("Rp. " + subtotal);
        holder.tvCartItemCount.setText ("" + count);
        holder.ivCartItemImage.setImageResource (alCartItem.get (position).getImgURL ());
    }

    @Override
    public int getItemCount ()
    {
        return alCartItem.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvCartItemName;
        TextView tvCartItemPrice;
        TextView tvCartSubtotalNominal;
        TextView tvCartItemCount;
        ImageView ivCartMinus;
        ImageView ivCartPlus;
        ImageView ivCartItemImage;
        Button ivCartDelete;

        public MyViewHolder (View itemView)
        {
            super (itemView);
            tvCartItemName = (TextView) itemView.findViewById (R.id.tvCartItemName);
            tvCartItemPrice = (TextView) itemView.findViewById (R.id.tvCartItemPrice);
            tvCartItemCount = (TextView) itemView.findViewById (R.id.tvCartItemCount);
            tvCartSubtotalNominal = (TextView) itemView.findViewById (R.id.tvCartSubtotalNominal);
            ivCartMinus = (ImageView) itemView.findViewById (R.id.ivCartMinus);
            ivCartPlus = (ImageView) itemView.findViewById (R.id.ivCartPlus);
            ivCartItemImage = (ImageView) itemView.findViewById (R.id.ivCartItemImage);
            ivCartDelete = (Button) itemView.findViewById (R.id.ivCartDelete);
        }
    }
}
