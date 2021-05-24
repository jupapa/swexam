package swmentor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static StringTokenizer st;
	private static int[] input;
	private static int [] PD_odd;
	private static int [] PD_even;
	private static int M;
	private static boolean isPd;
	private static boolean isPrint = true;

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("/Users/actmember/eclipse-workspace/swmentor/src/swmentor/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		input = new int[N+1];
		PD_odd = new int[N+1];
		PD_even = new int[N+1];
		
		st = new StringTokenizer(br.readLine().trim(), " ");	
		int idx=1;
		
		// input을 받
		while(st.hasMoreTokens()) {
			input[idx++] = Integer.parseInt(st.nextToken());
		}
		
		if(isPrint) {
			System.out.println("--- iput --- ");
			for(int a:input) {
				System.out.print(a+" ");
			}
			System.out.println("");
		}
		M = Integer.parseInt(br.readLine().trim());
		
		
		//홀수 DP
		int dist;
		
		for(int i=1; i<=N; i++) {
			PD_odd[i] = PD_even[i] = 1;
		}
		
		for(int mid = 1 ; mid <= N ; mid ++ ) {
			dist = 1;
			while(mid-dist>0 && mid+dist<N) {
				if(input[mid-dist] == input[mid+dist]) dist++; 
				else {
					break;
				}
			}
			
			if(dist!=1) PD_odd[mid] = dist; 
		}
		
		//짝수 DP
		int mid2;
		for(int mid1 = 1 ; mid1 <= N ; mid1 ++ ) {
			dist = 1;
			mid2 = mid1+1;	
			if(isPrint) System.out.println("mid1 : "+mid1);
		
			if(mid1-dist>0 && mid2+dist<=N && input[mid1]!=input[mid2]) continue;
			else {
//				System.out.println("mid1-dist : "+(mid1-dist)+" mid2+dist : "+(mid2+dist));
				while(mid1-dist>0 && mid2+dist<=N) {
					if(isPrint) System.out.println("mid1-dist, mid2+dist : "+(mid1-dist)+", "+(mid2+dist)+" "+dist);
					if(input[mid1-dist] == input[mid2+dist]) dist++;
					else {	
						break;
					}
				}
				if(dist!=1) PD_even[mid1] = PD_even[mid2] = dist;
			}
			
		}
		
		
		
		if(isPrint) {
			System.out.println("------------");
			System.out.println("pd_odd");
			
			for( int i = 0 ; i <= N ; i ++) {
				System.out.print(PD_odd[i]+" ");
			}
			
			System.out.println("");
			System.out.println("pd_even");
			for( int i = 0 ; i <= N ; i ++) {
				System.out.print(PD_even[i]+" ");
			}
			}
		
		
		//짝수 DP 구하기
		
		int start, end, middle, left;
		boolean rst = false;
		for( int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine().trim());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			
			middle = (end+start)/2;
			left = (end-start)%2; // 홀수이면 even, 짝수이면 odd
			if(isPrint) System.out.println("start, middle, end : "+start+", "+middle+", "+end);
			
			if(left==0 && PD_odd[middle]>1) System.out.println(1); 
			else if(left==1 && PD_odd[middle]>1) System.out.println(1); 
			else System.out.println(0);
		}
		
		
	}

	private static boolean find(int s, int e) {
		// TODO Auto-generated method stub
		int loop, start, end;
		end = e;
		start = s;
		
		boolean isOdd, isEven;
		
		isOdd = isEven = false;
		isPd = true;
		
		if((end-start)%2==0) isOdd = true;
		else isEven = true;
		
		
		loop  = e - s +1;
		
//		System.out.println("start, end, loop : " +start+","+end+","+loop+" ");
		
		if(isOdd && checkOdd(s, e)) {
//			System.out.println("hit odd");
			return true;
		}
		if(isEven && checkEven(s, e)) {
//			System.out.println("hit even");
			return true;
		}
		
		
		if(isPrint) System.out.println("find next step -->");
		
		for(int i = start ; i <start+(loop/2) ; i++) {
//			System.out.println("i, end-i : "+i+","+(end-i));
//			System.out.println("pd[i]: "+PD[i]);
//			System.out.println("input[i], input[end-i] : "+input[i]+","+input[end-i]);
			
			if(isPrint) {	
			System.out.println(" i , start, e : "+i+" "+start+" "+e);
			System.out.println(" end - i+start) : "+(end-i+start));
			}
			
			if(i==(end-i+start)) {
				isPd = true;
				break;
			}
			
			if(isOdd) isPd = checkOdd(i, end-i+start);
			if(isEven) isPd = checkEven(i, end-i+start);
			
			if(input[i]!=input[end-i+start]) isPd = false;
			else isPd = true; 
			
			if(!isPd) break;
		}
		
		if(isPd) {
			
			// 메모리에 저
			if(isOdd) {
				
				if(isPrint) System.out.println(" odd saved !! "+s+","+e);
				PD_odd[(s+e)/2] = (e-s)/2;
			}
			
			if(isEven) {
				//System.out.println(" even !! "+s+","+e);
				
				if(isPrint) System.out.println(" even saved !! "+s+","+e);
				PD_even[(s+e)/2] = PD_even[(s+e)/2+1] = (e-s)/2;
			}
		} 
		
		return isPd;
	}

	private static boolean checkEven(int s, int e) {
		// TODO Auto-generated method stub
		boolean rst = false;
		
		if((PD_odd[(s+e)/2] == (e-s)/2) && (PD_even[(s+e)/2+1] == (e-s)/2)) rst = true;
		else rst = false;
		
		if(isPrint) System.out.println("checkEven ("+s+","+e+") : "+rst);
		
		return rst;
	}

	private static boolean checkOdd(int s, int e) {
		// TODO Auto-generated method stub
		
		boolean rst = false;
		
		if((PD_odd[(s+e)/2] == (e-s)/2)) rst = true;
		else rst = false;
		
		if(isPrint) System.out.println("checkOdd ("+s+","+e+") : "+rst);
		
		return rst;
	}
	
	
}
