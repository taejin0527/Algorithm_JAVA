package IM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 9. 24 
 * @author : "AoN"

 * @Description : BOJ 14696 - 딱지놀이
 * @Link : https://www.acmicpc.net/problem/14696
 * 
 */

public class BOJ_14696 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; ++i) {
			
			// A
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sizeA = Integer.parseInt(st.nextToken());
			Integer A[] = new Integer[sizeA];
			for(int j=0; j<sizeA; ++j) {
				A[j] = Integer.parseInt(st.nextToken());
			}
			
			// B
			st = new StringTokenizer(br.readLine());
			int sizeB = Integer.parseInt(st.nextToken());
			Integer B[] = new Integer[sizeB];
			for(int j=0; j<sizeB; ++j) {
				B[j] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(A, Collections.reverseOrder());
			Arrays.sort(B, Collections.reverseOrder());
			
			int idx = 0;
			boolean gameSet = false;
			while(!gameSet) {
				if(idx == sizeA && idx == sizeB) {
					System.out.println("D"); break;
				}
				if(idx == sizeA) {
					System.out.println("B"); break;
				}
				if(idx == sizeB) {
					System.out.println("A"); break;
				}

				if(A[idx] > B[idx]) {
					System.out.println("A"); break;
				}else if(A[idx] < B[idx]) {
					System.out.println("B"); break;
				}
				++idx;
			}
		}
		
		br.close();
	}
}
