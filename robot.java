import java.io.*;  
import java.util.Scanner;  
public class Robot 
{  

public static void main(String[] args) throws Exception  
    {  
        Scanner s = new Scanner(new File("C:\\floorplan.csv"));  
        s.useDelimiter(",");  
        while (s.hasNext()){  
            System.out.print(s.next());  
        }   
        s.close();
    }  
}  
