package com.uas.airport.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uas.airport.API.APIRequestData;
import com.uas.airport.API.RetroServer;
import com.uas.airport.Model.ModelResponse;
import com.uas.airport.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private String yId, yNama, ySejarah, yLuasbandara, yKota, yTahunberdiri;
    private EditText etNama, etSejarah, etLuasbandara, etKota, etTahunberdiri;
    private Button btnUbah;
    private String nama, sejarah, luasbandara, kota, tahunberdiri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);
        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        ySejarah = ambil.getStringExtra("xSejarah");
        yLuasbandara = ambil.getStringExtra("xLuasbandara");
        yKota = ambil.getStringExtra("xKota");
        yTahunberdiri = ambil.getStringExtra("xTahunberdiri");

        etNama = findViewById(R.id.et_nama);
        etSejarah = findViewById(R.id.et_sejarah);
        etLuasbandara = findViewById(R.id.et_luas_bandara);
        etKota = findViewById(R.id.et_kota);
        etTahunberdiri = findViewById(R.id.et_tahun_berdiri);

        btnUbah = findViewById(R.id.btn_ubah);

        etNama.setText(yNama);
        etSejarah.setText(ySejarah);
        etLuasbandara.setText(yLuasbandara);
        etKota.setText(yKota);
        etTahunberdiri.setText(yTahunberdiri);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                sejarah = etSejarah.getText().toString();
                luasbandara = etLuasbandara.getText().toString();
                kota = etKota.getText().toString();
                tahunberdiri = etTahunberdiri.getText().toString();

                if (nama.trim().isEmpty()){
                    etNama.setError("Nama Tidak Boleh Kosong");
                }
                else if (sejarah.trim().isEmpty()){
                    etSejarah.setError("Sejarah Tidak Boleh Kosong");
                }
                else if (luasbandara.trim().isEmpty()){
                    etLuasbandara.setError("luas bandara Tidak Boleh Kosong");
                }
                else if (kota.trim().isEmpty()){
                    etKota.setError("kota Tidak Boleh Kosong");
                }
                else if (tahunberdiri.trim().isEmpty()) {
                    etTahunberdiri.setError("tahun Tidak Boleh Kosong");
                }else {
                    ubahAirport();
                }
            }
        });

    }
    private void ubahAirport(){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardUpdate(yId, nama, sejarah, luasbandara, kota, tahunberdiri);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode, pesan;
                kode = response.body().getKode();
                pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode : " + "Pesan : " + pesan, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                Log.d("Disini","Errornya itu: "+ t.getMessage());

            }
        });
    }
}
