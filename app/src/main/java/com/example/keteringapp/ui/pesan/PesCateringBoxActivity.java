package com.example.keteringapp.ui.pesan;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.keteringapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PesCateringBoxActivity extends AppCompatActivity {
    TextView tvtgl,hrg;
    EditText etnama,etnohp,etalamat,ettotal;
    RadioButton Vayam,Vudang,Vcumi,Vdaging;
    EditText ettgl;
    RadioGroup rg;
    Button btn_plus;
    Button btn_min;
    Button Simpan;
    Spinner spBox;
    Integer valuejumlah = 0;
    int harga[] = {20000,35000,25000,30000};
    TextView tv_angka;
    DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pes_catering_box);

        hrg = findViewById(R.id.hrg);
        tvtgl = findViewById(R.id.tv_tanggal);
        ettgl = findViewById(R.id.et_tanggal);
        etnama = findViewById(R.id.et_nama);
        etalamat = findViewById(R.id.et_alamat);
        etnohp = findViewById(R.id.et_wa);
        btn_plus = findViewById(R.id.plus);
        btn_min = findViewById(R.id.min);
        rg = findViewById(R.id.rgpilih);
        tv_angka = findViewById(R.id.angka);
        spBox = findViewById(R.id.pil_amblantar);
        Vayam = findViewById(R.id.pktayam);
        Vudang = findViewById(R.id.pktudang);
        Vcumi = findViewById(R.id.pktcumi);
        Vdaging = findViewById(R.id.pktdaging);
        Simpan = findViewById(R.id.simpan);
        ettotal = findViewById(R.id.ethargatotal);




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
                        PesCateringBoxActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
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
                        PesCateringBoxActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                 int hrd = Integer.parseInt(hrg.getText().toString());
                int total = tot*hrd;
                ettotal.setText(String.valueOf(total));
            }
        });
        btn_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valuejumlah > 1){
                    valuejumlah -= 1;
                    tv_angka.setText(valuejumlah.toString());
                    int tot =Integer.parseInt(tv_angka.getText().toString());
                    int hrd = Integer.parseInt(hrg.getText().toString());
                    int total = tot*hrd;
                    ettotal.setText(String.valueOf(total));
                }
            }
        });

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vNama = etnama.getText().toString();
                String vNohp = etnohp.getText().toString();
                String vAlamat = etalamat.getText().toString();
                String Spbox = String.valueOf(spBox.getSelectedItem());
                String vTanggal = ettgl.getText().toString();
                String pilpkt = " ";
                if (Vayam.isChecked()){
                    pilpkt+= "Paket Ayam";
                }
                if (Vudang.isChecked()){
                    pilpkt+= "Paket Udang";
                }
                if (Vcumi.isChecked()){
                    pilpkt+= "Paket Cumi";
                }
                if (Vdaging.isChecked()){
                    pilpkt+= "Paket Daging";
                }
                String vPorsi = tv_angka.getText().toString();
                String vTotal = ettotal.getText().toString();


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

                Intent box = new Intent(PesCateringBoxActivity.this, ValueBoxActivity.class);
                box.putExtra("extraNama", vNama);
                box.putExtra("extraNohp", vNohp);
                box.putExtra("extraAlamat", vAlamat);
                box.putExtra("extraSpbox", Spbox);
                box.putExtra("extraTanggal", vTanggal);
                box.putExtra("extraPaket", pilpkt);
                box.putExtra("extraPorsi", vPorsi);
                box.putExtra("extraTotal", vTotal);
                startActivity(box);

            }
        });

}


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
        switch (view.getId()) {
            case R.id.pktayam:
                if (checked)
                    // Same day service
                    ettotal.setText(String.valueOf(harga[0]));
                hrg.setText(String.valueOf(harga[0]));
                    displayToast(getString(R.string.pktayam));
                break;
            case R.id.pktcumi:
                if (checked)
                    // Next day delivery
                    ettotal.setText(String.valueOf(harga[2]));
                hrg.setText(String.valueOf(harga[2]));

                displayToast(getString(R.string.pktcumi));
                break;
            case R.id.pktudang:
                if (checked)
                    // Next day delivery
                    ettotal.setText(String.valueOf(harga[1]));
                hrg.setText(String.valueOf(harga[1]));

                displayToast(getString(R.string.pktudang));
                break;
            case R.id.pktdaging:
                if (checked)
                    // Next day delivery
                    ettotal.setText(String.valueOf(harga[3]));
                hrg.setText(String.valueOf(harga[3]));

                displayToast(getString(R.string.pktdaging));
                break;
            default:
                // Do nothing.
                break;
        }
    }
    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

}

