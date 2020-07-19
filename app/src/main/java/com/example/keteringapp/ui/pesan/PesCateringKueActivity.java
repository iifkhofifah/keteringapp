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
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.keteringapp.R;

import java.util.Calendar;

public class PesCateringKueActivity extends AppCompatActivity {

    EditText etnama, etnohp, etalamat, ethartot;
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6;
    TextView tvtgl,Hrg;
    EditText ettgl;
    Button btn_plus;
    Button btn_min, Simpan;
    Spinner spkue;
    Integer valuejumlah = 0;
    int hargakue[] = {2000, 3000, 4000};
    TextView tv_angka;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pes_catering_kue);
        Hrg = findViewById(R.id.hrg);
        tvtgl = findViewById(R.id.tv_tanggal);
        ettgl = findViewById(R.id.et_tanggal);
        etnama = findViewById(R.id.et_nama);
        etalamat = findViewById(R.id.et_alamat);
        etnohp = findViewById(R.id.et_wa);
        cb1 = findViewById(R.id.ck_kue1);
        cb2 = findViewById(R.id.ck_kue2);
        cb3 = findViewById(R.id.ck_kue3);
        cb4 = findViewById(R.id.ck_kue4);
        cb5 = findViewById(R.id.ck_kue5);
        cb6 = findViewById(R.id.ck_kue6);
        spkue = findViewById(R.id.pil_amblantar);
        btn_plus = findViewById(R.id.plus);
        btn_min = findViewById(R.id.min);
        tv_angka = findViewById(R.id.angka);
        ethartot = findViewById(R.id.ethargatotal);
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
                        PesCateringKueActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
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
                        PesCateringKueActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuejumlah += 1;
                tv_angka.setText(valuejumlah.toString());
                int tot =Integer.parseInt(tv_angka.getText().toString());
                int hrd = Integer.parseInt(Hrg.getText().toString());
                int total = tot*hrd;
                ethartot.setText(String.valueOf(total));

            }
        });
        btn_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valuejumlah > 1) {
                    valuejumlah -= 1;
                    tv_angka.setText(valuejumlah.toString());
                    int tot =Integer.parseInt(tv_angka.getText().toString());
                    int hrd = Integer.parseInt(Hrg.getText().toString());
                    int total = tot*hrd;
                    ethartot.setText(String.valueOf(total));
                }
            }
        });

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vNama = etnama.getText().toString();
                String vNohp = etnohp.getText().toString();
                String vAlamat = etalamat.getText().toString();
                String Spkue = String.valueOf(spkue.getSelectedItem());
                String vTanggal = ettgl.getText().toString();
                String vPorsi = tv_angka.getText().toString();
                String vHartot = ethartot.getText().toString();
                String vPilkue = "";

                if (cb1.isChecked()) {
                    vPilkue += "Risol \n";
                }
                if (cb2.isChecked()) {
                    vPilkue += "Lemper \n";
                }
                if (cb3.isChecked()) {
                    vPilkue += "Putri Ayu \n";
                }
                if (cb4.isChecked()) {
                    vPilkue += "Kue Lapis \n";
                }
                if (cb5.isChecked()) {
                    vPilkue += "Kue Cikak \n";
                }
                if (cb6.isChecked()) {
                    vPilkue += "Buras";
                }
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

                Intent Kue = new Intent(PesCateringKueActivity.this, ValueKueActivity.class);
                Kue.putExtra("extraNama", vNama);
                Kue.putExtra("extraNohp", vNohp);
                Kue.putExtra("extraAlamat", vAlamat);
                Kue.putExtra("extraSpkue", Spkue);
                Kue.putExtra("extraTanggal", vTanggal);
                Kue.putExtra("extraPilkue", vPilkue);
                Kue.putExtra("extraPorsi", vPorsi);
                Kue.putExtra("extraTotal", vHartot);
                startActivity(Kue);
            }
        });


    }

    public void onChecked(View view) {
        if (cb1.isChecked() && cb2.isChecked() && cb3.isChecked() && cb4.isChecked() && cb5.isChecked() && cb6.isChecked())
        {
            int total = hargakue[0]+hargakue[1]+hargakue[1]+hargakue[0]+hargakue[0]+hargakue[2];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb2.isChecked() && cb3.isChecked() && cb4.isChecked() && cb5.isChecked())
        {
            int total = hargakue[0]+hargakue[1]+hargakue[1]+hargakue[0]+hargakue[0];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb2.isChecked() && cb3.isChecked() && cb4.isChecked() && cb5.isChecked() && cb6.isChecked())
        {
            int total = hargakue[1]+hargakue[1]+hargakue[0]+hargakue[0]+hargakue[2];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb3.isChecked() && cb4.isChecked() && cb5.isChecked() && cb6.isChecked())
        {
            int total = hargakue[0]+hargakue[1]+hargakue[0]+hargakue[0]+hargakue[2];
            ethartot.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb4.isChecked() && cb5.isChecked() && cb6.isChecked() && cb2.isChecked())
        {
            int total = hargakue[0]+hargakue[0]+hargakue[0]+hargakue[2]+hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb3.isChecked() && cb5.isChecked() && cb6.isChecked() && cb2.isChecked())
        {
            int total = hargakue[0]+hargakue[1]+hargakue[0]+hargakue[2]+hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb3.isChecked() && cb4.isChecked() && cb6.isChecked() && cb2.isChecked())
        {
            int total = hargakue[0]+hargakue[1]+hargakue[0]+hargakue[2]+hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb2.isChecked() && cb3.isChecked() && cb4.isChecked())
        {
            int total = hargakue[0]+hargakue[1]+hargakue[1]+hargakue[0];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb2.isChecked() && cb3.isChecked() && cb4.isChecked() && cb5.isChecked())
        {
            int total = hargakue[1]+hargakue[1]+hargakue[0]+hargakue[0];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb3.isChecked() && cb5.isChecked()&& cb6.isChecked())
        {
            int total = hargakue[0]+hargakue[1]+hargakue[0]+hargakue[2];
            ethartot.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb4.isChecked()&& cb6.isChecked()&& cb2.isChecked())
        {
            int total = hargakue[0]+hargakue[0]+hargakue[2]+hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb3.isChecked()&& cb6.isChecked()&& cb2.isChecked())
        {
            int total = hargakue[0]+hargakue[1]+hargakue[2]+hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb3.isChecked()&& cb6.isChecked()&& cb2.isChecked())
        {
            int total = hargakue[0]+hargakue[1]+hargakue[2]+hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb2.isChecked() && cb3.isChecked())
        {
            int total = hargakue[0]+hargakue[1]+hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb2.isChecked() && cb3.isChecked() && cb4.isChecked())
        {
            int total = hargakue[1]+hargakue[1]+hargakue[0];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb3.isChecked() && cb6.isChecked())
        {
            int total = hargakue[0]+hargakue[1]+hargakue[2];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb4.isChecked()&& cb2.isChecked())
        {
            int total = hargakue[0]+hargakue[0]+hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb5.isChecked()&& cb2.isChecked())
        {
            int total = hargakue[0]+hargakue[0]+hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb6.isChecked()&& cb2.isChecked())
        {
            int total = hargakue[0]+hargakue[2]+hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb3.isChecked()&& cb5.isChecked())
        {
            int total = hargakue[0]+hargakue[1]+hargakue[0];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb2.isChecked() && cb4.isChecked()&& cb6.isChecked())
        {
            int total = hargakue[1]+hargakue[0]+hargakue[2];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb2.isChecked() )
        {
            int total = hargakue[0]+hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb2.isChecked() && cb3.isChecked())
        {
            int total = hargakue[0]+hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb3.isChecked())
        {
            int total = hargakue[0]+hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb4.isChecked())
        {
            int total = hargakue[0]+hargakue[0];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb5.isChecked())
        {
            int total = hargakue[0]+hargakue[0];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() && cb6.isChecked())
        {
            int total = hargakue[0]+hargakue[2];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb3.isChecked() && cb5.isChecked())
        {
            int total = hargakue[1]+hargakue[0];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb1.isChecked() )
        {
            int total = hargakue[0];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb2.isChecked() )
        {
            int total = hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb3.isChecked() )
        {
            int total = hargakue[1];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }

        else if (cb4.isChecked() )
        {
            int total = hargakue[0];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb5.isChecked() )
        {
            int total = hargakue[0];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else if (cb6.isChecked() )
        {
            int total = hargakue[2];
            ethartot.setText(String.valueOf(total));
            Hrg.setText(String.valueOf(total));
        }
        else{
            ethartot.setText("0");
            Hrg.setText("0");
        }

    }
}
