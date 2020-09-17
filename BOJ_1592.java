package d0917;

import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 17 
 * @author : "AoN"

 * @Description : BOJ 1592 - 영식이와 친구들
 * @Link : https://www.acmicpc.net/problem/1592
 * 
 */

public class BOJ_1592 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int L = sc.nextInt();
		
		int p[] = new int[N];
		
		int i = 0, cnt = 0;
		while(true) {
			++p[i]; 
			if(p[i] == M) break;
			++cnt;
			i = (i+L)%N;
		}	
		System.out.println(cnt);
	}
}
