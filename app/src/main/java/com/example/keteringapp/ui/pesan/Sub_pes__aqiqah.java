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

public class Sub_pes__aqiqah extends AppCompatActivity {
    TextView tvtgl,tv_hrg;
    EditText ettgl,etnama,etnohp,etalamat;
    Spinner Spaqiqah;
    Button Simpan;
    DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_pes__aqiqah);

        tvtgl = findViewById(R.id.tv_tanggal);
        ettgl = findViewById(R.id.et_tanggal);
        etnama = findViewById(R.id.et_nama);
        etnohp = findViewById(R.id.et_wa);
        etalamat = findViewById(R.id.et_alamat);
        Spaqiqah = findViewById(R.id.pil_amblantar);
        Simpan = findViewById(R.id.simpan);
        tv_hrg = findViewById(R.id.ethargatotal);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvtgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Sub_pes__aqiqah.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                tvtgl.setText(date);
            }
        };
        ettgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Sub_pes__aqiqah.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        ettgl.setText(date);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getpesaqiqah();
                String vNama = etnama.getText().toString();
                String vNohp = etnohp.getText().toString();
                String vAlamat = etalamat.getText().toString();
                String spaqiqah = String.valueOf(Spaqiqah.getSelectedItem());
                String vTanggal = ettgl.getText().toString();
                String vHarga = tv_hrg.getText().toString();

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

                Intent aqiqah = new Intent(Sub_pes__aqiqah.this, ValueAqiqahActivity.class);
                aqiqah.putExtra("extraNama", vNama);
                aqiqah.putExtra("extraNohp", vNohp);
                aqiqah.putExtra("extraAlamat", vAlamat);
                aqiqah.putExtra("extraSpaqiqah", spaqiqah);
                aqiqah.putExtra("extraTanggal", vTanggal);
                aqiqah.putExtra("extraHarga", vHarga);
                startActivity(aqiqah);
            }
        });
    }


}
