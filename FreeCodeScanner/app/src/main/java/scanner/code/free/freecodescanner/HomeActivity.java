package scanner.code.free.freecodescanner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        ImageButton qrScannerImageView = (ImageButton) findViewById(R.id.qr_code_image_button);
        ImageButton barCodeScanner = (ImageButton) findViewById(R.id.bar_code_image_button);

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
        if(view.getId() == R.id.bar_code_image_button) {
            Toast.makeText(this, "Bar code", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "QR code", Toast.LENGTH_SHORT).show();
        }
    }
}
