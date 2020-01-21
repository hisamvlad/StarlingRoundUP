package com.starling.roundupV1;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static com.starling.roundupV1.BearerToken.bearerToken;


// Gets the transaction feed
public class GetTransactionFeed {


    private static HttpsURLConnection connection;


    // Get the account feed
    public static String getFeed() {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        String responseBody = null;


        try {
            URL url = new URL("https://api-sandbox.starlingbank.com/api/v2/feed/account/ed5437b1-e6d5-436a-9dc4-b780296a2529/category/a3266fcf-f7e7-44e9-b872-8ede2d2d1b38");
            connection = (HttpsURLConnection) url.openConnection();

            // GET setup
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", bearerToken);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);



            int status = connection.getResponseCode();


            // handle bad status
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.toString());
            responseBody = responseContent.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return responseBody;
    }
}
