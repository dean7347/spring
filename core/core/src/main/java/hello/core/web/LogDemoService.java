package hello.core.web;

import hello.core.common.myLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {


    private final hello.core.common.myLogger myLogger;
    public void logic(String id) {

        myLogger.log(id);
    }
}
