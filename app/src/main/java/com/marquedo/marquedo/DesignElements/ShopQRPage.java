package com.marquedo.marquedo.DesignElements;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.marquedo.marquedo.R;

public class ShopQRPage extends AppCompatActivity {

    private ImageView storeqr;
    private String storename = "Super Shopping Centre";
    /*Bitmap bitmap;
    QRGEncoder*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.designelements_manage_shop_qr_page);

        storeqr = findViewById(R.id.storeqrid);

        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(storename, BarcodeFormat.QR_CODE, 350,350);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            storeqr.setImageBitmap(bitmap);
            InputMethodManager manager = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE
            );
            //manager.hideSoftInputFromWindow()

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}