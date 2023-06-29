package com.uas.airport.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uas.airport.API.APIRequestData;
import com.uas.airport.API.RetroServer;
import com.uas.airport.Activity.DetailActivity;
import com.uas.airport.Activity.MainActivity;
import com.uas.airport.Activity.UbahActivity;
import com.uas.airport.Model.ModelAirport;
import com.uas.airport.Model.ModelResponse;
import com.uas.airport.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAirport extends RecyclerView.Adapter<AdapterAirport.VHAirport> {
    private Context ctx;
    private List<ModelAirport> listAirport;

    public AdapterAirport(Context ctx, List<ModelAirport> listAirport) {
        this.ctx = ctx;
        this.listAirport = listAirport;


    }
    public VHAirport onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_airport, parent, false);
        return new VHAirport(varView);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterAirport.VHAirport holder, int position) {
        ModelAirport MA = listAirport.get(position);
        holder.tvId.setText(MA.getId());
        holder.tvNama.setText(MA.getNama());
        holder.tvSejarah.setText(MA.getSejarah());
        holder.tvLuasbandara.setText(MA.getLuasbandara());
        holder.tvKota.setText(MA.getKota());
        holder.tvTahunberdiri.setText(MA.getTahunberdiri());

    }

    @Override
    public int getItemCount() { return listAirport.size();}
    public class VHAirport extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvSejarah, tvLuasbandara, tvKota, tvTahunberdiri;
        Button btnHapus, btnDetail, btnUbah;

        public VHAirport(@NonNull View itemview){
            super(itemview);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvSejarah = itemView.findViewById(R.id.tv_sejarah);
            tvLuasbandara = itemView.findViewById(R.id.tv_luas_bandara);
            tvKota = itemView.findViewById(R.id.tv_kota);
            tvTahunberdiri = itemView.findViewById(R.id.tv_tahun_berdiri);

            btnHapus = itemView.findViewById(R.id.btn_hapus);
            btnUbah = itemView.findViewById(R.id.btn_ubah);
            btnDetail = itemView.findViewById(R.id.btn_detail);

            btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { deleteAirport(tvId.getText().toString());}
            });
            btnUbah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pindah = new Intent(ctx, UbahActivity.class);
                    pindah.putExtra("xId", tvId.getText().toString());
                    pindah.putExtra("xNama", tvNama.getText().toString());
                    pindah.putExtra("xSejarah", tvSejarah.getText().toString());
                    pindah.putExtra("xLuasbandara", tvLuasbandara.getText().toString());
                    pindah.putExtra("xKota", tvKota.getText().toString());
                    pindah.putExtra("xTahunberdiri", tvTahunberdiri.getText().toString());
                    ctx.startActivity(pindah);
                }
            });
            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pindah = new Intent(ctx, UbahActivity.class);
                    pindah.putExtra("xId", tvId.getText().toString());
                    pindah.putExtra("xNama", tvNama.getText().toString());
                    pindah.putExtra("xSejarah", tvSejarah.getText().toString());
                    pindah.putExtra("xLuasbandara", tvLuasbandara.getText().toString());
                    pindah.putExtra("xKota", tvKota.getText().toString());
                    pindah.putExtra("xTahunberdiri", tvTahunberdiri.getText().toString());
                    ctx.startActivity(pindah);
                }
            });
        }
        void deleteAirport(String id){
            APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = API.ardDelete(id);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "kode: " + kode+ "pesan : " + pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveAirport();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Error! Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();

                }

            });
        }
}
}
