package cv.sunwell.permaisuriban.modules.main.account.edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_edit);
        setTitle ("Edit Profile");

        btnSaveEdit = findViewById(R.id.btnSaveEdit);

        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveExit();
            }
        });
    }

    private void saveExit(){
        Intent intent = new Intent (EditActivity.this, MainActivity.class);
        intent.putExtra("frgToLoad", 4);
        startActivity (intent);
        finish ();
    }
}
