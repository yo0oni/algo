import java.util.*;

class Solution {

    static class Music {
        int idx, play;

        public Music(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Music>> musicMap = new HashMap<>();
        Map<String, Integer> genrePlayTotal = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            musicMap.putIfAbsent(genre, new ArrayList<>());
            musicMap.get(genre).add(new Music(i, play));

            genrePlayTotal.put(genre, genrePlayTotal.getOrDefault(genre, 0) + play);
        }

        List<String> sortedGenres = new ArrayList<>(genrePlayTotal.keySet());
        sortedGenres.sort((a, b) -> genrePlayTotal.get(b) - genrePlayTotal.get(a));

        List<Integer> answer = new ArrayList<>();
        for (String genre : sortedGenres) {
            List<Music> musics = musicMap.get(genre);
            musics.sort((a, b) -> {
                if (b.play == a.play) return a.idx - b.idx;
                return b.play - a.play;
            });

            answer.add(musics.get(0).idx);
            if (musics.size() > 1) answer.add(musics.get(1).idx);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
