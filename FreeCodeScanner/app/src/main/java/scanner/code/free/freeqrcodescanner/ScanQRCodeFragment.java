package scanner.code.free.freeqrcodescanner;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRCodeFragment extends Fragment implements ZXingScannerView.ResultHandler {

    private String TAG = ScanQRCodeFragment.class.getSimpleName();
    private ZXingScannerView mScannerView;
    private static ScanQRCodeFragment mInstance;


    static ScanQRCodeFragment newInstance(){
        if(null == mInstance) {
            mInstance = new ScanQRCodeFragment();
        }

        return mInstance;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mScannerView = new ZXingScannerView(getActivity());
        return mScannerView;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }*/

    @Override
    public void onResume() {
        super.onResume();

        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void handleResult(Result result) {
        Log.d(TAG, result.getBarcodeFormat().toString());

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        // Create and show the dialog.
        MessageDialogFragment newFragment = new MessageDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULT_KEY, result.getText());
        newFragment.setArguments(bundle);

        newFragment.show(ft, getString(scanner.code.free.freeqrcodescanner.R.string.str_scan_result));

        getFragmentManager().popBackStack();
    }

    @Override
    public void onStop() {
        super.onStop();
        mScannerView.stopCamera();
    }
}
