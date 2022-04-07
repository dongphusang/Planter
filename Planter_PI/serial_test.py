
import serial
from datetime import datetime
import os
from influxdb_client import InfluxDBClient, Point, WritePrecision
from influxdb_client.client.write_api import SYNCHRONOUS

# You can generate an API token from the "API Tokens Tab" in the UI
token = "LWEgMpE4FqoeYhHw798yuf9PKlA2ZlYOPZK9w2TDehhjUAl02NtZnVewh18TfN8e4Uebk6BMz0wfV41GKfYjGA=="
org = "marcodsang@gmail.com"
bucket = "marcodsang's Bucket"

moisture_stat = 0
soil_moist_stat = 0
water_level_stat = 0


# getting data from arduino
if __name__ == '__main__':
    ser = serial.Serial('/dev/ttyACM0', 9600, timeout=1)
    ser.reset_input_buffer()
    while True:
        if ser.in_waiting > 0:
            line = (ser.readline().decode('utf-8').rstrip())

            with InfluxDBClient(url="https://us-east-1-1.aws.cloud2.influxdata.com", token=token, org=org) as client:
                point = Point("RaspberryPI") \
                    .tag("host", "SangDong") \
                    .field("planter_stats", line) \
                    .time(datetime.utcnow(), WritePrecision.MS)   

                write_api = client.write_api()
                write_api.write(bucket, org, point)
            
            print(line)
            print()