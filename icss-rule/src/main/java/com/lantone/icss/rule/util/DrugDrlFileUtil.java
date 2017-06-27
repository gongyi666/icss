package com.lantone.icss.rule.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.core.util.StringUtils;
public class DrugDrlFileUtil {
	// private final String moduleName = "lantone-icss-20170323";
	 private final String moduleName = "lantone-icss-daguo";
	    private final String bean_path = "D:/LTHIS/code/dev/icss/icss-rule/src/main/resources/drugs";
	    
	 
	    private final String driverName = "com.mysql.jdbc.Driver";
	 
	    private final String user = "root";
	 
	    private final String password = "langtong";
	 
	    private final String url = "jdbc:mysql://192.168.2.157:3306/" + moduleName + "?characterEncoding=utf8";
	 
	    private Connection conn = null;
	 
	 
	    private void init() throws ClassNotFoundException, SQLException {
	        Class.forName(driverName);
	        conn = DriverManager.getConnection(url, user, password);
	    }
	    
	    private List<DrugRule> findDiagnoseInfos() throws SQLException {
	    	List<DrugRule> drugRuleList = new ArrayList<DrugRule>();
	        PreparedStatement pstate = conn.prepareStatement("select * from kl_drug_rule order by LENGTH(rule) desc ");
	        ResultSet results = pstate.executeQuery();
	        while ( results.next() ) {
	        	DrugRule drugRule = new DrugRule();
	        	drugRule.setStandardCode(results.getString("standard_code"));
	        	drugRule.setRule(results.getString("rule"));
	        	drugRule.setRemark(results.getString("remark"));
	        	drugRule.setName(results.getString("name"));
	        	drugRule.setCode(results.getString("code"));
	        	drugRuleList.add(drugRule);
	        }
	        return drugRuleList;
	    }
	 
