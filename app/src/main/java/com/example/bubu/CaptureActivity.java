package com.example.bubu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

import static androidx.core.math.MathUtils.clamp;

public class CaptureActivity extends Activity implements SurfaceHolder.Callback {
    TextView testView;

    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    Camera.PictureCallback rawCallback;
    Camera.ShutterCallback shutterCallback;
    Camera.PictureCallback jpegCallback;
    //  FancyButton start, stop, capture;

    /**
     * Called when the activity is first created.
     */
    @SuppressLint("DefaultLocale")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.camera_layout);

        surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        rawCallback = (data, camera) -> {
        };

        shutterCallback = () -> {
        };

        jpegCallback = (data, camera) -> {

            File pictureFileDir = getDir();

            if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
                Toast.makeText(getApplicationContext(), "Can't create directory to save image.",
                        Toast.LENGTH_LONG).show();
                return;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss");
            String date = dateFormat.format(new Date());
            String photoFile ="BUBU_IMG_"+ date + ".jpg";

            String filename = pictureFileDir.getPath() + File.separator + photoFile;

            File pictureFile = new File(filename);
            FileOutputStream outStream;
            Bitmap realImage = BitmapFactory.decodeByteArray(data, 0, data.length);


            try {
                outStream = new FileOutputStream(pictureFile);
                RotateImage(pictureFile,realImage,outStream);
               // outStream.write(data);
                outStream.close();
                MediaScannerConnection.scanFile(this, new String[] { pictureFile.getPath() }, new String[] { "image/jpeg" }, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        new Handler().postDelayed(this::start_camera, 10);
        //setListener();
    }

    void RotateImage(File pictureFile, Bitmap realImage, FileOutputStream fos) throws IOException {
        ExifInterface exif=new ExifInterface(pictureFile.toString());
        if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("6")){
            realImage= rotate(realImage, 90);
        } else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("8")){
            realImage= rotate(realImage, 270);
        } else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("3")){
            realImage= rotate(realImage, 180);
        } else if(exif.getAttribute(ExifInterface.TAG_ORIENTATION).equalsIgnoreCase("0")){
            realImage= rotate(realImage, 90);
        }
        realImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        fos.close();
    }

    public static Bitmap rotate(Bitmap bitmap, int degree) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix mtx = new Matrix();
        mtx.setRotate(degree);

        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }
    private void captureImage() {
        // TODO Auto-generated method stub
        camera.autoFocus((success, camera) -> {
            if (success) {
                camera.takePicture(shutterCallback, rawCallback, jpegCallback);
                blink();

//                Toast.makeText(getApplicationContext(), "New Image saved",
//                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void blink() {
        Animation anim = new AlphaAnimation(0.0f, 0.0f);
        anim.setDuration(50);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(1);
        surfaceView.startAnimation(anim);
    }

    private void start_camera() {
        try {
            camera = Camera.open();
        } catch (RuntimeException e) {
            return;
        }
        Camera.Parameters param;
        param = camera.getParameters();

        //modify parameter
        param.setPreviewFrameRate(20);
        param.setPreviewSize(176, 144);
        param.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        camera.setDisplayOrientation(90);
        camera.setParameters(param);
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {
            return;
        }
    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int width, int height) {
        // TODO Auto-generated method stub

//        if (isPreviewRunning) {
//            camera.stopPreview();
//        }
//
//        Camera.Parameters parameters = camera.getParameters();
//        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
//
//        if(display.getRotation() == Surface.ROTATION_0) {
//            parameters.setPreviewSize(height, width);
//            camera.setDisplayOrientation(90);
//        }
//
//        if(display.getRotation() == Surface.ROTATION_90) {
//            parameters.setPreviewSize(width, height);
//        }
//
//        if(display.getRotation() == Surface.ROTATION_180) {
//            parameters.setPreviewSize(height, width);
//        }
//
//        if(display.getRotation() == Surface.ROTATION_270) {
//            parameters.setPreviewSize(width, height);
//            camera.setDisplayOrientation(180);
//        }
//
//        camera.setParameters(parameters);
//        previewCamera();
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    public void onClick(View view) {
        captureImage();
    }

    private File getDir() {
        File sdDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        return new File(sdDir, "BubuFiles");
    }

    @Override
    protected void onResume() {
        new Handler().postDelayed(this::start_camera, 10);
        super.onResume();
    }

    public void viewLastImage(View view) {
        Toast.makeText(this,"Go to Gallery bubu", Toast.LENGTH_SHORT).show();
    }

}