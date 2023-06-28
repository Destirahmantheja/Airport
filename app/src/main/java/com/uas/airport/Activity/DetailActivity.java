package com.uas.airport.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uas.airport.R;

public class DetailActivity extends AppCompatActivity {
    private TextView tvNama, tvSejarah, tvLuasbandara, tvKota, tvTahunberdiri;
    private String detNama, detSejarah, detLuasbandara, detKota, detTahunberdiri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama = findViewById(R.id.tv_nama);
        tvSejarah = findViewById(R.id.tv_sejarah);
        tvLuasbandara = findViewById(R.id.tv_luas_bandara);
        tvKota = findViewById(R.id.tv_kota);
        tvTahunberdiri = findViewById(R.id.tv_tahun_berdiri);

        Intent terima = getIntent();
        detNama = terima.getStringExtra("xNama");
        detSejarah = terima.getStringExtra("xSejarah");
        detLuasbandara = terima.getStringExtra("xLuasbandara");
        detKota = terima.getStringExtra("xKota");
        detTahunberdiri = terima.getStringExtra("xTahunberdiri");

        tvNama.setText(detNama);
        tvSejarah.setText(detSejarah);
        tvLuasbandara.setText(detLuasbandara);
        tvKota.setText(detKota);
        tvTahunberdiri.setText(detTahunberdiri);
    }
}
