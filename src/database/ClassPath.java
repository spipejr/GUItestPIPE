package database;

public class ClassPath {
    //check os
    public static String OS = System.getProperty("os.name").toLowerCase();
    public static String ROOTPATH = "";
    public static String usercom = System.getProperty("user.name"); //get username ของเครื่องเรา
    static{
        if(OS.indexOf("linux")!=-1){
            ROOTPATH = "/";
        }else{
            ROOTPATH = "C:/";
        }
        
    }
}
