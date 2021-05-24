package swmentor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_back {

	private static int N;
	private static StringTokenizer st;
	private static int[] input;
	private static int[] PD;
	private static int M;
	private static boolean isPd;

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("/Users/actmember/eclipse-workspace/swmentor/src/swmentor/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		input = new int[N+1];
		PD = new int[N+1];
		
		st = new StringTokenizer(br.readLine().trim(), " ");	
		int idx=1;
		while(st.hasMoreTokens()) {
			input[idx++] = Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(" iput --> ");
//		for(int a:input) {
//			System.out.print(a+" ");
//		}
//		System.out.println("");
		M = Integer.parseInt(br.readLine().trim());
		
		int start, end;
		boolean rst = false;
		for( int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine().trim());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			if(find(start, end)) System.out.println(1);
			else System.out.println(0);
			
			System.out.println();
		}
	}

	private static boolean find(int s, int e) {
		// TODO Auto-generated method stub
		int loop, start, end;
		end = e;
		start = s;
		
		end++;
		if ((end-start)%2==1) {
			loop = (end-start)/2;
		} else {
			loop = (end-start)/2;
		}
		
		isPd = true;
		
//		System.out.println("start, end, loop : " +start+","+end+","+loop+" ");
		for(int i = start ; i <start+loop ; i++) {
//			System.out.println("i, end-i : "+i+","+(end-i));
//			System.out.println("pd[i]: "+PD[i]);
//			System.out.println("input[i], input[end-i] : "+input[i]+","+input[end-i]);
			
			if(PD[i]==input[end-i]) break;
			if(input[i]==input[end-i]) continue;
			else isPd = false;
		}
		
		if(isPd) {
			if(PD[s]<e) {
				PD[s]=e;
			}
		} 
		
		return isPd;
	}
}
