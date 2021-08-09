import java.util.*;
import java.io.*;

public class Parser 
{
	public static Vector<String> args = new Vector<String>();
	private String cmd;
	
	public boolean parse(String input) throws IOException
	{
		/* Split the string taking space  as a delimiter
		 * Take first part and assign it to the cmd variable
		 * and the other parts will be saved in args vector which is the argument of the command.
		 */
		String[] temp = input.split(" ");
		cmd = temp[0];
		for(int i = 1; i < temp.length; i++)
		{
			args.add(temp[i]);
		}
		/// Start check for commands.
		
		if(cmd.equals("pwd"))
		{
			if(args.size() == 0)
			{
				return true;
			}
			System.out.println("Too many arguments.");
			
		}else if(cmd.equals("cd"))
		{
			if((args.size() == 1 || args.size() == 0)) 
			{
				
				return true;
			}
			System.out.println("Too many arguments.");
			
		}else if(cmd.equals("ls"))
		{
			if(args.size() == 0) 
			{
				return true;
			}
			System.out.println("Too many arguments.");
		}else if(cmd.equals("cp"))
		{
			 if(args.size() != 0) 
	            {
	               
	                return true;
	            }else System.out.println("Too few arguments.");
			
		}else if(cmd.equals("cat"))
		{
			if(args.size() != 0) 
			{
				
				return true;
			}else
			{
				System.out.println("Too few arguments.");
			}
		}else if(cmd.equals("more"))
		{
			if(args.size() == 1) 
			{
				
				return true;
			}else if(args.size() > 1)
			{
				System.out.println("Too many arguments.");
			}else
			{
				System.out.println("Too few arguments.");
			}
		}else if(cmd.equals("mkdir"))
		{
			if(args.size() != 0)
            {
                return true;
            }
            System.out.println("Too few arguments.");
		}else if(cmd.equals("rmdir"))
		{
			if(args.size() != 0)
            {
               
                return true;
            }
            System.out.println("Too few arguments.");
		}else if(cmd.equals("mv"))
		{
			if(args.size() != 0)
			{
				
				return true;
			}
			System.out.println("Too few arguments.");
		}else if(cmd.equals("rm"))
		{
			if(args.size() != 0)
			{
				
				return true;
			}
			System.out.println("Too few arguments.");
		}else if(cmd.equals("date"))
		{
			if(args.size() == 0)
			{
				return true;
			}
			System.out.println("Too many arguments.");
		}else if(cmd.equals("help"))
		{

			if(args.size() == 1 || args.size() == 0)
			{
				
				return true;
			}else
			{
				System.out.println("Too many arguments.");
			}
			
		}else if(cmd.equals("args"))
		{
			if(args.size() == 1)
			{
				
				return true;
			}else if(args.size() > 1)
			{
				System.out.println("Too many arguments.");
			}else
			{
				System.out.println("Too few arguments.");
			}
			
		}else if(cmd.equals("clear"))
		{
			if(args.size() == 0)
			{
				return true;
			}
			System.out.println("Too many arguments.");
		}
		System.out.println("Invalid command.");
		return false;
	}
	public String getCmd()
	{
		return cmd;
	}
	public Vector<String> getArguments()
	{
		return args;
	}
}
