package main.jp.co.suzukisho.rd.eratosthenes;

import java.util.List;

import main.jp.co.suzukisho.rd.eratosthenes.logic.impl.EratosthenesLogicImpl;
import main.jp.co.suzukisho.rd.eratosthenes.printer.SetPrinter;

public class Main {

    public static void main(String[] args) {
        int n = 100;
        exec(n);
    }

    private static void exec(int n) {
        //TODO EratosthenesLogicImplをStreampAPIで実装する
        EratosthenesLogicImpl eratosthenesLogic = new EratosthenesLogicImpl();
        List<Integer> primeNumber = eratosthenesLogic.calcPrimeNumbers(n);
        SetPrinter printer = new SetPrinter();
        printer.print(primeNumber);
    }

}
