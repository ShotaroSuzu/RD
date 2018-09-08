package test.jp.co.suzukisho.rd.eratosthenes.logic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.List;
import org.junit.Test;

import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableList;

import main.jp.co.suzukisho.rd.eratosthenes.logic.EratosthenesLogic;
import main.jp.co.suzukisho.rd.eratosthenes.logic.impl.EratosthenesLogicImpl;

public class EratosthenesLogicTest {
    private static final List<Integer> SMALLEST_PRIME_NUMBER_LIST;
    private static final int SMALLEST_PRIME_NUMBER = 2;
    private static final List<Integer> PRIME_NUMEBER_TO_100;
    private static final int ONE_HUNDRED = 100;
    private EratosthenesLogic eratosthenesLogic = new EratosthenesLogicImpl();

    static {
        Builder<Integer> builderForPrimeNumberTo2 = ImmutableList.builder();//Guava のImmutable Listの初期化（Builderによるもの）
        builderForPrimeNumberTo2.add(2);
        SMALLEST_PRIME_NUMBER_LIST = builderForPrimeNumberTo2.build();

        //Guava のImmutable Listの初期化（直接作成）
        PRIME_NUMEBER_TO_100 = ImmutableList.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
    }

    @Test
    public void testCalcPrimeNumbersSmallestPrimeNumber() {
        assertThat(eratosthenesLogic.calcPrimeNumbers(SMALLEST_PRIME_NUMBER)).as("２を引数にとったときのテスト").isEqualTo(SMALLEST_PRIME_NUMBER_LIST);
    }

    @Test
    public void testCalcPrimeNumbers10() {
        assertThat(eratosthenesLogic.calcPrimeNumbers(ONE_HUNDRED)).as("10を引数にとったときのテスト").isEqualTo(PRIME_NUMEBER_TO_100);
    }

    @Test
    public void testCalcPrimeNumbers0() {
        //TODO ラムダ式のキャッチアップ
        assertThatThrownBy(() -> eratosthenesLogic.calcPrimeNumbers(0))
            .as("0を引数にとったときのテスト")
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("0から0までに素数はありません");
    }

    @Test
    public void testCalcPrimeNumbers1() {
        assertThatThrownBy(() -> eratosthenesLogic.calcPrimeNumbers(1))
            .as("1を引数にとったときのテスト")
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("0から1までに素数はありません");
    }

    @Test
    public void testCalcPrimeNumbersNegativeNumber() {
        assertThatThrownBy(() -> eratosthenesLogic.calcPrimeNumbers(-1))
            .as("-1を引数にとったときのテスト")
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("負の数の素数は定義されていません");
    }

}
