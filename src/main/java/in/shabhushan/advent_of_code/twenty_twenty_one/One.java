package in.shabhushan.advent_of_code.twenty_twenty_one;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public class One {
  public static void main(String[] args) {
    int[] arr = new BufferedReader(new InputStreamReader(
        Objects.requireNonNull(in.shabhushan.advent_of_code.twenty_twenty.One.class.getClassLoader().getResourceAsStream("advent-of-code/2021/1.txt"))
    ))
        .lines()
        .mapToInt(Integer::valueOf)
        .toArray();

    int increment = 0;
    for (int i = 1; i < arr.length; i++) {
      if (arr[i - 1] < arr[i]) {
        increment++;
      }
    }

    System.out.println(increment);

    int sum = arr[0] + arr[1] + arr[2];
    increment = 0;

    for (int i = 3; i < arr.length; i++) {
      int nSum = sum + arr[i] - arr[i - 3];

      if (sum < nSum) {
        increment++;
      }

      sum = nSum;
    }

    System.out.println(increment);
  }
}
