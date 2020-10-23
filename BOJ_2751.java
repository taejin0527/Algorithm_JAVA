import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 23
 * @author : "AoN"
 * 
 * @Description : BOJ 2751 - 수 정렬하기 2
 * @Link : https://www.acmicpc.net/problem/2751
 * 
 */

public class BOJ_2751 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}

		Collections.sort(list);

		for (int value : list) {
			sb.append(value).append('\n');
		}
		System.out.println(sb);

		br.close();
	}
}
