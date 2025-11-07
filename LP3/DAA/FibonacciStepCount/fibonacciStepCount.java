import java.util.*;

public class fibonacciStepCount {
    static int stepCount = 0;

    static int fibRecursive(int n) {
        stepCount++;
        if (n <= 1)
            return n;
        else
            return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    static int fibIterative(int n) {
        int a = 0, b = 1, c = 0;
        int steps = 0;

        if (n == 0) {
            System.out.println("Steps (iterative): " + steps);
            return 0;
        }
       System.out.print("Fibonacci series (iterative): 0 1 ");
        for (int i = 2; i <= n; i++) {
            c = a + b;  
            a = b;
            b = c;
            steps++;
            System.out.print(c + " ");
        }

        System.out.println("\nSteps (iterative): " + steps);
        return b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n (to find nth Fibonacci number): ");
        int n = sc.nextInt();

        // Recursive version
        stepCount = 0;
        int resultRec = fibRecursive(n);
        System.out.println("\nRecursive Fibonacci(" + n + ") = " + resultRec);
        System.out.println("Steps (recursive): " + stepCount);

        // Iterative version
        int resultIter = fibIterative(n);
        System.out.println("Iterative Fibonacci(" + n + ") = " + resultIter);
    }
}
