package com.starling.roundupV1;


// Adds up what is in saving goal already and
// what can be put towards the goal

import org.json.JSONArray;
import org.json.JSONObject;

public class AddToCurrentSavings {

    public static int totalToSave(String currentSavings, int totalPenny) {
        int totalUpdate = 0;

        JSONObject jobj = new JSONObject(currentSavings);
        JSONArray feedItems = jobj.getJSONArray("savingsGoalList");

        for (int i = 0; i < feedItems.length(); i++) {
            JSONObject amount = (JSONObject) feedItems.getJSONObject(i).get("totalSaved");
            Integer minorUnits = (Integer) amount.get("minorUnits");
            totalUpdate = minorUnits + totalPenny;


        }

        return totalUpdate;
    }
}
