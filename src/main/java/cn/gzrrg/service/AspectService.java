package cn.gzrrg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *  这个借口都会被当做join point
 * @author huangsz  2018/7/25 0025
 */
@Service
public class AspectService {

    private Logger log = LoggerFactory.getLogger(AspectService.class);

    public String logMethod(String params){
      log.info("记录的是接收的参数呢：" + params);
      return "返回参数：" + params;
    }

    public void exceptionMethod() throws  Exception {
        log.info("发生了一些异常呢哦！");
        throw new Exception("发生了异常！");
    }
}
