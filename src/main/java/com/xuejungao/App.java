package com.xuejungao;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        // 字符串 adc def  AB
        System.out.println(reverse("abc def  AB"));
        System.out.println(reverse("a"));
        System.out.println(reverse("ab"));
        System.out.println(reverse("abc"));
        System.out.println(reverse("abc  de"));
        System.out.println(reverse("abc  def  "));
        System.out.println(reverse("abc   abc"));
        System.out.println(reverse(" abc   abc"));
        System.out.println(reverse("It't is a cat ? "));
    }


    // 输入字符串进行翻转
    public static StringBuffer reverse(String s){
        // 通过空格格式
        String words[] = s.split("\\s+");
        // 通过for循环进行新字符串拼接
        String newWord = "";
        for (int i= 0;i <words.length;i++){
            // 获取每一个字符串进行翻转
            String word = words[i];
            String everyWord = "";
            for (int j = word.length()-1;j >=0;j--){
                everyWord += word.charAt(j);
            }
            newWord += everyWord + " ";
        }

        // 通过for循环加空格进入
        StringBuffer sb = new StringBuffer();
        sb.append(newWord);
        for (int i = 0;i< s.length();i++){
            if(s.charAt(i) == ' '){
                if(sb.length() > i && sb.charAt(i) != ' '){
                    sb.insert(i,' ');
                }
                if(sb.length()< i){
                    sb.insert(sb.length(),' ');
                }
            }
        }
        return sb;
    }
}
