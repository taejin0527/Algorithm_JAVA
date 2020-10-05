import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 4 
 * @author : "AoN"

 * @Description : BOJ 2629 - 양팔저울
 * @Link : https://www.acmicpc.net/problem/2629
 * 
 */

public class BOJ_2629 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] chu = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++)
			chu[i] = Integer.parseInt(st.nextToken());
		
		int max = 30 * 500;
		boolean[] check = new boolean[max+1];
		boolean[] save = new boolean[max+1];
		check[0] = true;
		save[0] = true;
		
		int left, right;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j <= max ; j++) {
				if(check[j]) {
					left = Math.abs(chu[i] - j);
					right = Math.abs(chu[i] + j);
					save[left] = true;
					save[right] = true;
					save[chu[i]] = true;
				}
			}
			for(int j = 0 ; j <= max ; j++)
				check[j] = save[j];
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(tmp <= max && check[tmp])
				System.out.print("Y ");
			else
				System.out.print("N ");
		}
	}
}
