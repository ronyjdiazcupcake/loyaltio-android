package com.slashmobility.loyaltio.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.slashmobility.loyaltio.R;


/**

 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */

public class ProgressDialogHelper {

    private ProgressDialog progressDialog;

    public void showProgressDialog(Context context, boolean cancelable) {
        hideProgressDialog();
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setCancelable(cancelable);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public void hideProgressDialog() {
        if (progressDialog != null) {
            try {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                progressDialog = null;
            } catch (Exception e) {

            }
        }
    }

}