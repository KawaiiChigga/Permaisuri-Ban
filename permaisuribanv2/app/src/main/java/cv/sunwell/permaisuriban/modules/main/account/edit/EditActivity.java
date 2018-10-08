package cv.sunwell.permaisuriban.modules.main.account.edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.main.MainActivity;

public class EditActivity extends AppCompatActivity
{
    Button btnSaveEdit;
    EditText etDobEdit;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_edit);
        setTitle ("Edit Profile");

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


    }

    private void saveExit(){
        if(checkFormat()==true){
            Intent intent = new Intent (EditActivity.this, MainActivity.class);
            intent.putExtra("frgToLoad", 4);
            startActivity (intent);
            finish ();
        }

    }

    private boolean checkFormat(){
        if(etDobEdit.getText().length()!=10){
            etDobEdit.setError("Format tanggal salah");
            return false;
        }
        else{
            if(!etDobEdit.getText().toString().substring(2,3).equals("/") || !etDobEdit.getText().toString().substring(5,6).equals("/")){
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
}
