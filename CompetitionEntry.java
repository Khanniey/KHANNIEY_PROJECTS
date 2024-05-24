// Student Number : 4270025
// Student Name: Makhanani Mlambo
// Assignment 2

import java.util.*;

public class CompetitionEntry {
    private Node head;

    public CompetitionEntry(Node head) {
        this.head = null;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void addTeam(Team team) {
        Node node = new Node(team);
        if (head == null) {
            head = node;
        } else {
            Node current = head;
            while (current.getNextNode() != null) {
                current = current.getNextNode();
            }
            current.setNextNode(node);
        }
    }

    static void registerTeams(CompetitionEntry competitionEntry, Scanner scanner) {
        int numTeams;
        do {
            System.out.print("Please enter the number of teams to register: ");
            numTeams = scanner.nextInt();
            scanner.nextLine();
        } while (numTeams <= 0);

        for (int i = 0; i < numTeams; i++) {
            System.out.println("Please enter details for the teams " + (i + 1) + ":");
            System.out.print("Please enter the Team Name: ");
            String teamName = scanner.nextLine();
            System.out.print("Please enter the Team Number: ");
            int teamNumber = scanner.nextInt();
            System.out.print("Please enter the Registration Year: ");
            long regYear = scanner.nextLong();
            System.out.print("Please enter the First Score: ");
            int firstScore = scanner.nextInt();
            System.out.print("Please enter the Second Score: ");
            int secondScore = scanner.nextInt();
            scanner.nextLine();

            competitionEntry.registerTeam(teamName, teamNumber, regYear, firstScore, secondScore);
        }
    }

    public void registerTeam(String name, int number, long regyear, int firstscore, int secondscore) {
        Team team = new Team(name, number, regyear, firstscore, secondscore);
        addTeam(team);
    }

    public Team deregister_Last_team() {
        if (head == null) {
            return null;
        } else if (head.getNextNode() == null) {
            Team removedTeam = head.getTeam();
            head = null;
            return removedTeam;
        } else {
            Node current = head;
            Node previous = null;
            while (current.getNextNode() != null) {
                previous = current;
                current = current.getNextNode();
            }
            previous.setNextNode(null);
            return current.getTeam();
        }
    }

    public Team deregister_Particular_team(int teamNumber) {
        return deregisterTeam(head, teamNumber, null);
    }

    private Team deregisterTeam(Node current, int teamNumber, Node before) {
        if (current == null) {
            return null;
        }
        if (current.getTeam().getTeamNumber() == teamNumber) {
            Team removedTeam = current.getTeam();
            if (before == null) {
                head = current.getNextNode();
            } else {
                before.setNextNode(current.getNextNode());
            }
            return removedTeam;
        }
        return deregisterTeam(current.getNextNode(), teamNumber, current);
    }

    public void insertBefore(int teamNumber, Team newTeam) {
        if (head == null) {
            addTeam(newTeam);
        } else if (head.getTeam().getTeamNumber() == teamNumber) {
            Node newNode = new Node(newTeam);
            newNode.setNextNode(head);
            head = newNode;
        } else {
            Node current = head;
            Node previous = null;
            while (current != null && current.getTeam().getTeamNumber() != teamNumber) {
                previous = current;
                current = current.getNextNode();
            }
            if (current == null) {

                addTeam(newTeam);
            } else {
                Node newNode = new Node(newTeam);
                newNode.setNextNode(current);
                previous.setNextNode(newNode);
            }
        }
    }

    public int competitionEntrySize() {
        return countTeams(head);
    }

    private int countTeams(Node current) {
        if (current == null) {
            return 0;
        }
        return 1 + countTeams(current.getNextNode());
    }

    void display() {
        displayTeams(head);
    }

    private void displayTeams(Node current) {
        if (current == null) {
            return;
        }
        System.out.println(current.getTeam().getTeamName() + " " +
                current.getTeam().getTeamNumber() + " " +
                current.getTeam().getRegYear() + " " +
                current.getTeam().getFirstScore() + " " +
                current.getTeam().getSecondScore() + " " +
                current.getTeam().getFinalScore());
        displayTeams(current.getNextNode());

    }

    private List<Team> addToTeamList(Node current, List<Team> teamList) {
        if (current == null) {
            return teamList;
        }
        teamList = addToTeamList(current.getNextNode(), teamList);
        teamList.add(current.getTeam());
        return teamList;
    }

    public void sortTeams(String sortBy) {
        if (head == null || head.getNextNode() == null) {
            return;
        }

        java.util.List<Team> teamList = new java.util.ArrayList<>();
        teamList = addToTeamList(head, new ArrayList<>());

        switch (sortBy) {
            case "teamName":
                Collections.sort(teamList, Comparator.comparing(Team::getTeamName));
                break;
            case "teamNumber":
                Collections.sort(teamList, Comparator.comparingInt(Team::getTeamNumber));
                break;
            case "regYear":
                Collections.sort(teamList, Comparator.comparingLong(Team::getRegYear));
                break;
            case "firstScore":
                Collections.sort(teamList, Comparator.comparingInt(Team::getFirstScore));
                break;
            case "secondScore":
                Collections.sort(teamList, Comparator.comparingInt(Team::getSecondScore));
                break;
            case "finalScore":
                Collections.sort(teamList, Comparator.comparingDouble(Team::getFinalScore).reversed());
                break;
            default:
                System.out.println("This in unfortunetly a wrong property for sorting.");
                return;
        }

        Node currentNode = head;
        for (Team team : teamList) {
            if (currentNode == null) {
                currentNode = new Node(team);
                head = currentNode;
            } else {
                currentNode.setTeam(team);
                if (currentNode.getNextNode() == null) {
                    currentNode.setNextNode(null);
                }
                currentNode = currentNode.getNextNode();
            }
        }

    }
}
