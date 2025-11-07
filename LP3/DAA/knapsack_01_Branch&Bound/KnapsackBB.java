import java.util.*;

class Item {
    int weight, value;
    double ratio;

    Item(int w, int v) {
        weight = w;
        value = v;
        ratio = (double) v / w;
    }
}

class Node {
    int level, profit, weight;
    double bound;
}

public class KnapsackBB {

    // Function to calculate upper bound of profit in subtree
    static double findBound(Node node, int n, int W, Item[] items) {
        if (node.weight >= W)
            return 0;

        double profitBound = node.profit;
        int j = node.level + 1;
        int totalWeight = node.weight;

        // take items fully while we can
        while (j < n && totalWeight + items[j].weight <= W) {
            totalWeight += items[j].weight;
            profitBound += items[j].value;
            j++;
        }

        // take fraction of next item (for upper bound)
        if (j < n)
            profitBound += (W - totalWeight) * items[j].ratio;

        return profitBound;
    }

    static int knapsack(int W, Item[] items, int n) {
        // sort by value/weight ratio (descending)
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        Queue<Node> queue = new LinkedList<>();
        Node u = new Node(); // current node
        Node v;              // next node

        u.level = -1;
        u.profit = 0;
        u.weight = 0;

        double maxProfit = 0;
        u.bound = findBound(u, n, W, items);
        queue.add(u);

        while (!queue.isEmpty()) {
            u = queue.poll();

            // if we already processed all items
            if (u.level == n - 1)
                continue;

            // explore next level
            v = new Node();
            v.level = u.level + 1;

            // Case 1: include next item
            v.weight = u.weight + items[v.level].weight;
            v.profit = u.profit + items[v.level].value;

            if (v.weight <= W && v.profit > maxProfit)
                maxProfit = v.profit;

            v.bound = findBound(v, n, W, items);
            if (v.bound > maxProfit)
                queue.add(v);

            // Case 2: exclude next item
            v = new Node();
            v.level = u.level + 1;
            v.weight = u.weight;
            v.profit = u.profit;
            v.bound = findBound(v, n, W, items);
            if (v.bound > maxProfit)
                queue.add(v);
        }

        return (int) maxProfit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];
        System.out.println("Enter weights of items:");
        int[] w = new int[n];
        for (int i = 0; i < n; i++) w[i] = sc.nextInt();

        System.out.println("Enter values of items:");
        int[] val = new int[n];
        for (int i = 0; i < n; i++) val[i] = sc.nextInt();

        for (int i = 0; i < n; i++) {
            items[i] = new Item(w[i], val[i]);
        }

        System.out.print("Enter capacity of knapsack: ");
        int W = sc.nextInt();

        int result = knapsack(W, items, n);
        System.out.println("\n✅ Maximum value = " + result);
    }
}
