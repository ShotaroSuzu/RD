package main.jp.co.suzukisho.rd.password.logic;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class RandomStringMaker {

    public String create(int length) {
        //TODO 各文字種の配分を決める→各文字種から割り当てられた文字数を持ってくる→持ってきた文字をくっつける→文字をランダムに並び替える
        //TODO ランダム値の扱いのキャッチアップ
        //TODO Collectionsのキャッチアップ（ソート）
        //TODO 文字列Streamの扱い　https://qiita.com/kumazo/items/0876c5b251ecc131c960#14charsequencechars--codepoints
        //TODO refactor
        List<String> alphabet = ImmutableList.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"); 
        List<String> number = ImmutableList.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        Random random = new Random();//TODO refactor
        int numberOfAlphabet = random.nextInt(length) + 1;
        int numberOfNumber = length - numberOfAlphabet;
        List<String> valueOfAlplhabet = createRandomString(numberOfAlphabet, alphabet);
        List<String> valueOfNumber = createRandomString(numberOfNumber, number);
        List<String> values = Lists.newArrayList();
        values.addAll(valueOfAlplhabet);
        values.addAll(valueOfNumber);
        Collections.shuffle(values);
        System.out.println(values);
        StringBuilder stringValues = new StringBuilder();
        for(String s : values) {
            stringValues.append(s);
        }
        return stringValues.toString();
    }

    private List<String> createRandomString(int length, List<String> population) {
        List<String> elements = Lists.newArrayList(); 
        for(int i = 1; i <=length ; i++) {
            elements.add(population.get(new Random().nextInt(population.size())));
        }
        return elements;
    }

}
