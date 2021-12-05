package in.shabhushan.advent_of_code.twenty_twenty_one;

import in.shabhushan.advent_of_code.twenty_twenty.One;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Two {
  public static void main(String[] args) {
    List<String> input = new BufferedReader(new InputStreamReader(
        Objects.requireNonNull(One.class.getClassLoader().getResourceAsStream("advent-of-code/2021/2.txt"))
    )).lines().collect(Collectors.toList());

    long horizontalPosition = 0;
    long depth = 0;

    for (String s: input) {
      String[] command = s.split(" ");

      int val = Integer.parseInt(command[1]);

      if (command[0].equals("forward")) {
        horizontalPosition += val;
      } else if (command[0].equals("up")) {
        depth -= val;
      } else {
        depth += val;
      }
    }

    System.out.println(horizontalPosition * depth);

    horizontalPosition = 0;
    depth = 0;
    long aim = 0;

    for (String s: input) {
      String[] command = s.split(" ");

      int val = Integer.parseInt(command[1]);

      if (command[0].equals("forward")) {
        horizontalPosition += val;
        depth += aim * val;
      } else if (command[0].equals("up")) {
        aim -= val;
      } else {
        aim += val;
      }
    }

    System.out.println(horizontalPosition * depth);
  }
}
