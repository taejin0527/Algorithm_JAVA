import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @FileName : BOJ_15686.java
 * @Project : 0825
 * @Date : 2020. 8. 25 
 * @author : "AoN"

 * @Description : NextPermutation을 활용한 순열
 * @Link : https://www.acmicpc.net/problem/15686
 */

public class BOJ_15686 {
	
	private Scanner sc;
	private int N, M, minChickenDist = Integer.MAX_VALUE;
	private int storeCnt;
	private List<Coordinate> houses, chickenStore;
	private int[] P;
	
	
	public BOJ_15686() {
		// 우선 집과 치킨집들의 좌표를 저장
		getInputAndMakeSet();
		// 조합을 모두 구하고 각 조합에 따른 거리 계산 및 최소값(minChickenDist) 갱신
		findMinChickenDistance();
		
		System.out.println(minChickenDist);
	}
	
	
	private void getInputAndMakeSet() {
		sc = new Scanner(System.in);
		
		N = sc.nextInt();	// 도시의 정보
		M = sc.nextInt();	// 치킨집 수
		houses = new ArrayList<>();
		chickenStore = new ArrayList<>();

		// 0은 빈 칸, 1은 집, 2는 치킨집
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				int temp = sc.nextInt();
				if(temp == 1) houses.add(new Coordinate(i, j));
				if(temp == 2) chickenStore.add(new Coordinate(i, j));
			}
		}
		storeCnt = chickenStore.size();
		
		sc.close();
	}
	
	private void findMinChickenDistance() {
		/**
		 * 방문체크와 재귀를 통한 DFS 방식으로 구현하는게 훨씬 가독성이 좋지만 연습을 위해...
		 * 치킨집의 모든 조합을 순열(nextPermutation)을 응용한 방식으로 구하고
		 * 각각의 맨해튼 거리를 계산해 최소값을 구한다
		 */

		for(List<Coordinate> comb : combinationByNextPermutation()) {		// NextPermutation을 응용한 치킨집 조합 마다
			int chickenDist = 0;
			
			for(Coordinate a : houses) {									// 각각의 집에서
				int minDist = Integer.MAX_VALUE;
				
				for(Coordinate b : comb) {									// 조합에 있는 치킨집까지의 거리 중에
					/** 
					 * 거리는 Manhattan distance 사용
					 * "치킨 거리" = 집과 가장 가까운 치킨집 사이의 거리
					 */
					minDist = Math.min(minDist, manhattanDistance(a, b));	// 최소값을 구한다
				}
				chickenDist += minDist;										// 각 집에서 해당 치킨집까지의 최소 거리가 "치킨거리"에 해당한다
			}
			minChickenDist = Math.min(chickenDist, minChickenDist);			// 최종적으로 구한 "치킨거리" 중 가장 최소값을 구한다
			
		}

	}
	
	private List<List<Coordinate>> combinationByNextPermutation() {
		List<List<Coordinate>> ret = new ArrayList<>();
		
		P = new int[storeCnt];
		/**
		 * 전체 n개의 원소들 중에서 k개를 뽑는 조합(=nCk)을 구한다면 
		 * n개의 벡터 원소에 0을 k개 1을 나머지인 n-k개 집어넣어서 순열을 돌리고 
		 * 0에 해당하는 인덱스만 가져오면 된다
		 */
		
		for(int i=0; i<storeCnt; ++i) {		// 전체 치킨집 수(storeCnt)에서 M개의 치킨집만 살아남는다
			P[i] = (i < M) ? 0 : 1;			// 조합에서는 순서가 없기 때문에 미선택/선택 기준으로 새로운 배열 P를 생성한다
		}
		
		do {
			List<Coordinate> comb = new ArrayList<>();

			for(int i=0; i<storeCnt; ++i) {
				if(P[i] == 0) {
					comb.add(chickenStore.get(i));
				}
			}
			ret.add(comb);
		}while(nextPermutation());
		
		return ret;
	}
	
	private boolean nextPermutation() {
		// step1. 꼭대기 찾기
		int i=storeCnt-1;
		while(i>0 && P[i-1]>=P[i]) --i;
		if(i==0) return false;
		
		// step2. i-1위치와 교환할 다음단계 큰 수 뒷쪽에서 찾기
		int j=storeCnt-1;
		while(P[i-1]>=P[j])	--j;
		
		// step3. i-1위치값과 j 위치값 교환
		swap(i-1,j);
		
		// step4. i위치부터 맨 뒤까지 오름차순 정렬
		int k = storeCnt-1;
		while(i<k) {
			swap(i++,k--);			
		}
		
		return true;
	}
	
	private void swap(int i, int j) {
		int temp = P[i];
		P[i] = P[j];
		P[j] = temp;
	}
	
	private int manhattanDistance(Coordinate a, Coordinate b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}
	
	
	public static void main(String[] args) {
		new BOJ_15686();
	}
	
	class Coordinate {
		int x, y;
		
		public Coordinate(int x, int y) {
			this.x = x; this.y = y;
		}

		@Override
		public String toString() {
			return "Coordinate [x=" + x + ", y=" + y + "]";
		}
	}
}
