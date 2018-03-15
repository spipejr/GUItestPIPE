package database;

public class ClassPath {
    //check os
    public static String OS = System.getProperty("os.name").toLowerCase();
    public static String ROOTPATH = "";

    static{
        if(OS.indexOf("linux")!=-1){
            ROOTPATH = "/";
        }else{
            ROOTPATH = "C:/";
        }
        
    }
}
