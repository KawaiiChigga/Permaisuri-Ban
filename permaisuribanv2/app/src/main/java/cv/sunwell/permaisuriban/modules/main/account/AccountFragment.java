package cv.sunwell.permaisuriban.modules.main.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.main.MainActivity;
import cv.sunwell.permaisuriban.modules.main.account.edit.EditActivity;

public class AccountFragment extends Fragment
{

    private TextView tvName, tvEmail, tvPhone;
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    Context context;
    Button logoutButton;
    Button editButton;
    String name, address, email;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        context = getActivity ().getBaseContext ();
        view = inflater.inflate (R.layout.fragment_account, container, false);
        tvName = (TextView) view.findViewById (R.id.tvAccountName);
        tvEmail = view.findViewById(R.id.tvAccountEmail);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        name = sharedPreferences.getString ("name", "");
        email = sharedPreferences.getString("email", "");

        if(!name.equalsIgnoreCase(""))
        {
            name = name + "  Sethi";  /* Edit the value here*/
        }

        tvName.setText(name);
        tvEmail.setText(email);

        logoutButton = view.findViewById(R.id.btnLogOut);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ocLogout();
            }
        });

        editButton = view.findViewById(R.id.btnEdit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ocEditProfile();
            }
        });
        layoutManager = new LinearLayoutManager(getActivity ());

        return view;
    }

    public void onResume ()
    {
        super.onResume ();

        ((MainActivity) getActivity ()).setActionBarTitle (R.string.title_account);
    }

    public void ocLogout() {
        ((MainActivity) getActivity ()).onLogout();
    }

    public void ocEditProfile(){
        Intent intent = new Intent(context, EditActivity.class);
        startActivity(intent);
    }

}
