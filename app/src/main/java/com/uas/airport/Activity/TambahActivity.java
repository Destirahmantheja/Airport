package com.uas.airport.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etSejarah, etLuasBandara, etKota, etTahunberdiri;
    private Button btnTambah;
    private String nama, sejarah, luasbandara, kota, tahunberdiri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etSejarah = findViewById(R.id.et_sejarah);
        etLuasBandara = findViewById(R.id.et_luas_bandara);
        etKota = findViewById(R.id.et_kota);
        etTahunberdiri = findViewById(R.id.et_tahun_berdiri);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                sejarah = etSejarah.getText().toString();
                luasbandara = etLuasBandara.getText().toString();
                kota = etKota.getText().toString();
                tahunberdiri = etTahunberdiri.getText().toString();

                if (nama.trim().isEmpty()) {
                    etNama.setError("Nama tidak boleh kosong");
                } else if (sejarah.trim().isEmpty()) {
                    etSejarah.setError("Sejarah tidak boleh kosong");
                } else if (luasbandara.trim().isEmpty()) {
                    etLuasBandara.setError("luas tidak boleh kosong");
                } else if (kota.trim().isEmpty()) {
                    etKota.setError("Kota tidak boleh kosong");
                } else if (tahunberdiri.trim().isEmpty()) {
                    etTahunberdiri.setError("Tahun tidak boleh kosong");
                } else {
                    tambahAirport();
                }

            }
        });
    }
    private void tambahAirport (){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        retrofit2.Call<ModelResponse> proses = API.ardCreate(nama, sejarah, luasbandara, kota, tahunberdiri);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ModelResponse> call, Response<ModelResponse> response) {
               String kode, pesan;
               kode = response.body().getKode();
               pesan = response.body().getPesan();

               Toast.makeText(TambahActivity.this, "Kode : " + kode + "Pesan : " + pesan, Toast.LENGTH_SHORT).show();
               finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                Log.d("Disini", "Errornya itu: " + t.getMessage());

            }
        });
    }
}
