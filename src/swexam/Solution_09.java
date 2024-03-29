package swexam;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Solution_09 {

	private static int start;
	private static int end;
	private static int[][] nodeArr;
	private static Integer[][] edge;
	private static int minMax[] = new int[2]; // min, max
	private static int finalMinMax[] = new int[2]; // min, max
	private static int totNodeLength;
	private static int totEdgeLengh;

	public static void main(String[] args) {
		Scanner sc = null;
		try {
			System.setIn(new FileInputStream(new File("/Users/actmember/eclipse-workspace/swexam/src/swexam/input.txt")));
			sc = new Scanner(System.in);
			int tc = sc.nextInt();
			
			finalMinMax[0] = 1000000001;
			finalMinMax[1] = 0;
			
			minMax[0] = 1000000001;
			minMax[1] = 0;
			
			// node의 갯수와 edge 갯수를 받아 온다.
			for (int i = 0; i < tc; i++) {
				int nodeCnt = sc.nextInt();
				int edgeCnt = sc.nextInt();

				nodeArr = new int[nodeCnt + 1][2]; // 0:visit 여부, 1:node수
				edge = new Integer[edgeCnt * 2][3]; // 0:parent, 1:child, 2:speed

				// 한 개의 case 값
				for (int j = 0; j < edgeCnt; j++) {
					edge[j][0] = sc.nextInt();
					edge[j][1] = sc.nextInt();
					edge[j][2] = sc.nextInt();

					nodeArr[edge[j][0]][1]++; // parent count
					nodeArr[edge[j][1]][1]++; // child count

				}

				for (int k = 0; k < edgeCnt; k++) { // parent child --> child parent edge 만들
					edge[edgeCnt + k][0] = edge[k][1];
					edge[edgeCnt + k][1] = edge[k][0];
					edge[edgeCnt + k][2] = edge[k][2];
				}

				start = sc.nextInt();
				end = sc.nextInt();
				totNodeLength = nodeArr.length;
				totEdgeLengh = edge.length;
			}

			sortArray(edge);
			printArray(edge);
			System.out.println("");
			
			System.out.println("node length "+nodeArr.length);

			nodeArr[start][0] = 1; // 방문

			int startParentIdx = srchStartIdx(start);

			System.out.println("start Index : " + startParentIdx);

			System.out.print(start);

			// root node 부터 edge를 탐색한다.
			visitEdge(startParentIdx, start);

			System.out.println("#" + tc + " " + (finalMinMax[1] - finalMinMax[0]));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean visitEdge(int startParentIdx, int parent) {
		// TODO Auto-generated method stub
		boolean tmpVal = false;
		boolean rtnVal = false;
		
		// 자식노드 차례로 방문
	
		int range = nodeArr[parent][1];
		int childNodeIndex = 0;

		for(int i=startParentIdx; i<+startParentIdx+range; i++) {
			// Child Node가 이미 방문한 노드이며 Pass
			
			if(nodeArr[edge[i][1]][0] == 1) continue;
			
			nodeArr[edge[i][1]][0] = 1;
			
			if(edge[i][1] == end) {  // node 가 end 이면 update 하고 Next
				rtnVal = true;
				updMinMax(edge[i][2]);
				System.out.println(("->"+end));
				nodeArr[edge[i][1]][0] = 0;	
			}else {
				System.out.println("->"+edge[i][1]);
				childNodeIndex = srchStartIdx(edge[i][1]);	
			}
			
			tmpVal = visitEdge(childNodeIndex, edge[i][1]);
			if(tmpVal) {
				rtnVal = true;
				updMinMax(edge[i][2]);
			}
			
			nodeArr[edge[i][1]][0] = 0;
			
		}
		
		if(parent == start && rtnVal) {
			updFinalMinMax();
		} 
		
		return rtnVal;
	}

	private static void updFinalMinMax() {
		// TODO Auto-generated method stub
		if (finalMinMax[0] > minMax[0])
			finalMinMax[0] = minMax[0];
		if (finalMinMax[1] < minMax[1])
			finalMinMax[1] = minMax[1];
		System.out.println("finalMinMax = "+"("+finalMinMax[0]+","+finalMinMax[1]+")");
	}

	private static void updMinMax(int speed) {
		// TODO Auto-generated method stub
		if (minMax[0] > speed)
			minMax[0] = speed;
		if (minMax[1] < speed)
			minMax[1] = speed;
		
		System.out.println("minMax = "+"("+minMax[0]+","+minMax[1]+")");
	}

	public static void printArray(Object[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print("\n"+i+")");
			for (int j = 0; j < arr[i].length; j++)
				System.out.print(" " + arr[i][j]);
		}

	}

	public static void sortArray(Integer[][] arr) {
		Arrays.sort(arr, new Comparator<Object[]>() {
			public int compare(Object[] arr1, Object[] arr2) {
				if (arr1[0] == arr2[0]) {
					if (((Comparable) arr1[1]).compareTo(arr2[1]) > 0)
						return 1;
					else
						return -1;
				}
				if (((Comparable) arr1[0]).compareTo(arr2[0]) > 0)
					return 1;
				else
					return -1;
			}
		});
	}

	private static int srchStartIdx(int node) {
		int idx = 0;

		int mid;
		int left = 0;
		int right = totEdgeLengh - 1;

		try {
			while (right >= left) {
				mid = (right + left) / 2;
				// System.out.println("mid:"+mid);
				if (edge[mid][0] == node) {
					idx = mid;
					if (mid - 1 >= 0 && edge[mid - 1][0] == node) {
						right = mid;
					} else {
						return idx;
					}
				} else if (edge[mid][0] > node) {
					right = mid;
				} else if (edge[mid][0] < node) {
					left = mid;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return idx;
		}
	}

}
