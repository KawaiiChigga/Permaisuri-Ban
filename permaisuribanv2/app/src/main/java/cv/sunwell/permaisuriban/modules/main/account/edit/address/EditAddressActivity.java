package cv.sunwell.permaisuriban.modules.main.account.edit.address;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.model.Regency;
import cv.sunwell.permaisuriban.modules.main.ExtraUtility;
import cv.sunwell.permaisuriban.networking.ApiInterface;
import cv.sunwell.permaisuriban.networking.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAddressActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String address, token;
    int addressid, provinceid, userid, oldregencyid, regencyid;
    EditText etAddressEdit;
    Spinner provinceSpinner, regencySpinner;
    ArrayAdapter<String> dataAdapter;
    ArrayAdapter<Regency> regencySpinnerAdapter;
    List<String> provinces;
    List<Regency> regencies;
    Button updateButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        setTitle("Edit Address");
        apiInterface = ApiUtils.getAPIService();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(EditAddressActivity.this);
        editor = sharedPreferences.edit();
        addressid = getIntent().getIntExtra("systemid", 0);
        address = getIntent().getStringExtra("address");
        provinceid = getIntent().getIntExtra("provinceid", 0);
        oldregencyid = getIntent().getIntExtra("regencyid", 0);
        etAddressEdit = findViewById(R.id.etAddressEdit);
        etAddressEdit.setText(address);
        userid = sharedPreferences.getInt("userid", 1);
        token = sharedPreferences.getString("token", "");
        provinceSpinner = findViewById(R.id.spinnerProvince);
        regencySpinner = findViewById(R.id.spinnerRegency);
        provinces = new ArrayList<String>();
        regencies = new ArrayList<Regency>();
        regencySpinnerAdapter = new ArrayAdapter<Regency>(EditAddressActivity.this, android.R.layout.simple_spinner_item, regencies);
        regencySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regencySpinner.setAdapter(regencySpinnerAdapter);
        dataAdapter = new ArrayAdapter<String>(EditAddressActivity.this, android.R.layout.simple_spinner_item, provinces);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner.setAdapter(dataAdapter);
        updateButton = findViewById(R.id.btnSaveAddressEdit);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmUpdate();
            }
        });

        LoadProvinceData();

        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                provinceSpinner.setSelection(i);
                regencies.clear();
                getRegencyFromProvince(i + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        regencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                regencySpinner.setSelection(i);
                regencyid = regencySpinnerAdapter.getItem(i).getRegencyId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    void LoadProvinceData() {
        final ProgressDialog loading = ProgressDialog.show(EditAddressActivity.this, null, "Harap Tunggu...", true, false);
        Call<JsonObject> getCall = apiInterface.getProvince(token, userid);
        getCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body().get("success").getAsBoolean()) {
                    JsonArray message = response.body().get("message").getAsJsonArray();
                    for (int i = 0; i < message.size(); i++) {
                        JsonObject tempObject = message.get(i).getAsJsonObject();
                        provinces.add(ExtraUtility.removeQuotation(tempObject.get("name").getAsString()));
                    }
                    dataAdapter.notifyDataSetChanged();
                    provinceSpinner.setSelection(provinceid - 1);
                    loading.dismiss();

                } else {
                    loading.dismiss();
                    Toast.makeText(EditAddressActivity.this, "Failed to get data from server", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(EditAddressActivity.this, "Failed to get data from server", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    void getRegencyFromProvince(int provinceid) {
        final ProgressDialog loading = ProgressDialog.show(EditAddressActivity.this, null, "Harap Tunggu...", true, false);
        Call<JsonObject> getCall = apiInterface.getRegency(token, userid, provinceid);
        getCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body().get("success").getAsBoolean()) {
                    JsonArray message = response.body().get("message").getAsJsonArray();
                    for (int i = 0; i < message.size(); i++) {
                        JsonObject tempObject = message.get(i).getAsJsonObject();
                        Regency tempRegency = new Regency(tempObject.get("systemid").getAsInt(), ExtraUtility.removeQuotation(tempObject.get("name").getAsString()));
                        regencies.add(tempRegency);
                    }
                    regencySpinnerAdapter.notifyDataSetChanged();
                    for (int j = 0; j < regencies.size(); j++) {
                        if (regencies.get(j).getRegencyId() == oldregencyid) {
                            regencySpinner.setSelection(regencySpinnerAdapter.getPosition(regencies.get(j)));
                            regencyid = regencySpinnerAdapter.getItem(j).getRegencyId();
                        } else {
                            regencySpinner.setSelection(0);
                            regencyid = regencySpinnerAdapter.getItem(0).getRegencyId();
                        }

                    }
                    loading.dismiss();

                } else {
                    loading.dismiss();
                    Toast.makeText(EditAddressActivity.this, "Failed to get data from server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(EditAddressActivity.this, "Failed to get data from server", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    void confirmUpdate() {
        String street = etAddressEdit.getText().toString();
        if (!street.equals("")) {
            etAddressEdit.setError(null);
            final ProgressDialog loading = ProgressDialog.show(EditAddressActivity.this, null, "Harap Tunggu...", true, false);
            JsonObject joCred = new JsonObject();

            joCred.addProperty("street", street);
            joCred.addProperty("regency_id", regencyid);
            joCred.addProperty("address_id", addressid);
            Call<JsonObject> getCall = apiInterface.updateAddress(token, userid, userid, joCred);
            getCall.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.body().get("success").getAsBoolean()) {
                        Toast.makeText(EditAddressActivity.this, "Update address berhasil", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                        finish();

                    } else {
                        loading.dismiss();
                        Toast.makeText(EditAddressActivity.this, "Failed to get data from server", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    loading.dismiss();
                    Toast.makeText(EditAddressActivity.this, "Failed to get data from server", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            etAddressEdit.setError("Alamat tidak boleh kosong!");
        }

    }

}
