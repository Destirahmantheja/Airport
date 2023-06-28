package com.uas.airport.Model;

import java.util.List;

public class ModelResponse {
    public String kode, pesan;
    private List<ModelAirport> data;
    public String getKode(){return kode;}
    public String getPesan(){return pesan;}

    public List<ModelAirport> getData() {
        return data;
    }
}
