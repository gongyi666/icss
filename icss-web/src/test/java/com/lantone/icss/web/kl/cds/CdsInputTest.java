package com.lantone.icss.web.kl.cds;

import com.esotericsoftware.kryo.util.ObjectMap;
import com.lantone.core.service.RedisService;
import com.lantone.core.utils.spring.SpringContextUtil;
import freemarker.template.Template;
import org.codehaus.jackson.map.ObjectMapper;
import org.opencds.vmr.v1_0.schema.CDSInput;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by langtong0002 on 2017/3/30.
 */
public class CdsInputTest {
    public static void main(String[] args){
        try {
            BeanFactory factory=new ClassPathXmlApplicationContext("spring-web.xml");
            FreeMarkerConfigurer freeMarkerConfiguration=(FreeMarkerConfigurer)factory.getBean("freemarkerConfig");

            //FreeMarkerConfigurationFactoryBean freeMarkerConfiguration = SpringContextUtil.getBean("freemarkerConfig");
            Template template = freeMarkerConfiguration.getConfiguration().getTemplate("vmrinput.xml");
            Map<String, String> freeMakerMap = new HashMap<>();
            freeMakerMap.put("patientUUID","123");
            freeMakerMap.put("duration","10");
            String vmrInputString = FreeMarkerTemplateUtils.processTemplateIntoString(template, freeMakerMap);
            System.out.println(vmrInputString);
            CDSInput cdsInput = MarshalUtils.unmarshal(new ByteArrayInputStream(vmrInputString.getBytes("UTF-8")), CDSInput.class);
            String object = JsonRedisSeriazilerUtils.seriazileAsString(cdsInput);
            RedisService redisService = (RedisService)factory.getBean("redisService");
            //存值
            redisService.add("1",object);
            //取值
            String returnObject = (String)redisService.get("1");
            CDSInput returnCdsInput = JsonRedisSeriazilerUtils.deserializeAsObject(returnObject,CDSInput.class);
            System.out.println(returnCdsInput.getTemplateId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
