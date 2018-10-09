package cv.sunwell.permaisuriban.modules.main.account.edit.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Item;

public class EditAddressActivity  extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private EditAddressAdapter editAddressAdapter;
    private ArrayList<Item> listItem = new ArrayList<> ();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_edit_address);
        setTitle ("Manage Address");

        insertItem ();
        recyclerView = (RecyclerView) findViewById (R.id.rvAddress);

        layoutManager = new LinearLayoutManager(EditAddressActivity.this);
        editAddressAdapter = new EditAddressAdapter (listItem,EditAddressActivity.this);
        recyclerView.setAdapter (editAddressAdapter);
        recyclerView.setLayoutManager (layoutManager);

    }

    public void insertItem ()
    {
        Item temp = new Item (81, "Alamat Rumah","Jl. Ninjaku no 88", 125000, R.drawable.tire_dummy,1,"Passenger");
        listItem.add (temp);
        temp = new Item (82, "Alamat Kantor","Jl. Jauh sekali no 2002", 960000, R.drawable.tire_dummy2,1,"Passenger");
        listItem.add (temp);

    }
}
