import java.util.*;

class Item {
    double profit, weight, ratio;

    Item(double profit, double weight) {
        this.profit = profit;
        this.weight = weight;
        this.ratio = profit / weight;
    }
}

public class fractionalKnapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Take number of objects
        System.out.print("Enter total number of objects: ");
        int n = sc.nextInt();

        // Step 2: Take profit and weight for each object
        Item[] items = new Item[n];
        System.out.println("\nEnter profit and weight for each object:");
        for (int i = 0; i < n; i++) {
            System.out.print("Object " + (i + 1) + " profit: ");
            double profit = sc.nextDouble();
            System.out.print("Object " + (i + 1) + " weight: ");
            double weight = sc.nextDouble();
            items[i] = new Item(profit, weight);
        }

        // Step 3: Take capacity of knapsack
        System.out.print("\nEnter knapsack capacity (W): ");
        double capacity = sc.nextDouble();

        // Step 4: Sort items by ratio (descending) using Arrays.sort (O(n log n))
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        // Step 5: Apply Greedy method
        double totalProfit = 0.0;
        double remaining = capacity;

        for (Item item : items) {
            if (remaining >= item.weight) {
                // take full object
                totalProfit += item.profit;
                remaining -= item.weight;
            } else {
                // take fractional part
                double fraction = remaining / item.weight;
                totalProfit += item.profit * fraction;
                break; // knapsack is full
            }
        }

        // Step 6: Print only final result
        System.out.println("\nMaximum total profit in knapsack = " + String.format("%.2f", totalProfit));
    }
}
