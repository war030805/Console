package Console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public abstract class Console {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    //B
    //background
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    //BO
    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    //U
    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    //HI
    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    //BOHI
    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    //HIB
    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE

    private static HashMap<String,String> COLOR_WITH_CODE;


    public static void init() {
        if (COLOR_WITH_CODE ==null) {
            COLOR_WITH_CODE =getStringStringHashMap();
        }
    }

    private static String replaceWithColors(String input) {
        class StringRememberment {
            private final int begin;
            private final String string;

            public StringRememberment(int begin, String string) {
                this.begin = begin;
                this.string = string;
            }
        }
        init();
        String beginInput=input;
        ArrayList<StringRememberment> rememberments=new ArrayList<>();
//        String[] strings=beginInput.split("\n");
//        String last="";
//        StringBuilder builder=new StringBuilder();
//        for (int i = 0; i < strings.length; i++) {
//            if (i!=0) {
//                builder.append("\n");
//            }
//
//            builder.append(last);
//            last=getLastColor(strings[i]);
//            builder.append(strings[i]);
//        }
//        input=builder.toString();
        int begin=-1;
        int end=-1;
        do {
            begin=input.indexOf("/-");
            if (begin==-1) break;
            end=input.indexOf("-/");
            if (end==-1) break;

//            String string=input.substring(begin+2, end);
//            String replaceWith=getStringColor(string);
//            boolean containsAnny=false;
//            String[] strings=string.split(" ");
//
//            if(!containsAnny){
//                StringRememberment stringRememberment=new StringRememberment(begin, replaceWith);
//                rememberments.add(stringRememberment);
//                input=input.replace(replaceWith,"");
//            }
            String string=input.substring(begin+2, end);
            String replaceWith=getStringColor(string);
            if (COLOR_WITH_CODE.containsKey(string)) {
                input=input.replace(replaceWith, COLOR_WITH_CODE.get(string));
            } else {
                StringRememberment stringRememberment=new StringRememberment(begin, replaceWith);
                rememberments.add(stringRememberment);
                input=input.replace(replaceWith,"");
            }


        } while (true);

        if (!rememberments.isEmpty()) {
            StringBuilder stringBuilder=new StringBuilder(input);
            for (StringRememberment rememberment : rememberments) {
                stringBuilder.insert(rememberment.begin, rememberment.string);
            }
            input=stringBuilder.toString();
        }
        if (!beginInput.equals(input)) {
            input=input.replace("\n",ANSI_RESET+"\n");
        }
        return input;
    }
    private static String getLastColor(String input) {
        int begin=-1;
        int end=-1;
        String last="";
        do {
            begin=input.indexOf("/-");
            if (begin==-1) break;
            end=input.indexOf("-/");
            if (end==-1) break;

            String string=input.substring(begin+2, end);
            String replaceWith=getStringColor(string);
            if (COLOR_WITH_CODE.containsKey(string)) {
                last=replaceWith;
            }
            input=input.replace(replaceWith,"");
        } while (true);
        return last;
    }

    private static HashMap<String, String> getStringStringHashMap() {
        HashMap<String, String> colorWithCode = getStringColorHashMap();
        HashMap<String, String> backColorWithCode = getStringBackColorHashMap();
        HashMap<String, String> newColorWithCode = new HashMap<>(colorWithCode);
        newColorWithCode.putAll(backColorWithCode);
        Set<String> stringSet=colorWithCode.keySet();
        for (String color : stringSet) {
            for(String backColor : backColorWithCode.keySet()) {
                String colorCode=colorWithCode.get(color) + " " +backColorWithCode.get(backColor);
                newColorWithCode.put(color+ " "+  backColor, colorCode);
                newColorWithCode.put(backColor+ " "+  color, colorCode);
            }
        }
        newColorWithCode.put("RESET", ANSI_RESET);
        return newColorWithCode;
    }

    private static HashMap<String, String> getStringColorHashMap() {
        HashMap<String, String> colorWithCode = new HashMap<>();
        colorWithCode.put("BLACK", ANSI_BLACK);
        colorWithCode.put("RED", ANSI_RED);
        colorWithCode.put("GREEN", ANSI_GREEN);
        colorWithCode.put("YELLOW", ANSI_YELLOW);
        colorWithCode.put("BLUE", ANSI_BLUE);
        colorWithCode.put("PURPLE", ANSI_PURPLE);
        colorWithCode.put("CYAN", ANSI_CYAN);
        colorWithCode.put("WHITE", ANSI_WHITE);

        colorWithCode.put("BLACK_BO", BLACK_BOLD);
        colorWithCode.put("RED_BO", RED_BOLD);
        colorWithCode.put("GREEN_BO", GREEN_BOLD);
        colorWithCode.put("YELLOW_BO", YELLOW_BOLD);
        colorWithCode.put("BLUE_BO", BLUE_BOLD);
        colorWithCode.put("PURPLE_BO", PURPLE_BOLD);
        colorWithCode.put("CYAN_BO", CYAN_BOLD);
        colorWithCode.put("WHITE_BO", WHITE_BOLD);

        colorWithCode.put("BLACK_U", BLACK_UNDERLINED);
        colorWithCode.put("RED_U", RED_UNDERLINED);
        colorWithCode.put("GREEN_U", GREEN_UNDERLINED);
        colorWithCode.put("YELLOW_U", YELLOW_UNDERLINED);
        colorWithCode.put("BLUE_U", BLUE_UNDERLINED);
        colorWithCode.put("PURPLE_U", PURPLE_UNDERLINED);
        colorWithCode.put("CYAN_U", CYAN_UNDERLINED);
        colorWithCode.put("WHITE_U", WHITE_UNDERLINED);

        colorWithCode.put("BLACK_HI", BLACK_BRIGHT);
        colorWithCode.put("RED_HI", RED_BRIGHT);
        colorWithCode.put("GREEN_HI", GREEN_BRIGHT);
        colorWithCode.put("YELLOW_HI", YELLOW_BRIGHT);
        colorWithCode.put("BLUE_HI", BLUE_BRIGHT);
        colorWithCode.put("PURPLE_HI", PURPLE_BRIGHT);
        colorWithCode.put("CYAN_HI", CYAN_BRIGHT);
        colorWithCode.put("WHITE_HI", WHITE_BRIGHT);

        colorWithCode.put("BLACK_HIBO",BLACK_BOLD_BRIGHT);
        colorWithCode.put("RED_HIBO",RED_BOLD_BRIGHT);
        colorWithCode.put("GREEN_HIBO",GREEN_BOLD_BRIGHT);
        colorWithCode.put("YELLOW_HIBO",YELLOW_BOLD_BRIGHT);
        colorWithCode.put("BLUE_HIBO",BLUE_BOLD_BRIGHT);
        colorWithCode.put("PURPLE_HIBO",PURPLE_BOLD_BRIGHT);
        colorWithCode.put("CYAN_HIBO",CYAN_BOLD_BRIGHT);
        colorWithCode.put("WHITE_HIBO",WHITE_BOLD_BRIGHT);


        colorWithCode.put("BLACK_BOHI",BLUE_BOLD_BRIGHT);
        colorWithCode.put("RED_BOHI",RED_BOLD_BRIGHT);
        colorWithCode.put("GREEN_BOHI",GREEN_BOLD_BRIGHT);
        colorWithCode.put("YELLOW_BOHI",YELLOW_BOLD_BRIGHT);
        colorWithCode.put("BLUE_BOHI",BLUE_BOLD_BRIGHT);
        colorWithCode.put("PURPLE_BOHI",PURPLE_BOLD_BRIGHT);
        colorWithCode.put("CYAN_BOHI",CYAN_BOLD_BRIGHT);
        colorWithCode.put("WHITE_BOHI",WHITE_BOLD_BRIGHT);

        return colorWithCode;
    }
    private static HashMap<String,String> getStringBackColorHashMap() {
        HashMap<String,String> backGroundStrings=new HashMap<>();
        backGroundStrings.put("BLACK_B", ANSI_BLACK_BACKGROUND);
        backGroundStrings.put("RED_B", ANSI_RED_BACKGROUND);
        backGroundStrings.put("GREEN_B", ANSI_GREEN_BACKGROUND);
        backGroundStrings.put("YELLOW_B", ANSI_YELLOW_BACKGROUND);
        backGroundStrings.put("BLUE_B", ANSI_BLUE_BACKGROUND);
        backGroundStrings.put("PURPLE_B", ANSI_PURPLE_BACKGROUND);
        backGroundStrings.put("CYAN_B", ANSI_CYAN_BACKGROUND);
        backGroundStrings.put("WHITE_B", ANSI_WHITE_BACKGROUND);

        backGroundStrings.put("BLACK_HIB", BLACK_BACKGROUND_BRIGHT);
        backGroundStrings.put("RED_HIB", RED_BACKGROUND_BRIGHT);
        backGroundStrings.put("GREEN_HIB", GREEN_BACKGROUND_BRIGHT);
        backGroundStrings.put("YELLOW_HIB", YELLOW_BACKGROUND_BRIGHT);
        backGroundStrings.put("BLUE_HIB", BLUE_BACKGROUND_BRIGHT);
        backGroundStrings.put("PURPLE_HIB", PURPLE_BACKGROUND_BRIGHT);
        backGroundStrings.put("CYAN_HIB", CYAN_BACKGROUND_BRIGHT);
        backGroundStrings.put("WHITE_HIB", WHITE_BACKGROUND_BRIGHT);
        return backGroundStrings;
    }

    private static String getStringColor(String input) {
        return "/-"+input+"-/";
    }
    public static void println(String s) {
        s=replaceWithColors(s);
        System.out.println(s);
    }
    public static void println() {
        println("");
    }
    public static void print(String s) {
        s=replaceWithColors(s);
        System.out.print(s);
    }
    public static void printf(String format, Object... args) {
        print(String.format(format,args));
    }
}
