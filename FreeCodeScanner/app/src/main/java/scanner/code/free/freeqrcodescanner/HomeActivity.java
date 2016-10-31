package scanner.code.free.freeqrcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener,
        MessageDialogFragment.Communicator, ZXingScannerView.ResultHandler {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private AdView mAdView;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(scanner.code.free.freeqrcodescanner.R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdView = (AdView) findViewById(scanner.code.free.freeqrcodescanner.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mScannerView = new ZXingScannerView(this);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mScannerView.setLayoutParams(params);
        frameLayout.addView(mScannerView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(null != mAdView){
            mAdView.resume();
        }

        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(null != mAdView){
            mAdView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != mAdView){
            mAdView.destroy();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == scanner.code.free.freeqrcodescanner.R.id.bar_code_image_button) {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0); //Barcode Scanner to scan for us
        } else {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(ScanQRCodeFragment.class.getName());
            if(fragment == null) {
                fragment = ScanQRCodeFragment.newInstance();
            }

            getSupportFragmentManager().beginTransaction().add(scanner.code.free.freeqrcodescanner.R.id.container, fragment, fragment.getClass().getName())
                    .commit();
        }
    }


    @Override
    public void onDialogButtonClick() {

        /*Fragment fragment = getSupportFragmentManager().findFragmentByTag(ScanQRCodeFragment.class.getName());
        if(fragment == null) {
            fragment = ScanQRCodeFragment.newInstance();
        }
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();*/
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();

    }

    @Override
    public void handleResult(Result result) {
        Log.d(TAG, result.getBarcodeFormat().toString());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Create and show the dialog.
        MessageDialogFragment newFragment = new MessageDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULT_KEY, result.getText());
        newFragment.setArguments(bundle);

        newFragment.show(ft, getString(R.string.str_scan_result));

        getFragmentManager().popBackStack();
    }
}
