package in.shabhushan.advent_of_code.twenty_twenty_one;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Three {
  public static void main(String[] args) {
    int[] arr = new BufferedReader(new InputStreamReader(
        Objects.requireNonNull(in.shabhushan.advent_of_code.twenty_twenty.One.class.getClassLoader().getResourceAsStream("advent-of-code/2021/3.txt"))
    ))
        .lines()
        .mapToInt(e -> Integer.parseInt(e, 2))
        .toArray();

    int gamma = 0;
    int epsilon = 0;

    for (int i = 0; i < 32; i++) {
      int ones = 0;
      int zeros = 0;

      for (int j: arr) {
        int b = (j & (1 << i));

        if (b == 0) zeros++; else ones++;
      }

      // System.out.println("i " + i + " ones " + ones + " zeros " + zeros);

      if (ones > zeros) {
        gamma = (gamma | (1 << i));
        epsilon = (epsilon & ~(1 << i));
      } else if (zeros > ones && zeros != arr.length) {
        gamma = (gamma & ~(1 << i));
        epsilon = (epsilon | (1 << i));
      }
    }

    System.out.println(Integer.toBinaryString(gamma) + " " + Integer.toBinaryString(epsilon));
    System.out.println(gamma * epsilon);

    List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

    // There are 12 bits in actual list. Hence, 11 here.
    int bit = 11;// mostSignificantBit(list.get(0));

    int oxygen = oxygenGenerationCalculation(list, bit);
    int co2 = co2GenerationCalculation(list, bit);

    System.out.println(oxygen);
    System.out.println(co2);
    System.out.println(oxygen * co2);
  }

  private static int co2GenerationCalculation(List<Integer> list, int bit) {
    // oxygen generator calculation
    List<Integer> co2List = new ArrayList<>(list);

    for (int i = bit; i >= 0; i--) {
      int ones = 0;
      int zeros = 0;

      for (int j: co2List) {
        int b = (j & (1 << i));

        if (b == 0) zeros++; else ones++;
      }

      boolean moreOnes = ones >= zeros;

      int bitToCheck = i;

      co2List = co2List.stream().filter(a -> {
        if (moreOnes) {
          // filter num with 0's at bit position
          return (a & (1 << bitToCheck)) == 0;
        } else {
          // filter num with 1's at bit position
          return (a & (1 << bitToCheck)) != 0;
        }
      }).collect(Collectors.toList());

      if (co2List.size() == 1) break;
    }

    return co2List.get(0);
  }

  private static int oxygenGenerationCalculation(List<Integer> list, int bit) {
    // oxygen generator calculation
    List<Integer> oxygenList = new ArrayList<>(list);

    for (int i = bit; i >= 0; i--) {
      int ones = 0;
      int zeros = 0;

      for (int j: oxygenList) {
        int b = (j & (1 << i));

        if (b == 0) zeros++; else ones++;
      }

      boolean moreOnes = ones >= zeros;

      int bitToCheck = i;

      oxygenList = oxygenList.stream().filter(a -> {
        if (moreOnes) {
          // filter num with 1's at bit position
          return (a & (1 << bitToCheck)) != 0;
        } else {
          // filter num with 0's at bit position
          return (a & (1 << bitToCheck)) == 0;
        }
      }).collect(Collectors.toList());

      if (oxygenList.size() == 1) break;
    }

    return oxygenList.get(0);
  }

  private static int mostSignificantBit(int x) {
    return (int) (Math.log(x) / Math.log(2));
  }
}
