package com.quest.solutions.run.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by arle0814 on 09.03.2015.
 */
public class SpringApplicationContext {

    private static ApplicationContext ctx = null;

    public static ApplicationContext getInstance(){
        if(ctx == null){
            ctx = new ClassPathXmlApplicationContext(
                    "classpath:/scenarioA/SettingContext.xml");
        }
        return ctx;
    }

}
