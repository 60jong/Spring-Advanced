package site._60jong.advanced.kj.aop.proxy.common.v1;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainServiceV1Impl implements MainServiceV1 {

    private final MainRepositoryV1 mainRepository;

    @Override
    public long execute(String name) {
        if (name.equals("ex")) {
            throw new IllegalArgumentException("이름 예외 발생");
        }
        return mainRepository.save(name);
    }
}
