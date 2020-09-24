package IM;

import java.io.*;
import java.util.*;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 24 
 * @author : "AoN"

 * @Description : BOJ 2559 - 수열
 * @Link : https://www.acmicpc.net/problem/2559
 * 
 */

public class BOJ_2559 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int temp[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; ++i) temp[i] = Integer.parseInt(st.nextToken());
		
		int sum=0, maxSum=0;
		
		for(int i=0; i<K; ++i) {
			sum += temp[i];
		}
		maxSum = sum;
		
		int s=0, e=K;
		for(int i=e; i<N; ++i) {
			
			sum += temp[e] - temp[s];
			++s; ++e;
			maxSum = Math.max(maxSum, sum);
		}
		
		System.out.println(maxSum);
		br.close();
	}
}
