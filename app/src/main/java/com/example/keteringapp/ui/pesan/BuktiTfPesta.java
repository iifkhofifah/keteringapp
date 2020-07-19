package com.example.keteringapp.ui.pesan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keteringapp.MainActivity;
import com.example.keteringapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class BuktiTfPesta extends AppCompatActivity {


    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.android.example.notifyme.ACTION_UPDATE_NOTIFICATION";
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";
    private static final int NOTIFICATION_ID = 0;
    private NotificationManager mNotifyManager;
    private NotificationReceiver mReceiver = new NotificationReceiver();
    TextView vAlamat,vNama;
    private Button btnChoose,btnUpload,Submit;
    private ImageView imgView;
    private Uri filepath;
    private final int PICK_IMAGE_REQUEST = 71;
    private static final String CHANNEL_ID = "IIP notif";
    private static final String CHANNEL_NAME = "Iip notif";
    private static final  String CHANNEL_DESC = "IIP";

    // firebase
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bukti_tf_pesta);

        btnChoose = (Button) findViewById(R.id.btn_choose);
        btnUpload = (Button) findViewById(R.id.btn_upload);
        imgView = (ImageView) findViewById(R.id.imageView);
        Submit = findViewById(R.id.submit);
        vNama = findViewById(R.id.vnama);
        vAlamat = findViewById(R.id.valamat);

        Intent valuebkt = getIntent();
        String velnama = valuebkt.getStringExtra("Velnama");
        String velalamat = valuebkt.getStringExtra("Velalamat");

        vNama.setText(velnama);
        vAlamat.setText(velalamat);


        //Firebase Init
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel ch = new NotificationChannel(
                    CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager mn = getSystemService(NotificationManager.class);
            mn.createNotificationChannel(ch);
        }
        createNotificationChannel();
        registerReceiver(mReceiver,
                new IntentFilter(ACTION_UPDATE_NOTIFICATION));
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), BuktiTfPesta.class);
//                startActivity(intent);
                Intent intent = new Intent(BuktiTfPesta.this, MainActivity.class);
                startActivity(intent);

                notifikasi();
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();

            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
            }
        });
    }

    private void UploadImage() {
        if(filepath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(BuktiTfPesta.this,"Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(BuktiTfPesta.this,"Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded"+(int)progress+"%");
                        }
                    });

        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imgView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private class NotificationReceiver extends BroadcastReceiver {
        public NotificationReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
        }
    }
    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    private void createNotificationChannel() {
        mNotifyManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
        }
    }
    private void sendNotification() {
//        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
//        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this,
//                NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

    }

    private NotificationCompat.Builder getNotificationBuilder() {
        Intent notificationIntent = new Intent(this, BuktiTfPesta.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity
                (this, NOTIFICATION_ID, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat
                .Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle(getString(R.string.notification_pesta))
                .setContentText(getString(R.string.notification_text))
                .setSmallIcon(R.drawable.ic_android)
                .setAutoCancel(true).setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        return notifyBuilder;
    }

    private void notifikasi(){
        NotificationCompat.Builder mb =
                new NotificationCompat.Builder(this,CHANNEL_ID)
                        .setSmallIcon(R.drawable.icon_logout)
                        .setContentTitle("Pesanan Catering Pesta Sudah Terkirim")
                        .setContentText("Terimakasih Sudah Meng-Order")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat nm = NotificationManagerCompat.from(this);
        nm.notify(1,mb.build());


    }
}
