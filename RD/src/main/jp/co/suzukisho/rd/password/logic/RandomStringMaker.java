package main.jp.co.suzukisho.rd.password.logic;

import java.util.Collection;

public interface RandomStringMaker {
    public String create(int length);
    public String createWithout(Collection<Character> excludeCharactors, int length);
}
