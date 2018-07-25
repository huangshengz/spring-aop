package cn.gzrrg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;


/**
 *  统计方法耗时
 * @author huangsz  2018/7/25 0025
 */
@Service
public class SomeService {

    private Logger log = LoggerFactory.getLogger(SomeService.class);
    private Random random = new Random(System.currentTimeMillis());
    public void testTime(){
        log.info("这个是耗时测试");
        try {
            // 模拟耗时任务
            Thread.sleep(random.nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
