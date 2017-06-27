package com.lantone.icss.rule.service.impl;

import javax.annotation.Resource;

import org.kie.api.command.Command;
import org.kie.internal.command.CommandFactory;
import org.kie.internal.runtime.StatelessKnowledgeSession;
import org.springframework.stereotype.Service;

import com.lantone.icss.rule.model.IcssObjectInput;
import com.lantone.icss.rule.model.IcssObjectOutput;
import com.lantone.icss.rule.service.DiagnosisService;
/**
 * 
* @ClassName: DiagnosisServiceImpl
* @Package com.lantone.icss.rule.service.impl   
* @Description: TODO 
* @author 楼辉荣 (Fyeman)
* @date 2017年5月7日 下午11:15:30  
*
 */
@Service
public class DiagnosisServiceImpl implements DiagnosisService {
	@Resource(name = "ksession-rules")
    private StatelessKnowledgeSession statelessKnowledgeSession;
	
	public void diagnosis(IcssObjectInput input, IcssObjectOutput output) {
        Command<IcssObjectInput> cmd = CommandFactory.newInsert(input);
        try {
        	statelessKnowledgeSession.setGlobal("output", output);
            statelessKnowledgeSession.execute(cmd);
            output = (IcssObjectOutput) statelessKnowledgeSession.getGlobals().get("output");
        } catch (LinkageError error) {
            System.out.println("" + error.getMessage());
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
	}
}
