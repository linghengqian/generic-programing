package sukai.desginpattern.proxypattern.caseII;

/**
 * @description
 *
 * @author chengsukai
 *
 * @create 2022-05-05 13:38
 **/
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}