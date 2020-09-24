package IM;

import java.util.*;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 22 
 * @author : "AoN"

 * @Description : BOJ 2635 - 수 이어가기
 * @Link : https://www.acmicpc.net/problem/2635
 * 
 */

public class BOJ_2635 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int maxCnt = 1;
		List<Integer> ans = new ArrayList<>();
		
		for(int i = N; i >= N/2; --i) {
			int pre = N;
			int post = i;
			List<Integer> temp = new ArrayList<>();
			
			temp.add(N);
			
			while(true) {
				if(post < 0) break;
		
				temp.add(post);
				
				int t = pre;
				pre = post;
				post = t - pre;
			}
			
			if(temp.size() > maxCnt) {
				maxCnt = temp.size();
				ans = temp;
			}
			
		}
		
		System.out.println(maxCnt);
		for(int num : ans) System.out.print(num + " ");
		
		sc.close();
	}
}
