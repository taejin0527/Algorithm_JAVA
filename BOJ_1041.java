import java.util.Arrays;
import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 20 
 * @author : "AoN"

 * @Description : BOJ 1041 - 주사위
 * @Link : https://www.acmicpc.net/problem/1041
 * 
 */

public class BOJ_1041 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long N = sc.nextInt();
		
		int num[] = new int[6];
		int total = 0, maxNum = 0;
		for(int i=0; i<6; ++i) {
			num[i] = sc.nextInt();
			total += num[i];
			maxNum = Math.max(maxNum, num[i]);
		}
		
		if(N==1) {
			System.out.println(total - maxNum);
		} else {
			int s[] = {Math.min(num[0], num[5]), Math.min(num[1], num[4]), Math.min(num[2], num[3])};
			Arrays.sort(s);
			int p3 = s[0] + s[1] + s[2];
			int p2 = s[0] + s[1];
			int p1 = s[0];
			
			long n3 = 4;
			long n2 = (N - 1) * 4 + (N - 2) * 4;
			long n1 = (N - 1) * (N - 2) * 4 + (N - 2) * (N - 2);
			
			long ans = p3*n3 + p2*n2 + p1*n1;
			System.out.println(ans);
		}
		
		sc.close();
	}
}
