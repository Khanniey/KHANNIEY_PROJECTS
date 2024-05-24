// Student Number: 4270025
// Student Name: Makhanani Mlambo
// Assignment 2

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting {
    public static void sortTeams(List<Team> teams) {
        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team team1, Team team2) {
                if (team1.getFinalScore() != team2.getFinalScore()) {
                    return Double.compare(team2.getFinalScore(), team1.getFinalScore());
                } else if (!team1.getTeamName().equals(team2.getTeamName())) {
                    return team1.getTeamName().compareTo(team2.getTeamName());
                } else {
                    return Integer.compare(team1.getTeamNumber(), team2.getTeamNumber());
                }
            }
        });
    }
}
