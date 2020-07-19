package com.example.keteringapp.ui.pesan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keteringapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Sub_pes_diet extends AppCompatActivity {


    TextView Hrg;
    EditText etnama,etnohp,etalamat,etbb,ethartot;
    Spinner spdiet,sppkt;
    Button Simpan;
    int paket = 100000;
    String spinwkt [] = {"1 Hari","1 Minggu","2 Minggu","1 Bulan"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_pes_diet);


        Hrg = findViewById(R.id.hrg);
        etnama = findViewById(R.id.et_nama);
        etnohp = findViewById(R.id.et_wa);
        etalamat = findViewById(R.id.et_alamat);
        etbb = findViewById(R.id.et_bb);
        spdiet = findViewById(R.id.pil_amblantar);
        sppkt = findViewById(R.id.pkthari);
        Simpan = findViewById(R.id.simpan);
        ethartot = findViewById(R.id.ethargatotal);
        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vNama = etnama.getText().toString();
                String vNohp = etnohp.getText().toString();
                String vAlamat = etalamat.getText().toString();
                String Spdiet = String.valueOf(spdiet.getSelectedItem());
                String vBB = etbb.getText().toString();
                String Sppkt = String.valueOf(sppkt.getSelectedItem());
                String vTotal = ethartot.getText().toString();

                if (vNama.isEmpty()) {
                    etnama.setError("Nama tidak boleh kosong");
                    etnama.requestFocus();
                    return;
                }
                if (vNama.length() < 3) {
                    etnama.setError("Nama harus lebih dari 2 karakter");
                    return;
                }

                if (vNohp.isEmpty()) {
                    etnohp.setError("Nomor telpon tidak boleh kosong");
                    etnohp.requestFocus();
                    return;
                }
                if (vNohp.length() < 12) {
                    etnohp.setError("Masukan nomor telepon yang valid");
                    return;
                }
                if (vAlamat.isEmpty()) {
                    etalamat.setError("Alamat tidak boleh kosong");
                    etalamat.requestFocus();
                    return;
                }

                Intent diet = new Intent(Sub_pes_diet.this, ValueDietActivity.class);
                diet.putExtra("extraNama", vNama);
                diet.putExtra("extraNohp", vNohp);
                diet.putExtra("extraAlamat", vAlamat);
                diet.putExtra("extraSpdiet", Spdiet);
                diet.putExtra("extraBb", vBB);
                diet.putExtra("extraSppaket", Sppkt);
                diet.putExtra("extraTotal", vTotal);
                startActivity(diet);
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Sub_pes_diet.this,android.R.layout.simple_list_item_1,spinwkt);
        sppkt.setAdapter(arrayAdapter);

        sppkt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = sppkt.getItemAtPosition(position).toString();
                if (select == spinwkt[0]){

                    ethartot.setText(String.valueOf(paket));
                }
                else if (select == spinwkt[1]){
                    int tot = paket*7;
                    ethartot.setText(String.valueOf(tot));
                }
                else if (select == spinwkt[2]){
                    int tot = paket*14;
                    ethartot.setText(String.valueOf(tot));
                }
                else if (select == spinwkt[3]){
                    int tot = paket*30;
                    ethartot.setText(String.valueOf(tot));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
