package cv.sunwell.permaisuriban.modules.main.transaction;

import android.content.Context;
import android.content.Intent;
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
import cv.sunwell.permaisuriban.modules.main.transaction.detail.TransactionDetailActivity;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder>
{

    ArrayList<Item> alTransactionItem = new ArrayList<> ();
    Context context;

    public TransactionAdapter (ArrayList<Item> _alTransactionItem, Context _context)
    {
        this.alTransactionItem = _alTransactionItem;
        this.context = _context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.fragment_transaction_item, parent, false);
        return new MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, int position)
    {
        holder.tvTransItemName.setText (alTransactionItem.get (position).getName ());
        holder.tvTransItemPrice.setText ("Rp. " + alTransactionItem.get (position).getPrice ());
        holder.ivTransItemImage.setImageResource (alTransactionItem.get (position).getImgURL ());
        holder.cvTransItem.setOnClickListener (new View.OnClickListener ()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent (context, TransactionDetailActivity.class);
//                intent.putExtra ("image_url", arrayList.get (position).getIc_img ());
//                intent.putExtra("image_name", arrayList.get (position).getIc_name ());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount ()
    {
        return alTransactionItem.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTransItemName;
        TextView tvTransItemPrice;
        ImageView ivTransItemImage;
        CardView cvTransItem;

        public MyViewHolder (View itemView)
        {
            super (itemView);
            tvTransItemName = (TextView) itemView.findViewById (R.id.tvTransItemName);
            tvTransItemPrice = (TextView) itemView.findViewById (R.id.tvTransItemPrice);
            ivTransItemImage = (ImageView) itemView.findViewById (R.id.ivTransItemImage);
            cvTransItem = (CardView) itemView.findViewById (R.id.cvTransItem);

        }
    }
}
