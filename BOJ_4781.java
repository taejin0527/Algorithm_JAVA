import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 5
 * @author : "AoN"
 * 
 * @Description : BOJ 4781 - 사탕 가게
 * @Link : https://www.acmicpc.net/problem/4781
 * 
 */

public class BOJ_4781 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int n = sc.nextInt();
			int m = (int) Math.round(sc.nextDouble() * 100);
			
			if (n == 0 && m == 0) break;
			
			int[] candyCal = new int[n];
			int[] candyPrice = new int[n];
			for (int i = 0; i < n; i++) {
				candyCal[i] = sc.nextInt();
				candyPrice[i] = (int) Math.round(sc.nextDouble() * 100);
			}
			
			int[] d = new int[m + 1];
			for (int i = 0; i <= m; ++i) {
				for (int j = 0; j < n; ++j) {
					if (i - candyPrice[j] >= 0) {
						d[i] = Math.max(d[i], d[i - candyPrice[j]] + candyCal[j]);
					}
				}
			}
			System.out.println(d[m]);
		}
		sc.close();
	}
}
