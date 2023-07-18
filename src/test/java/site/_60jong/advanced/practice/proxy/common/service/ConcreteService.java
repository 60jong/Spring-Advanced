package site._60jong.advanced.practice.proxy.common.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteService {

    public void save() {
        log.info("concrete save 호출");
    }

    public void find() {
        log.info("concrete find 호출");
    }
}
