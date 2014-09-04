package br.edu.ifpb.emailserverpod;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author FILIPE
 */
public class Testes {

    int count = 0;
    
    public boolean testaNet(){
        boolean result = true;
        try{
            count = count + 1;
            URL url2 = new URL("http://smtp.gmail.com");
            HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
            connection.setConnectTimeout(1000);
            connection.connect();
            result = true;
        }catch (  MalformedURLException e){
            result = false;
            e.printStackTrace();
        }catch (IOException e){
            if(count < 3){
                testaNet();
            }else{
                result = false;
            }
            e.printStackTrace();
        }
        return result;
    }        
    
}
