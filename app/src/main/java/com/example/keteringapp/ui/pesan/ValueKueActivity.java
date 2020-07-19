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

public class ValueKueActivity extends AppCompatActivity {

    TextView Vnama,Vnohp,Valamat,Vpil,Vtanggal,Vporsi,Vpaket,Vhartot;
    private Button Next;
    private DatabaseReference databasegetpeskue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_kue);

        databasegetpeskue = FirebaseDatabase.getInstance().getReference("pemkue");

        Vnama = findViewById(R.id.vnama);
        Vnohp = findViewById(R.id.vwa);
        Valamat = findViewById(R.id.valamat);
        Vpil = findViewById(R.id.vpil);
        Vtanggal = findViewById(R.id.vtanggal);
        Vpaket = findViewById(R.id.vpilcat);
        Vporsi = findViewById(R.id.vporsi);
        Vhartot = findViewById(R.id.vhartot);

        Intent valuekue = getIntent();
        String vnama = valuekue.getStringExtra("extraNama");
        String vnohp = valuekue.getStringExtra("extraNohp");
        String valamat = valuekue.getStringExtra("extraAlamat");
        String Vspkue = valuekue.getStringExtra("extraSpkue");
        String vtanggal = valuekue.getStringExtra("extraTanggal");
        String vpilkue = valuekue.getStringExtra("extraPilkue");
        String vporsi = valuekue.getStringExtra("extraPorsi");
        String vhartot = valuekue.getStringExtra("extraTotal");

        Vnama.setText(vnama);
        Vnohp.setText(vnohp);
        Valamat.setText(valamat);
        Vpil.setText(Vspkue);
        Vtanggal.setText(vtanggal);
        Vpaket.setText(vpilkue);
        Vporsi.setText(vporsi);
        Vhartot.setText(vhartot);

        Next = findViewById(R.id.submit);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String VelNama = Vnama.getText().toString();
                String VelAlamat = Valamat.getText().toString();
                Intent bktkue = new Intent(ValueKueActivity.this, BuktiTfKue.class);
                bktkue.putExtra("Velnama", VelNama);
                bktkue.putExtra("Velalamat", VelAlamat);
                startActivity(bktkue);
                getpeskue();
            }
        });
    }
    private void getpeskue(){
        String Nama = Vnama.getText().toString().trim();
        String Nohp = Vnohp.getText().toString().trim();
        String Alamat = Valamat.getText().toString().trim();
        String Sppil = Vpil.getText().toString().trim();
        String Tanggal = Vtanggal.getText().toString().trim();
        String Paket = Vpaket.getText().toString().trim();
        String Porsi = Vporsi.getText().toString().trim();
        int Total = Integer.parseInt(Vhartot.getText().toString().trim());

        if(!TextUtils.isEmpty(Nama)){

            String id = databasegetpeskue.push().getKey();

            getpeskue peskue = new getpeskue(id,Nama,Nohp,Alamat,Sppil,Tanggal,Paket,Porsi,Total);

            databasegetpeskue.child(id).setValue(peskue);

            Toast.makeText(this, "Pemesanan Catering Kue", Toast.LENGTH_LONG).show();


        }else {
            Toast.makeText(this,"Anda Belum Mengisi Pemesanan", Toast.LENGTH_LONG).show();
        }
    }
}
