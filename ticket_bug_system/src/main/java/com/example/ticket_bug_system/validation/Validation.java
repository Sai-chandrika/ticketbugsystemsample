package com.example.ticket_bug_system.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 12-10-2023
 */
public class Validation {
    public static Boolean isValidEmailPattern(String email) {
        if (email != null && !email.isEmpty()) {
            String ePattern = "^(?=.{1,256})(?=.{1,64}@.{1,255}$)[a-zA-Z][a-zA-Z0-9._%+()-]*@[a-zA-Z0-9.()-]+\\.[a-zA-Z]{2,}$";
            Pattern p = Pattern.compile(ePattern);
            Matcher m = p.matcher(email);
            return m.matches();
        } else return null;
    }


    public static Boolean isValidMobileNumber(String mobileNumber) {
        if (mobileNumber != null && !mobileNumber.isEmpty()) {
            String ePattern = ("^(0|9)?[6-9]{1}[0-9]{9}+$");
            Pattern p = Pattern.compile(ePattern);
            Matcher m = p.matcher(mobileNumber);
            return m.matches();
        } else return null;
    }

    public static Boolean isValidPassword(String password) {
//        String ePattern=("^[a-zA-Z0-9'@&#.\\s]{8,15}$");
        String ePattern = ("^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\s).{8,}$");
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static String properTextCase(String stringValue) {
        if (stringValue != null && !stringValue.isEmpty()) {
            String afterTrim = stringValue.trim();
            String[] words = afterTrim.split("\\s+");
            StringBuilder newString = new StringBuilder();
            for (String w : words) {
                String first = w.substring(0, 1);      //First Letter
                String rest = w.substring(1);         //Rest of the letters
                newString.append(first.toUpperCase()).append(rest).append(" ");
            }

            return newString.toString().trim();
        }
        return stringValue;
    }
}
