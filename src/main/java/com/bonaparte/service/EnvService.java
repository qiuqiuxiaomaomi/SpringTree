package com.bonaparte.service;

import org.springframework.stereotype.Service;

import java.lang.management.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yangmingquan on 2018/6/29.
 */
@Service
public class EnvService {
    public Properties getSystemProperty(){
        //获取当前系统配置
        Properties properties = System.getProperties();
        // <> Java 编译的时候会类型推定
        Map<String, String> map = new HashMap<>();
        map = System.getenv();
        if(properties != null){
            System.out.println("---------------properties-----------");
            System.out.println(properties);
        }
        if(map != null){
            System.out.println("---------------env------------------");
            System.out.println(map);
        }
        return properties;
    }

    public Object getJvmConfig(){
        //获取堆栈信息
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        System.out.println("-------memoryMXBean-------");
        System.out.println(memoryMXBean);
        //
        List<String> map =ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println("-------runtimeMXBean-------");
        System.out.println(map);
        //获取运行时状态
        Runtime runtime = Runtime.getRuntime();
        System.out.println("-------runtime-------");
        System.out.println(runtime);
        // 获取线程情况
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        System.out.println("-------threadMXBean-------");
        System.out.println(threadMXBean.getAllThreadIds());
        System.out.println(threadMXBean.getCurrentThreadCpuTime());
        // 获取加载器信息
        ClassLoadingMXBean classLoadingMXBean =ManagementFactory.getClassLoadingMXBean();
        System.out.println("-------classLoadingMXBean-------");
        System.out.println(classLoadingMXBean.getLoadedClassCount());
        System.out.println(classLoadingMXBean.getUnloadedClassCount());
        // 获取GC回收情况
        List<GarbageCollectorMXBean> garbageCollectorMXBeans =ManagementFactory.getGarbageCollectorMXBeans();
        System.out.println("-------garbageCollectorMXBeans-------");
        garbageCollectorMXBeans.stream().forEach(x->{
            System.out.println(x.getName());
            System.out.println(x.getCollectionCount());
            System.out.println(x.getMemoryPoolNames());
        });
        // 获取线程池信息
        List<MemoryPoolMXBean> memoryPoolMXBeans =ManagementFactory.getMemoryPoolMXBeans();
        System.out.println("-------memoryPoolMXBeans-------");
        memoryPoolMXBeans.stream().forEach(x -> {
            System.out.println(x.getName());
            System.out.println(x.getType());
            System.out.println(x.getUsage());
        });
        System.out.println(memoryPoolMXBeans);
        return memoryMXBean;
    }
}
