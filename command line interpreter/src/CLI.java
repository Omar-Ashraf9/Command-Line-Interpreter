import java.util.*;
import java.io.*;

public class CLI {
	static String result;
	
	public static void checkForTerminal(String cmd, Vector<String> arg, Terminal t ) throws FileNotFoundException {
		if(cmd.equals("pwd")) {
			t.pwd();
		}else if(cmd.equals("cd")) {
			t.cd(arg);
		}else if(cmd.equals("help")) {
			result = t.help(arg);
		}else if(cmd.equals("ls")) {
			result = t.ls();
		}else if(cmd.equals("more")) {
			result = t.more(arg);
		}else if(cmd.equals("mv")) {
			try {
				t.mv(arg.get(0), arg.get(1));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(cmd.equals("cp")) {
			t.cp(arg);
		}else if(cmd.equals("clear")) {
			t.clear();
		}else if(cmd.equals("mkdir")) {
			t.mkdir(arg);
		}else if(cmd.equals("rm")) {
			t.rm(arg);
		}else if(cmd.equals("cat")) {
			result = t.cat(arg);
		}else if(cmd.equals("rmdir")) {
			t.rmdir(arg);
		}else if(cmd.equals("args")) {
			result = t.Args(arg);
		}else if(cmd.equals("date")) {
			t.date();
			
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		
		String userDir = new File(System.getProperty("user.dir")).getAbsolutePath(); 
		String rootDir = userDir.substring(0, userDir.indexOf(File.separator)+1);
		System.setProperty("user.dir",rootDir);
		//////////////////////////////////
		Terminal x = new Terminal();
		x.path = rootDir;
		Parser obj = new Parser();
		
		String cmd;
		Vector<String> arg;
		//////////////////////////////////
		Scanner in = new Scanner(System.in); 
		while(true)
		{
			String input = in.nextLine();
			
			if(input.equals("exit"))
			{
				System.exit(0);
			}
			
		
			String[] temp = input.split("\\|");
			
			for(int i = 0; i < temp.length; i++)
			{
				if(temp[i].contains(">>"))
				{
					String[] m = temp[i].split(">>");
					String to_parse = m[0];
					if(obj.parse(to_parse) == true) {
						checkForTerminal(obj.getCmd(), obj.getArguments(), x);
					}
					
					
					String destination = m[1];
					
					BufferedWriter writer = new BufferedWriter(new FileWriter(destination, true));
				    writer.append('\n');
				    writer.append(result);
				    writer.close();
				    obj.args.clear();
				    result = "";
					continue;
				}
				else if(temp[i].contains(">"))
				{
					String[] m = temp[i].split(">");
					String to_parse = m[0];
					if(obj.parse(to_parse) == true) {
						checkForTerminal(obj.getCmd(), obj.getArguments(), x);
					}
					
					
					String destination = m[1];
					
					BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
				    writer.write(result);
				    writer.close();
				    obj.args.clear();
				    result = "";
					continue;
				}
				
				if(obj.parse(temp[i]) == true) {
					checkForTerminal(obj.getCmd(), obj.getArguments(), x);
				}
				obj.args.clear();
				
			}
			

			
			
		}
		
	}
	
	
		
  
}

