
int water_level_pin = A0;
int water_level_val = 0;
bool isRefillRequired = false;

int soil_moisture_pin = A1;
int soil_moist_val = 0;


String result = "";

void setup() {
  Serial.begin(9600);

  
  
}

void loop() {
  // retrieving values from sensors
  water_level_val = analogRead(water_level_pin);
  soil_moist_val = analogRead(soil_moisture_pin);
  
  // performing assessment
  if(water_level_val <= 280)
    isRefillRequired = true;
  else
    isRefillRequired = false;

  result = String("") + "waterlevel: " + water_level_val + " refill: " + isRefillRequired + " soilmoist: " + soil_moist_val;
  Serial.println(result);
  delay(10000);
}
