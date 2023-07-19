package site._60jong.advanced.practice.proxy.pureproxy.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProxyPatternClient {

    private final Subject subject;

    public void execute() {
        subject.operation();
    }
}
