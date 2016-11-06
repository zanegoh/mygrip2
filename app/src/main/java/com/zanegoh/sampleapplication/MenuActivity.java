package com.zanegoh.sampleapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by ZaneGoh on 11/6/16.
 */

public class MenuActivity extends Activity {
    Button btnCamera;
    Button btnFlash;

    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.menu_layout);
        btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Hihi", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 123);
            }
        });

        btnFlash = (Button) findViewById(R.id.btnFlash);
        btnFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
                if (hasFlash) {
                    Camera camera;
                    camera = Camera.open();
                    Camera.Parameters params;
                    params = camera.getParameters();
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(params);
                    camera.startPreview();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {
            //Toast.makeText(getApplicationContext(), "Yeah came out image.", Toast.LENGTH_LONG).show();
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ImageView iv = (ImageView) findViewById(R.id.cameraImageView);
            iv.setImageBitmap(image);

        }
    }
}
