package cv.sunwell.permaisuriban.modules.auth.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.auth.AuthActivity;
import cv.sunwell.permaisuriban.modules.auth.AuthSingleton;
import cv.sunwell.permaisuriban.modules.auth.login.LoginActivity;
import cv.sunwell.permaisuriban.modules.auth.login.LoginResultActivity;

public class RegisterActivity extends AppCompatActivity
{

    @Override
    public void onBackPressed ()
    {
        super.onBackPressed ();
        Intent intent = new Intent(RegisterActivity.this, AuthActivity.class);
        startActivity(intent);
        finish();

    }

    Button btnReg;
    EditText etEmail, etPhonenumber, etAddress, etUsername, etPassword;
    String sEmail, sPhoneNumber, sAddress, sUsername, sPassword;
    AlertDialog.Builder builder;
    String reg_url = "http://139.59.101.119:8080/permaisuri/resources/customers";


    @Override
    protected void onCreate (final Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);

        //firstname
        //lastname
        btnReg = (Button) findViewById (R.id.btnRegister);
        etEmail = (EditText) findViewById (R.id.etRegEmail);
        etPhonenumber = (EditText) findViewById (R.id.etRegPhonenumber);
        etAddress = (EditText) findViewById (R.id.etRegAddress);
        etUsername = (EditText) findViewById (R.id.etRegUsername);
        etPassword = (EditText) findViewById (R.id.etRegPassword);
        //confirm password

        builder = new AlertDialog.Builder (RegisterActivity.this);
        btnReg.setOnClickListener (new View.OnClickListener ()
        {
            @Override
            public void onClick (View v)
            {
                sEmail = etEmail.getText ().toString ();
                sPhoneNumber = etPhonenumber.getText ().toString ();
                sAddress = etAddress.getText ().toString ();
                sUsername = etUsername.getText ().toString ();
                sPassword = etPassword.getText ().toString ();
                //confirm password
                if (sEmail.equals ("") || sPhoneNumber.equals ("") || sAddress.equals ("") || sUsername.equals ("") || sPassword.equals ("")) {
                    builder.setTitle ("Something went wrong ...");
                    builder.setMessage ("Please fill all the fields ...");
                    displayAlert ("input_error");
                } else {
                    JSONObject data = new JSONObject ();
                    JSONObject userCred = new JSONObject ();

                    try {
                        userCred.put ("userName", sUsername);
                        userCred.put ("pwd", sPassword);
                        data.put ("userCredential", userCred);
                        data.put ("firstName", "Richard");
                        data.put ("lastName", "Dwiputra");
                        data.put ("email", sEmail);
                        data.put ("address", sAddress);
                    }
                    catch (JSONException _e) {
                        _e.printStackTrace ();
                    }

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (Request.Method.POST, reg_url, data,
                            new Response.Listener<JSONObject> ()
                            {
                                @Override
                                public void onResponse (JSONObject response)
                                {
                                    try {
                                        JSONObject userCred = response.getJSONObject ("userCredential");
                                        Intent intent = new Intent (RegisterActivity.this, RegisterResultActivity.class);
                                        Bundle bundle = new Bundle ();
                                        bundle.putString ("userName", userCred.getString ("userName"));
                                        bundle.putString ("email", response.getString ("email"));
                                        bundle.putString ("firstName", response.getString ("firstName"));
                                        bundle.putString ("lastName", response.getString ("lastName"));
                                        bundle.putString ("address", response.getString ("address"));
                                        intent.putExtras (bundle);
                                        startActivity (intent);
                                    }
                                    catch (JSONException _e) {
                                        _e.printStackTrace ();
                                    }
                                }
                            },
                            new Response.ErrorListener ()
                            {
                                @Override
                                public void onErrorResponse (VolleyError error)
                                {
                                    Toast.makeText (RegisterActivity.this, "Error", Toast.LENGTH_LONG).show ();
                                    error.printStackTrace ();
                                }
                            }
                    );
                    AuthSingleton.getAsInstance (RegisterActivity.this).addToRequestQueue (jsonObjectRequest);
                }
            }
        });
    }

    public void displayAlert (String _message)
    {
        builder.setMessage (_message);
        builder.setPositiveButton ("OK", new DialogInterface.OnClickListener ()
        {
            @Override
            public void onClick (DialogInterface dialog, int which)
            {
                etPassword.setText ("");
            }
        });
        AlertDialog alertDialog = builder.create ();
        alertDialog.show ();
    }
}
