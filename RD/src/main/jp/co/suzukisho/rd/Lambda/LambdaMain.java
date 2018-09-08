package main.jp.co.suzukisho.rd.Lambda;

public class LambdaMain {

    /**
     * 以下のURLを参考にした
     * https://qiita.com/sano1202/items/64593e8e981e8d6439d3
     * @param args
     */
    public static void main(String[] args) {
        new LambdaMain().execNotImplement();
        new LambdaMain().execImplement();
        new LambdaMain().execNoName();
        new LambdaMain().execLambda();
        new LambdaMain().execLambdaMethod();
    }

    public void execNotImplement() {
        class Local {
            public void exec() {
                System.out.println("Hello original");
            }
        }
        Local local = new Local();
        local.exec();
    }

    public void execImplement() {
        class Local implements Runnable {
            public void run() {
                System.out.println("Hello implements");
            }
        }
        Local local = new Local();
        local.run();
    }

    public void execNoName() {
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello no name!");
                
            }
        };
        runner.run();
    }

    public void execLambda() {
        Runnable runner = () -> {System.out.println("Hello Lambda!");};
        runner.run();
    }

    public void execLambdaMethod() {
        execMethod(() -> {System.out.println("Hello Lambda method!");});
    }

    private void execMethod(Runnable r) {
        r.run();
    }

}
