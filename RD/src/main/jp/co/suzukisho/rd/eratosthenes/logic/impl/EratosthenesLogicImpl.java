package main.jp.co.suzukisho.rd.eratosthenes.logic.impl;

import java.util.List;
import java.util.stream.IntStream;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import main.jp.co.suzukisho.rd.eratosthenes.logic.EratosthenesLogic;

public class EratosthenesLogicImpl implements EratosthenesLogic{
    Integer SMALLEST_PRIME_NUMBER = 2;
    public List<Integer> calcPrimeNumbers(int n) {
        validate(n);
        List<Integer> naturalNumbers = Ints.asList(IntStream.rangeClosed(0, n).toArray());//TODO guavaのライブラリのキャッチアップ.Streamライブラリ
        List<Integer> candidatePrimeNumbers = naturalNumbers.subList(SMALLEST_PRIME_NUMBER, n+1);
        Integer tempSmallestPrimeNumber = SMALLEST_PRIME_NUMBER;
        return recursiveSieve(n, candidatePrimeNumbers, tempSmallestPrimeNumber);
    }

    private void validate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("負の数の素数は定義されていません");
        }
        if (n >= 0 && n < 2) {
            throw new IllegalArgumentException(String.format("0から%sまでに素数はありません", n));
        }
    }

    private List<Integer> recursiveSieve(Integer largestNumber, List<Integer> primeNumbersCandidate, Integer opening) {
        if(Math.pow(opening.doubleValue(), 2) > largestNumber.doubleValue()) {
            return primeNumbersCandidate;
        }
        List<Integer> seivedPrimeNumbersCandidate = Lists.newArrayList();
        for(Integer element: primeNumbersCandidate) {
            if(element == opening.intValue() || element % opening.intValue() != 0) {
                seivedPrimeNumbersCandidate.add(element);
            }
        }
        Integer nextOpening = seivedPrimeNumbersCandidate.get(seivedPrimeNumbersCandidate.indexOf(opening) + 1);
        return recursiveSieve(largestNumber, seivedPrimeNumbersCandidate, nextOpening);
    }
}
