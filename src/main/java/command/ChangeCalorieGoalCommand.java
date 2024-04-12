package command;

import activeedge.task.Task;
import activeedge.task.TaskList;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static activeedge.Storage.saveLogsToFile;
import static activeedge.task.TaskList.tasksList;

public class ChangeCalorieGoalCommand extends Command {

    public void execute() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = currentDateTime.format(dateFormatter);
        String time = currentDateTime.format(timeFormatter);

        for (int i = 0; i < tasksList.size(); i++) {
            Task tasksList = TaskList.tasksList.get(i);
            if (tasksList.toString().startsWith("Goal Calorie")) {
                TaskList.delete(i);
            }
        }

        Scanner scanner = new Scanner(System.in);

        int newCalorieGoal = 0;
        int i = 0;
        while (i < 1) {
            System.out.println("Please input your new daily calorie goal: ");
            try {
                newCalorieGoal = Integer.parseInt(scanner.nextLine());
                if (newCalorieGoal >= 1 && newCalorieGoal <= 10000) {
                    AddCalorieGoalCommand addCalorieGoalCommand = new
                            AddCalorieGoalCommand(newCalorieGoal, date, time);
                    addCalorieGoalCommand.execute();
                    System.out.println("You have successfully changed your calorie goal! " +
                            "You can continue logging data!");
                    saveLogsToFile("data/data.txt");
                    i++;
                } else {
                    System.out.println("Please input a positive integer between 1 and 10000!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input a positive integer between 1 and 10000!");
            }
        }
    }
}


