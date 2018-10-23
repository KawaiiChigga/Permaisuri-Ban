package cv.sunwell.permaisuriban.modules.main.transaction.detail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cv.sunwell.permaisuriban.R;

public class TransStatusFragment extends android.support.v4.app.Fragment
{
    View view;

    public TransStatusFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate (R.layout.fragment_trans_status,container,false);
        return view;
    }
}
