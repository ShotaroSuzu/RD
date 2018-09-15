package main.jp.co.suzukisho.rd.password.logic.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import main.jp.co.suzukisho.rd.password.CharactorKind;
import main.jp.co.suzukisho.rd.password.logic.RandomStringMaker;

public class RandomStringMakerImpl implements RandomStringMaker{
    private static final int KIND_OF_STRINGS = 3;

    public String create(int length) {
        //TODO Collectionsのキャッチアップ（ソート）
        //TODO 文字列Streamの扱い　https://qiita.com/kumazo/items/0876c5b251ecc131c960#14charsequencechars--codepoints
        //TODO refactor
        if(length < 3) {
            throw new IllegalArgumentException("引数は３以上を指定してください。");
        }
        List<Integer> distributeNumbers = distributeNumbers(length, KIND_OF_STRINGS);
        
        List<Character> valueOfAlplhabet = createRandomString(distributeNumbers.get(0), CharactorKind.ALPHABET.getAllCharactor());
        List<Character> valueOfNumber = createRandomString(distributeNumbers.get(1), CharactorKind.NUMBERS.getAllCharactor());
        List<Character> valueOfOtherCharactors = createRandomString(distributeNumbers.get(2), CharactorKind.OTHER_CHARACTORS.getAllCharactor());
        
        List<Character> values = Lists.newArrayList(valueOfAlplhabet);
        values.addAll(valueOfNumber);
        values.addAll(valueOfOtherCharactors);
        Collections.shuffle(values);
        return Joiner.on("").join(values);
        }

    private List<Integer> distributeNumbers(int wholeLength, int divideNumber) {
        //擬似的な分布。ランダム関数を正規分布に変換するような方法があるあらしい
        //とりあえず、分割する数を当分する。あまりは最初の数に足す。
        int dividedNumber = wholeLength / divideNumber;
        List<Integer> distributeNumbers = Lists.newArrayList();
        distributeNumbers.add(dividedNumber + wholeLength % divideNumber);
        for (int i = 1; i < divideNumber; i++) {
            distributeNumbers.add(Integer.valueOf(dividedNumber));
        }
        return distributeNumbers;
    }

    private List<Character> createRandomString(int length, List<Character> population) {
        List<Character> elements = Lists.newArrayList(); 
        for(int i = 1; i <=length ; i++) {
            elements.add(population.get(new Random().nextInt(population.size())));
        }
        return elements;
    }

    @Override
    public String createWithout(Collection<Character> excludeCharactors, int length) {
        String passwordValue = create(length);
        if (hasExcludeCharactors(passwordValue, excludeCharactors)) {
            return replaceExcludeCharactors(passwordValue, excludeCharactors);
        }
        return passwordValue;
    }

    private String replaceExcludeCharactors(String passwordValue, Collection<Character> excludeCharactors) {
        StringBuilder replacedPassword = new StringBuilder();
        for (int i = 0; i < passwordValue.length(); i++) {
            if(excludeCharactors.contains(passwordValue.charAt(i))) {
                Character newCharacter = createSameKindValue(passwordValue.charAt(i));
                int count = 0;
                while(excludeCharactors.contains(newCharacter)) {
                    newCharacter = createSameKindValue(newCharacter);
                    if(count++ > 10000) {
                        throw new IllegalArgumentException("使用不可の文字列に同じ種類の文字列すべてが含まれている可能性があります。");
                    }
                }
                replacedPassword.append(newCharacter);
            } else {
                replacedPassword.append(passwordValue.charAt(i));
            }
        }
        return replacedPassword.toString();
    }

    private Character createSameKindValue(Character character) {
        CharactorKind kind = CharactorKind.getKind(character);
        Character appendCharacter = createByKind(kind);
        while (appendCharacter.equals(character)) {
            appendCharacter = createByKind(kind);
        }
        return appendCharacter;
    }

    private char createByKind(CharactorKind kind) {
        return kind.getAllCharactor().get(new Random().nextInt(kind.getAllCharactor().size()));
    }

    private boolean hasExcludeCharactors(String passwordValue, Collection<Character> excludeCharactors) {
        for (Character character : excludeCharactors) {
            if(passwordValue.contains(String.valueOf(character))) {
                return true;
            }
        }
        return false;
    }
}
