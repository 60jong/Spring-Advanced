package site._60jong.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import site._60jong.aop.member.MemberService;
import site._60jong.aop.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        log.info("helloMethod = {}", helloMethod);
        // public java.lang.String site._60jong.aop.member.MemberServiceImpl.hello(java.lang.String)
    }

    @Test
    void exactMatch() {
        pointcut.setExpression("execution(public java.lang.String site._60jong.aop.member.MemberServiceImpl.hello(java.lang.String))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatch() {
        pointcut.setExpression("execution(* hello(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatch_pattern() {
        pointcut.setExpression("execution(* hello*(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch() {
        pointcut.setExpression("execution(* site._60jong.aop.member.*.*(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch_하위_포함O() {
        pointcut.setExpression("execution(* site._60jong.aop..*.*(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch_하위_포함X() {
        pointcut.setExpression("execution(* site._60jong.aop.*.*(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void typeMatchSuperClass() {
        pointcut.setExpression("execution(* site._60jong.aop.member.MemberService.hello(..))");

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchSuperClass_not_reverse() {
        pointcut.setExpression("execution(* site._60jong.aop.member.MemberServiceImpl.hello(..))");

        assertThat(pointcut.matches(helloMethod, MemberService.class)).isFalse();
    }

    @Test
    void typeMatch_internal_method() throws NoSuchMethodException {
        pointcut.setExpression("execution(* site._60jong.aop.member.MemberServiceImpl.*(..))");

        Method internalMethod = MemberServiceImpl.class
                .getMethod("internal", String.class);

        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchSuperClass_internal_method() throws NoSuchMethodException {
        pointcut.setExpression("execution(* site._60jong.aop.member.MemberService.*(..))");

        Method internalMethod = MemberServiceImpl.class
                .getMethod("internal", String.class);

        assertThat(pointcut.matches(internalMethod, MemberService.class)).isFalse();
    }
}
