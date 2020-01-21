package com.starling.roundupV1;

public class RoundUpApplication {

//Left it this way to illustrate the compact logic
    public static void main(String[] args) throws Exception {
        // Gets transaction feed from user account
        GetTransactionFeed gtf = new GetTransactionFeed();
        // Gets the total that can be put towards saving goals
        RoundUpCalculator ruc = new RoundUpCalculator();
        ruc.parse(gtf.getFeed());
        // Gets the amount currently in savings goal
        GetCurrentSavings gcs = new GetCurrentSavings();
        gcs.getSavingsFeed();
        // Adds up total sum to update savings goal
        AddToCurrentSavings atcs = new AddToCurrentSavings();
        int totalUpdate = atcs.totalToSave( gcs.getSavingsFeed(), ruc.parse(gtf.getFeed()));
        // update the savings goal
        PutTowardsSavings ptn = new PutTowardsSavings();
        ptn.updateSavingsGoal(totalUpdate);
    }
}
