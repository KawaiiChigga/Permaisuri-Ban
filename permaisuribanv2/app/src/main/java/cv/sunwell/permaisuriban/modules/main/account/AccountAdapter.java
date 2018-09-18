package cv.sunwell.permaisuriban.modules.main.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.MyViewHolder>
{
    ArrayList<String> alAccountMenus = new ArrayList<> ();
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    AccountAdapter (ArrayList<String> _arrayList, Context _context)
    {
        this.alAccountMenus = _arrayList;
        this.context = _context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.fragment_account_menu, parent, false);
        return new MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, final int position)
    {
        holder.expandableTextView.setText (alAccountMenus.get (position));
        holder.expandableTextView.setOnClickListener (new View.OnClickListener ()
        {
            @Override
            public void onClick (View v)
            {
                switch (alAccountMenus.get (position)) {
                    case "Edit Profile":
                        Toast.makeText (context, "This is Edit Profile", Toast.LENGTH_LONG).show ();
                        break;
                    case "Shipping \n\n Current Method : Delivery & Installation \n Method Price : Rp.150.000":
                        Toast.makeText (context, "This is Shipping", Toast.LENGTH_LONG).show ();
                        break;
                    case "Address \n\n First Address : Jalan Dadali No.44 \n Second Address : Jalan Jendral Sudirman No.443":
                        Toast.makeText (context, "This is Address", Toast.LENGTH_LONG).show ();
                        break;
                    case "Bank Account \n\n Bank Name : BCA \n Account Number : 2818122123":
//                        Intent intent = new Intent (context, EditActivity.class);
//                        Bundle bundle = new Bundle ();
//                        bundle.putString ("name", "Bank Account");
//                        intent.putExtras (bundle);
//                        context.startActivity (intent);
                        Toast.makeText (context, "This is Bank Account", Toast.LENGTH_LONG).show ();
                        break;
                    case "E-mail \n\n Current Email : dadali44@gmail.com \n Recovery Email : richardandreas21@gmail.com":
//                        intent = new Intent (context, EditActivity.class);
//                        bundle = new Bundle ();
//                        bundle.putString ("name", "E-mail");
//                        intent.putExtras (bundle);
//                        context.startActivity (intent);
                        Toast.makeText (context, "This is E-mail", Toast.LENGTH_LONG).show ();
                        break;
                    case "Password \n\n ********":
                        Toast.makeText (context, "This is Password", Toast.LENGTH_LONG).show ();
                        break;
                    case "Notifications":
                        Toast.makeText (context, "This is Notifications", Toast.LENGTH_LONG).show ();
                        break;
                    case "Logout":
                        Toast.makeText (context, "This is Logout", Toast.LENGTH_LONG).show ();

                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount ()
    {
        return alAccountMenus.size ();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ExpandableTextView expandableTextView;

        public MyViewHolder (View itemView)
        {
            super (itemView);
            expandableTextView = (ExpandableTextView) itemView.findViewById (R.id.etvAccount);
        }
    }

    public void setFilter (ArrayList<String> _arrayList)
    {
        alAccountMenus = new ArrayList<> ();
        alAccountMenus.addAll (_arrayList);
        notifyDataSetChanged ();
    }

}
