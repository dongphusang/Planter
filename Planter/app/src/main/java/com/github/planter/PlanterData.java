package com.github.planter;

import android.os.AsyncTask;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.domain.HealthCheck;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import java.util.List;

public class PlanterData {

    // declaring attributes
    String imageLink;
    String currentPlantName;




    public PlanterData() {
        new AsyncHealthCheck().execute();


    }

    private class AsyncHealthCheck extends AsyncTask<Void, Void, HealthCheck> {
        String token = "LWEgMpE4FqoeYhHw798yuf9PKlA2ZlYOPZK9w2TDehhjUAl02NtZnVewh18TfN8e4Uebk6BMz0wfV41GKfYjGA==";
        String bucket = "marcodsang's Bucket";
        String org = "marcodsang@gmail.com";
        @Override
        protected HealthCheck doInBackground(Void... voids) {
            InfluxDBClient client = InfluxDBClientFactory.create("https://us-east-1-1.aws.cloud2.influxdata.com", token.toCharArray());

            // Querying syntax
            String query = "from(bucket: \"marcodsang's Bucket\") |> range(start: 0)";
            List<FluxTable> tables = client.getQueryApi().query(query, org);

            for (FluxTable table : tables) {
                for (FluxRecord record : table.getRecords()) {
                    System.out.println("Line52_pulledrecord: "+record.getValue());
                }
            } // end of querying

            return client.health();
        }

        @Override
        protected void onPostExecute(HealthCheck healthCheck) {
            System.out.println(String.format("InfluxDB health: %s", healthCheck));

        }
    }







}
