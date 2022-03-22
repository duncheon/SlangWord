package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dictionary.Dictionary;
import dictionary.SlangWord;

public class Search {
    public static List<SlangWord> searchByDef(String wordsInput, Dictionary mDict) {
        List<SlangWord> foundArr = new ArrayList<>();
        HashMap<String,String> wordsData = mDict.getData();
        String pattern = String.format(".*?%s.*?",wordsInput).toLowerCase();
        for (String i : wordsData.keySet()) {
            if (wordsData.get(i).toLowerCase().matches(pattern)) {
                foundArr.add(new SlangWord(wordsData.get(i),i));
            }
        }
        return foundArr;
    }

    public static SlangWord searchBySlang(String wordsInput, Dictionary mDict) {
        HashMap<String,String> wordsData = mDict.getData();
        String def = wordsData.get(wordsInput);
        return new SlangWord(wordsInput, def);
    }
}
