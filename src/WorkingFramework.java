import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class WorkingFramework {

	public boolean pref(int[][] women, int w, int m, int m1, int N) {
		for (int i= 0; i < N; i++ ) {

			if (women[w][i] == m1)
				return true;

			if (women[w][i] == m)
				return false;
		}
		return false;

	}

	public int newgaleshapley(int[][] men, int[][] women, int N) {

		HashMap<Integer, int[]> womenmap= new HashMap<>();

		for (int i= 0; i < N; i++ ) {
			int[] temp= new int[N];
			for (int j= 0; j < N; j++ ) {
				temp[women[i][j]]= j;

			}
			womenmap.put(i, temp);

		}

		int[] wpartner= new int[N];
		boolean[] mfree= new boolean[N];
		Arrays.fill(wpartner, -1);
		Arrays.fill(mfree, false);
		int free= N; // number of free men
		int tt= 0;
		while (free > 0) {

			int m; // men no proposed
			for (m= 0; m < N; m++ ) {
				if (mfree[m] == false) break;
			}

			for (int i= 0; i < N && mfree[m] == false; i++ ) {
				int w;
				if (m == 0) {
					if (i >= N - 1) {
						w= -1;
						tt= 1;
						break;
					} else w= men[m][i + 1];

				} else {
					if (m >= N) {
						w= -1;
					} else w= men[m][i];
				}
				if (w != -1) {
					if (wpartner[w] == -1) { // Women free
						wpartner[w]= m;
						mfree[m]= true;
						free-- ;
						break;

					} else { // Women not free

						int m1= wpartner[w];

						int[] curr= womenmap.get(w);

						if (curr[m] < curr[m1]) {
							wpartner[w]= m;
							mfree[m]= true;
							mfree[m1]= false;

						}

					}
				}
			}
			if (tt == 1) break;
		}
		if (tt == 1) return 1;
		int tempwomen= men[0][0];

		int tempmen= wpartner[tempwomen];
		int r2;
		HashMap<Integer, Integer> cmp= new HashMap<>();
		cmp= stablemarriage(men, women, N);
		int t= cmp.get(tempwomen);
		if (wpartner[tempwomen] == -1) r2= 1;
		else if (womenmap.get(tempwomen)[tempmen] <= womenmap.get(tempwomen)[t]) r2= 3;
		else r2= 2;
		return r2;

	}

	public HashMap<Integer, Integer> stablemarriage(int[][] men, int[][] women, int N) {

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

		return wpartner;

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
			WorkingFramework f1= new WorkingFramework();
			System.out.println(f1.stablemarriage(men, women, tot).get(0));
			System.out.println(f1.newgaleshapley(men, women, tot));

		} catch (IOException e) {
			System.out.println("IOExcpetion encountered");

		}

	}
}
