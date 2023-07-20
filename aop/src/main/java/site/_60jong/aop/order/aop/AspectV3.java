package site._60jong.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {

    // 클래스 이름이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {} // pointcut signature

    // site._60jong.aop 아래에 있으면서 클래스 이름이 *Service
    @Pointcut("execution(* site._60jong.aop.*..*Service.*(..))")
    private void allServiceInPackage() {}

    @Around("allServiceInPackage()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());

        Object result = joinPoint.proceed();
        return result;
    }

    @Around("allService() && allServiceInPackage()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());

            Object result = joinPoint.proceed();

            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
}
