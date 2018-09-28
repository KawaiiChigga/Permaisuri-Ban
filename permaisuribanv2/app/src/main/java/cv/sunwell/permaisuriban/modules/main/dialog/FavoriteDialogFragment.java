package cv.sunwell.permaisuriban.modules.main.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.main.cart.CartFragment;
import cv.sunwell.permaisuriban.modules.main.favourite.FavouriteFragment;

public class FavoriteDialogFragment extends DialogFragment {

    String favItemName;
    int imgUrl;
    int position;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favItemName = getArguments().getString("name");
        imgUrl = getArguments().getInt("imgUrl");
        position = getArguments().getInt("position");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout. dialog_favorite, null);
        builder.setView(view);
        final FavouriteFragment frag = (FavouriteFragment)getTargetFragment();

        TextView tvDelDialog = view.findViewById(R.id.tvFavDialog);
        tvDelDialog.setText(favItemName);

        ImageView ivDelDialog = view.findViewById(R.id.ivFavDialog);
        ivDelDialog.setImageResource(imgUrl);

        Button btnDelDialogNo = view.findViewById(R.id.btnFavDialogNo);
        btnDelDialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        Button btnDelDialogYes = view.findViewById(R.id.btnFavDialogGo);
        btnDelDialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if(frag != null){
                    frag.goItem(position);
                }
            }
        });

        Button btnDelDialogYesAdd = view.findViewById(R.id.btnFavDialogDel);
        btnDelDialogYesAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(frag != null){
                    frag.deleteItem(position);
                }
                dismiss();
            }
        });

        return builder.create();
    }

}
