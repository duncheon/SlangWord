package dictionary;

import dictionary.SlangWord;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;

public class Dictionary {
    private HashMap<String,String> words;
    public Dictionary() {
        this.words = new HashMap<>();
    }

    public void generateDataFromFile(String filepath) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath + ".txt"));
            
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
            System.out.println(i + ": " + this.words.get(i));
        }
    }
    public void saveWord(SlangWord newWord, String filepath) {
        PrintStream ps;
        try {
            ps = new PrintStream(new FileOutputStream("./" + filepath + ".txt",true));
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

    public void add(SlangWord newWord) {
        if (this.words.put(newWord.getKeyword(), newWord.getDefinition()) == null) {
            saveWord(newWord, "slang-test");
        }
        else {
            // Alert already exist
            System.out.println("Slang Word already there");
        }
    }

    public static void main(String args[]) throws IOException {
        Dictionary mDictionary = new Dictionary();
        mDictionary.generateDataFromFile("slang-test");
        mDictionary.printAll();
        mDictionary.add(new SlangWord("HELOALL","HI"));
    }
}
