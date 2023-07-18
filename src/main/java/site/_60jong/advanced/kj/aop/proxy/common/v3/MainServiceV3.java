package site._60jong.advanced.kj.aop.proxy.common.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MainServiceV3 {

    private final MainRepositoryV3 mainRepository;

    public long execute(String name) {
        if (name.equals("ex")) {
            throw new IllegalArgumentException("이름 예외 발생");
        }
        return mainRepository.save(name);
    }
}
