package site._60jong.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class TraceAspect {

    @Before("@annotation(site._60jong.aop.exam.annotation.Trace)")
    public void doTrace(JoinPoint joinPoint) {
        log.info("[trace] {} args = {}", joinPoint, joinPoint.getArgs());
    }
}
