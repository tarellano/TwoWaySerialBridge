package com.tarellano;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        SerialBridge serialBridge = new SerialBridge();
        try {
            SerialPort serialPortA = SerialBridge.getSerialPort("COM5", 38400, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            SerialPort serialPortB = SerialBridge.getSerialPort("COM6", 38400, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialBridge.bridgeSerialPorts(serialPortA, serialPortB);
        } catch (NoSuchPortException e) {
            e.printStackTrace();
        } catch (PortInUseException e) {
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
