package iot.safe;


import arduino.Arduino;

public class test1 {
    public static void main(String[] args) {

        String ArduinoPort = "COM6"; //Your port name here
        int BAUD_RATE = 9600;
        Arduino arduino = new Arduino(ArduinoPort, BAUD_RATE);
        arduino.openConnection();
        arduino.serialWrite("d1"); //serialWrite is an overridden method, allowing both characters and strings.
//        arduino.serialWrite('1', 20); //its second parameter even allows delays. more details can be found in the documentation.

    }
}
