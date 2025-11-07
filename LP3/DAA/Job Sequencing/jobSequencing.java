import java.util.*;

class Job {
    char id;      // Job ID
    int deadline; // Deadline
    int profit;   // Profit

    Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class jobSequencing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();

        Job[] jobs = new Job[n];

        // Taking job details from user
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Job " + (i + 1) + ":");
            System.out.print("Job ID (single character): ");
            char id = sc.next().charAt(0);
            System.out.print("Deadline: ");
            int deadline = sc.nextInt();
            System.out.print("Profit: ");
            int profit = sc.nextInt();

            jobs[i] = new Job(id, deadline, profit);
        }

        // Step 1: Sort jobs by profit (descending order)
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Step 2: Find the max deadline
        int maxDeadline = 0;
        for (Job job : jobs)
            if (job.deadline > maxDeadline)
                maxDeadline = job.deadline;

        // Step 3: Create slot array (to store scheduled jobs)
        char[] schedule = new char[maxDeadline];
        boolean[] slotUsed = new boolean[maxDeadline];

        int totalProfit = 0;

        // Step 4: Schedule each job
        for (Job job : jobs) {
            // find an empty slot before or at the job’s deadline
            for (int j = job.deadline - 1; j >= 0; j--) {
                if (!slotUsed[j]) {
                    slotUsed[j] = true;
                    schedule[j] = job.id;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        // Step 5: Print result
        System.out.println("\nJob sequence (to maximize profit):");
        for (int i = 0; i < maxDeadline; i++) {
            if (slotUsed[i])
                System.out.print(schedule[i] + " ");
        }
        System.out.println("\nTotal Profit = " + totalProfit);
    }
}
