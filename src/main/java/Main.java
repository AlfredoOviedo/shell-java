import java.util.Scanner;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Arrays;

public class Main {

  private static Path currentDir;
  
  public static void main(String[] args) throws Exception {
    currentDir = Paths.get(System.getProperty("user.dir"));
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
                  if(!part.contains("echo")) {
                   System.out.print(part + " "); 
                  }
              }
            System.out.print("\n");
            }else if(input.contains("type")) {
              String command_paths = System.getenv("PATH");
              String[] path_command = command_paths.split(":");
              String cmd = parts[1];
                if(cmd.equals("type") || cmd.equals("echo") || cmd.equals("exit") || cmd.equals("pwd")) {
                  System.out.println(cmd + " " + "is a shell builtin");
                  continue;
                }
 
                boolean cmdExist = false;

                for (int i = 0; i < path_command.length; i++) {
                  File file = new File(path_command[i], cmd);
                  if(file.exists() && file.canExecute()) {
                    System.out.println(cmd + " is " + file.getAbsolutePath());
                    cmdExist = true;
                    continue;
                  }
                }
                if (cmdExist == false) {
                  System.out.print(cmd + ": not found" + "\n");
                  continue;                   
                }
            }else if(parts[0].contains("pwd")) {
              System.out.println(currentDir.toAbsolutePath());
              continue;
            }else if (parts[0].contains("cd")){
              String path = parts[1];
              if(path.startsWith("./")){
                String cleanPath = path.replaceFirst("\\./","");
                System.out.println("CLEAN PATH: " + cleanPath);
                String newPath = cleanPath;
                System.out.println("NEW PATH: " + newPath);
                // TODO: hadle ../../ when there is no more room to go up 
                String curr = currentDir.toString();
                System.out.println("CURR" + curr);
                String[] currlocation = curr.split("/");
                System.out.println("Currlocation: " );
                String cl = currlocation[1];
                System.out.println("CL: "+ cl);    
                Path resolvedPath = currentDir.resolve("/"+cl+"/"+newPath);
                System.out.println("ResolvedPath: "+ resolvedPath);
                System.out.println("CurrentDir: " + currentDir);
                
                if (Files.isDirectory(resolvedPath)) {
                  currentDir = resolvedPath;
                  continue;
                }  
              }else if(path.contains("../../")) {
              Path twoParentsUp = currentDir.getParent().getParent();
              if (Files.isDirectory(twoParentsUp)) {
                currentDir = twoParentsUp;
                continue;
              }
                  
              }              
              
               Path resolvedPath = currentDir.resolve(parts[1]);
               if (Files.isDirectory(resolvedPath)) {
                 currentDir = resolvedPath;
               }else {
                System.out.println("cd: " + path + ": No such file or directory");
                continue;
              }
            }else if(!input.isEmpty()){
              String command_paths = System.getenv("PATH");
              String[] path_command = command_paths.split(":");
              String cmd = parts[0];

              boolean cmdExist = false;

                for (int i = 0; i < path_command.length; i++) {
                  File file = new File(path_command[i], cmd);
                  if(file.exists() && file.canExecute()) {

                    Process process = Runtime.getRuntime().exec(parts);
                    
                    process.getInputStream().transferTo(System.out);
                    
                    cmdExist = true;
                    continue;
                  }

                }
                if (cmdExist == false) {
                  System.out.print(cmd + ": not found" + "\n");
                  continue;
                }
            }
            else {
             System.out.println(input+ ": " + "command not found");  
            }
          } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
      }
    }
    scanner.close();
  }
}
