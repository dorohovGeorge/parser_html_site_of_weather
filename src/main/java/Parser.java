import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static Document getPage() throws IOException {
        String url = "http://www.pogoda.spb.ru/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    private static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    private static String getDateFromString(String stringData) throws Exception {
        Matcher matcher = pattern.matcher(stringData);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new Exception("Can't extract date from screen");
    }

    private static void printFourValues(Elements values, int index) {
        for (int i = 0; i < 4; i++) {
            Element valueElement = values.get(i);
            for (Element td : valueElement.select("td")) {
                System.out.print(td.text() + "    ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        Document page = getPage();
        // css query language
        Element tableWth = page.select("table[class=wt]").first();
        Elements names = tableWth.select("tr[class=wth");
        Elements values = tableWth.select("tr[valign=top");
        int index = 0;

        for (Element name : names) {
            String dateString = name.select("th[id=dt]").text();
            String date = getDateFromString(dateString);
            System.out.println(date + "     Явления       Температура     Давл    Влажность     Ветер");
            printFourValues(values, index);

            index++;
        }
    }
}
