package cv.sunwell.permaisuriban.modules.main.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cv.sunwell.permaisuriban.R;
import cv.sunwell.permaisuriban.modules.main.cart.CartFragment;

public class CartDeleteDialogFragment extends DialogFragment {

    String cartItemName;
    int imgUrl;
    int id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartItemName = getArguments().getString("name");
        imgUrl = getArguments().getInt("imgUrl");
        id = getArguments().getInt("itemId");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout. dialog_delete, null);
        builder.setView(view);
        final CartFragment frag = (CartFragment)getTargetFragment();

        TextView tvDelDialog = view.findViewById(R.id.tvDelDialog);
        tvDelDialog.setText(cartItemName);

        ImageView ivDelDialog = view.findViewById(R.id.ivDelDialog);
        ivDelDialog.setImageResource(imgUrl);

        Button btnDelDialogNo = view.findViewById(R.id.btnDelDialogNo);
        btnDelDialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        Button btnDelDialogYes = view.findViewById(R.id.btnDelDialogYes);
        btnDelDialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if(frag != null){
                    frag.deleteItem(id);
                }
            }
        });

        Button btnDelDialogYesAdd = view.findViewById(R.id.btnDelDialogYesAdd);
        btnDelDialogYesAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Hapus dan tambahkan", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        return builder.create();
    }

}
