package cv.sunwell.permaisuriban.modules.main.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.main.MainActivity;

public class AccountFragment extends Fragment
{

    private TextView tvName, tvAddress;
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AccountAdapter accountAdapter;
    private String[] menuNames = {"Edit Profile",
            "Shipping \n\n Current Method : Delivery & Installation \n Method Price : Rp.150.000" ,
            "Address \n\n First Address : Jalan Dadali No.44 \n Second Address : Jalan Jendral Sudirman No.443",
            "Bank Account \n\n Bank Name : BCA \n Account Number : 2818122123",
            "E-mail \n\n Current Email : dadali44@gmail.com \n Recovery Email : richardandreas21@gmail.com",
            "Password",
            "Notifications",
            "Logout"};
    private ArrayList<String> listMenu = new ArrayList<> ();
    Context context;
    String username, name, address, email;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        context = getActivity ().getBaseContext ();
        view = inflater.inflate (R.layout.fragment_account, container, false);
        recyclerView = (RecyclerView) view.findViewById (R.id.rvAccount);
        tvName = (TextView) view.findViewById (R.id.tvAccountName);
        tvAddress = (TextView) view.findViewById (R.id.tvAccountAddress);

        sharedPreferences = getActivity ().getSharedPreferences ("loginPref", Context.MODE_PRIVATE);
        username = sharedPreferences.getString ("userName", "");
        email = sharedPreferences.getString ("email", "");
//        name = ((sharedPreferences.getString ("firstName",""))+" "+sharedPreferences.getString ("lastName",""));
//        address = sharedPreferences.getString ("address","");


        if (username.length () != 0) {
            tvName.setText (username);
            tvAddress.setText (email);
        }

        layoutManager = new LinearLayoutManager ((MainActivity) getActivity ());
        listMenu.addAll (Arrays.asList (menuNames));
        accountAdapter = new AccountAdapter (listMenu, context);
        recyclerView.setAdapter (accountAdapter);
        recyclerView.setLayoutManager (layoutManager);
        return view;
    }

    public void onResume ()
    {
        super.onResume ();

        ((MainActivity) getActivity ()).setActionBarTitle (R.string.title_account);
    }

}
