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
            String[] parts = input.split(" ");
            if (parts[0].contains("echo")) {   
              for(String part : parts) {
             //   if(parts[0].equals("echo")){
                  if(!part.contains("echo")) {
                   System.out.print(part + " "); 
                  }
               // }
              }
            }else if(input.contains("type")) {
              String cmd = parts[1];
                if(cmd.equals("type") || cmd.equals("echo") || cmd.equals("exit")) {
                  System.out.print(cmd + " " + "is a shell builtin");
                } else {
                  System.out.print(cmd + ": not found");
                }
            }
             else {
             System.out.print(input+ ": " + "command not found");  
            }
            System.out.print("\n");            
          } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
      }
    }
    scanner.close();
  }
}
