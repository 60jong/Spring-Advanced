package site._60jong.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import site._60jong.aop.member.MemberRepository;
import site._60jong.aop.member.MemberService;
import site._60jong.aop.member.MemberServiceImpl;
import site._60jong.aop.member.annotation.ClassAop;
import site._60jong.aop.member.annotation.MethodAop;

@Slf4j
@Import(ParameterTest.MemberAspect.class)
@SpringBootTest
public class ParameterTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void normalAspect() {
        memberService.hello("name, s");
        memberRepository.save("name, r");
    }

    @Aspect
    static class MemberAspect {
        @Pointcut("execution(* site._60jong.aop.member..*(..))")
        private void allMember() {}

        @Pointcut("within(site._60jong.aop.member.MemberServiceImpl)")
        private void exactMemberServiceImpl() {}

        @Around("exactMemberServiceImpl()")
        public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("target class : {}", joinPoint.getTarget().getClass());

            Object result = joinPoint.proceed();

            log.info("result = {}", result);
            return result;
        }

        @Before("allMember() && args(arg)")
        public void getParam(Object arg) {
            log.info("arg : {}", arg);
        }

        @Before("allMember() && @target(site._60jong.aop.member.annotation.ClassAop)")
        public void isClassAop(JoinPoint joinPoint) {
            log.info("class aop class : {}", joinPoint.getTarget());
        }

        @Before("allMember() && @annotation(site._60jong.aop.member.annotation.MethodAop)")
        public void isMethodAop(JoinPoint joinPoint) {
            log.info("method aop class : {}", joinPoint.getTarget());
        }

        @Before("allMember() && @target(classAop)")
        public void passClassAop(JoinPoint joinPoint, ClassAop classAop) {
            log.info("class aop value : {}", classAop.getClass());
        }

        @Before("allMember() && @annotation(methodAop)")
        public void passMethodAop(JoinPoint joinPoint, MethodAop methodAop) {
            log.info("method aop value : {}", methodAop.value());
        }
    }
}
