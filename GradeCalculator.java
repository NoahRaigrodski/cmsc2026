import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * Class: CMSC203
 * Instructor: Dr. Grinberg
 * Description: This class takes 2 separate user inputs and uses them to calculate a grade that is then outputted through a txt file
 * Due: 2/3/2026
 * Platform/compiler: IntilliJ IDEA
 */
public class GradeCalculator {

    public static void main(String[] args) {
        String courseName, firstName, lastName, letterGrade, categoryName1, categoryName2, categoryName3;
        int categories, totalWeight = 0;
        double overallAverage = 0, catTotal = 0, catAverage;

        try {
            File gradeConfig = new File("gradeconfig.txt");
            File gradesInput = new File("grades_input.txt");
            Scanner configScanny = new Scanner(gradeConfig);
            Scanner inputScanny = new Scanner(gradesInput);

            PrintWriter output = new PrintWriter("gradeReport.txt");

            courseName = configScanny.nextLine();
            categories = configScanny.nextInt();
            configScanny.nextLine();

            firstName = inputScanny.nextLine();
            lastName = inputScanny.nextLine();

            System.out.println("Course name: " + courseName);
            System.out.println("Name: " + firstName + " " + lastName);

            output.println("Course Name: " + courseName);
            output.println("Name: " + firstName + " " + lastName);

            /* For every category store the category name and weight from the config file
            as well as the number of scores from the input file */
            for (int i = 0; i < categories; i++) {
                String configCategory = configScanny.next();
                int weight = configScanny.nextInt();
                configScanny.nextLine();
                totalWeight += weight;
                catTotal = 0;

                String inputCategory = inputScanny.nextLine();
                int numOfScores = inputScanny.nextInt();
                inputScanny.nextLine();

                /* If the category from the input file matches the one in the config file
                Then add every score from the config file to the category total
                 */
                if (inputCategory.equalsIgnoreCase(configCategory)) {
                    for (int j = 0; j < numOfScores; j++) {
                        catTotal += inputScanny.nextDouble();
                    }
                    inputScanny.nextLine();

                    // Calculate the category average and add it to the overall average after multiplying by its weight
                    catAverage = catTotal / numOfScores;
                    overallAverage += catAverage * (weight / 100.0);

                    System.out.println(configCategory + " has a weight of " + weight + " and an average of " + catAverage + ".");
                    output.println(configCategory + " has a weight of " + weight + " and an average of " + catAverage + ".");

                    // If the categories do not match then skip all the scores
                } else {
                    for (int j = 0; j < numOfScores; j++) {
                        inputScanny.nextDouble();
                    }
                    inputScanny.nextLine();

                }
            }

            // Determine letterGrade based on overallAverage
            if (overallAverage >= 90) {
                letterGrade = "A";
            } else if (overallAverage >= 80) {
                letterGrade = "B";
            } else if (overallAverage >= 70) {
                letterGrade = "C";
            } else if (overallAverage >= 60) {
                letterGrade = "D";
            } else {
                letterGrade = "F";
            }

            Scanner scanny = new Scanner(System.in);
            String userInput;

            // Put the user in an input loop until they enter either Y or N
            do {
                System.out.println("Apply +/- grading? (Y/N)");
                output.println("Apply +/- grading? (Y/N)");
                userInput = scanny.nextLine();
                output.println(userInput);
            } while (!userInput.equalsIgnoreCase("Y") && !userInput.equalsIgnoreCase("N"));

            // Determine the final letter grade if the user wanted it
            if (userInput.equalsIgnoreCase("Y")) {
                if (letterGrade.equals("A")) {
                    if (overallAverage >= 98) {
                        letterGrade = "A+";
                    } else if (overallAverage <= 92) {
                        letterGrade = "A-";
                    }
                }
                if (letterGrade.equals("B")) {
                    if (overallAverage >= 88) {
                        letterGrade = "B+";
                    } else if (overallAverage <= 82) {
                        letterGrade = "B-";
                    }
                }
                if (letterGrade.equals("C")) {
                    if (overallAverage >= 78) {
                        letterGrade = "C+";
                    } else if (overallAverage <= 72) {
                        letterGrade = "C-";
                    }
                }
                if (letterGrade.equals("D")) {
                    if (overallAverage >= 68) {
                        letterGrade = "D+";
                    } else if (overallAverage <= 62) {
                        letterGrade = "D-";
                    }
                }
            }

            System.out.println("Grade average: " + overallAverage);
            System.out.println("Final letter grade: " + letterGrade);

            output.println("Grade average: " + overallAverage);
            output.println("Final letter grade: " + letterGrade);

            output.close();

            // If the files aren't found then run the program with a default configuration
        } catch (FileNotFoundException e) {
            System.out.println("ERROR FILE NOT FOUND. DEFAULT CONFIGURATION WILL BE USED.");
            firstName = "John";
            lastName = "Doe";
            courseName = "English";
            categoryName1 = "Quizzes";
            categoryName2 = "Assignments";
            categoryName3 = "Homework";
            int quizWeight = 20, homeworkWeight = 30, assignmentWeight = 50;
            double quizAverage = 95.0, homeworkAverage = 85.0, assignmentAverage = 97.0;
            overallAverage = (quizAverage + homeworkAverage + assignmentAverage) / 3.0;
            letterGrade = "A";

            System.out.println("Course name: " + courseName);
            System.out.println("Name: " + firstName + " " + lastName);
            System.out.println(categoryName1 + " has a weight of " + quizWeight + " and an average of " + quizAverage + ".");
            System.out.println(categoryName2 + " has a weight of " + homeworkWeight + " and an average of " + homeworkAverage + ".");
            System.out.println(categoryName3 + " has a weight of " + assignmentWeight + " and an average of " + assignmentAverage + ".");
            System.out.println("Grade average: " + overallAverage);
            System.out.println("Final letter grade: " + letterGrade);
        }

        System.out.println("Coded by Noah Raigrodski.");

    }
}