package cv.sunwell.permaisuriban.modules.main.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.media.Image;
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
import cv.sunwell.permaisuriban.modules.main.home.category.detail.ItemDetailActivity;

public class BuyItemDialogFragment extends DialogFragment {

    String buyItemName;
    int imgUrl;
    int position;
    TextView tvBuyDialogCount;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buyItemName = getArguments().getString("name");
        imgUrl = getArguments().getInt("imgUrl");
        position = getArguments().getInt("position");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout. dialog_buy, null);
        builder.setView(view);
        final ItemDetailActivity act = (ItemDetailActivity)getActivity();

        final TextView tvBuyDialog = view.findViewById(R.id.tvBuyDialog);
        tvBuyDialog.setText(buyItemName);
        tvBuyDialogCount = view.findViewById(R.id.tvBuyDialogCount);
        ImageView ivBuyDialog = view.findViewById(R.id.ivBuyDialog);
        ivBuyDialog.setImageResource(imgUrl);

        Button btnBuyDialogNo = view.findViewById(R.id.btnBuyDialogNo);
        btnBuyDialogNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        Button btnBuyDialogYes = view.findViewById(R.id.btnBuyDialogYes);
        btnBuyDialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if(act != null){
                    act.addSomething();
                }
            }
        });

        Button btnBuyDialogYesAdd = view.findViewById(R.id.btnBuyDialogYesGo);
        btnBuyDialogYesAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if(act != null){
                    act.addSomethingGo();
                }
            }
        });

        ImageView btnBuyDialogMinus = view.findViewById(R.id.ivBuyDialogMinus);
        btnBuyDialogMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer buyCount = Integer.parseInt(tvBuyDialogCount.getText().toString());
                buyCount--;
                tvBuyDialogCount.setText(String.valueOf(buyCount));
            }
        });

        ImageView btnBuyDialogPlus = view.findViewById(R.id.ivBuyDialogPlus);
        btnBuyDialogPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer buyCount = Integer.parseInt(tvBuyDialogCount.getText().toString());
                buyCount++;
                tvBuyDialogCount.setText(String.valueOf(buyCount));
            }
        });

        return builder.create();
    }

}
