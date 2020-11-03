import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @FileName : Solution.java
 * @Project : Algorithm
 * @Date : 2020. 11. 3
 * @author : "AoN"
 * 
 * @Description : 2383. [모의 SW 역량테스트] 점심 식사시간
 * @Link :
 *       https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl
 * 
 */

public class SWEA_2383 {
	static int P, S, ans;
	static List<Integer> PR, PC, SR, SC, SL;
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb =new StringBuilder();
		for(int tc = 1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			PR= new ArrayList<Integer>();
			PC= new ArrayList<Integer>();
			SR= new ArrayList<Integer>();
			SC= new ArrayList<Integer>();	
			SL = new ArrayList<Integer>();
			
			int[][] arr =new int[N][N];
			for(int i =0; i< N ; i++) {
				StringTokenizer st =new StringTokenizer(br.readLine());
				for(int j =0; j<N ;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] == 1) {
						PC.add(i);
						PR.add(j);
					}else if(arr[i][j] > 1) {
						SC.add(i);
						SR.add(j);
						SL.add(arr[i][j]);
					}
				}
			}
			
			P = PC.size();// 사람수 
			S = SC.size(); // 계단 수 
			ans = Integer.MAX_VALUE;
			check(new int[P],0);
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		
		System.out.println(sb);
		br.close();
	}
	private static void check(int[] tmp, int p) {
		if(p==P) {
			int time =0; 
			for(int s =0; s<S;s++) { 
				List<Integer> or = new ArrayList<Integer>();
				for(int i =0; i< P; i++) {
					if(tmp[i]==s) {
						or.add(Math.abs(PR.get(i)-SR.get(s))+ Math.abs(PC.get(i)-SC.get(s)));
					}
				}
				Collections.sort(or);

				int out = SL.get(s);// 계단 길이 
				int size = or.size();
				for( int i = 3; i<size; i++) {
					if(or.get(i)-or.get(i-3)<out) {
						or.set(i, out+or.get(i-3));
					}
				}
				time = (size>0 && or.get(size-1)+out+1>time) ? or.get(size-1)+out+1 : time;

			}
			ans = Math.min(ans, time);
			return;
		}
		
		for(int i =0; i< S;i++) {
			tmp[p] = i;
			check(tmp, p+1);
		}
	}
}