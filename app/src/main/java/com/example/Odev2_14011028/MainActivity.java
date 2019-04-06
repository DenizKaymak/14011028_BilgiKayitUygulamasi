package com.example.Odev2_14011028;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener*/ {

    private static EditText editName;
    private static EditText editSurname;
    private static EditText editIdentity;
    private static EditText editEmail;
    private static EditText editPhone;
    private static EditText editBirthlocation;
    private static Spinner spinDay;
    private static Spinner spinMonth;
    private static Spinner spinYear;
    private static Button butConfirm;
    private static ImageView resim;
    private static Button butResim;
    private static Button butKamera;
    private static Button butClear;

    Bundle bundle=new Bundle();
    int request_code=1;

    Uri image_uri;



    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1001;

    private static final int CAMERA_PERMISSION_CODE=2000;
    private static final int IMAGE_CAPTURE_CODE=2001;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinnerday=findViewById(R.id.spinner_day);
        Spinner spinnermonth=findViewById(R.id.spinner_month);
        Spinner spinneryear=findViewById(R.id.spinner_year);
        resim=findViewById(R.id.imageView);
        butResim=findViewById(R.id.button_image);
        butKamera=findViewById(R.id.button_camera);
        butClear=findViewById(R.id.button_clear);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.days,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.months,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3=ArrayAdapter.createFromResource(this,R.array.years,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerday.setAdapter(adapter1);
        spinnermonth.setAdapter(adapter2);
        spinneryear.setAdapter(adapter3);
        onConfirm();

        butResim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        //permission not granted,request it.
                        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                        //Show popup for runtime permission.
                        requestPermissions(permissions,PERMISSION_CODE);

                    }else{
                        //permission already granted
                        pickImageFromGallery();

                    }
                }else{
                    //SYSTEM OS less than Marshmellow
                    pickImageFromGallery();
                }

            }
        });

        butKamera.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        //permission not granted,request it.
                        String[] permissions={Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions,CAMERA_PERMISSION_CODE);
                    }else{
                        //permission already granted
                        openCamera();
                    }
                }else{
                    //SYSTEM OS less than Marshmellow
                    openCamera();
                }
            }
        });

        butClear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

    }

    private void openCamera(){
        ContentValues values=new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From Camera");
        image_uri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE);

    }

    private void pickImageFromGallery() {
        Intent resimsec=new Intent(Intent.ACTION_PICK);
        resimsec.setType("image/*");
        startActivityForResult(resimsec,IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
            }
            case CAMERA_PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openCamera();
                    //permission denied icin toast yapilabilir.
                }

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK && requestCode==IMAGE_PICK_CODE){
            resim.setScaleType(ImageView.ScaleType.FIT_XY);
            resim.setImageURI(data.getData());

        }else if(resultCode==RESULT_OK && requestCode==IMAGE_CAPTURE_CODE){
            resim.setImageURI(image_uri);
        }


    }

    public void onConfirm(){

        editName=(EditText)findViewById(R.id.editText_name);
        editSurname=(EditText)findViewById(R.id.editText_surname);
        editIdentity=(EditText)findViewById(R.id.editText_identity);
        editEmail=(EditText)findViewById(R.id.editText_email);
        editPhone=(EditText)findViewById(R.id.editText_phone);
        editBirthlocation=(EditText)findViewById(R.id.editText_birthlocation);
        spinDay=(Spinner)findViewById(R.id.spinner_day);
        spinMonth=(Spinner)findViewById(R.id.spinner_month);
        spinYear=(Spinner)findViewById(R.id.spinner_year);
        butConfirm=(Button)findViewById(R.id.button_confirm);
        resim=(ImageView)findViewById(R.id.imageView);


        butConfirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(MainActivity.this,test,Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(".SecondActivity");
                        bundle.putString("ad",editName.getText().toString());
                        bundle.putString("soyad",editSurname.getText().toString());
                        bundle.putString("tcno",editIdentity.getText().toString());
                        bundle.putString("email",editEmail.getText().toString());
                        bundle.putString("telno",editPhone.getText().toString());
                        bundle.putString("dogyer",editBirthlocation.getText().toString());
                        String test=spinDay.getSelectedItem().toString()+" "+spinMonth.getSelectedItem().toString()+" "+spinYear.getSelectedItem().toString();
                        bundle.putString("dogtar",test);

                        resim.invalidate();


                        Bitmap bmp=((BitmapDrawable)resim.getDrawable()).getBitmap();
                        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bmp, 200, 200, false);
                        bundle.putParcelable("bitmapimg",resizedBitmap);

                        intent.putExtras(bundle);
                        startActivityForResult(intent,request_code);
                    }
                }


        );

    }

}
