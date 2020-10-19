import java.util.Scanner;

/**
 * @FileName : Main.java
 * @Project : Algorithm
 * @Date : 2020. 10. 14
 * @author : "AoN"
 * 
 * @Description : BOJ 1019 - 책 페이지
 * @Link : https://www.acmicpc.net/problem/1019
 * 
 */

public class BOJ_1019 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		int prev = 0;
		int num[] = new int[10];
		for (int i = 1; N > 0; i *= 10) {
			int now = N % 10;
			N /= 10;

			for (int j = 0; j < now; ++j)
				num[j] += (N + 1) * i;
			num[now] += N * i + prev + 1;
			for (int j = now + 1; j < 10; ++j)
				num[j] += N * i;
			num[0] -= i;
			prev += now * i;
		}

		for (int i = 0; i < 10; ++i) {
			System.out.print(num[i] + " ");
		}

		sc.close();
	}
}

/***
public class Main {
	
	static int[] cnt;
	static int start, end, digit;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		digit = 1;
		start = 1;
		end = sc.nextInt();
		
		cnt = new int[10];
		
		while(start <= end) {
			// 시작 페이지의 마지막 자리가 0이 될 때 까지 ++ 
			while(start % 10 != 0 && start <= end) {
				counting(start, digit);
				start++;
			}
			
			// 마지막 페이지의 마지막 자리가 9가 될 때 까지 -- 
			while(end % 10 != 9 && start <= end) {
				counting(end, digit);
				end--;
			}
			
			if(start > end) break;
			
			// 마지막 자릿수를 제거한다. 
			start /= 10;
			end /= 10;
			
			// start ~ end 사이의 0 ~ 9 갯수를 모두 센다.
			// 현재 자릿수 만큼 곱해줘야한다. 
			for(int i = 0 ; i < 10 ; ++i) {
				cnt[i] += (end - start + 1) * digit;
			}
			
			// 자릿수를 높인다. 
			digit *= 10;
		}
		
		for(long i : cnt) {
			System.out.print(i + " ");
		}
	}

	private static void counting(int num, int digit) {
		while(num > 0) {
			cnt[num % 10] += digit;
			num /= 10;
		}
	}
}
***/