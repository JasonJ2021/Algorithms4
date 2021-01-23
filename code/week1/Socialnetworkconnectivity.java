package week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Socialnetworkconnectivity {
    private WeightedQuickUnionUF quickUnionUF;
    private FileInputStream input;

    public Socialnetworkconnectivity(int num, FileInputStream input) {
        this.input = input;
        quickUnionUF = new WeightedQuickUnionUF(num);
    }

    public String getEarliestConTime() {
        Scanner scanner = new Scanner(input);
        String EarliestContime = null;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line != null && !line.trim().equals("")) {
                String[] lines = line.split(" ");
                if (lines.length == 3) {
                    String timeStamp = lines[0];
                    int p = Integer.parseInt(lines[1]);
                    int q = Integer.parseInt(lines[2]);
                    if (quickUnionUF.connected(p, q)) continue;
                    quickUnionUF.union(p, q);
                    if (quickUnionUF.count() == 1) {
                        EarliestContime = timeStamp;
                        break;
                    }

                }
            }
        }
        return EarliestContime;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\61644\\IdeaProjects\\Algorithms4\\code\\week1\\test.txt");
        Socialnetworkconnectivity solve = new Socialnetworkconnectivity(10,inputStream);
        String earliestTime = solve.getEarliestConTime();
        System.out.println("the earliest time for socialnetworkConnectivity is " + earliestTime);
    }
}

