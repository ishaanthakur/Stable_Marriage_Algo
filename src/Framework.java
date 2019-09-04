import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Framework {

	// Returns true if women w prefers m1 over m else false

	public boolean pref(int[][] women, int w, int m, int m1, int N) {
		for (int i= 0; i < N; i++ ) {

			if (women[w][i] == m1)
				return true;

			if (women[w][i] == m)
				return false;
		}
		return false;

	}

	public boolean pref2(HashMap<Integer, HashMap<Integer, Integer>> women, int w, int m, int m1, int N) {

		if (women.get(w).get(m1) > women.get(w).get(m)) {

			return true;

		}

		return false;
	}

//	public int newgaleshapley(int[][] men, int[][] women, int N) {
//
//		int womenpartner[]= new int[N]; // women's partner
//		boolean menfree[]= new boolean[N]; // free men
//		int free= N; // number of free men
//		Arrays.fill(womenpartner, -1);
//		Arrays.fill(menfree, false);
//		while (free > 0) {
//			int flag= 0; 
//			int m= 0;
//			for (m= 0; m < N; m++ ) {
//				if (menfree[m] == false) break;
//			}
//
//			for (int i= 0; i < N && menfree[m] == false; i++ ) {
//				int w;
//				if (flag == 0) {
//					w= men[m][i + 1];
//					flag= 1;
//				} else w= men[m][i];
//				if (womenpartner[w] == -1) {
//					womenpartner[w]= m;
//					menfree[m]= true;
//					free-- ;
//
//				} else { // Women not free
//					int m1= womenpartner[w];
//					if (pref(women, w, m, m1, N) == false) {
//						womenpartner[w]= m;
//						menfree[m]= true;
//						menfree[m1]= false;
//
//					}
//
//				}
//			}
//
//		}
//
//		int[] comp= stablemarriage(men, women, N);
//
//		for (int i= 0; i < N; i++ ) if (womenpartner[i] == -1) return 1;
//		int fl= 0;
//		for (int i= 0; i < N; i++ ) {
//
//			if (pref(women, i, womenpartner[i], comp[i], N) == true) fl= 1;
//			else fl= 0;
//		}
//		if (fl == 0) return 3;
//		else return 2;
//
//	}

	public int stablemarriage(int[][] men, int[][] women, int N) {

		HashMap<Integer, HashMap<Integer, Integer>> womenmap= new HashMap<>();

		for (int i= 0; i < N; i++ ) {
			HashMap<Integer, Integer> temp= new HashMap<>();
			for (int j= 0; j < N; j++ ) {
				if (!temp.containsKey(women[i][j])) temp.put(women[i][j], j);

			}
			womenmap.put(i, temp);

		}

		HashMap<Integer, Integer> wpartner= new HashMap<>();
//		boolean menfree[]= new boolean[N]; // free men
		HashMap<Integer, Boolean> mfree= new HashMap<>();
		int free= N; // number of free men
//	
		while (free > 0) {

			int m= 0; // men no proposed
			for (m= 0; m < N; m++ ) {
				if (mfree.get(m) == null || mfree.get(m) != true) break;
			}

			for (int i= 0; i < N && (mfree.get(m) == null || mfree.get(m) != true); i++ ) {
				int w= men[m][i];
				if (!wpartner.containsKey(w)) { // Women free
					wpartner.put(w, m);
					mfree.put(m, true);
					free-- ;
					break;

				} else { // Women not free

					int m1= wpartner.get(w);

					HashMap<Integer, Integer> curr= new HashMap<>();
					curr= womenmap.get(w);

					if (curr.get(m) < curr.get(m1)) {
						wpartner.put(w, m);
						mfree.put(m, true);
						mfree.put(m1, false);

					}

				}
			}
		}

		return wpartner.get(0);

	}

	public static void main(String[] args) {

		BufferedReader in= null;
		try {
			in= new BufferedReader(new InputStreamReader(System.in));
			String[] line= in.readLine().split("\\s+");
			int tot= Integer.parseInt(line[0]);
			int[][] men= new int[tot][tot];
			int[][] women= new int[tot][tot];

			for (int i= 0; i < tot; i++ ) {
				String[] pref= in.readLine().split(" ");
				for (int j= 0; j < tot; j++ ) {
					men[i][j]= Integer.parseInt(pref[j]);
//					System.out.print(men[i][j]);

				}
//				System.out.println(" ");
			}

			for (int i= 0; i < tot; i++ ) {
				String[] pref= in.readLine().split(" ");
				for (int k= 0; k < tot; k++ ) {
					women[i][k]= Integer.parseInt(pref[k]);
//					System.out.print(women[i][k]);

				}
			}
			in.close();
			Framework f1= new Framework();
			System.out.println(f1.stablemarriage(men, women, tot));
//			System.out.println(f1.newgaleshapley(men, women, tot));

		} catch (IOException e) {
			System.out.println("IOExcpetion encountered");

		}

	}
}
