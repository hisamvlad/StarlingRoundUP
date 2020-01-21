package com.starling.roundupV1;

import static com.starling.roundupV1.BearerToken.bearerToken;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

// Updates the saving goal
public class PutTowardsSavings {

    private static HttpsURLConnection connection;

    public static void updateSavingsGoal(int totalUpdate) throws Exception {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        UUIDGenerator uuID = new UUIDGenerator();

        try {
            URL url = new URL("https://api-sandbox.starlingbank.com/api/v2/account/ed5437b1-e6d5-436a-9dc4-b780296a2529/savings-goals/01f7be31-b571-4f7b-a009-e7859918c40b/add-money/"
                    + uuID.getUUID());
            connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", bearerToken);

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // JSON that needs to be sent
//            {
//                "amount": {
//                "currency": "GBP",
//                        "minorUnits": 11223344
//            }
//            }

            // When outputting amount.toString(), it gives me IDEMPOTENCY_MISSMATCH
            JSONObject jo = new JSONObject();
            jo.put("currency", "GBP");
            jo.put("minorUnits", totalUpdate);
            JSONObject amount = new JSONObject();
            JSONObject savings = amount.put("amount", jo);


            // When outputting data, it gives me IDEMPOTENCY_MISSMATCH
            String data = "{\"amount\":{\"currency\":\"GBP\",\"minorUnits\": " + totalUpdate + " }}";

            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());
            out.write(

                    savings.toString()
            );
            out.flush();
            out.close();

            int status = connection.getResponseCode();
            System.out.println(status);

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

        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }
}