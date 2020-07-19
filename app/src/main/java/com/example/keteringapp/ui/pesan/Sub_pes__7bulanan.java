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

public class Sub_pes__7bulanan extends AppCompatActivity {
    TextView tvtgl,Hrg;
    EditText ettgl,etnama,etnohp,etalamat,ethartot;
    Button btn_plus;
    Button btn_min;
    Button Simpan;
    Spinner spnubulan;
    Integer valuejumlah = 0;
    TextView tv_angka;
    int hartot = 30000;
    DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_pes__7bulanan);


        Hrg = findViewById(R.id.hrg);
        ethartot = findViewById(R.id.ethargatotal);
        tvtgl = findViewById(R.id.tv_tanggal);
        ettgl = findViewById(R.id.et_tanggal);
        etnama = findViewById(R.id.et_nama);
        etalamat = findViewById(R.id.et_alamat);
        etnohp = findViewById(R.id.et_wa);
        spnubulan = findViewById(R.id.pil_amblantar);
        btn_plus = findViewById(R.id.plus);
        btn_min = findViewById(R.id.min);
        tv_angka = findViewById(R.id.angka);
        Simpan = findViewById(R.id.simpan);

        if (savedInstanceState != null) {
            String nilaiSaved = savedInstanceState.getString("nilai");
            tv_angka.setText(nilaiSaved);
        }

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        tvtgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Sub_pes__7bulanan.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
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
                        Sub_pes__7bulanan.this, new DatePickerDialog.OnDateSetListener() {
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
        Hrg.setText(String.valueOf(hartot));
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuejumlah += 1;
                tv_angka.setText(valuejumlah.toString());
                int hrg = Integer.parseInt(Hrg.getText().toString());
                int angka = Integer.parseInt(tv_angka.getText().toString());
                int tot = hrg*angka;
                ethartot.setText(String.valueOf(tot));
            }
        });
        btn_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valuejumlah > 1){
                    valuejumlah -= 1;
                    tv_angka.setText(valuejumlah.toString());
                    int hrg = Integer.parseInt(Hrg.getText().toString());
                    int angka = Integer.parseInt(tv_angka.getText().toString());
                    int tot = hrg*angka;
                    ethartot.setText(String.valueOf(tot));
                }
            }
        });

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vNama = etnama.getText().toString();
                String vNohp = etnohp.getText().toString();
                String vAlamat = etalamat.getText().toString();
                String spNubulan = String.valueOf(spnubulan.getSelectedItem());
                String vTanggal = ettgl.getText().toString();
                String vPorsi = tv_angka.getText().toString();
                String vHarga = ethartot.getText().toString();

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

                Intent veg = new Intent(Sub_pes__7bulanan.this, Value7bulanActivity.class);
                veg.putExtra("extraNama", vNama);
                veg.putExtra("extraNohp", vNohp);
                veg.putExtra("extraAlamat", vAlamat);
                veg.putExtra("extraNubulan", spNubulan);
                veg.putExtra("extraTanggal", vTanggal);
                veg.putExtra("extraPorsi", vPorsi);
                veg.putExtra("extraTotal", vHarga);
                startActivity(veg);

            }
        });

    }

}
