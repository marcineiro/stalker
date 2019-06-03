package com.example.murilomarcineiro.stalker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.murilomarcineiro.stalker.model.Person;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class GalleryActivity extends AppCompatActivity {

    private ArrayList<String> photos = new ArrayList<>();
    private Person p;

    private static final int REQUEST_CAMERA_CODE = 1234;
    private static final int REQUEST_PERMISSION_CODE = 4321;
    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private File file;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        if(getIntent().getExtras()!=null){
            this.p = (Person) getIntent().getExtras().get(MainActivity.PERSON_KEY);
            if(p!=null){
                this.photos = p.getPaths();
            } else {
                ArrayList<String> a = getIntent().getStringArrayListExtra(NewActivity.LIST_PATH_KEY);
                if(a!=null)
                    this.photos = a;
            }
        }

        this.verifyPermission();
    }

    private String generateFileName(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "stalker_"+timeStamp+".jpg";
    }

    private Uri getUri(){
        File path = new File(Environment.getExternalStorageDirectory(), "tis/images");
        if(!path.exists() && !path.mkdirs()){
            Toast.makeText(this, "Failed to create dir", Toast.LENGTH_SHORT).show();
        }
        this.file = new File(path, this.generateFileName());
        return FileProvider.getUriForFile(this, "com.example.stalker", this.file);
    }

    public void addPictureToGallery(){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(this.fileUri);
        this.sendBroadcast(intent);
    }

    public void onClickNew(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            intent.putExtra(MediaStore.EXTRA_OUTPUT, this.getUri());
            startActivityForResult(intent, REQUEST_CAMERA_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK){
            photos.add(this.file.getAbsolutePath());
            this.addPictureToGallery();
        }
    }

    private void verifyPermission(){
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSION_CODE);
        }
    }

    public void onClickList(View view){
        Intent intent = new Intent(this,PhotosActivity.class);
        intent.putExtra(NewActivity.LIST_PATH_KEY,this.photos);
        startActivity(intent);
    }

    public void onClickBack(View view){
        Intent intent = new Intent(this, NewActivity.class);
        if(!this.photos.isEmpty())
            intent.putExtra(NewActivity.LIST_PATH_KEY, this.photos);
        if(p!=null)
            intent.putExtra(MainActivity.PERSON_KEY,(Parcelable) p);
        startActivity(intent);

        finish();
    }
}
