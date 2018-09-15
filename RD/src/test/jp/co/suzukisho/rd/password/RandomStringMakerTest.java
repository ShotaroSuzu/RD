package test.jp.co.suzukisho.rd.password;


import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.google.common.collect.Lists;

import main.jp.co.suzukisho.rd.password.CharactorKind;
import main.jp.co.suzukisho.rd.password.logic.impl.RandomStringMakerImpl;

public class RandomStringMakerTest {
    private RandomStringMakerImpl randomStringMaker = new RandomStringMakerImpl();
    private int PASSWORD_LENGTH = 100;

    @Test
    public void 文字数3を入れるとその長さのパスワードを生成してくれる() {
        int actual = randomStringMaker .create(3).length();
        assertThat(actual).as("３を入力すると、文字数が３の文字列が出来上がる").isEqualTo(3);
    }

    @Test
    public void 引数の文字数が3文字より小さいときはIllegalArgumentExceptionを返す() {
        assertThatThrownBy(() -> randomStringMaker.create(2))
            .as("引数が２のときにIllegalArgumentExceptionを吐く")
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("引数は３以上を指定してください。");
    }
    @Test
    public void 生成値はランダムである() {//TODO ランダム値生成のテストとしては本当は不適切だが。。。
        int length = 10;
        String first = randomStringMaker .create(length);
        String second = randomStringMaker.create(length);
        assertThat(first).as("２回連続で実行した場合、同じ文字列が生成される確率は限りなく低い").isNotEqualTo(second);
    }

    @Test
    public void アルファベットを一つ以上含む() {//TODO Javaにおける正規表現の扱いのキャッチアップ（java.util.regex） https://qiita.com/sea_ship/items/7c8811b5cf37d700adc4
        Matcher mather = createMatcherOfRandamStringMaker(PASSWORD_LENGTH, ".*[a-zA-Z].*");
        assertThat(mather.matches()).as("アルファベット(a-z,A-Z)を一つ以上含む").isEqualTo(true);
    }

    @Test
    public void 数字を一つ以上含む() {
        Matcher mather = createMatcherOfRandamStringMaker(PASSWORD_LENGTH, ".*[0-9].*");
        assertThat(mather.matches()).as("数字を一つ以上含む(0-9)を一つ以上含む").isEqualTo(true);
    }

    @Test
    public void その他の文字を一つ以上含む() {
        Matcher mather = createMatcherOfRandamStringMaker(PASSWORD_LENGTH, ".*[!_\\-\\.\\?].*");
        assertThat(mather.matches()).as("「!_-.?」のいずれかの文字を一つ以上含む").isEqualTo(true);
    }

    private Matcher createMatcherOfRandamStringMaker(int length, String patternString) {
        String actual = randomStringMaker.create(length);
        Pattern pattern = Pattern.compile(patternString);
        Matcher mather = pattern.matcher(actual);
        return mather;
    }

    @Test
    public void 使わない文字列を渡すとその文字を含まない文字列を生成() {
        List<Character> excludeCharactors = Lists.newArrayList('a', '_', 'b', '3', '?');
        String actual = randomStringMaker.createWithout(excludeCharactors, PASSWORD_LENGTH);
        assertThat(hasExcludeCharactors(actual, excludeCharactors))
        .as(excludeCharactors.toString() + "の文字を含まない")
        .isEqualTo(false);
    }

    @Test
    public void 含まない文字が一つの文字種すべてを含んでいた場合はIllegalArgumentExceptionを吐く() {
        List<Character> excludeCharacters = CharactorKind.ALPHABET.getAllCharactor();
        assertThatThrownBy(() -> randomStringMaker.createWithout(excludeCharacters, PASSWORD_LENGTH))
        .as("含まない文字が一つの文字種すべてを含んでいた場合はIllegalArgumentExceptionを吐く")
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("使用不可の文字列に同じ種類の文字列すべてが含まれている可能性があります。");
        
    }

    @Test
    public void 含まない文字で本機能のサポート外の文字を指定してもExceptionを返さない() {
        List<Character> excludeCharacters = Lists.newArrayList('%', '_', '-', '!', ')', '(');
        String actual = randomStringMaker.createWithout(excludeCharacters, PASSWORD_LENGTH);
        assertThat(actual)
        .as("含まない文字で本機能のサポート外の文字を指定してもExceptionを返さない")
        .doesNotContain(excludeCharacters.get(0).toString());
    }
    
    private boolean hasExcludeCharactors(String actual, List<Character> excludeCharactors) {
        for (Character charactor : excludeCharactors) {
            if (actual.contains(String.valueOf(charactor))) {
                return true;
            }
        }
        return false;
    }
}
