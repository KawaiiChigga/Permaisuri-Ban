package cv.sunwell.permaisuriban.modules.auth.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cv.sunwell.permaisuriban.R;

public class RegisterResultActivity extends AppCompatActivity
{

    TextView tvUsername, tvEmail, tvFirstName, tvLastName, tvAddress;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register_result);


        tvUsername = (TextView)findViewById (R.id.tvResultRegUsername);
        tvEmail = (TextView)findViewById (R.id.tvResultRegEmail);
        tvFirstName = (TextView)findViewById (R.id.tvFirstName);
        tvLastName = (TextView)findViewById (R.id.tvLastName);
        tvAddress =(TextView)findViewById (R.id.tvLastName);

        Bundle bundle = getIntent ().getExtras ();
        tvUsername.setText ("Welcome "+bundle.getString ("userName"));
        tvFirstName.setText ("First Name : "+bundle.getString ("firstName"));
        tvLastName.setText ("Last Name : "+bundle.getString ("lastName"));
        tvAddress.setText ("Address : "+bundle.getString ("address"));
        tvEmail.setText ("Email : "+bundle.getString ("email"));
    }
}
