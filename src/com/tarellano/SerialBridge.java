package com.tarellano;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Thomas Arellano on 2016-06-06.
 */
public class SerialBridge {

    public void bridgeSerialPorts(SerialPort serialPortA, SerialPort serialPortB) throws IOException {

        InputStream serialPortInA = serialPortA.getInputStream();
        InputStream serialPortInB = serialPortB.getInputStream();
        OutputStream serialPortOutA = serialPortA.getOutputStream();
        OutputStream serialPortOutB = serialPortB.getOutputStream();

        Thread serialThreadAtoB = new SerialPortReadWrite(serialPortInA, serialPortOutB);
        Thread serialThreadBtoA = new SerialPortReadWrite(serialPortInB, serialPortOutA);

    }

    public class SerialPortReadWrite extends Thread {

        private InputStream in;
        private OutputStream out;

        public SerialPortReadWrite(InputStream in, OutputStream out)
        {
            this.in = in;
            this.out = out;
        }

        @Override
        public void run(){
            byte[] buff = new byte[128];
            int b;
            try {
                while ((b = in.read(buff)) > -1){
                    out.write(buff, 0, b);
                    System.out.print(new String(buff, 0, b));
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }

    public static SerialPort getSerialPort(String portName, int baudRate, int dataBits, int stopBits, int parityBits)
            throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
        CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (commPortIdentifier.isCurrentlyOwned()){
            System.out.println(portName + " is already in use");
            return null;
        }

        CommPort commPort = commPortIdentifier.open(SerialBridge.class.getName() , 2000);
        SerialPort serialPort = (SerialPort) commPort;

        serialPort.setSerialPortParams(baudRate, dataBits, stopBits, parityBits);
        return serialPort;
    }
}
