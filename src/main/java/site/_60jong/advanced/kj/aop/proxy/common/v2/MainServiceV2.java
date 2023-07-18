package site._60jong.advanced.kj.aop.proxy.common.v2;

import lombok.RequiredArgsConstructor;
import site._60jong.advanced.kj.aop.proxy.common.v1.MainRepositoryV1;
import site._60jong.advanced.kj.aop.proxy.common.v1.MainServiceV1;

@RequiredArgsConstructor
public class MainServiceV2 {

    private final MainRepositoryV2 mainRepository;

    public long execute(String name) {
        if (name.equals("ex")) {
            throw new IllegalArgumentException("이름 예외 발생");
        }
        return mainRepository.save(name);
    }
}
