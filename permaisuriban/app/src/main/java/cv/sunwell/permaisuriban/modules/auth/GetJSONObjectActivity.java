package cv.sunwell.permaisuriban.modules.auth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import cv.sunwell.permaisuriban.R;

public class GetJSONObjectActivity extends AppCompatActivity
{
    Button btnGetData;
    TextView tvFirstName, tvLastName, tvEmail;
    String json_url = "http://139.59.101.119:8080/permaisuri/resources/customers?systemId=7";


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_get_json_object);
        btnGetData = (Button) findViewById (R.id.btnGetData);
        tvFirstName = (TextView) findViewById (R.id.tvFirstName);
        tvLastName = (TextView) findViewById (R.id.tvLastName);
        tvEmail = (TextView) findViewById (R.id.tvEmail);

        btnGetData.setOnClickListener (new View.OnClickListener (){

            @Override
            public void onClick (View v)
            {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (Request.Method.GET, json_url, new JSONObject (),
                new Response.Listener<JSONObject> ()
                {
                    @Override
                    public void onResponse (JSONObject response)
                    {
                        try
                        {
                            System.out.println ("Response: " + response.toString () );
                            JSONObject data = response.getJSONObject ("data");
                            String firstName = data.getString ("firstName");
                            String lastName = data.getString ("lastName");
                            String email = data.getString ("email");

                            tvFirstName.setText (firstName);
                            tvLastName.setText (lastName);
                            tvEmail.setText (email);
                            Toast.makeText (GetJSONObjectActivity.this, "Done Deal", Toast.LENGTH_SHORT).show ();
                        }
                        catch (JSONException _e)
                        {
                            _e.printStackTrace ();
                        }
                    }
                }, new Response.ErrorListener (){

                    @Override
                    public void onErrorResponse (VolleyError error)
                    {
                        Toast.makeText (GetJSONObjectActivity.this,"Something went wrong", Toast.LENGTH_SHORT).show ();
                        error.printStackTrace ();
                    }
                });
        AuthSingleton.getAsInstance (GetJSONObjectActivity.this).addToRequestQueue (jsonObjectRequest);
            }
        });


    }
}
