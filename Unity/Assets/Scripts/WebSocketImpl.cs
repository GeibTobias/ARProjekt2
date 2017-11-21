using SocketIO;
using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Text;
using UnityEngine;
using WebSocketSharp;

public class WebSocketImpl : MonoBehaviour {

    public String serverIP = "localhost";
    public String serverPort = "8080";

    private SocketIOComponent socket;

    // Use this for initialization
    void Start () {
        Debug.Log("Starting websocket");
        connect();
    }
	
	// Update is called once per frame
	void Update () {
		
	}


    private void connect()
    {
        WebSocket socket = new WebSocket(string.Format("ws://{0}:{1}/ws-map-update/map/route/get", this.serverIP, this.serverPort));

        socket.OnOpen += ws_onOpen;
        socket.OnMessage += ws_onMessage;

        socket.Connect();
        String msg = "Hello";
        socket.Send(msg);

        
    }

    public void ws_onOpen(object sender, EventArgs e)
    {
        Debug.Log("WS open"); 
        Debug.Log(sender); 
    }

    public void ws_onMessage(object sender, EventArgs e)
    {
        Debug.Log("WS onMessage");
        Debug.Log(sender);
    }
}
