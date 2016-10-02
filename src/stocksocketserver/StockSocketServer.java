/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocksocketserver;

/**
 *
 * @author lejtman
 */
public class StockSocketServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StockServer ss = new StockServer(4444);
        ss.start();
    }
    
}
