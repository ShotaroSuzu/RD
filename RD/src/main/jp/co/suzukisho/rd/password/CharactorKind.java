package main.jp.co.suzukisho.rd.password;

import java.util.List;

import com.google.common.collect.ImmutableList;

import main.jp.co.suzukisho.rd.password.util.UnspportedCharactorException;

public enum CharactorKind {

    ALPHABET(ImmutableList.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')),
    NUMBERS( ImmutableList.of('1', '2', '3', '4', '5', '6', '7', '8', '9', '0')),
    OTHER_CHARACTORS(ImmutableList.of('!', '_', '-', '.', '?'));

    private List<Character> elements;
    private CharactorKind(List<Character> elements) {
        this.elements = elements;
    }

    public static CharactorKind getKind(Character character) {
        if (ALPHABET.elements.contains(character)) {
            return ALPHABET;
        }
        if(NUMBERS.elements.contains(character)) {
            return CharactorKind.NUMBERS;
        }
        if(OTHER_CHARACTORS.elements.contains(character)) {
            return OTHER_CHARACTORS;
        }
        throw new UnspportedCharactorException("[" + character + "]はサポートされていない文字種別です。");
    }

    public List<Character> getAllCharactor() {
        return elements;
    }
}