import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 11. 3 
 * @author : "AoN"

 * @Description :BOJ 14719 - 빗물
 * @Link : https://www.acmicpc.net/problem/14719
 * 
 */

public class BOJ_14719 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		sc.nextInt();
		int W = sc.nextInt();
		
		int block[] = new int[W];
		for(int i=0; i<W; ++i) {
			block[i] = sc.nextInt();
		}
		
		int left=0, right=0;
		int rain=0;
		
		for(int cur=1; cur<W-1; ++cur) {
			left = right = 0;
			
			for(int j=0; j<cur; ++j) {
				left = Math.max(left, block[j]);
			}
			for(int j=cur+1; j<W; ++j) {
				right = Math.max(right,  block[j]);
			}
			
			if(block[cur] < left && block[cur] < right)
				rain += Math.min(left, right) - block[cur];
		}
		
		System.out.println(rain);
		sc.close();
	}
}
