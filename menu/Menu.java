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
    public void menuPause() {
        System.out.println("Press Enter to continue...");
        sc.nextLine();
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
        if (rs.getDefinition() != null) {
            list.add(rs);
        }
        System.out.println();
        if (rs.getDefinition() == null) {
            System.out.println("No result found for this slang word\n");
            menuPause();
        }
        else {
            System.out.println("Your slang word: " + input + ", Definition: " + rs.getDefinition() + "\n");
            menuPause();
        }
        mHistory.saveHistory(list, "history", input, "bySlang");
    }
    
    public void runfindByDef() {
        System.out.println("Input your slang word definition to look for ( can be keyword or short def... )");
        String input = sc.nextLine();
        List<SlangWord> rs = Search.searchByDef(input, mDict);
        int size = rs.size();
        System.out.println();
        if (size == 0) {
            System.out.println("No result found for this definition\n");
            menuPause();
        }
        else {
            System.out.println("Found total " + size + " slang word(s) match(es) your definition");
            for (SlangWord i : rs) {
                System.out.println("Slang: " + i.getKeyword() + " ,Definiton: " + i.getDefinition());
            }
            System.out.println();
            menuPause();
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
        sc.nextLine();
        System.out.println();
        if (selection > mHistory.getSize() - 1 || mHistory.getSize() == 0) {
            System.out.println("Empty history or this record number is invalid" + "\n");
        }
        else {
            System.out.println(this.mHistory.get(selection) + "\n");
        }
        menuPause();
        historyMenu();
    }

    public void addSlangWordMenu() throws IOException {
        System.out.println("SlangWord dictionary --- Add slang word");
        System.out.print("Input slang word you wish to add: ");
        String key = sc.nextLine();
        System.out.print("Input definition for your slang word: ");
        String def = sc.nextLine();
        System.out.println();
        if (this.mDict.add(new SlangWord(key,def))) {
            System.out.println("Added " + key + " successfully\n");
            menuPause();
        }
        else {
            System.out.println("This slang word is already exist\n");
            menuPause();
        }
    }

    public void editSlangWordMenu() throws IOException {
        System.out.println("SlangWord dictionary --- Edit slang word");
        System.out.print("Input slang word you wish to edit: ");
        String key = sc.nextLine();
        if (this.mDict.keyExist(key)) {
            System.out.print("Input new slang keyword for your slang word: ");
            String newKey = sc.nextLine();
            System.out.print("Input new definition for your slang word: ");
            String def = sc.nextLine();
            System.out.println();
            this.mDict.update(key, new SlangWord(newKey,def));
            System.out.println("Updated successfully\n");
            menuPause();
        }
        else {
            System.out.println("The slang word you want to edit does not exist\n");
            menuPause();
        }
    }

    public void deleteSlangWordMenu() throws IOException {
        System.out.println("SlangWord dictionary --- Delete slang word");
        System.out.print("Input slang word you wish to delete: ");
        String key = sc.nextLine();
        if (this.mDict.keyExist(key)) {
            System.out.println("Do you wish to delete slang word: " + key + " , defitnition: " + this.mDict.getData().get(key));
            int selection = 0;
            do {
                System.out.print("Input 1(Yes) or 2(No): ");
                selection = sc.nextInt();
                sc.nextLine();
                System.out.println();
            } while(selection != 1 && selection != 2);
            if (selection == 1) {
                this.mDict.delete(key);
                System.out.println("Removed " + key + " successfully\n");
                menuPause();
            }
            else {
                System.out.println("Aborted deleting the slang word\n");
                menuPause();
            }
        }
        else {
            System.out.println("The slang word you want to delete does not exist\n");
            menuPause();
        }
    }

    public void resetSlangWordMenu() throws IOException {
        System.out.println("SlangWord dictionary --- Reset to default slang word dictionary");
        System.out.println("Are you sure you want to recover default slang word dictionary");
        int selection = 0;
        do {
                System.out.print("Input 1(Yes) or 2(No): ");
                selection = sc.nextInt();
                sc.nextLine();
                System.out.println();
        } while(selection != 1 && selection != 2);
        if (selection == 1) {
            this.mDict.resetData("slang_default", "slang");
            System.out.println("Successfully reset dictionary\n");
            menuPause();
        }
        else {
            System.out.println("Aborted reseting the slang word dictionary\n");
            menuPause();
        }
    }

    public void slangWordOfTheDayMenu() {
        System.out.println("SlangWord dictionary --- Slang word of the day");
        SlangWord rs = mDict.randomSlang(1).get(0);
        System.out.println("Slang: " + rs.getKeyword() + " , definition: " + rs.getDefinition() + "\n");
        menuPause();
    }

    public void slangWordQuizMenu() throws IOException {
        System.out.println("SlangWord dictionary --- Slang word quiz game");
        System.out.println("Choose game mode you want to play: ");
        System.out.println("1. Guess the definiton of the given slang word");
        System.out.println("2. Guess the slangword with given definition");
        System.out.println("3. Back to main menu");
        runSlangWordQuizMenu();
    }

    public void runSlangWordQuizMenu() throws IOException {
        System.out.print("Input selection: ");
        int selection = sc.nextInt();
        sc.nextLine();
        System.out.println();
        switch(selection) {
            case 1:
                slangWordQuizAnswerMenu(1);
                break;
            case 2:
                slangWordQuizAnswerMenu(2);
                break;
            case 3:
                printMenu();
            default:
                break;
        }
        slangWordQuizMenu();
    }
    public void slangWordQuizAnswerMenu(int type) {
        List<SlangWord> rand= this.mDict.randomSlang(4);
        int question =  (int) (Math.random() * 4);

        if (type == 1) {
            System.out.println("Choose the right definiton for the slang word: " + rand.get(question).getKeyword());
        }
        else {
            System.out.println("Choose the right slang word for the definition: " + rand.get(question).getDefinition());
        }
        
        for (int i = 0 ; i < rand.size() ; i++) {
            if (type == 1) {
                System.out.println(i + 1 + ". " + rand.get(i).getDefinition());
            }
            if (type == 2) {
                System.out.println(i + 1 + ". " + rand.get(i).getKeyword());
            }
        }
        SlangWord answer = rand.get(question);
        runSlangWordQuizAnswerMenu(question, answer, type);
    }
    public void runSlangWordQuizAnswerMenu(int answerNum, SlangWord answerSlang, int type) {
        System.out.print("Your answer: ");
        int pAnswer = sc.nextInt();
        sc.nextLine();
        System.out.println();
        if (pAnswer == answerNum + 1) {
            System.out.println((answerNum + 1) + " was the correct answer");
        }
        else {
            System.out.println("The correct answer was " + (answerNum + 1));
        }
        System.out.println("Do you wish to continue play the game (this mode) ?");
        System.out.print("Input 1(Yes) or 2(No): ");
        pAnswer = sc.nextInt();
        sc.nextLine();
        System.out.println();
        switch(pAnswer) {
            case 1:
                slangWordQuizAnswerMenu(type);
                break;
            case 2:
                // auto return to main quiz menu
                break;
            default:
                break;
        }
    }

    public void runSlangWordAnswerSelection(int correctAnswer) {
        System.out.println("Your answer: ");
        int pAnswer = sc.nextInt();
        sc.nextLine();
        if (correctAnswer == pAnswer) {
            System.out.println("Your answer is correct !");
        }
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
                mHistory.loadHistory("history");
                historyMenu();
                break;
            case 3:
                addSlangWordMenu();
                break;
            case 4:
                editSlangWordMenu();
                break;
            case 5:
                deleteSlangWordMenu();
                break;
            case 6:
                resetSlangWordMenu();
                break;
            case 7:
                slangWordOfTheDayMenu();
                break;
            case 8:
                slangWordQuizMenu();
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
