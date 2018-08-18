package utils;

public class StringDistanceMetrics {

	public static int levenshteinDistance(CharSequence lhs, CharSequence rhs) {
		int len0 = lhs.length() + 1;
		int len1 = rhs.length() + 1;

		// the array of distances
		int[] cost = new int[len0];
		int[] newcost = new int[len0];

		// initial cost of skipping prefix in String s0
		for (int i = 0; i < len0; i++)
			cost[i] = i;

		// dynamically computing the array of distances

		// transformation cost for each letter in s1
		for (int j = 1; j < len1; j++) {
			// initial cost of skipping prefix in String s1
			newcost[0] = j;

			// transformation cost for each letter in s0
			for (int i = 1; i < len0; i++) {
				// matching current letters in both strings
				int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

				// computing cost for each transformation
				int cost_replace = cost[i - 1] + match;
				int cost_insert = cost[i] + 1;
				int cost_delete = newcost[i - 1] + 1;

				// keep minimum cost
				newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
			}

			// swap cost/newcost arrays
			int[] swap = cost;
			cost = newcost;
			newcost = swap;
		}

		// the distance is the cost for transforming all letters in both strings
		return cost[len0 - 1];
	}

	public static int levenshteinDistance(String lhs, String rhs) {
		int len0 = lhs.length() + 1;
		int len1 = rhs.length() + 1;

		// the array of distances
		int[] cost = new int[len0];
		int[] newcost = new int[len0];

		// initial cost of skipping prefix in String s0
		for (int i = 0; i < len0; i++)
			cost[i] = i;

		// dynamically computing the array of distances

		// transformation cost for each letter in s1
		for (int j = 1; j < len1; j++) {
			// initial cost of skipping prefix in String s1
			newcost[0] = j;

			// transformation cost for each letter in s0
			for (int i = 1; i < len0; i++) {
				// matching current letters in both strings
				int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

				// computing cost for each transformation
				int cost_replace = cost[i - 1] + match;
				int cost_insert = cost[i] + 1;
				int cost_delete = newcost[i - 1] + 1;

				// keep minimum cost
				newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
			}

			// swap cost/newcost arrays
			int[] swap = cost;
			cost = newcost;
			newcost = swap;
		}

		// the distance is the cost for transforming all letters in both strings
		return cost[len0 - 1];
	}

	public static int damerauLevenshteinDistance(String source, String target) {
		if (source == null || target == null) {
			throw new IllegalArgumentException("Parameter must not be null");
		}
		int sourceLength = source.length();
		int targetLength = target.length();
		if (sourceLength == 0)
			return targetLength;
		if (targetLength == 0)
			return sourceLength;
		int[][] dist = new int[sourceLength + 1][targetLength + 1];
		for (int i = 0; i < sourceLength + 1; i++) {
			dist[i][0] = i;
		}
		for (int j = 0; j < targetLength + 1; j++) {
			dist[0][j] = j;
		}
		for (int i = 1; i < sourceLength + 1; i++) {
			for (int j = 1; j < targetLength + 1; j++) {
				int cost = source.charAt(i - 1) == target.charAt(j - 1) ? 0 : 1;
				dist[i][j] = Math.min(Math.min(dist[i - 1][j] + 1, dist[i][j - 1] + 1), dist[i - 1][j - 1] + cost);
				if (i > 1 && j > 1 && source.charAt(i - 1) == target.charAt(j - 2)
						&& source.charAt(i - 2) == target.charAt(j - 1)) {
					dist[i][j] = Math.min(dist[i][j], dist[i - 2][j - 2] + cost);
				}
			}
		}
		return dist[sourceLength][targetLength];
	}

	public static int damerauLevenshteinDistance(CharSequence source, CharSequence target) {
		if (source == null || target == null) {
			throw new IllegalArgumentException("Parameter must not be null");
		}
		int sourceLength = source.length();
		int targetLength = target.length();
		if (sourceLength == 0)
			return targetLength;
		if (targetLength == 0)
			return sourceLength;
		int[][] dist = new int[sourceLength + 1][targetLength + 1];
		for (int i = 0; i < sourceLength + 1; i++) {
			dist[i][0] = i;
		}
		for (int j = 0; j < targetLength + 1; j++) {
			dist[0][j] = j;
		}
		for (int i = 1; i < sourceLength + 1; i++) {
			for (int j = 1; j < targetLength + 1; j++) {
				int cost = source.charAt(i - 1) == target.charAt(j - 1) ? 0 : 1;
				dist[i][j] = Math.min(Math.min(dist[i - 1][j] + 1, dist[i][j - 1] + 1), dist[i - 1][j - 1] + cost);
				if (i > 1 && j > 1 && source.charAt(i - 1) == target.charAt(j - 2)
						&& source.charAt(i - 2) == target.charAt(j - 1)) {
					dist[i][j] = Math.min(dist[i][j], dist[i - 2][j - 2] + cost);
				}
			}
		}
		return dist[sourceLength][targetLength];
	}

}