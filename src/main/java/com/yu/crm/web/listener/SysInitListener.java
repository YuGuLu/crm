package com.yu.crm.web.listener;

import com.yu.crm.settings.domain.DicValue;
import com.yu.crm.settings.service.DicService;
import com.yu.crm.settings.service.impl.DicServiceImpl;
import com.yu.crm.utils.ServiceFactory;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.*;

/**
 *
 */
public class SysInitListener implements ServletContextListener {

    /*
        该方法是用来监听上下文域对象的方法，当服务器启动，上下文域对象创建
        ，对象创建完毕后，马上执行该方法

        event：该参数能取得监听的对象，监听的是什么对象，就能通过该参数取得什么对象
                例如我们现在监听的是上下文对象，通过该参数就可以取得上下文域对象
     */

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("服务器缓存处理数据字典开始");

        ServletContext application = event.getServletContext();

        //取数据字典
        DicService ds = (DicService) ServiceFactory.getService(new DicServiceImpl());

        /*
            应该管业务层要
                7个list（对应表中7种类型）

                可以打包成一个map
                业务层应该是这样保存数据的：
                    map.put("appellationList",dvList1);
                    map.put("clueStateList",dvList2);
                    map.put("stageList",dvList3);
                    ....
         */
        Map<String, List<DicValue>> map =  ds.getAll();

        //将map解析为上下文域对象中保存的键值对
        Set<String> set = map.keySet();
        for(String key:set){

            application.setAttribute(key,map.get(key));

        }

        System.out.println("服务器缓存处理数据字典结束");

        //----------------------------------------------------

        //数据字典处理完毕后，处理Stage2Possibility.properties文件
        /*

            处理Stage2Possibility.properties文件步骤：
                解析该文件，将该属性文件中的键值对关系处理成为Java中的键值对关系（map）

                Map<String,String> pMap = ...
                pMap.put();
                ....

                pMap保存值后，放在服务器缓存中
                application.setAttribute("pMap",pMap);

         */

        //解析preperties文件
        Map<String,String> pMap = new HashMap<>();

        ResourceBundle bundle = ResourceBundle.getBundle("Stage2Possibility");

        Enumeration<String> e = bundle.getKeys();

        while(e.hasMoreElements()){
            //阶段
            String key = e.nextElement();
            //可能性
            String value = bundle.getString(key);

            pMap.put(key,value);
        }

        //将pMap保存到服务器缓存中
        application.setAttribute("pMap",pMap);
    }
}























