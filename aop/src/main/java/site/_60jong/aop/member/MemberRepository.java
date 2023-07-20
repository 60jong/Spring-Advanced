package site._60jong.aop.member;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    public String save(String name) {
        return "ok";
    }
}
