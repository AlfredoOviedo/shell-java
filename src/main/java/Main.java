import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    
    Scanner scanner = new Scanner(System.in);

    while (true)  {
    System.out.print("$ ");
    String input = scanner.nextLine();
    if (input.equalsIgnoreCase("exit 0")) {
      break;
    }       
      try {
            if (input.contains("echo")) {
              String[] parts = input.split(" ");
              
              for(String part : parts) {
                if(!part.contains("echo")){
                  System.out.print(part + " ");
                } 
              }
            } else {
             System.out.println(input+ ": " + "command not found");  
            }
            System.out.print("\n");
          } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
      }
    }
    scanner.close();
  }
}
