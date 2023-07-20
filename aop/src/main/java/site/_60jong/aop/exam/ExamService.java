package site._60jong.aop.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site._60jong.aop.exam.annotation.Retry;
import site._60jong.aop.exam.annotation.Trace;

@RequiredArgsConstructor
@Service
public class ExamService {

    private final ExamRepository examRepository;

    @Retry(4)
    @Trace
    public String request(String itemId) {
        return examRepository.save(itemId);
    }
}
