package dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import util.RandomSlang;

public class Dictionary {
    private HashMap<String,String> words;
    private List<String> keys; // for randomize
    public Dictionary() {
        this.words = new HashMap<>();
        this.keys = new ArrayList<>();
    }

    public HashMap<String,String> getData() {
        return this.words;
    }

    public List<String>  getKeys() {
        return this.keys;
    }

    public void generateDataFromFile(String filepath) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/" + filepath + ".txt"));
            
            br.readLine(); // skip first line
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                else {
                    String dataArray[] = line.split("\\`",-1);
                    if (dataArray.length == 2) { // in case something when wrong with the data
                        words.put(dataArray[0], dataArray[1]);
                        keys.add(dataArray[0]);
                    }
                }
            }
            br.close();
        } catch (FileNotFoundException exc) {
            System.out.println(exc);
        }
    }

    public void printAll() {
        for (String i : this.words.keySet()) {
            System.out.println(i + this.words.get(i));
        }
    }

    public void saveWord(SlangWord newWord, String filepath) {
        PrintStream ps;
        try {
            ps = new PrintStream(new FileOutputStream("./data/" + filepath + ".txt",true));
            try {
                String line = newWord.getKeyword() + "`" + newWord.getDefinition();
                ps.println(line);
            }
            catch(Exception exc) {
                System.out.print(exc);
            }
            ps.close();
        }
        catch (FileNotFoundException exc) {
            System.out.println("File not found");
        }
    }
    public void saveSlangData(String path) throws IOException {
        PrintStream ps;
        try {
            ps = new PrintStream(new FileOutputStream("./data/" + path + ".txt"));
            try {
                ps.println("Slag`Meaning");
                for (String i : this.words.keySet()) {
                    String line = i + "`" + this.words.get(i);
                    ps.println(line);
                }
            }
            catch(Exception exc) {
                System.out.print(exc);
            }
            ps.close();
        }
        catch (FileNotFoundException exc) {
            System.out.println("File not found");
        }
    }

    public Boolean add(SlangWord newWord) {
        if (this.words.containsKey(newWord.getKeyword()) == false) {
            saveWord(newWord, "slang-test");
            return true;
        }
        else {
            // Alert already exist
            return false;
        }
    }

    public Boolean keyExist(String key) {
        if (this.words.containsKey(key)) {
            return true;
        }
        return false;
    }
    public void update(String key, SlangWord newVer) throws IOException {
        this.words.remove(key);
        this.words.put(newVer.getKeyword(),newVer.getDefinition());
        saveSlangData("slang");
    }

    public void delete(String key) throws IOException {
        this.words.remove(key);
        saveSlangData("slang");
    }

    public void resetData(String backupPath,String resetPath) throws IOException {
        generateDataFromFile(backupPath);
        saveSlangData(resetPath);
    }
    public static void main(String[] args) throws IOException {
        Dictionary mDictionary = new Dictionary();
        mDictionary.generateDataFromFile("slang");

        List<SlangWord> rs = RandomSlang.randomSlang(mDictionary, mDictionary.getKeys(), 4);

        for (int i = 0 ; i < rs.size();i++) {
            rs.get(i).printSlangWord();
        }

        mDictionary.resetData("slang_default","slang_test");
    }

}
