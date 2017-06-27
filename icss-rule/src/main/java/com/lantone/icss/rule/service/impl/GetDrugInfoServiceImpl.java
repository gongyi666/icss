package com.lantone.icss.rule.service.impl;

import javax.annotation.Resource;

import org.kie.api.command.Command;
import org.kie.internal.command.CommandFactory;
import org.kie.internal.runtime.StatelessKnowledgeSession;
import org.springframework.stereotype.Service;

import com.lantone.icss.rule.model.IcssDrugInput;
import com.lantone.icss.rule.model.IcssDrugOutput;
import com.lantone.icss.rule.model.IcssObjectInput;
import com.lantone.icss.rule.service.GetDrugInfoService;
@Service
public class GetDrugInfoServiceImpl implements GetDrugInfoService{

	@Resource(name = "ksession-drug-rules")
    private StatelessKnowledgeSession statelessKnowledgeSession;
	public void GetDrugInfo(IcssDrugInput input, IcssDrugOutput output) {
		 Command<IcssObjectInput> cmd = CommandFactory.newInsert(input);
	        try {
	        	statelessKnowledgeSession.setGlobal("output", output);
	            statelessKnowledgeSession.execute(cmd);
	            output = (IcssDrugOutput) statelessKnowledgeSession.getGlobals().get("output");
	        } catch (LinkageError error) {
	            System.out.println("" + error.getMessage());
	        } catch (Exception ex) {
	            System.out.println("" + ex);
	        }
		
	}

}
