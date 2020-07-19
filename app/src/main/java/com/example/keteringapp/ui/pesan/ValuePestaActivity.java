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
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keteringapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ValuePestaActivity extends AppCompatActivity {

    TextView Vnama,Vnohp,Valamat,Vsppras,Vtanggal,Vpiltam,Vtotal;

    private Button Next;

    private DatabaseReference databasegetpesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_pesta);

        databasegetpesta = FirebaseDatabase.getInstance().getReference("pempesta");

        Vnama = findViewById(R.id.vnama);
        Vnohp = findViewById(R.id.vwa);
        Valamat = findViewById(R.id.valamat);
        Vsppras = findViewById(R.id.vpilpras);
        Vpiltam = findViewById(R.id.vpiltam);
        Vtanggal = findViewById(R.id.vtanggal);
        Vtotal = findViewById(R.id.vtotal);

        Intent valuekue = getIntent();
        String vnama = valuekue.getStringExtra("extraNama");
        String vnohp = valuekue.getStringExtra("extraNohp");
        String valamat = valuekue.getStringExtra("extraAlamat");
        String vsppras = valuekue.getStringExtra("extraSppes");
        String vtanggal = valuekue.getStringExtra("extraTanggal");
        String vpiltam = valuekue.getStringExtra("extraPiltam");
        String vtotal = valuekue.getStringExtra("extraTotal");

        Vnama.setText(vnama);
        Vnohp.setText(vnohp);
        Valamat.setText(valamat);
        Vsppras.setText(vsppras);
        Vtanggal.setText(vtanggal);
        Vpiltam.setText(vpiltam);
        Vtotal.setText(vtotal);

        Next = findViewById(R.id.next);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String VelNama = Vnama.getText().toString();
                String VelAlamat = Valamat.getText().toString();
                Intent bktpesta = new Intent(ValuePestaActivity.this, BuktiTfPesta.class);
                bktpesta.putExtra("Velnama", VelNama);
                bktpesta.putExtra("Velalamat", VelAlamat);
                startActivity(bktpesta);
                getpesta();
            }
        });
    }
    private void getpesta(){
        String Nama = Vnama.getText().toString().trim();
        String Nohp = Vnohp.getText().toString().trim();
        String Alamat = Valamat.getText().toString().trim();
        String Sppras = Vsppras.getText().toString().trim();
        String Tanggal = Vtanggal.getText().toString().trim();
        String Cbtamb = Vpiltam.getText().toString().trim();
        int Total = Integer.parseInt(Vtotal.getText().toString().trim());

        if(!TextUtils.isEmpty(Nama)){

            String id = databasegetpesta.push().getKey();

            getpesta pesta = new getpesta(id,Nama,Nohp,Alamat,Sppras,Tanggal,Cbtamb,Total);

            databasegetpesta.child(id).setValue(pesta);

            Toast.makeText(this, "Pemesanan Catering Pesta", Toast.LENGTH_LONG).show();


        }else {
            Toast.makeText(this,"Anda Belum Mengisi Pemesanan", Toast.LENGTH_LONG).show();
        }
    }
}
