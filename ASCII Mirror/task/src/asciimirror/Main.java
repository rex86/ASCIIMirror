package asciimirror;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String fileName;
    static File file;
    static int longestLine;
    static StringBuilder stringBuilder;
    public static void main(String[] args) {
//        String fileName = "Humb.txt";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the file path:");
        String answer = scanner.nextLine();
        fileName = answer;
        file = new File(fileName);
        var fileList = getLinesToListLineByLine(fileName);
        int temp = 0;

        char[][] replacements = {
                {'(', ')'},
                {')', '('},
                {'/', '\\'},
                {'\\', '/'},
                {'<', '>'},
                {'>', '<'},
                {'{', '}'},
                {'}', '{'},
                {'[', ']'},
                {']', '['}
        };
        if (file.exists() && file.isFile()) {
//            fileList.forEach(item -> System.out.println(item));
            longestLine = getTheLongestLineFromList(fileList);
            for (int i = 0; i < fileList.size(); i++) {

                System.out.print(fileList.get(i));
                if (longestLine > fileList.get(i).length()) {
                    moveSpace(longestLine - fileList.get(i).length());
                }
                System.out.print(" | ");
                stringBuilder = new StringBuilder();
                stringBuilder.append(fileList.get(i));

                System.out.print(replaceReversedString(stringBuilder.toString(),replacements));
//                if (longestLine > fileList.get(i).length()) {
//                    moveSpace(longestLine - fileList.get(i).length());
//                }
                System.out.println();

            }

        } else {
            System.out.println("File not found!");
        }
    }

    private static void cowPrint() {
        System.out.println("            ^__^\n" +
                "    _______/(oo)\n" +
                "/\\/(       /(__)\n" +
                "   | w----||    \n" +
                "   ||     ||    ");
    }

    private static List<String> getLinesToListLineByLine(String fileName) {
        List<String> allList = null;
        try {
            allList = Files.readAllLines(Paths.get(fileName));
            int longestLine = getTheLongestLineFromList(allList);

        } catch (IOException e) {
        }
        return allList;

    }

    private static void moveSpace(int longestLine) {
        for (int i = 0; i < longestLine; i++) {
            System.out.print(" ");
        }
    }

    private static int getTheLongestLineFromList(List<String> list) {
        int longestLine = list.get(1).length();

        for (int i = 0; i < list.size(); i++) {
            if (longestLine < list.get(i).length()) {
                longestLine = list.get(i).length();
            }
        }
        return longestLine;

    }

    private static String replaceReversedString(String input, char[][] replacements) {
        StringBuilder line = new StringBuilder(input);

        if (longestLine > input.length()) {

            for(int i = 0;i<longestLine - input.length();i++){
                line.append(" ");
            }
        }

        line.reverse();
        char actualCharInLine;
        String replaceStr;
        boolean hasCharInLine=false;


        int replaceStart=0;
        int replaceEnd=0;
        int testIndex=0;

        for (int i=0;i<line.length();i++) {
            actualCharInLine = line.charAt(i);
            for(int j=0;j<replacements.length;j++){

                if(actualCharInLine == replacements[j][0]){
                    replaceStr = Character.toString(replacements[j][1]);
                    line.replace(i,i+1,replaceStr);
                }
            }
        }
        return line.toString();
    }

    private static List<Integer> getIndexOfReplacements(String input, char findChar) {
        var resultList = new ArrayList<Integer>();
        int index = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == findChar) {
                resultList.add(i);
            }
        }

        return resultList;
    }
}
