import java.util.*;
import java.util.logging.FileHandler;
import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.FileSystem;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.*;


public class Terminal 
{
	//public String result;
	public static String path = ""; /// keep track of current directory.
	
	public void cp(Vector<String> args)
    {

        if(args.get(args.size()-1).charAt(1)!=':') {

            args.set(args.size()-1,path+ args.get(args.size()-1));

        }
        if(args.get(args.size()-1).charAt(args.get(args.size()-1).length()-1)!='\\') {
            args.set(args.size()-1,args.get(args.size()-1)+'\\');
        }

        for(int i=0;i<args.size()-1;i++) {
            if(args.get(i).charAt(1)!=':') {
                args.set(i,path+ args.get(i));
            }
            File f = new File(args.get(i));
            Path sourceDirectory = Paths.get(args.get(i));
            Path targetDirectory = Paths.get(args.get(args.size()-1)+f.getName());

            try {
                Files.copy(sourceDirectory, targetDirectory);
                System.out.println("Copy Succesful");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

}
	
	public void mv(String sourcePath, String destinationPath) throws IOException
	{
		File src = new File(sourcePath);
		File des = new File(destinationPath);

		
			if(src.isFile() || src.isDirectory())
			{
				if(!des.isDirectory())
				{
					/// if destination file does not exist , make a new file with name of distpath and put src file inside it.
					Files.move(src.toPath(), des.toPath(), StandardCopyOption.REPLACE_EXISTING);
				}else
				{
					/// if destination path already exist , put the content of src file inside dest file.
					src.renameTo(new File(System.getProperty("user.dir") + destinationPath + "\\" + src.getName()));
				}
			}
	}
	
	
	public String more(Vector<String> args) throws FileNotFoundException
	{
		String result = "";
		String testPath = path;
        String CurrentCat = args.get(0);
        String newFilename = testPath + CurrentCat;
		File filename = new File(newFilename);
		Scanner sc = new Scanner(filename); 
		if(filename.canRead())
		{
			while (sc.hasNextLine()) 
			 {
				 result += sc.nextLine() + "\n";
				  
			 }  
			System.out.println(result);
			sc.close();
		}
		return result;
	}
	public void rm(Vector<String> args)
    {

        for(int i = 0 ; i < args.size() ; i++) {
            String testPath = path;
            String CurrentDelete = args.get(i);
            String newFilename = testPath + CurrentDelete;
            File testFile = new File(newFilename);


            if(testFile.isFile() && testFile.exists()) {
                testFile.delete();
                System.out.println(newFilename + " has been deleted");
            }else if(testFile.isDirectory()){
                System.out.println("cant use rm to delete directory");
            }else {
                System.out.println("Error : invalid path");
            }

        }
    }
	public void pwd()
	{
		System.out.println(path);
		
	}
	
	public String cat(Vector<String> args)
    {
		String result = "";
        for(int i = 0 ; i < args.size() ; i++) {
            String testPath = path;
            String CurrentCat = args.get(i);
            String newFilename = testPath + CurrentCat;
            File catFile = new File(newFilename);

            if(catFile.isFile() && catFile.exists()) {
                try {
                    Scanner sc = new Scanner(catFile);
                    while (sc.hasNextLine()) 
                     {
                    	result += sc.nextLine() + "\n";
                          
                     }
                    System.out.println(result); 
                     sc.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
            }else {
                System.out.println("Error : invalid path");
            }
        }
        return result;
    }
	
	public void date()
	{
		Date date = new Date();

	   /// display time and date using toString().
	   System.out.println(date.toString());
	}
	
	public void cd(Vector<String> args)
	{
		/* to read absolute path  
		 * check if second character is :
		 * if true check if this file exist
		 * if true assign the directory to path variable.
		 * if not print error message
		 */
		String temp = args.get(0);
        if(temp.charAt(1) == ':')
        {
            /// absolute path
            File tmpDir = new File(temp);
            boolean exists = tmpDir.exists();
            if(exists)
            {
                path = temp;

            }
            else 
            {
                System.out.println("Error: Invalid path");
            }
            
        }else
        {
        	/* check if the last symbol is \
			 * if true - concatenate the temp to path and add \ at the end.
			 * if false - add \ before the desired directory and then concatenate temp and add \ at the end 
			 */
            temp = path + temp + "\\";
            File tmpDir = new File(temp);
            boolean exists = tmpDir.exists();
            if(exists)
            {
                path = temp;

            }else
            {
                System.out.println("Error: Invalid path");

            }


        }
        if(path.charAt(path.length() - 1) != '\\')
        {
            path+="\\";
        }
        pwd();
	}
	public String help(Vector<String> args) throws FileNotFoundException
	{
		 String result="";
		 if(args.isEmpty())
		 {
			 File file = new File("G:\\College\\java\\A1_CS_20170345_20170352_20170363\\help.txt"); 
			 Scanner sc = new Scanner(file); 
				  
			 while (sc.hasNextLine()) 
			 {
				 result += sc.nextLine() + "\n";
				 
			 }     
			 sc.close();
			 
		 }else
		 {
			 String m = args.get(0);
			 if(m.equals("cd"))
			 {
				 result = "changes the current directory.                                          |cd [directory]";
			 }else if(m.equals("cp"))
			 {
				 result = "copies one or more files to another location.                           |help|cp Src_file Dest_file";
			 }else if(m.equals("mv"))
			 {
				 result = "moves one or more files from one directory to another.                  |mv Src Dest\r\n"; 
			
			 }else if(m.equals("rm"))
			 {
				 result = "removes each file specified on the command line.	                       |rm file1,file2..";
			 }else if(m.equals("pwd"))
			 {
				 result = "prints the name of the working directory.		                       |No Argument.";
			 }else if(m.equals("cat"))
			 {
				 result = "reads data from files, and outputs their contents.	                   |cat file1,file2..";
			 }else if(m.equals("date"))
			 {
				 result = "display the system date and time.                                       |No Argument.";
			 }else if(m.equals("help"))
			 {
				 result = "produce a list of commands and their usage.                             |help [command]";
			 }else if(m.equals("ls"))
			 {
				 result = "list all files that existing in the current directory.                  |No Argument.";
			 }else if(m.equals("more"))
			 {
				 result = "displays additional information one page at a time.                     |more [filename].";
			 }else if(m.equals("mkdir"))
			 {
				 result = "creates new directories in a file system.                               |mkdir [directories].";
			 }else if(m.equals("clear"))
			 {
				 result = "clear the screen and all commands.                                      |No Argument";
			 }else
			 {
				 result = "remove empty directories.      		                                   |redir [filename].";
			 }
		 }
		 System.out.println(result);
		 return result;
		 
	
	}
	public String Args(Vector<String> args){
        String input = args.get(0);
        String result = "";
        if(input.equals("cd") ) {
            result = "cd [directory]";

        }else if(input.equals("ls")){
            result = "no arguments";

        }else if(input.equals("cp")) {
            result = "cp [source] [distination]";

        }else if(input.equals("cat")) {
            result = "cat [file1] [file2] .. ";

        }else if(input.equals("more")) {
            result = "more [filename]";

        }else if(input.equals("mkdir")){
            result = "mkdir [directory1] [directory2] ..";

        }else if(input.equals("rmdir")) {
            result = "rmdir [directory1] [directory2] ..";

        }else if(input.equals("mv")) {
            result = "mv [source] [distination] ..";

        }else if(input.equals("rm")) {
            result = "rm [file1] [file2] ..";

        }else if(input.equals("date")) {
            result = "no arguments";

        }else if(input.equals("help")) {
            result = "help [command]";

        }else if(input.equals("pwd")) {
            result = "no arguments";

        }else if(input.equals("clear")) {
            result = "no arguments";

        }else {
            result = "wrong argument";
        }
        System.out.println(result);
        return result;
    }
	public String ls()
	{
		String result = "";
		File dir = new File(path);
        String[] files = dir.list();
        for(int i = 0; i < files.length; i++)
        {
        	result += files[i];
        	System.out.println(files[i]);
        }
        return result;
        
	}
	public void mkdir(Vector<String> args)
    {
        String temp;

        for(int i=0;i<args.size();i++) {
            if(args.get(i).charAt(1)==':') {
                if (args.get(i).charAt(args.size()-1)!='\\') {
                    args.set(i, args.get(i)+"\\");
                }
                File f = new File(args.get(i)); 
                f.mkdir();
                System.out.println("Directory is created");

            }
            else {
                temp=path + args.get(i);
                File f = new File(temp); 
                if (f.mkdir()) { 
                    System.out.println("Directory is created"); 
                } 
                else {
                    System.out.println("Directory cannot be created"); 
                }
            }
        }
        pwd();
      }
	
	public void rmdir(Vector<String> args)
    {

        String temp;
        for(int i=0;i<args.size();i++) {
            if(args.get(i).charAt(1)==':') {
                if (args.get(i).charAt(args.size()-1)!='\\') {
                    args.set(i, args.get(i)+"\\");
                }
                File f = new File(args.get(i)); 
                delete(f);

            }
            else {
                temp=path + args.get(i);

                File f = new File(temp); 
                if(!f.exists()){

                   System.out.println("Directory does not exist.");

                }else{

                   delete(f);
                }

                System.out.println("Directory Deleted");

            }
        }
        pwd();
      }
	
	
	
	public static void delete (File f) {
        if(f.isDirectory()){

            //directory is empty, then delete it
            if(f.list().length==0){

               f.delete();
               System.out.println("Directory is deleted : ");

            }else{

               //list all the directory contents
               String files[] = f.list();

               for (String temp : files) {
                  //construct the file structure
                  File tempdelete = new File(f,temp);

                  //recursive delete
                 delete(tempdelete);
               }

               //check the directory again, if empty then delete it
               if(f.list().length==0){
                    f.delete();
                 System.out.println("Directory is deleted : ");
               }
            }

        }
        else{
            //if file, then delete it

            System.out.println("File is deleted : ");
        }
    }
	
	public void clear()
	{
		for(int i = 0; i < 10000; i++) {

            System.out.println();

    }
	}
	
	
}
