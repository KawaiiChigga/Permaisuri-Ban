package cv.sunwell.permaisuriban.modules.auth.login;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.auth.AuthActivity;
import cv.sunwell.permaisuriban.modules.auth.AuthSingleton;
import cv.sunwell.permaisuriban.modules.main.MainActivity;

public class LoginActivity extends AppCompatActivity
{

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText etUsername, etPassword;
    String username, password;
    Button btnLoginNormal;
    Button btnLoginFacebook;
    Button btnLoginGoogle;
    String login_url = "http://139.59.101.119:8080/permaisuri/resources/login";
    TextView txtTesting;
    AlertDialog.Builder builder;


    @Override
    public void onBackPressed ()
    {
        //logout prevention
        Intent intent = new Intent(LoginActivity.this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        builder = new AlertDialog.Builder (LoginActivity.this);
        etUsername = (EditText) findViewById (R.id.etLogUsername);
        etPassword = (EditText) findViewById (R.id.etLogPassword);
        btnLoginNormal = (Button) findViewById (R.id.btnLoginNormal);

        btnLoginNormal.setOnClickListener (new View.OnClickListener ()
        {

            @Override
            public void onClick (View v)
            {
                username = etUsername.getText ().toString ();
                password = etPassword.getText ().toString ();

                if (username.equals ("") || password.equals ("")) {
                    builder.setTitle ("Something went wrong");
                    displayAlert ("Please fill username and password");
                } else {
                    JSONObject jsonObject = new JSONObject ();
                    try {
                        jsonObject.put ("userName", username);
                        jsonObject.put ("pwd", password);
                    }
                    catch (JSONException _e) {
                        _e.printStackTrace ();
                    }

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (Request.Method.POST, login_url, jsonObject,
                            new Response.Listener<JSONObject> ()
                            {
                                @Override
                                public void onResponse (JSONObject response)
                                {
                                    try {
                                        JSONObject data = response.getJSONObject ("data");
                                        JSONObject userCred = data.getJSONObject ("userCredential");
                                        sharedPreferences = getSharedPreferences ("loginPref", Context.MODE_PRIVATE);
                                        editor = sharedPreferences.edit ();
                                        editor.putString ("userName", userCred.getString ("userName"));
                                        editor.putString ("email", data.getString ("email"));
//                                        editor.putString ("address",data.getString ("address"));
//                                        editor.putString ("firstName",data.getString ("firstName"));
//                                        editor.putString ("lastName",data.getString("lastName"));

                                        editor.commit ();
                                        Intent intent = new Intent (LoginActivity.this, MainActivity.class);
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
                                    Toast.makeText (LoginActivity.this, "Error", Toast.LENGTH_LONG).show ();
                                    error.printStackTrace ();
                                }
                            }
                    );
                    AuthSingleton.getAsInstance (LoginActivity.this).addToRequestQueue (jsonObjectRequest);
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
                etUsername.setText ("");
                etPassword.setText ("");
            }
        });
        AlertDialog alertDialog = builder.create ();
        alertDialog.show ();
    }


}