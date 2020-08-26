import java.util.Scanner;

/**
 * @FileName : BOJ_1074.java
 * @Project : 0825
 * @Date : 2020. 8. 25 
 * @author : "AoN"

 * @Description : 분할정복 문제 
 * @Link : https://www.acmicpc.net/problem/1074
 * 
 */

public class BOJ_1074 {
	
	private int N, r, c, cnt=0;
	boolean isFound = false;
	
	private void Solution() {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();	// 15보다 작거나 같은 자연수
		r = sc.nextInt();	// 0 <= r <= 2^N-1
		c = sc.nextInt();	// 0 <= c <= 2^N-1
		
		/**
		 * N이 주어졌을 때, (r, c)를 몇 번째로 방문하는지 출력하는 프로그램을 작성
		 * Z모양으로 탐색
		 */
		zSearch(0, 0, 1<<N);
		System.out.println(cnt);
		
		sc.close();
	}
	
	private void zSearch(int x, int y, int length) {
		// (base case 0) 원하는 답을 찾았을 경우 모든 재귀를 빠르게 종료
		if(isFound) return;
		
		// (base case 1) 도착지점(r,c)에 도착했을 경우 종료
		if(x == r && y == c) {
			isFound = true;
			return;
		}
		
		// (base case 2) 1x1 배열 즉, 더 이상 재귀로 들어갈 수 없는 경우 종료
		if(length == 1) {
			cnt++;
			return;
		}
		
		/**
		 * Optimization이 없어도 테스트는 통과하지만
		 * 수행시간의 차이는 약 30배 정도 차이가 난다
		 */
		// (base case <Optimization>) 해당 사분면의 범위가 도착지점에서 벗어나면
		// 계속 재귀로 들어가는 것이 아니라 사분면 크기만큼 cnt만 더해주고 리턴함
		if(!(x <= r && r < x + length && y <= c && c < y + length)) {
			cnt += length * length;
			return;
		}
		
		
		// 재귀적으로 각각 1,2,3,4 사분면을 탐색함
		length = length >> 1;
		zSearch(x, y, length);	// 1 사분면 upperLeft
		zSearch(x, y + length, length);	// 2 사분면 upperRight
		zSearch(x + length, y, length);	// 3 사분면 lowerLeft
		zSearch(x + length, y + length, length);	// 4 사분면 lowerRight
	}
	
	public static void main(String[] args) {
		new BOJ_1074().Solution();
	}
}
