package cv.sunwell.permaisuriban.modules.main.transaction.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cv.sunwell.permaisuriban.R;

public class TransDetailFragment extends Fragment
{
    View view;

    public TransDetailFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate (R.layout.fragment_trans_detail,container,false);
        return view;
    }
}
