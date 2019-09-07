import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

 class Main {

	public int newgaleshapley(int[][] men, int[][] women, int N) {

		int[][] womenmap= new int[N][N];
		int[] wpartner= new int[N];
		int[] after= new int[N];
		int tt= 0;
		LinkedList<Integer> mfree= new LinkedList<>();
		for (int i= 0; i < N; i++ ) {
			mfree.push(i);
			after[i]= 0;
			wpartner[i]= -1;
			for (int j= 0; j < N; j++ ) {
				womenmap[i][women[i][j]]= j;
			}
		}

		while (mfree.size() > 0) {
			int m= mfree.removeFirst();
			int w= -1;
			if (m == 0) {
				if (after[m] >= N - 1) {
					tt= 1;
					break;
				} else {
					w= men[m][after[m] + 1];
					after[m]++ ;
				}
			} else w= men[m][after[m]++ ];

			if (wpartner[w] == -1) {
				wpartner[w]= m;

			} // women free case
			else {
				int m1= wpartner[w];
				if (womenmap[w][m] < womenmap[w][m1]) {
					wpartner[w]= m;
					mfree.add(m1);

				} else mfree.add(m);

			}

		}
		if (tt == 1) return 1;
		int tempwomen= men[0][0];

		int tempmen= wpartner[tempwomen];
		int r2;
//		HashMap<Integer, Integer> cmp= new HashMap<>();
		int[] cmp= stablemarriage(men, women, N);
//		if (wpartner[tempwomen] == -1) r2= 1;
//		for (int i= 0; i < N; i++ ) { System.out.println(wpartner[i]); }
		if (womenmap[tempwomen][tempmen] <= womenmap[tempwomen][cmp[tempwomen]]) r2= 3;
		else r2= 2;
		return r2;

	}

	public int[] stablemarriage(int[][] men, int[][] women, int N) {
		int[][] womenmap= new int[N][N];

		int[] wpartner= new int[N];
		int[] after= new int[N];

		LinkedList<Integer> mfree= new LinkedList<>();
		for (int i= 0; i < N; i++ ) {
			mfree.push(i);
			after[i]= 0;
			wpartner[i]= -1;
			for (int j= 0; j < N; j++ ) {
				womenmap[i][women[i][j]]= j;
			}
		}

		while (mfree.size() > 0) {
			int m= mfree.removeFirst();
			int w= men[m][after[m]++ ];

			if (wpartner[w] == -1) {
				wpartner[w]= m;

			} // women free case
			else {
				int m1= wpartner[w];
				if (womenmap[w][m] < womenmap[w][m1]) {
					wpartner[w]= m;
					mfree.add(m1);

				} else mfree.add(m);

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

				}

			}

			for (int i= 0; i < tot; i++ ) {
				String[] pref= in.readLine().split(" ");
				for (int k= 0; k < tot; k++ ) {
					women[i][k]= Integer.parseInt(pref[k]);

				}
			}
			in.close();
			Main f1= new Main();
			System.out.println(f1.stablemarriage(men, women, tot)[0]);
			System.out.println(f1.newgaleshapley(men, women, tot));

		} catch (IOException e) {
			System.out.println("IOExcpetion encountered");

		}

	}
}
