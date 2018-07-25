package cn.gzrrg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *  正常的没有被切的业务代码
 * @author huangsz  2018/7/25 0025
 */
@Service
public class NormalService {

    private Logger log = LoggerFactory.getLogger(NormalService.class);

    public void normal(){
        log.info("一切正常");
    }
}
