package Test;

public class Test {
    public static void main(String args[]) {
        System.out.println("Separate function test....");
        String test = "Hello`Hi";
        String str_array[] = test.split("\\`",-1);
        for (String i : str_array) {
            System.out.println(i);
        }
    }
}