	    private void buildRules(List<DrugRule> drugRuleInfo) throws IOException {
	        File folder = new File(bean_path);
	        if ( !folder.exists() ) {
	            folder.mkdir();
	        }
	 
	        File drlFile = new File(bean_path, "drug.drl");
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(drlFile)));
	        bw.write("package com.lantone.icss.rule");
	        bw.newLine();
	        bw.newLine();
	        bw.write("import java.util.List;");
	        bw.newLine();
	        bw.write("import java.util.ArrayList;");
	        bw.newLine();
	        bw.newLine();
	        bw.write("import com.lantone.icss.rule.model.IcssDrugInput;");
	        bw.newLine();
	        bw.write("import com.lantone.icss.rule.model.ChiefComplaint;");
	        bw.newLine();
	        bw.write("import com.lantone.icss.rule.model.LabResult;");
	        bw.newLine();
	        bw.write("import com.lantone.icss.rule.model.OtherHistory;");
	        bw.newLine();
	        bw.write("import com.lantone.icss.rule.model.PastMedicalHistory;");
	        bw.newLine();
	        bw.write("import com.lantone.icss.rule.model.VitalSign;");
	        bw.newLine();
	        bw.write("import com.lantone.icss.rule.util.PhraseUtil;");
	        bw.newLine();
	        bw.write("import com.lantone.icss.rule.model.Diseases;");
	        bw.newLine();
	        bw.write("import java.util.Arrays;");
	        
	        bw.newLine();
	        
	        bw.write("import com.lantone.icss.rule.model.IcssDrugOutput;");
	        bw.newLine();
	        bw.newLine();
	        bw.write("//declare any global variables here");
	        bw.newLine();
	        bw.write("global IcssDrugOutput output;");
	        bw.newLine();

	        for (int i = 0; i < drugRuleInfo.size(); i++) {
	        	DrugRule drugRule = drugRuleInfo.get(i);
	        	if (StringUtils.isEmpty(drugRule.getStandardCode())) {
	        		System.out.println("数据验证未通过：药品StandardCode编码不允许为空！(id:" + drugRule.getId() + ", description:" + drugRule.getStandardCode() + ")");
	        		continue;
	        	}
	        	if (StringUtils.isEmpty(drugRule.getCode())) {
	        		System.out.println("数据验证未通过：药品使用规则公式不允许为空！(drugRule:" + drugRule.getId() + ", description:" + drugRule.getRemark() + ")");
	        		continue;
	        	}
	        	bw.newLine();
	            bw.write("//"+drugRule.getRemark());
	        	bw.newLine();
	        	bw.write("rule \"" + drugRule.getName() + "\"");
	        	bw.newLine();
	        	bw.write("	salience " + (1000+i));
	        	bw.newLine();
	        	bw.write("	no-loop true");
	        	bw.newLine();
	        	bw.write("	when");
	        	bw.newLine();
	        	//bw.write("		System.out.println(\"" + drugRule.getStandardCode() + ": " +  drugRule.getCode() + "\");");
	            //bw.newLine();
	        	bw.write("		input: IcssDrugInput(");
	        	
	        	String[] or_s = drugRule.getRule().split(",");
	        	for (int k = 0; k < or_s.length; k++) {
	        		String[] and_s = or_s[k].split("&&");
	        		for (int j = 0; j < and_s.length; j++) {
	        			formulaToRule(drugRule, bw, and_s[j]);
	        			if (j < and_s.length - 1) {
	        				bw.append(" && ");
	        			}
	        		}
	        		if (k < or_s.length - 1) {
	    				bw.append(" || ");
	    			}
	        	}
	        	bw.append(") ");
	        	bw.newLine();
	        	bw.write("	then");
	        	bw.newLine();
	        	//bw.write("List<String> SList=Arrays.asList(\""+drugRule.getCode()+"\".split(\",\"));");
	        	//bw.newLine();
	        	
	        	bw.write("		output.getDrugList().put(\"" + drugRule.getStandardCode() + "\",(List<String>)Arrays.asList(\""+drugRule.getCode()+"\".split(\",\")));");
	        	bw.newLine();
	        	//bw.write("		output.getDrugList().put(\"" + drugRule.getStandardCode() + "\",\"" + drugRule.getCode() + "\");");
	        	//bw.newLine();
	        	//bw.write("		System.out.println(\"" + drugRule.getStandardCode() + ": output.getDrugList().get(\"" + drugRule.getStandardCode() + "\"));");
	        	bw.write("		System.out.println(\"" + drugRule.getStandardCode() + ": \" + output.getDrugList().get(\"" + drugRule.getStandardCode() + "\").get(0));");

	        	bw.newLine();
	        	bw.write("end");
	        	bw.newLine();
	        }
	       
	        
	     
	        bw.flush();
	        bw.close();
	    }
	    
	    private Map<String, String> symbolMap = new HashMap<String, String>();
	    
	    private BufferedWriter formulaToRule(DrugRule drugRule, BufferedWriter bw, String and_part) throws IOException {
	    	String[] formula = and_part.split("/");
	    	if (formula.length != 1 && formula.length != 4) {
	    		System.out.println("数据验证未通过：诊断依据规则公式有误！(id:" + drugRule.getId() + ", description:" + drugRule.getRemark() + ")");
	    	}
	    	String[] symbols = formula[0].split("_");
	    	if (symbols.length != 2) {
	    		System.out.println("数据验证未通过：诊断依据规则公式有误！(id:" + drugRule.getId() + ", description:" + drugRule.getRemark() + ")");
	    	}
	    	
	    	String[] childs = {};
	    	if (formula.length == 4) {
	    		childs = formula[1].split("_");
	    	}
	    	switch (symbols[0]) {
	    		case "SYMPTOM":
	    			if (formula.length == 1) {
	    				bw.append("input.getChiefComplaint().getComplaints.get(\"" + symbols[1] + "\") != null");
	    			}
	    			if (formula.length == 4) {
	    				if (childs[0].equals("NUMBER")) {
	        				bw.append("input.getChiefComplaint().getComplaints().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().phraseObject2Numerical(input.getChiefComplaint().getComplaints().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) " + symbolMap.get(formula[2]) + formula[3]);
	    				} else {
	    					if (formula[2].equals("In")) {
	    						bw.append("input.getChiefComplaint().getComplaints().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().isInArray(\"" + formula[3] + "\", input.getChiefComplaint().getComplaints().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) == true");
	    					} else {
	    						bw.append("input.getChiefComplaint().getComplaints().get(\"" + symbols[1] + "\") != null && input.getChiefComplaint().getComplaints().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\") " + symbolMap.get(formula[2]) + " \"" + formula[3] + "\"");
	    					}
	        			}
	    			}
	    			break;
	    		case "PAST":
	    			if (symbols[1].equals("NUMBER")) {
	    				bw.append("input.getPhraseUtil().phraseObject2Numerical(input.getChiefComplaint().getComplaints.get(\"" + symbols[2] + "\")) " + symbolMap.get(formula[1]) + " " + formula[2]);
	    			} else {
	    				bw.append("input.getChiefComplaint().getComplaints.get(\"" + symbols[2] + "\") " + symbolMap.get(formula[1]) + " \"" + formula[2] + "\"");
	    			}
	    			break;
	    		case "OTHER":
	    			if (symbols[1].equals("NUMBER")) {
	    				bw.append("input.getPhraseUtil().phraseObject2Numerical(input.getChiefComplaint().getComplaints.get(\"" + symbols[2] + "\")) " + symbolMap.get(formula[1]) + " " + formula[2]);
	    			} else {
	    				bw.append("input.getChiefComplaint().getComplaints.get(\"" + symbols[2] + "\") " + symbolMap.get(formula[1]) + " \"" + formula[2] + "\"");
	    			}
	    			break;
	    		case "VITAL":
	    			if (formula.length == 1) {
	    				bw.append("input.getVitalSign().vitals.get(\"" + symbols[1] + "\") != null");
	    			}
	    			if (formula.length == 4) {
	    				if (childs[0].equals("NUMBER")) {
	        				bw.append("input.getVitalSign().getVitals().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().phraseObject2Numerical(input.getVitalSign().getVitals().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) " + symbolMap.get(formula[2]) + formula[3]);
	    				} else {
	    					if (formula[2].equals("In")) {
	    						bw.append("input.getVitalSign().getVitals().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().isInArray(\"" + formula[3] + "\", input.getVitalSign().getVitals().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) == true");
	    					} else {
	    						bw.append("input.getVitalSign().getVitals().get(\"" + symbols[1] + "\") != null && input.getVitalSign().getVitals().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\") " + symbolMap.get(formula[2]) + " \"" + formula[3] + "\"");
	    					}
	    				}
	    			}
	    			break;
	    		case "LIS":
	    			if (formula.length == 1) {
	    				bw.append("input.getLabResult().labs.get(\"" + symbols[1] + "\") != null");
	    			}
	    			if (formula.length == 4) {
	    				if (childs[0].equals("NUMBER")) {
	        				bw.append("input.getLabResult().getLabs().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().phraseObject2Numerical(input.getLabResult().getLabs().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) " + symbolMap.get(formula[2]) + formula[3]);
	    				} else {
	    					if (formula[2].equals("In")) {
	    						bw.append("input.getLabResult().getLabs().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().isInArray(\"" + formula[3] + "\", input.getLabResult().getLabs().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) == true");
	    					} else {
	    						bw.append("input.getLabResult().getLabs().get(\"" + symbols[1] + "\") != null && input.getLabResult().getLabs().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\") " + symbolMap.get(formula[2]) + " \"" + formula[3] + "\"");
	    					}
	    				}
	    			}
	    			break;
	    		case "PACS":
	    			if (symbols[1].equals("NUMBER")) {
	    				bw.append("input.getPhraseUtil().phraseObject2Numerical(input.getChiefComplaint().getComplaints.get(\"" + symbols[2] + "\")) " + symbolMap.get(formula[1]) + " " + formula[2]);
	    			} else {
	    				bw.append("input.getChiefComplaint().getComplaints.get(\"" + symbols[2] + "\") " + symbolMap.get(formula[1]) + " \"" + formula[2] + "\"");
	    			}
	    			break;
	    		case "DIS":
	    			if (formula.length>0) {
	    				bw.append("input.getDiseases().getDisease.get(\"" + symbols[1] + "\") != null");
	    			}
	    			
	    			break;
	    	}
	    	return bw;
	    }
	 
	 
	    public void generate() throws ClassNotFoundException, SQLException, IOException {
	        init();
	        List<DrugRule> drugRuleInfo = findDiagnoseInfos();
	        
	        symbolMap.put("EqualTo", "==");
	    	symbolMap.put("NotEqualTo", "!=");
	    	symbolMap.put("GreaterThan", ">");
	    	symbolMap.put("GreaterThanOrEqualTo", ">=");
	    	symbolMap.put("LessThan", "<");
	    	symbolMap.put("LessThanOrEqualTo", "<=");
	    	symbolMap.put("In", "contains");
	    	
	    	
	        buildRules(drugRuleInfo);
	        conn.close();
	    }
	 
	    public static void main( String[] args ) {
	        try {
	           new DrugDrlFileUtil().generate();
	        	
	        	/*String a="A001";
	        	List<String>tt=Arrays.asList(a.split(","));	   
	        	System.out.print(tt.get(0));*/
	           List<String>tt=Arrays.asList("D005,D006,(D007,D008)".split(","));
	           System.out.print(tt.get(0));
	        //   Map<String,String> tt=new HashMap<String,String>();
	          // tt.put("aa", "1");
	         //  System.out.println(tt.get("bb"));
	       } catch ( ClassNotFoundException e ) {
	            e.printStackTrace();
	        } catch ( SQLException e ) {
	            e.printStackTrace();
	        } catch ( IOException e ) {
	            e.printStackTrace();
	        }
	    }
}
