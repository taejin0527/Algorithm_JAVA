import java.util.ArrayList;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 17
 * @author : "AoN"
 * 
 * @Description : BOJ 1021 - 회전하는 큐
 * @Link : https://www.acmicpc.net/problem/1021
 * 
 */

public class BOJ_1021 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 1; i <= N; ++i)
			arr.add(i);

		int cnt = 0;
		for (int i = 0; i < M; i++) {
			int num = sc.nextInt();
			
			while (true) {
				if (arr.get(0) == num) {
					arr.remove(0);
					break;
				} else {
					if (arr.indexOf(num) <= arr.size() / 2)
						arr.add(arr.size() - 1, arr.remove(0));
					else
						arr.add(0, arr.remove(arr.size() - 1));
					cnt++;
				}
			}
		}

		System.out.println(cnt);
		sc.close();
	}
}
