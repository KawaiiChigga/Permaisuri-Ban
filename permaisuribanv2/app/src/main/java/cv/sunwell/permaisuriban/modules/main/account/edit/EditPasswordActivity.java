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

public class EditPasswordActivity extends AppCompatActivity
{
    Button btnSaveEdit;
    EditText etOldPassEdit, etNewPassEdit, etConfirmPassEdit;
    ApiInterface apiInterface;
    SharedPreferences sharedPreferences;
    String token;
    String newpass, confirmpass;
    int userid;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_edit_password);
        setTitle ("Edit Password");
        apiInterface = ApiUtils.getAPIService();
        etOldPassEdit = findViewById(R.id.etOldPassEdit);
        etNewPassEdit = findViewById(R.id.etNewPassEdit);
        etConfirmPassEdit =findViewById(R.id.etConfirmPassEdit);
        btnSaveEdit = findViewById(R.id.btnSaveEdit);

        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveExit();
            }
        });

        etNewPassEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    checkFormat();
                }
            }
        });

        etConfirmPassEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    checkFormat();
                }
            }
        });

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(EditPasswordActivity.this);
        token = sharedPreferences.getString("token", "");
        userid = sharedPreferences.getInt("userid", 0);

    }

    private void saveExit(){
        if(checkFormat()==true){
            confirmEdit();
        }

    }

    private boolean checkFormat(){
        newpass = etNewPassEdit.getText().toString();
        confirmpass = etConfirmPassEdit.getText().toString();
        if(newpass.length()<4){
            etNewPassEdit.setError("Password terlalu pendek");
            return false;
        }
        else{
            if(!newpass.equals(confirmpass)){
                etConfirmPassEdit.setError("Password tidak sama");
                return false;
            }
            else{
                etNewPassEdit.setError(null);
                etConfirmPassEdit.setError(null);
                return true;
            }
        }
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d.");
    }

    private void confirmEdit(){
        final ProgressDialog loading = ProgressDialog.show(EditPasswordActivity.this, null, "Harap Tunggu...", true, false);
        String oldpass = etOldPassEdit.getText().toString();

        JsonObject joCred = new JsonObject();
        joCred.addProperty("old_password", oldpass);
        joCred.addProperty("new_password", newpass);

        Call<JsonObject> call = apiInterface.changePassword(token, userid, userid, joCred);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body().get("success").getAsBoolean()) {
                    Toast.makeText(EditPasswordActivity.this, "Berhasil merubah password!" , Toast.LENGTH_SHORT).show();
                    //Toast.makeText(mContext, response.body().get("message").toString(), Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                    Intent intent = new Intent (EditPasswordActivity.this, MainActivity.class);
                    intent.putExtra("frgToLoad", 0);
                    startActivity (intent);
                    finish ();
                }
                else {
                    loading.dismiss();
                    Toast.makeText(EditPasswordActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(EditPasswordActivity.this, "Error : " + t.toString(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

}
