package com.tarellano;

import gnu.io.*;

/**
 * Created by Thomas Arellano on 2016-06-06.
 */
public class SerialBridge {

    public void bridgeSerialPorts(SerialPort serialPortA, SerialPort serialPortB){

    }

    public SerialPort getSerialPort(String portName, int baudRate, int dataBits, int stopBits, int parityBits)
            throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
        CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (commPortIdentifier.isCurrentlyOwned()){
            System.out.println(portName + " is already in use");
        }

        CommPort commPort = commPortIdentifier.open(this.getClass().getName(), 2000);
        SerialPort serialPort = (SerialPort) commPort;

        serialPort.setSerialPortParams(baudRate, dataBits, stopBits, parityBits);
        return serialPort;
    }
}
