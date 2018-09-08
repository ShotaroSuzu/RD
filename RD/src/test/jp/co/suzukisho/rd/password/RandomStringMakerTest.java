package test.jp.co.suzukisho.rd.password;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import main.jp.co.suzukisho.rd.password.logic.RandomStringMaker;

public class RandomStringMakerTest {

    @Test
    public void 文字数3を入れるとその長さのパスワードを生成してくれる() {
        RandomStringMaker randomStringMaker = new RandomStringMaker();
        int actual = randomStringMaker .create(3).length();
        assertThat(actual).as("３を入力すると、文字数が３の文字列が出来上がる").isEqualTo(3);
    }

    @Test
    public void 生成値はランダムである() {//TODO ランダム値生成のテストとしては本当は不適切だが。。。
        RandomStringMaker randomStringMaker = new RandomStringMaker();
        int length = 10;
        String first = randomStringMaker .create(length);
        String second = randomStringMaker.create(length);
        assertThat(first).as("２回連続で実行した場合、同じ文字列が生成される確率は限りなく低い").isNotEqualTo(second);
    }

    @Test
    public void アルファベットを一つ以上含む() {//TODO Javaにおける正規表現の扱いのキャッチアップ（java.util.regex） https://qiita.com/sea_ship/items/7c8811b5cf37d700adc4
        RandomStringMaker randomStringMaker = new RandomStringMaker();
        String actual = randomStringMaker.create(10);
        Pattern pattern = Pattern.compile(".*[a-zA-Z].*");
        Matcher mather = pattern.matcher(actual);
        assertThat(mather.matches()).as("アルファベット(a-z,A-Z)を一つ以上含む").isEqualTo(true);
    }

    @Test
    public void 数字を一つ以上含む() {
        RandomStringMaker randomStringMaker = new RandomStringMaker();
        String actual = randomStringMaker.create(10);
        Pattern pattern = Pattern.compile(".*[0-9].*");
        Matcher mather = pattern.matcher(actual);
        assertThat(mather.matches()).as("数字を一つ以上含む(0-9)を一つ以上含む").isEqualTo(true);
    }
    
}
