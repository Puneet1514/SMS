package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final private int REQUEST_SEND_SMS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult( int requestCode,String[] permissions, int[] grantResults )
    {
        switch ( requestCode )
        {
            case REQUEST_SEND_SMS:
                if ( grantResults[0] == PackageManager.PERMISSION_GRANTED )
                {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults );
        };
    }

    public void onClick(View v)
    {
        EditText num = (EditText)findViewById(R.id.number);
        String number = num.getText().toString();
        EditText mess = (EditText)findViewById(R.id.message);
        String message = mess.getText().toString();
        num.setText("");
        mess.setText("");
        SendSMS(number, message);
    }

    private void SendSMS(String phoneNo, String message) {
        SmsManager sms  = SmsManager.getDefault();
        sms.sendTextMessage(phoneNo, null, message, null, null);
    }
}
