package Test;
import java.io.IOException;

import util.*;
public class Test {
    public static void main(String[] args) throws IOException {
        History mHistory = new History();
        // mHistory.saveHistory(new SlangWord("HELOAL", "heloal test"), "history");
        // mHistory.saveHistory(new SlangWord("HELOAL1", "heloal test1"), "history");
        // mHistory.saveHistory(new SlangWord("HELOAL2", "heloal test2"), "history");
        mHistory.loadHistory("history");
        mHistory.printHistory();
    }
}
