/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocksocketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.fluent.Request;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
/**
 *
 * @author lejtman
 */
public class StockServer {
    private int port;
    
    public StockServer(int port){
        this.port = port;     
    }
    
    public void start(){
        try {
            ServerSocket server = new ServerSocket(port);
            Socket clientSocket = server.accept();
            System.out.println("got connection");
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("hi from stock socket");
            Gson gson = new Gson();
            String jsonResponse = Request.Get("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol=msft").execute().returnContent().asString();
            JsonObject stockObj = gson.fromJson(jsonResponse, JsonObject.class);
            out.println(stockObj.get("LastPrice").getAsString());
            
            
        } catch (IOException ex) {
            Logger.getLogger(StockServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
