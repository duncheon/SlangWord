package dictionary;

public class SlangWord {
    private String keyword;
    private String definition;

    public SlangWord() {
        this.keyword = "";
        this.definition = "";
    }

    public SlangWord(String keyword, String definition) {
        this.keyword = keyword;
        this.definition = definition;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getDefinition() {
        return this.definition;
    }

    public void setSlangWord(String keyword, String definition) {
        this.keyword = keyword;
        this.definition = definition;
    }

    public void printSlangWord() {
        System.out.println(this.keyword + ": " + this.definition);
    }
}
