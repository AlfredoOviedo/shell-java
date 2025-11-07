import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    
    Scanner scanner = new Scanner(System.in);

    while (true)  {
    System.out.print("$ ");
    String input = scanner.nextLine();
    if (input.equalsIgnoreCase("exit 0")) {
      System.out.println("0");
      break;
    }       
      try {
            System.out.println(input+ ": " + "command not found"); 
          } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());

      }
    }
    scanner.close();
  }
}
