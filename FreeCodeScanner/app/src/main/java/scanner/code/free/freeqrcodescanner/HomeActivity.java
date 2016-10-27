package scanner.code.free.freeqrcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener, MessageDialogFragment.Communicator {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(scanner.code.free.freeqrcodescanner.R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(scanner.code.free.freeqrcodescanner.R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdView = (AdView) findViewById(scanner.code.free.freeqrcodescanner.R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        ImageButton qrScannerImageView = (ImageButton) findViewById(scanner.code.free.freeqrcodescanner.R.id.qr_code_image_button);
        ImageButton barCodeScanner = (ImageButton) findViewById(scanner.code.free.freeqrcodescanner.R.id.bar_code_image_button);

        qrScannerImageView.setOnClickListener(this);
        barCodeScanner.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(null != mAdView){
            mAdView.resume();
        }
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

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(ScanQRCodeFragment.class.getName());
        if(fragment == null) {
            fragment = ScanQRCodeFragment.newInstance();
        }

        getSupportFragmentManager().beginTransaction().remove(fragment).commit();

        /*getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, fragment.getClass().getName())
                .commit();*/
    }
}
