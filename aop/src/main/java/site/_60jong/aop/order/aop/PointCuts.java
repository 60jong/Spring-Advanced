package site._60jong.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    // 클래스 이름이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {} // pointcut signature

    // site._60jong.aop 아래에 있으면서 클래스 이름이 *Service
    @Pointcut("execution(* site._60jong.aop.*..*Service.*(..))")
    public void allServiceInPackage() {}

    @Pointcut("allService() && allServiceInPackage()")
    public void orderAndService() {}
}
