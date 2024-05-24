//NAME : MAKHANANI MLAMBO
//STUDENT NUMBER : 4270025
//FILE NAME : MeetUp.java
//PRACTICAL 1

import java.util.Scanner;

public class MeetUp {
    private Student[] studentArray;

    public void fillArray() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Good day. How many students do you want to input?");
            int numStudents = scanner.nextInt();
            studentArray = new Student[numStudents];

            for (int i = 0; i < numStudents; i++) {
                scanner.nextLine();
                System.out.println("PLEASE ENTER THE STUDENT DETAILS " + (i + 1) + ":");
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Student Number: ");
                int number = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Department: ");
                String department = scanner.nextLine();
                System.out.print("Gender: ");
                String gender = scanner.nextLine();
                System.out.print("Race: ");
                String race = scanner.nextLine();
                System.out.println("----------------------------");

                studentArray[i] = new Student(name, number, 0, "", department, gender, race);
            }
        }
    }

    private int countFemaleStudents() {
        int count = 0;
        for (Student student : studentArray) {
            if (student.getGender().equalsIgnoreCase("female")) {
                count++;
            }
        }
        return count;
    }

    public void checkDiversity() {
        int numFemaleStudents = countFemaleStudents();
        int numStudents = studentArray.length;
        int numRaces = countRaces();

        if (numFemaleStudents >= numStudents / 2 && numRaces >= 3) {
            System.out.println("Great! We are making progress! Let's keep it up.");
        } else {
            System.out.println("We need to work on diversifying more.");
        }
    }

    private int countRaces() {
        int count = 0;
        String[] races = new String[] { "Black", "White", "Coloured", "Indian", "Other" };
        for (String race : races) {
            for (Student student : studentArray) {
                if (student.getRace().equalsIgnoreCase(race)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MeetUp meetup = new MeetUp();
        meetup.fillArray();

        int numFemaleStudents = meetup.countFemaleStudents();

        int numRaces = meetup.countRaces();

        if (numFemaleStudents >= meetup.studentArray.length / 2 && numRaces >= 3) {
            System.out.println("Great! We are making progress! Let's keep it up.");
        } else {
            System.out.println("We need to work on diversifying more.");
        }
    }
}
