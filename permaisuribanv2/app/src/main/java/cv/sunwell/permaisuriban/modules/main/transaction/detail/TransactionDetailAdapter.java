package cv.sunwell.permaisuriban.modules.main.transaction.detail;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TransactionDetailAdapter extends FragmentPagerAdapter
{
    private final List<Fragment> fragmentList = new ArrayList<> ();
    private final List<String> stringList = new ArrayList<> ();


    public TransactionDetailAdapter (FragmentManager fm)
    {
        super (fm);
    }

    @Override
    public Fragment getItem (int position)
    {
        return fragmentList.get (position);
    }

    @Override
    public int getCount ()
    {
        return stringList.size ();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle (int position)
    {
        return stringList.get (position);
    }

    public void addFragmet(Fragment _fragment, String _s)
    {
        fragmentList.add(_fragment);
        stringList.add(_s);
    }

}
