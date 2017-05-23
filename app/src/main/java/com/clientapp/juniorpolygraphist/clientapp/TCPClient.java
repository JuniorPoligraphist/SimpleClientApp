package com.clientapp.juniorpolygraphist.clientapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Junior Polygraphist on 23.05.2017.
 */

public class TCPClient {

    private String serverMessage;
    public static final String SERVER_IP = "192.168.43.165"; //your personal computer IP address
    public static final int SERVER_PORT = 4444;
    private OnMessageReceived mMessageListener = null;

    private boolean mRun = false;

    PrintWriter out;
    BufferedReader in;

    // call constructor with params
    public TCPClient(OnMessageReceived listener) {
        mMessageListener = listener;
    }


    public void sendMessage(String message) {
        if (out != null && !out.checkError()) {
            out.println(message);
            out.flush();
        }
    }

    public void stopClient() {
        mRun = false;
    }

    public void run() {

        mRun = true;

        try {

            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

            Log.e("TCP Client", "Client: Connecting...");

            //create a socket
            // connection with server
            Socket socket = new Socket(serverAddr, SERVER_PORT);

            try {

                //send message to server
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                Log.e("TCP Client", "Client: Sent.");

                Log.e("TCP Client", "Client: Done.");

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while (mRun) {
                    serverMessage = in.readLine();

                    if (serverMessage != null && mMessageListener != null) {
                        mMessageListener.messageReceived(serverMessage);
                    }
                    serverMessage = null;

                }

                Log.e("RESPONSE FROM SERVER", "Server: Received Message: '" + serverMessage + "'");

            } catch (Exception e) {

                Log.e("TCP", "Server: Error", e);

            } finally {
                socket.close();
            }

        } catch (Exception e) {

            Log.e("TCP", "Client: Error", e);

        }

    }

    public interface OnMessageReceived {
        public void messageReceived(String message);
    }
}
