package Misc.ConsoleApplications;

/**
 * @author Jacob Swineford
 */
public class PageAlgorithms {

    public static void main(String[] args) {

    }

    // could be configured to return a list proper indexes
    private static void printPage(int page, int num, int len) {
        if (page < 1) page = 1;
        int from;
        int to;
        if (page * num <= len) {
            from = (page - 1) * num;
            to = page * num;
        } else if (page * num > len && len % num == 0) {
            printPage(len / num, num, len); // works for index, not for printing
            return;
        } else { // more than len
            from = len - (len % num);
            to = len;
        }
        System.out.print("Page " + page + ": ");
        for (int i = from; i < to; i++) {
            System.out.print(i + " ");
        }
    }

    // trims the page numbers off of a string, generally
    // the second part of the URL
    private static String trimPageNumbers(String toTrim) {
        boolean trimmed = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < toTrim.length(); i++) {
            char cur = toTrim.charAt(i);
            boolean isDigit = Character.isDigit(cur);
            if (!(isDigit && !trimmed) && !(!trimmed && cur == '.') && !(!trimmed && cur == '-')) {
                trimmed = true;
                sb.append(cur);
            }
        }
        return sb.toString();
    }

    // gets the new formatted URL string for the next page
    private static String getPageFormattedURL(String url, int toPage) {
        String firstPart = url.substring(0, url.indexOf("page=") + 5);
        String tempSecond = url.substring(url.indexOf("page=")  + 5);
        String secondPart = trimPageNumbers(tempSecond);
        return firstPart + toPage + secondPart;
    }
}
