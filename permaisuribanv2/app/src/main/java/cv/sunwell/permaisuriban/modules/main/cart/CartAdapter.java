package cv.sunwell.permaisuriban.modules.main.cart;

import android.app.Dialog;
import android.app.Fragment;
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
import android.widget.Toast;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;
import cv.sunwell.permaisuriban.modules.main.MainActivity;
import cv.sunwell.permaisuriban.modules.main.dialog.CartDeleteDialogFragment;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>
{
    ArrayList<Item> alCartItem = new ArrayList<> ();
    public CartFragment cartFragmente;

    public CartAdapter (ArrayList<Item> _alCartItem, CartFragment _cartFragment)
    {
        alCartItem = _alCartItem;
        cartFragmente = _cartFragment;
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
        holder.cartFragment = cartFragmente;
        holder.itemPosition = position;
        int price, count, subtotal;
        price = alCartItem.get (position).getPrice ();
        count = alCartItem.get (position).getCount ();
        subtotal = price * count;
        Log.d ("LOG", "Price : " + price);
        Log.d ("LOG", "Count : " + count);
        Log.d ("LOG", "Subtotal : " + subtotal);
        holder.cartItemName = alCartItem.get (position).getName ();
        holder.imgUrl = alCartItem.get (position).getImgURL ();
        holder.tvCartItemName.setText (holder.cartItemName);
        holder.tvCartItemPrice.setText ("Rp." + price);
        holder.tvCartSubtotalNominal.setText ("Rp. " + subtotal);
        holder.tvCartItemCount.setText ("" + count);
        holder.ivCartItemImage.setImageResource (holder.imgUrl);
    }

    @Override
    public int getItemCount ()
    {
        return alCartItem.size ();
    }

    public void deleteCartItem(int position){
        alCartItem.remove(position);
        notifyDataSetChanged ();
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
        Context context;
        CartFragment cartFragment;
        String cartItemName;
        int imgUrl;
        int itemPosition;
        public MyViewHolder (final View itemView)
        {
            super (itemView);
            context = itemView.getContext();
            tvCartItemName = (TextView) itemView.findViewById (R.id.tvCartItemName);
            tvCartItemPrice = (TextView) itemView.findViewById (R.id.tvCartItemPrice);
            tvCartItemCount = (TextView) itemView.findViewById (R.id.tvCartItemCount);
            tvCartSubtotalNominal = (TextView) itemView.findViewById (R.id.tvCartSubtotalNominal);
            ivCartMinus = (ImageView) itemView.findViewById (R.id.ivCartMinus);
            ivCartPlus = (ImageView) itemView.findViewById (R.id.ivCartPlus);
            ivCartItemImage = (ImageView) itemView.findViewById (R.id.ivCartItemImage);
            ivCartDelete = (Button) itemView.findViewById (R.id.ivCartDelete);
            ivCartDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(, "Clicked", Toast.LENGTH_SHORT).show();
                    cartFragment.showDeleteDialog(cartItemName, imgUrl, itemPosition);
                }
            });

        }
    }
}
