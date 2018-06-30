package com.bonaparte.service;

import com.bonaparte.entity.Charge;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by yangmingquan on 2018/6/29.
 * 多线程编程及高级功能 guava包，并发工具包
 */
public class MultiThreadService {
    public static final Log logger = LogFactory.getLog(MultiThreadService.class);

        private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
                8,
                1000 * 30,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingQueue(32),
                new ThreadPoolExecutor.CallerRunsPolicy()){
                protected void afterExecute(Runnable r, Throwable t) {
                    super.afterExecute(r,t);
                }
        };

        public static void execute(Runnable command) {
            if (command == null) {
                return;
            }
            threadPoolExecutor.execute(command);
        }

        public static <T> Future<T> submit(Callable<T> task) {
            return threadPoolExecutor.submit(task);
        }

        public void testThreadPoolExcutor(){
            List<Charge> chargeList = new ArrayList<>();
            List<Charge> finalChargeList = chargeList;
            Future<List> future = MultiThreadService.submit(() -> {
                        //添加对任务的处理
                        //统计缴费信息
                        return finalChargeList;
                    }
            );
            try {
                 chargeList = future.get(2, TimeUnit.SECONDS);
            } catch (TimeoutException | ExecutionException ex) {
                // 超时2s, 走openSearch
                logger.info(ex.getMessage());
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        }
}
