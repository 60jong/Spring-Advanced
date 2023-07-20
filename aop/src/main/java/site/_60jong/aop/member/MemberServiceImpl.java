package site._60jong.aop.member;

import org.springframework.stereotype.Service;
import site._60jong.aop.member.annotation.ClassAop;
import site._60jong.aop.member.annotation.MethodAop;

@ClassAop
@Service
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("Test Value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
