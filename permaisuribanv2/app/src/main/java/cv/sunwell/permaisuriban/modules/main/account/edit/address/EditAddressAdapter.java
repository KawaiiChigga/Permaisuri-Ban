package cv.sunwell.permaisuriban.modules.main.account.edit.address;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;
import cv.sunwell.permaisuriban.modules.main.transaction.detail.TransactionDetailActivity;

public class EditAddressAdapter extends RecyclerView.Adapter<EditAddressAdapter.MyViewHolder>
{

    ArrayList<Item> alAddressItem = new ArrayList<> ();
    Context context;

    public EditAddressAdapter (ArrayList<Item> _alTransactionItem, Context _context)
    {
        this.alAddressItem = _alTransactionItem;
        this.context = _context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.activity_edit_address_item, parent, false);
        return new MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, int position)
    {
        holder.tvAddressName.setText (alAddressItem.get (position).getBrand ());
        holder.tvAddressAddress.setText (alAddressItem.get (position).getName ());
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
        return alAddressItem.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvAddressName;
        TextView tvAddressAddress;
        CardView cvTransItem;

        public MyViewHolder (View itemView)
        {
            super (itemView);
            tvAddressName = (TextView) itemView.findViewById (R.id.tvAddressAddress);
            tvAddressAddress = (TextView) itemView.findViewById (R.id.tvAddressName);
            cvTransItem = (CardView) itemView.findViewById (R.id.cvAddressItem);

        }
    }
}
