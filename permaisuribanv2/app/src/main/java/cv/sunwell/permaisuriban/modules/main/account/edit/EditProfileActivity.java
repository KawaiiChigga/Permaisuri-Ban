package cv.sunwell.permaisuriban.modules.main.account.edit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.main.MainActivity;
import cv.sunwell.permaisuriban.networking.ApiInterface;
import cv.sunwell.permaisuriban.networking.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity
{
    Button btnSaveEdit;
    EditText etDobEdit, etFirstNameEdit, etLastNameEdit, etPhoneEdit;
    ApiInterface apiInterface;
    SharedPreferences sharedPreferences;
    String placeholderFirstname, placeholderLastname, placeholderPhone, placeholderDOB, token;
    int userid;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_edit_profile);
        setTitle ("Edit Profile");
        apiInterface = ApiUtils.getAPIService();
        etFirstNameEdit = findViewById(R.id.etFirstNameEdit);
        etLastNameEdit = findViewById(R.id.etLastNameEdit);
        etPhoneEdit =findViewById(R.id.etPhoneEdit);
        btnSaveEdit = findViewById(R.id.btnSaveEdit);
        etDobEdit = findViewById(R.id.etDobEdit);
        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveExit();
            }
        });

        etDobEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    checkFormat();
                }
            }
        });

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(EditProfileActivity.this);
        placeholderFirstname = sharedPreferences.getString ("firstname", "");
        placeholderLastname = sharedPreferences.getString ("lastname", "");
        placeholderPhone = sharedPreferences.getString ("phone", "");
        placeholderDOB = sharedPreferences.getString("birthdate", "");
        placeholderDOB = placeholderDOB.substring(8) + "-" + placeholderDOB.substring(5,7) + "-" + placeholderDOB.substring(0,4) ;
        token = sharedPreferences.getString("token", "");
        userid = sharedPreferences.getInt("userid", 0);
        etFirstNameEdit.setText(placeholderFirstname);
        etLastNameEdit.setText(placeholderLastname);
        etPhoneEdit.setText(placeholderPhone);
        etDobEdit.setText(placeholderDOB);

    }

    private void saveExit(){
        if(checkFormat()==true){
            confirmEdit();
        }

    }

    private boolean checkFormat(){
        if(etDobEdit.getText().length()!=10){
            etDobEdit.setError("Format tanggal salah");
            return false;
        }
        else{
            if(!etDobEdit.getText().toString().substring(2,3).equals("-") || !etDobEdit.getText().toString().substring(5,6).equals("-")){
                etDobEdit.setError("Format tanggal salah");
                return false;
            }
            else{
                if(!isNumeric(etDobEdit.getText().toString().substring(0,2)) || !isNumeric(etDobEdit.getText().toString().substring(3,5) ) || !isNumeric(etDobEdit.getText().toString().substring(6,9))){
                    etDobEdit.setError("Format tanggal salah");
                    return false;
                }
                else{
                    etDobEdit.setError(null);
                    return true;
                }
            }
        }
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d.");
    }

    private void confirmEdit(){
            final ProgressDialog loading = ProgressDialog.show(EditProfileActivity.this, null, "Harap Tunggu...", true, false);
            String firstname = etFirstNameEdit.getText().toString();
            String lastname = etLastNameEdit.getText().toString();
            String phone = etPhoneEdit.getText().toString();
            String dob = etDobEdit.getText().toString();

            JsonObject joCred = new JsonObject();
            joCred.addProperty("firstname", firstname);
            joCred.addProperty("lastname", lastname);
            joCred.addProperty("phone", phone);
            joCred.addProperty("birthdate", dob);


        Call<JsonObject> call = apiInterface.editUser(token, userid, userid, joCred);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.body().get("success").getAsBoolean()) {
                        Toast.makeText(EditProfileActivity.this, "Edit berhasil!" , Toast.LENGTH_SHORT).show();
                        //Toast.makeText(mContext, response.body().get("message").toString(), Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                        Intent intent = new Intent (EditProfileActivity.this, MainActivity.class);
                        intent.putExtra("frgToLoad", 0);
                        startActivity (intent);
                        finish ();
                    }
                    else {
                        loading.dismiss();
                        Toast.makeText(EditProfileActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(EditProfileActivity.this, "Error : " + t.toString(), Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            });
    }
}
