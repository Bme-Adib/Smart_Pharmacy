package iot.safe;

import com.fazecast.jSerialComm.SerialPort;
import iot.safe.Arduino.InitiateSerialPort;
import iot.safe.FireStore.FireBase;
import iot.safe.GUI.SignInWindow;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class MainTest {
    private static InitiateSerialPort arduino;
    static OutputStream outputStream;

    public static void main(String[] args) {
        arduino = new InitiateSerialPort();

        String portCOM = "COM6";
        arduino.setChosenPort(SerialPort.getCommPort(portCOM));

        arduino.getChosenPort().closePort();
        arduino.getChosenPort().openPort();
        try {
            arduino.getChosenPort().setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        } catch (Exception F) {
            JOptionPane.showMessageDialog(null, "Can't Connect to the Port", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Scanner scanner = new Scanner(System.in);

        arduino.getChosenPort().openPort();

//        Scanner scanner = new Scanner(System.in);
//        try {
//            scanner = new Scanner(arduino.getChosenPort().getInputStream());
//            System.out.println("Port Open Successfully");
//        } catch (Exception F) {
//            JOptionPane.showMessageDialog(null, "Can't Connect to the Port", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//        int i=0;
//        while (scanner.hasNextLine()) {
//            i++;
//            String who = scanner.nextLine();
//            System.out.println(who);
//            if (i>=5){
//                arduino.getChosenPort().closePort();
//            }

        outputStream = arduino.getChosenPort().getOutputStream();
        if (outputStream != null) {
            System.out.println("Port Open Successfully");
            while (true) {
                String messageString = scanner.nextLine();
                try {
                    outputStream.write(messageString.getBytes());
                    System.out.println(messageString);
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }else{
            System.out.println("null");
        }

    }
}
