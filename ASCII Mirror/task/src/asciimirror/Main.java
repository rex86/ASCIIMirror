package asciimirror;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String fileName;
    static File file;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the file path:");
        String answer = scanner.nextLine();
        fileName = answer;
        file = new File(fileName);
        var fileList = getLinesToListLineByLine(fileName);
        int temp = 0;
//        System.out.println(longestLine);


        if(file.exists() && file.isFile()){
//            fileList.forEach(item -> System.out.println(item));
            int longestLine = getTheLongestLineFromList(fileList);
            for(int i=0;i<fileList.size();i++){

                System.out.print(fileList.get(i));
                if(longestLine > fileList.get(i).length()){
                    moveSpace(longestLine-fileList.get(i).length());
                }
                System.out.print(" | ");
                System.out.print(fileList.get(i));
                if(longestLine > fileList.get(i).length()){
                    moveSpace(longestLine-fileList.get(i).length());
                }
                System.out.println();

            }

        }else{
            System.out.println("File not found!");
        }

    }

        private static void printFile(String fileName)  {
        String s = null;
        try {
            s = Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(s);
    }
    private static void cowPrint(){
        System.out.println("            ^__^\n" +
                "    _______/(oo)\n" +
                "/\\/(       /(__)\n" +
                "   | w----||    \n" +
                "   ||     ||    ");
    }

    private static void humphPrint(){
        System.out.println("   //\n" +
                " _oo\\\n" +
                "(__/ \\  _  _\n" +
                "   \\  \\/ \\/ \\\n" +
                "   (         )\\\n" +
                "    \\_______/  \\\n" +
                "     [[] [[]\n" +
                "     [[] [[]");
    }

    private static List<String> getLinesToListLineByLine(String fileName)  {
        List<String> allList = null;
        try {
            allList = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
//            System.out.println("File not found!");
        }
        return allList;

    }
    private static void moveSpace(int longestLine){
        for(int i=0;i<longestLine;i++){
            System.out.print(" ");
        }
    }

    private static int getTheLongestLineFromList(List<String> list){
        int longestLine = list.get(1).length();

        for(int i = 0 ; i< list.size();i++){
            if(longestLine<list.get(i).length()){
                longestLine = list.get(i).length();
            }
        }
        return longestLine;

    }

}
