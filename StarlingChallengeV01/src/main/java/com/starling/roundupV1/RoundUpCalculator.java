package com.starling.roundupV1;

import org.json.JSONArray;
import org.json.JSONObject;

// Parses the transaction feed JSON object and
// adds up pennies for saving goal
public class RoundUpCalculator {


    public static int parse(String responseBody) {
        int totalPenny = 0;

        JSONObject jobj = new JSONObject(responseBody);
        JSONArray feedItems = jobj.getJSONArray("feedItems");
        for (int i = 0; i < feedItems.length(); i++) {
            JSONObject amount = (JSONObject) feedItems.getJSONObject(i).get("amount");
            System.out.println(amount);
            Integer minorUnits = (Integer) amount.get("minorUnits");
            System.out.println(minorUnits);
            totalPenny += minorUnits % 100;
            System.out.println(totalPenny);
        }

        return totalPenny;
    }
}
