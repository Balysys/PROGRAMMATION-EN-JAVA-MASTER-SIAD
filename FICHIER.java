import java.io.*; 
class Demo
{
public static void main(String args[])
{
File file1 = new File ("\\junk\\test");
System.out.println("Name: " + file1.getName()); System.out.println("Path: " + file1.getPath()); System.out.println("Absolute Path: "
+ file1.getAbsolutePath()); System.out.println("Parent: " + file1.getParent()); System.out.println("Exists: " + file1.exists()); System.out.println("Write: " + file1.canWrite()); System.out.println("Read: " + file1.canRead()); System.out.println("Directory: " + file1.isDirectory()); System.out.println("File: " + file1.isFile()); System.out.println("Absolute: "+ file1.isAbsolute()); System.out.println("Length: " + file1.length());
}
}