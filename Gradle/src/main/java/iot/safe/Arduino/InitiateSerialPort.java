package iot.safe.Arduino;


import com.fazecast.jSerialComm.SerialPort;

public class InitiateSerialPort {
    private SerialPort chosenPort ;


    public SerialPort[] startSerial(){
        SerialPort[] portsAvailable = SerialPort.getCommPorts();
        return portsAvailable;
    }

    public void setChosenPort(SerialPort chosenPort) {
        this.chosenPort = chosenPort;
    }

    public SerialPort getChosenPort() {
        return chosenPort;
    }
}
