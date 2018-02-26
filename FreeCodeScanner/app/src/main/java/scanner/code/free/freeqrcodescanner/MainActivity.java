package scanner.code.free.freeqrcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {//implements ZXingScannerView.ResultHandler{

    //ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mScannerView = new ZXingScannerView(this);
        //setContentView(mScannerView);
    }
}
