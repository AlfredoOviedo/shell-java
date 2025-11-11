import java.util.Scanner;
import java.io.File;

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
                  
                   System.out.print("\n");
               // }
              }
            }else if(input.contains("type")) {
              String command_paths = System.getenv("PATH");
              String[] path_command = command_paths.split(":");
              String cmd = parts[1];
                if(cmd.equals("type") || cmd.equals("echo") || cmd.equals("exit")) {
                  System.out.println(cmd + " " + "is a shell builtin");
                  continue;
                }

                boolean cmdExist = false;

                for (int i = 0; i < path_command.length; i++) {
                  File file = new File(path_command[i], cmd);
                  if(file.exists() && file.canExecute()) {
                    System.out.println(cmd + " is " + file.getAbsolutePath());
                    cmdExist = true; continue;
                  }
                }
                if (cmdExist == false) {
                  System.out.print(cmd + ": not found" + "\n");
                  continue;                   
                }
            }
             else {
             System.out.print(input+ ": " + "command not found");  
            }
                     
          } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
      }
    }
    scanner.close();
  }
}
