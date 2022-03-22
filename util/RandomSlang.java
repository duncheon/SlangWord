package util;

import java.util.ArrayList;
import java.util.List;

import dictionary.Dictionary;
import dictionary.SlangWord;

public class RandomSlang {
    public static List<SlangWord> randomSlang(Dictionary mDic, List<String> keyList, int total) {
        List<SlangWord> rs = new ArrayList<>();
        for (int i = 0 ; i < total ;i++) {
            int randomIdx = (int) (Math.random() * keyList.size());
            String key = keyList.get(randomIdx);
            rs.add(new SlangWord(key,mDic.getData().get(key)));
        }
        return rs;
    }
}
