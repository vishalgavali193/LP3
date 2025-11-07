package Knapsack_01_dp;
import java.util.Scanner;
import java.util.*;

public class knapsack_01_dp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] weight = new int[n];
        int[] value = new int[n];

        System.out.println("Enter weights of items:");
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }

        System.out.println("Enter values of items:");
        for (int i = 0; i < n; i++) {
            value[i] = sc.nextInt();
        }

        System.out.print("Enter maximum capacity of knapsack: ");
        int capacity = sc.nextInt();

        int maxValue = knapsack(value, weight, capacity, n);
        System.out.println("Maximum value that can be obtained = " + maxValue);
    }

    static int knapsack(int[] value, int[] weight, int capacity, int n) {
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(
                        value[i - 1] + dp[i - 1][w - weight[i - 1]],
                        dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }
}
