package com.example.Odev2_14011028;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static TextView setName;
    private static TextView setSurname;
    private static TextView setIdentity;
    private static TextView setEmail;
    private static TextView setPhone;
    private static TextView setBirthlocation;
    private static TextView setBirthdate;
    private static ImageView setResim;
    private static Button butCall;
    private static Button butMail;
    private static Button butClasslist;
    String phoneNum;
    String email;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle = getIntent().getExtras();
        setName=(TextView)findViewById(R.id.textView_name2);
        setSurname=(TextView)findViewById(R.id.textView_surname2);
        setIdentity=(TextView)findViewById(R.id.textView_identity2);
        setEmail=(TextView)findViewById(R.id.textView_email2);
        setPhone=(TextView)findViewById(R.id.textView_phone2);
        setBirthlocation=(TextView)findViewById(R.id.textView_birthlocation2);
        setBirthdate=(TextView)findViewById(R.id.textView_birthdate2);
        butCall=(Button)findViewById(R.id.button_call);
        butMail=(Button)findViewById(R.id.button_mail);
        butClasslist=(Button)findViewById(R.id.button_classlist);

        Bitmap bitmapimage = bundle.getParcelable("bitmapimg");
        setResim = (ImageView)findViewById(R.id.imageView2);
        setResim.setImageBitmap(bitmapimage);

        setName.setText("Ad: "+bundle.getString("ad"));
        setSurname.setText("Soyad: "+bundle.getString("soyad"));
        setIdentity.setText("TC No: "+bundle.getString("tcno"));
        setEmail.setText("E-mail: "+bundle.getString("email"));
        setPhone.setText("Tel No:"+bundle.getString("telno"));
        setBirthlocation.setText("Dogum Yeri:" +bundle.getString("dogyer"));
        setBirthdate.setText("Dogum Tarihi: "+bundle.getString("dogtar"));
        phoneNum=bundle.getString("telno").trim();
        email=bundle.getString("email").trim();

        butCall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+Uri.encode(phoneNum)));
                startActivity(callIntent);

            }
        });

        butMail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sendEmail(email);

            }
        });

        butClasslist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(".Recycle");
                startActivity(intent);
            }
        });


    }

    public void sendEmail(String email){
        Intent mailIntent=new Intent(Intent.ACTION_SEND);
        mailIntent.setData(Uri.parse("mailto:"));
        mailIntent.setType("text/plain");
        mailIntent.putExtra(Intent.EXTRA_EMAIL,email);
        try{
            startActivity(Intent.createChooser(mailIntent,"Choose mail client"));

        }catch (Exception e){

        }
    }
}
