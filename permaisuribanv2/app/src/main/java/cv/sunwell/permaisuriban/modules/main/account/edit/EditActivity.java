package cv.sunwell.permaisuriban.modules.main.account.edit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import cv.sunwell.permaisuriban.R;

public class EditActivity extends AppCompatActivity
{
    TextView currentOp, newOp, currentPass, currentOpValue, insertNewOp;
    EditText newOpValue, currentOpPassword;
    String name;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_edit);
        currentOp = (TextView) findViewById (R.id.tvCurrentOp);
        currentOpValue = (TextView) findViewById (R.id.tvCurrentOpValue);
        newOp = (TextView) findViewById (R.id.tvNewOp);
        currentPass = (TextView) findViewById (R.id.tvCurrentOpPass);
        insertNewOp = (TextView) findViewById (R.id.tvInsertNew);


        newOpValue = (EditText) findViewById (R.id.etNewOpValue);
        currentOpPassword = (EditText) findViewById (R.id.etCurrentOpPassValue);

        name = getIntent ().getStringExtra ("name");
        setTitle (name);



    }
}
