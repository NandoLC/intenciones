package com.example.fernando.intencionesimplicitas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.ivimagen) ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }
    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5})
    protected void onClick(View v)
    {
        Intent intent;

        switch (v.getId())
        {
            case R.id.btn1://Llamada telefónica
                try {
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:4131215334"));
                    startActivity(intent);
                }catch (SecurityException e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn2://Correo
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");//mime ->Intercambio de información
                intent.putExtra(Intent.EXTRA_SUBJECT,"Asunto");
                intent.putExtra(Intent.EXTRA_TEXT,"Texto del mensaje");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"14030733@itcelaya.edu.mx"});
                startActivity(intent);
                break;
            case R.id.btn3://Pagina Web
                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.itcelaya.edu.mx"));
                startActivity(intent);
                break;
            case R.id.btn4://Camara
                Intent infoto = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(infoto,6);
                break;
            case R.id.btn5://Mensaje
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("4131215334",null,"Buenos días, have a nice day",null,null);
                Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 6 && resultCode == RESULT_OK){
            Bitmap objB = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(objB);
        }

    }


}
