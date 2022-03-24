package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import dictionary.SlangWord;

public class History {
    private List<String> historyArr;

    public History() {
        this.historyArr = new ArrayList<>();
    }
    public int getSize() {
        return this.historyArr.size();
    }
    // type : by def or by 
    public void saveHistory(List <SlangWord>foundSlang, String historyPath, String keyword, String mode) { // Slang mode or definiton mode
        PrintStream ps;
        try {
            ps = new PrintStream(new FileOutputStream("./data/" + historyPath + ".txt",true));
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();
                String historyLine =  "MODE: " + mode  + "`" + keyword + "`time: " + dtf.format(now) + "`found: " + foundSlang.size() + "\n";
                for (SlangWord i : foundSlang) {
                    historyLine += i.getKeyword() + "`" + i.getDefinition() + "\n";
                }
                ps.print(historyLine);
                historyArr.add(historyLine);
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

    public void loadHistory(String historyPath) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/" + historyPath + ".txt"));
            String historyLine = "";
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                else {
                    String dataArray[] = line.split("\\`",-1);
                    if (dataArray.length != 2) { // in case something when wrong with the data
                        this.historyArr.add(historyLine);
                        historyLine = "";
                        historyLine = line;
                        historyArr.add(historyLine);
                    }
                    else {
                        historyLine += "\n" + line;
                    }
                }
            }
            br.close();
        } catch (FileNotFoundException exc) {
            System.out.println(exc);
        }
    }

    public void printHistory() {
        for (String i : this.historyArr) {
            System.out.println(i);
        }
    }

    public String getShortFormat(String historyLine) {
        String[] arr = historyLine.split("\n");
        return arr[0];
    }
}
