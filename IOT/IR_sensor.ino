//Define pin numbers
const int ledPin = 3;
const int irSensorPin = 2;
void setup() {
    pinMode(ledPin, OUTPUT);
 pinMode(irSensorPin, INPUT);
Serial.begin(9600);
}
void loop() {
 int sensorState = digitalRead(irSensorPin);
 // The sensor outputs LOW when it detects an obstacle
 if (sensorState == LOW) {
 digitalWrite(ledPin, HIGH);
 Serial.print("Object is detected\n");
 } else {
 digitalWrite(ledPin, LOW);
 Serial.print("Object is not detected\n");
 }
 delay(1000); // Wait for 100 milliseconds
}