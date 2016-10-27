package scanner.code.free.freeqrcodescanner;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by ParmeshMahore on 10/25/16.
 */

public class MessageDialogFragment extends DialogFragment {

    private Communicator mCommunicator;
    interface Communicator {
        void onDialogButtonClick();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mCommunicator = (HomeActivity) getActivity();

        Bundle data = getArguments();
        String msg = "";
        if(null != data) {
            msg = data.getString(Constants.RESULT_KEY);
        }

        return new AlertDialog.Builder(getActivity())
                .setTitle(getString(scanner.code.free.freeqrcodescanner.R.string.str_scan_result))
                .setMessage(msg)
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCommunicator.onDialogButtonClick();
                    }
                })
                .setPositiveButton(android.R.string.yes,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCommunicator.onDialogButtonClick();
                    }
                })
                .create();
    }
}
