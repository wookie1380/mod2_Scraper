package mod2_Scraper;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";
        Document doc = Jsoup.connect(url).get();
        Element targetDiv = doc.selectFirst("div.chapter");
        String content = targetDiv.text();
        String[] words = content.split("\\s+");
        Map<String, Integer> wordCounts = new TreeMap<>();
        for (String word : words) {
            if (wordCounts.containsKey(word)) {
                int count = wordCounts.get(word) + 1;
                wordCounts.put(word, count);
            } else {
                wordCounts.put(word, 1);
            }
        }
        System.out.println("Word\\tCount");
        wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
    }

}
		


