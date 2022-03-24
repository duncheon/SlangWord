package menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dictionary.Dictionary;
import dictionary.SlangWord;
import util.History;
import util.Search;
public class Menu {
    private Dictionary mDict;
    private Scanner sc;
    private History mHistory;

    public Menu() throws IOException, ClassNotFoundException {
        this.mDict = new Dictionary();
        this.sc = new Scanner(System.in);
        this.mHistory = new History();
    }

    public void launchSetup() throws IOException {
        mDict.generateDataFromFile("slang");
    }

    public void findSlangWordMenu() throws IOException {
        System.out.println("SlangWord dictionary --- find slang word");
        System.out.println("1.Find by slang word");
        System.out.println("2.Find by definition");
        System.out.println("3.Back to main menu");
        System.out.println("4.Exit program");
        runFindSlangWordMenu();
    }
    
    public void runfindBySlang() {
        System.out.println("Input your slang: ");
        String input = sc.nextLine();

        List<SlangWord> list = new ArrayList<>();
        SlangWord rs = Search.searchBySlang(input, mDict);
        list.add(rs);

        if (rs.getDefinition() == null) {
            System.out.println("No result found for this slang word");
        }
        else {
            System.out.println("Your slang word: " + input + ", Definition: " + rs.getDefinition());
        }
        mHistory.saveHistory(list, "history", input, "bySlang");
    }
    
    public void runfindByDef() {
        System.out.println("Input your slang word definition to look for ( can be keyword or short def... )");
        String input = sc.nextLine();
        List<SlangWord> rs = Search.searchByDef(input, mDict);
        int size = rs.size();
        if (size == 0) {
            System.out.println("No result found for this definition");
        }
        else {
            System.out.println("Found total " + size + " slang word(s) match(es) your definition");
            for (SlangWord i : rs) {
                System.out.println("Slang: " + i.getKeyword() + " ,Definiton: " + i.getDefinition());
            }
        }
        mHistory.saveHistory(rs, "history", input, "byDefiniton");
    }

    public void runFindSlangWordMenu() throws IOException {
        System.out.print("Input selection: ");
        int selection = sc.nextInt();
        sc.nextLine();
        System.out.println();
        switch(selection) {
            case 1:
                runfindBySlang();
                break;
            case 2:
                runfindByDef();
                break;
            case 3:
                printMenu();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                break;
        }
        findSlangWordMenu();
    }

    public void historyMenu() throws IOException {
        System.out.println("SlangWord dictionary --- Search history");
        mHistory.printShortHistory();
        System.out.println("\n1.Detail history");
        System.out.println("2.Back to main menu");
        System.out.println("3.Exit");
        runHistoryMenu();
    }

    public void detailedHistory() throws IOException {
        System.out.println("Input history you wish to view detailed");
        int selection = this.sc.nextInt();
        System.out.println();
        System.out.println(this.mHistory.get(selection));
        historyMenu();
    }
    public void runHistoryMenu() throws IOException {
        System.out.print("Input selection: ");
        int selection = sc.nextInt();
        sc.nextLine();
        System.out.println();
        switch(selection) {
            case 1:
                detailedHistory();
                break;
            case 2:
                printMenu();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                break;
        }
    }
    public void printMenu() throws IOException {
        System.out.println("SlangWord dictionary");
        System.out.println("1.Find slang word");
        System.out.println("2.Search/Find history");
        System.out.println("3.Add new slang word");
        System.out.println("4.Edit slang word");
        System.out.println("5.Delete slang word");
        System.out.println("6.Reset default slang word list");
        System.out.println("7.Slang word of the day");
        System.out.println("8.Slang word game");
        System.out.println("9.Exit");
        runMenu();
    }
    public void runMenu() throws IOException {
        System.out.print("Input selection: ");
        int selection = sc.nextInt();
        sc.nextLine();
        System.out.println();
        switch(selection) {
            case 1:
                findSlangWordMenu();
                break;
            case 2:
                if (this.mHistory.getSize() == 0) {
                    mHistory.loadHistory("history");
                }
                historyMenu();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 9:
                System.exit(0);
                break;
            default:
                break;
        }
        printMenu();
    }

}
