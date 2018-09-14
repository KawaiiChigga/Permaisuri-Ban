package cv.sunwell.permaisuriban.modules.main.transaction.detail;

import android.app.ActionBar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cv.sunwell.permaisuriban.R;

public class TransactionDetailActivity extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TransactionDetailAdapter transactionDetailAdapter;

    public void setActionBarTitle(int title) {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        TextView textView = findViewById (R.id.tvActionBarTitle);
        textView.setText (title);
    }

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_transaction_detail);

        tabLayout = (TabLayout)findViewById (R.id.tlTransDetail);
        viewPager = (ViewPager)findViewById (R.id.vpTransDetail);
        transactionDetailAdapter = new TransactionDetailAdapter (getSupportFragmentManager ());

        //add fragment
        transactionDetailAdapter.addFragmet (new TransStatusFragment (),"STATUS");
        transactionDetailAdapter.addFragmet (new TransDetailFragment (),"DETAIL");
        transactionDetailAdapter.addFragmet (new TransAddressFragment (),"ADDRESS");


        viewPager.setAdapter (transactionDetailAdapter);
        tabLayout.setupWithViewPager (viewPager);

        setActionBarTitle (R.string.title_transaction_detail);
    }
}
