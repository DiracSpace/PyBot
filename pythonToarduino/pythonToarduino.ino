// sketch copied from Youtube channel Decipher Technic
void setup() {
  Serial.begin(9600); //configura el rango de baudio en 9600
  pinMode(13, OUTPUT);  //configura el pin de salida
}

void loop() {
  if(Serial.available())  //si se llena el bufer
  {
    switch(Serial.read())
    {
      case '0': digitalWrite(13, LOW);
                break;
      case '1': digitalWrite(13, HIGH);
                break;
      default: break;
    }
  }
}
