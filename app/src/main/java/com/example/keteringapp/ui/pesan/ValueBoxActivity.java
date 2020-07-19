package com.example.keteringapp.ui.pesan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keteringapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ValueBoxActivity extends AppCompatActivity {

    TextView Vnama,Vnohp,Valamat,Vpil,Vtanggal,Vporsi,Vpaket,Vtotal;
    Button Next;

    private DatabaseReference databasegetpesbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_box);

        databasegetpesbox = FirebaseDatabase.getInstance().getReference("pembox");

        Vnama = findViewById(R.id.vnama);
        Vnohp = findViewById(R.id.vwa);
        Valamat = findViewById(R.id.valamat);
        Vpil = findViewById(R.id.vpil);
        Vtanggal = findViewById(R.id.vtanggal);
        Vpaket = findViewById(R.id.vpilcat);
        Vporsi = findViewById(R.id.vporsi);
        Vtotal = findViewById(R.id.vhartot);

        Intent valuebox = getIntent();
        String vnama = valuebox.getStringExtra("extraNama");
        String vnohp = valuebox.getStringExtra("extraNohp");
        String valamat = valuebox.getStringExtra("extraAlamat");
        String Vspbox = valuebox.getStringExtra("extraSpbox");
        String vtanggal = valuebox.getStringExtra("extraTanggal");
        String vpaket = valuebox.getStringExtra("extraPaket");
        String vporsi = valuebox.getStringExtra("extraPorsi");
        String vtotal = valuebox.getStringExtra("extraTotal");

        Vnama.setText(vnama);
        Vnohp.setText(vnohp);
        Valamat.setText(valamat);
        Vpil.setText(Vspbox);
        Vtanggal.setText(vtanggal);
        Vpaket.setText(vpaket);
        Vporsi.setText(vporsi);
        Vtotal.setText(vtotal);



        Next = findViewById(R.id.submit);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String VelNama = Vnama.getText().toString();
                String VelAlamat = Valamat.getText().toString();
                Intent bktbox = new Intent(ValueBoxActivity.this, BuktiTfBox.class);
                bktbox.putExtra("Velnama", VelNama);
                bktbox.putExtra("Velalamat", VelAlamat);
                startActivity(bktbox);
                getpesbox();
            }
        });
    }

    private void getpesbox(){
        String Nama = Vnama.getText().toString().trim();
        String Nohp = Vnohp.getText().toString().trim();
        String Alamat = Valamat.getText().toString().trim();
        String Sppil = Vpil.getText().toString().trim();
        String Tanggal = Vtanggal.getText().toString().trim();
        String Paket = Vpaket.getText().toString().trim();
        String Porsi = Vporsi.getText().toString().trim();
        int Total = Integer.parseInt(Vtotal.getText().toString().trim());

        if(!TextUtils.isEmpty(Nama)){

            String id = databasegetpesbox.push().getKey();

            getpesbox pesbox = new getpesbox(id,Nama,Nohp,Alamat,Sppil,Tanggal,Paket,Porsi,Total);

            databasegetpesbox.child(id).setValue(pesbox);

            Toast.makeText(this, "Pemesanan Catering Box", Toast.LENGTH_LONG).show();


        }else {
            Toast.makeText(this,"Anda Belum Mengisi Pemesanan", Toast.LENGTH_LONG).show();
        }
    }
}
