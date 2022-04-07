package com.github.planter;

import android.os.AsyncTask;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.domain.HealthCheck;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import java.util.List;

public class CloudRetriever extends AsyncTask<Void, Void, HealthCheck>{

    private final String token = "HwAGsVycQexWxussFmx5QyMQ5gI-YwiYZjJTaC6ni83rAchRBFAlpz242j5Lj2IlOWIojK4NdMDjEo1QtYH7Mw==";
    private final String bucket = "planter";
    private final String org = "marcodsang@gmail.com";
    private String data;
    private String [] dataBag = new String[7];
    private StatBoard statBoard = new StatBoard();
    private final InfluxDBClient client = InfluxDBClientFactory.create("https://us-east-1-1.aws.cloud2.influxdata.com", token.toCharArray());

    protected HealthCheck doInBackground(Void... voids) {

        String query = "from(bucket: \"planter\") |> range(start: 0)";
        List<FluxTable> tables = client.getQueryApi().query(query, org);
        // query starts
        for (FluxTable table : tables) {
            for (FluxRecord record : table.getRecords()) {
                System.out.println("Line52_pulledrecord: "+record.getValue()); // debug
                data = record.getValue().toString();
            }
        } // end of querying

        // split and assign downloaded data to dataBag array
        if (data != null)
            dataBag = data.split(",");
        else
        {
            for (int i = 0; i <= 5; i++)
                dataBag[i]="N/A";
        }

        return client.health();
    }

    // execute after doInBackground() is finished
    @Override
    protected void onPostExecute(HealthCheck healthCheck) { StatBoard.updateTextViews(); }

    // return collected data
    public String[] getDataCollection() { return dataBag; }


}