package com.lantone.icss.rule.service.impl;

import javax.annotation.Resource;

import org.kie.api.command.Command;
import org.kie.internal.command.CommandFactory;
import org.kie.internal.runtime.StatelessKnowledgeSession;
import org.springframework.stereotype.Service;

import com.lantone.icss.rule.model.IcssObjectInput;
import com.lantone.icss.rule.model.IcssObjectOutput;
import com.lantone.icss.rule.service.GetSubitemRuleService;
/**
 * 
* @ClassName: DiagnosisServiceImpl
* @Package com.lantone.icss.rule.service.impl   
* @Description: TODO 
* @author yangguan (Fyeman)
* @date 2017年6月4日 下午11:15:30  
*
 */
@Service
public class GetSubitemRuleServiceImpl implements GetSubitemRuleService {
	@Resource(name = "ksession-subitem-rules")
    private StatelessKnowledgeSession statelessKnowledgeSession;
	
	public void subitemRule(IcssObjectInput input, IcssObjectOutput output) {
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
