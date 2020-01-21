package com.starling.roundupV1;

import static com.starling.roundupV1.BearerToken.bearerToken;


import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

// Gets what's saved up to date
public class GetCurrentSavings {

    private static HttpsURLConnection connection;

    public static String getSavingsFeed() {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        String currentSavings = null;

        //get saving goals
        try {
            URL url = new URL("https://api-sandbox.starlingbank.com/api/v2/account/ed5437b1-e6d5-436a-9dc4-b780296a2529/savings-goals");
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
            currentSavings = responseContent.toString();


        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return currentSavings;
    }
}
