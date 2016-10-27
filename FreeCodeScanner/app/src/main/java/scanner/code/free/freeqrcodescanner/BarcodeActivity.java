package scanner.code.free.freeqrcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BarcodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(scanner.code.free.freeqrcodescanner.R.layout.activity_barcode);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        //switch(arg0.getId()){
        //case R.id.butQR:
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        //break;
        //case R.id.butProd:
        intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
        //break;
        //case R.id.butOther:
        intent.putExtra("SCAN_FORMATS", "CODE_39,CODE_93,CODE_128,DATA_MATRIX,ITF,CODABAR");
        //break;
        //}
        startActivityForResult(intent, 0); //Barcode Scanner to scan for us
    }
}
