import java.io.DataInputStream;
import java.io.IOException;

/**
 * @FileName : Festival.java
 * @Project : ALGO
 * @Date : 2020. 8. 9 
 * @author : "AoN"

 * @Description :
 * 문제 링크 : https://algospot.com/judge/problem/read/FESTIVAL
 * 
 */

public class Festival {

	public static void main(String[] args) throws IOException {
		  Reader sc = new Reader();
		  int C = sc.nextInt();			// 테스트케이스의 수 (C <= 100)
		  
		  for (int tc=0; tc<C; ++tc) {
			  int N = sc.nextInt();		// 대여할 수 있는 날들의 수 (1 <= N <= 1000)
			  int L = sc.nextInt();		// 공연 팀의 수 (1 <= L <= 1000, L <= N)
			  
			  int[] cost = new int[N+1];
			  for (int i=1; i<=N; ++i) {
				  cost[i] = sc.nextInt();
			  }
			  
			  solve(N, L, cost);
		  }
	}

	static void solve(int N, int L, int[] cost) {
		double[] prefixSum = new double[N+1];
		for (int i=1; i<=N; ++i) {
			prefixSum[i] = prefixSum[i-1] + cost[i];
		}
		
		double maxMin = Integer.MAX_VALUE;
		double curMin;
		int from, to;
		for (int day=L; day<=N; ++day) {
			for (int i=1; i<=N-day+1; ++i) {
				from = i - 1;
				to = i + day - 1;
				curMin = (prefixSum[to] - prefixSum[from]) / (double) (day);
				
				maxMin = Math.min(curMin, maxMin);
			}
		}
		
		System.out.println(maxMin);
	}

	
	static class Reader
	{
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;
		
		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}
		
		public String nextLine() throws IOException {
			byte[] buf = new byte[64];
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n') break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}
		
		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while(c <= ' ') {
				c = read();
			}
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			
			if (neg) {
				return -ret;
			}
			return ret;
		}
		
		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}
		
		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}
	}
	
}
