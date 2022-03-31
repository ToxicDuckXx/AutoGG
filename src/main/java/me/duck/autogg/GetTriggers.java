package me.duck.autogg;

public class GetTriggers {

    public static boolean checkMessage(String message) {

        String[] triggers = getTriggers();

        for (String trigger:triggers) {
            if (message.contains(trigger.replaceAll("\n", "").trim())) {
                return true;
            }
        }
        return false;

    }

    private static String[] getTriggers() {

        String[] response;
        String triggers = "1st Killer - \n" +
                "1st Place - \n" +
                "Winner: \n" +
                " - Damage Dealt - \n" +
                "Winning Team -\n" +
                "1st - \n" +
                "Winners: \n" +
                "Winner: \n" +
                "Winning Team: \n" +
                " won the game!\n" +
                "Top Seeker: \n" +
                "1st Place: \n" +
                "Last team standing!\n" +
                "Winner #1 (\n" +
                "Top Survivors\n" +
                "Winners - \n" +
                "Sumo Duel - ";
        response = triggers.split("\n");
        return response;
    }

}
