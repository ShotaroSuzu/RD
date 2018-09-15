package main.jp.co.suzukisho.rd.password;

import java.util.List;

import com.google.common.collect.Lists;

import main.jp.co.suzukisho.rd.password.logic.RandomStringMaker;
import main.jp.co.suzukisho.rd.password.logic.impl.RandomStringMakerImpl;

public class PasswordRandomMakerMain {
    public static void main(String[] args) {
        if (args.length > 0) {
            int passwordLength = Integer.parseInt(args[0]);
            if(args.length > 1) {
                String excludeString = args[1];
                PasswordRandomMakerMain.executeWithExclueChard(passwordLength, excludeString);
                return;
            }
            PasswordRandomMakerMain.execute(passwordLength);
        }
    }

    private static void execute(int length) {
        RandomStringMaker randomStringMaker = new RandomStringMakerImpl();
        System.out.println(randomStringMaker.create(length));
    }

    private static void executeWithExclueChard(int length, String excludeStrings) {
        List<Character> excludeCharacters = Lists.newArrayList();
        for (int i = 0; i < excludeStrings.length(); i++) {
            excludeCharacters.add(excludeStrings.charAt(i));
        }
        RandomStringMaker randomStringMaker = new RandomStringMakerImpl();
        System.out.println(randomStringMaker.createWithout(excludeCharacters, length));
    }
}
