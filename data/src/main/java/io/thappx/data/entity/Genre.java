package io.thappx.data.entity;

import android.util.SparseArray;

/**
 * Helper class to convert genre id's to single string
 */
public class Genre {
    private static SparseArray<String> genreMap;

    public static String getGenreString(int[] genreIds) {
        if (genreIds != null && genreIds.length > 0) {
            if (genreMap == null) {
                instantiateHashMap();
            }

            String genreString = "";
            int length = genreIds.length;

            for (int i = 0; i < length - 1; ++i) {
                int genreId = genreIds[i];
                genreString += genreMap.get(genreId) + ", ";
            }

            return genreString + genreMap.get(genreIds[length - 1]);
        }
        return "";
    }

    private static void instantiateHashMap() {
        genreMap = new SparseArray<>(20);

        //Add known genres to the map
        genreMap.put(28, "Action");
        genreMap.put(12, "Adventure");
        genreMap.put(16, "Animation");
        genreMap.put(35, "Comedy");
        genreMap.put(80, "Crime");
        genreMap.put(99, "Documentary");
        genreMap.put(18, "Drama");
        genreMap.put(10751, "Family");
        genreMap.put(14, "Fantasy");
        genreMap.put(10769, "Foreign");
        genreMap.put(36, "History");
        genreMap.put(27, "Horror");
        genreMap.put(10402, "Music");
        genreMap.put(9648, "Mystery");
        genreMap.put(10749, "Romance");
        genreMap.put(878, "Science Fiction");
        genreMap.put(10770, "TV Movie");
        genreMap.put(53, "Thriller");
        genreMap.put(10752, "War");
        genreMap.put(37, "Western");
    }
}
