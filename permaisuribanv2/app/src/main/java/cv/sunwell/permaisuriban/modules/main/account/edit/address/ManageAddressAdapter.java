package cv.sunwell.permaisuriban.modules.main.account.edit.address;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Address;

public class ManageAddressAdapter extends RecyclerView.Adapter<ManageAddressAdapter.MyViewHolder>
{

    ArrayList<Address> alAddressItem = new ArrayList<> ();
    Context context;

    public ManageAddressAdapter(ArrayList<Address> _alTransactionItem, Context _context)
    {
        this.alAddressItem = _alTransactionItem;
        this.context = _context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.activity_manage_address_item, parent, false);
        return new MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, final int position)
    {

        holder.tvAddressName.setText ("Alamat " + (position+1) );
        holder.tvAddressAddress.setText (alAddressItem.get (position).getJalan () + ", " + alAddressItem.get(position).getRegency()+ ", " + alAddressItem.get(position).getProvinsi());
        holder.btnEditItemAddress.setOnClickListener (new View.OnClickListener ()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent (context, EditAddressActivity.class);
                intent.putExtra("systemid", alAddressItem.get(position).getSystemId());
                intent.putExtra("address", alAddressItem.get(position).getJalan());
                intent.putExtra("regencyid", alAddressItem.get(position).getRegencyId());
                intent.putExtra("provinceid", alAddressItem.get(position).getProvinsiId());
                context.startActivity(intent);
            }
        });

        holder.btnDeleteItemAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context instanceof ManageAddressActivity){
                    ((ManageAddressActivity)context).dialogDelete(alAddressItem.get(position).getSystemId());;
                }
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
        Button btnEditItemAddress;
        Button btnDeleteItemAddress;

        public MyViewHolder (View itemView)
        {
            super (itemView);
            tvAddressName = (TextView) itemView.findViewById (R.id.tvAddressName);
            tvAddressAddress = (TextView) itemView.findViewById (R.id.tvAddressAddress);
            btnEditItemAddress = (Button) itemView.findViewById (R.id.btnEditItemAddress);
            btnDeleteItemAddress = (Button) itemView.findViewById (R.id.btnDeleteItemAddress);
        }
    }
}
