import java.util.Scanner;

/**
 * @FileName : CLOCKSYNC.java
 * @Project : ALGO
 * @Date : 2020. 8. 22 
 * @author : "AoN"

 * @Description :
 * 문제 링크 : https://algospot.com/judge/problem/read/CLOCKSYNC
 * 
 */

public class Clocksync {
	
	private final int INF = Integer.MAX_VALUE-40, SWITCHES = 10, CLOCKS = 16;
	private int[] clocksNow;
	private int[][] linked = {
			{0, 1, 2},
			{3, 7, 9, 11},
			{4, 10, 14, 15},
			{0, 4, 5, 6, 7},
			{6, 7, 8, 10, 12},
			{0, 2, 14, 15},
			{3, 14, 15},
			{4, 5, 7, 14, 15},
			{1, 2, 3, 4, 5},
			{3, 4, 5, 9, 13},
	};
	
	public Clocksync() {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); assert T <= 30 : "Error:: testCase must be less than 30";

		
		while(--T >= 0) {
			clocksNow = new int[CLOCKS];
			
			for(int i=0; i<CLOCKS; ++i) {
				int curTime = sc.nextInt();
				assert curTime == 12 || curTime == 3 || curTime == 6 || curTime == 9: "Error:: Time must be 12 or 3 or 6 or 9";
				clocksNow[i] = curTime;
			}
			
			int ret = pushSwitch(0);
			
			System.out.println(ret != INF ? ret : -1);
		}	
		sc.close();
	}
	
	private int pushSwitch(int btn) {
		// 10개 버튼 모두 눌러봤을 때,
		if(btn == SWITCHES) return areSynced() ? 0 : INF;
		
		int ret = INF;
		for(int i=0; i<4; ++i) {
			ret = Math.min(ret,  i + pushSwitch(btn+1));
			
			for(int j=0; j<linked[btn].length; ++j) {
				int idx = linked[btn][j];
				clocksNow[idx] += 3;
				if(clocksNow[idx] == 15) clocksNow[idx] = 3;
			}
		}
		
		return ret;
	}
	
	private boolean areSynced() {
		for(int i=0; i<CLOCKS; ++i) {
			if(clocksNow[i] % 12 != 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		new Clocksync();
	}
}
