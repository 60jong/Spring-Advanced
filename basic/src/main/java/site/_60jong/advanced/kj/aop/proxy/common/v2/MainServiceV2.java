package site._60jong.advanced.kj.aop.proxy.common.v2;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import site._60jong.advanced.kj.aop.proxy.common.v1.MainRepositoryV1;
import site._60jong.advanced.kj.aop.proxy.common.v1.MainServiceV1;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainServiceV2 {

    private MainRepositoryV2 mainRepository;

    public MainServiceV2(MainRepositoryV2 mainRepository) {
        this.mainRepository = mainRepository;
    }

    public long execute(String name) {
        if (name.equals("ex")) {
            throw new IllegalArgumentException("이름 예외 발생");
        }
        return mainRepository.save(name);
    }
}
