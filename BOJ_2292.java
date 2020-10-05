import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 4 
 * @author : "AoN"

 * @Description : BOJ 2292 - 벌집
 * @Link : https://www.acmicpc.net/problem/2292
 * 
 */

public class BOJ_2292 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int ans = 1;
		int num = 1;
		int add = 6;
		while(true) {
			if(N <= num) break;
			
			num += add;
			add += 6;
			++ans;
		}
		
		 System.out.println(ans);
		
		sc.close();
	}
}
