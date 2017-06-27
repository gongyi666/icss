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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.core.util.StringUtils;
 
public class SubitemDrlFileUtil {
    
    private final String moduleName = "lantone-icss-20170323";
 
    private final String bean_path = "F:/Lantone云诊所平台/code/dev/icss/icss-rule/src/main/resources/subitemrules";
 
    private final String driverName = "com.mysql.jdbc.Driver";
 
    private final String user = "root";
 
    private final String password = "langtong";
 
    private final String url = "jdbc:mysql://192.168.2.157:3306/" + moduleName + "?characterEncoding=utf8";
 
    private Connection conn = null;
 
 
    private void init() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);
    }
    
    private List<DiagnoseInfo> findDiagnoseInfos() throws SQLException {
    	List<DiagnoseInfo> diagnoseInfos = new ArrayList<DiagnoseInfo>();
        PreparedStatement pstate = conn.prepareStatement("select * from kl_subitem_rule");
        ResultSet results = pstate.executeQuery();
        while ( results.next() ) {
        	DiagnoseInfo di = new DiagnoseInfo();
        	di.setCode(results.getString("code"));
        	di.setDescription(results.getString("description"));
        	di.setFormula(results.getString("formula"));
        	diagnoseInfos.add(di);
        }
        return diagnoseInfos;
    }
 
    private void buildRules(List<DiagnoseInfo> diagnoseInfos) throws IOException {
        File folder = new File(bean_path);
        if ( !folder.exists() ) {
            folder.mkdir();
        }
 
        File drlFile = new File(bean_path, "subitemrule.drl");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(drlFile)));
        bw.write("package com.lantone.icss.rule");
        bw.newLine();
        bw.newLine();
        bw.write("import java.util.List;");
        bw.newLine();
        bw.write("import java.util.ArrayList;");
        bw.newLine();
        bw.newLine();
        bw.write("import com.lantone.icss.rule.model.IcssObjectInput;");
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
        bw.newLine();
        
        bw.write("import com.lantone.icss.rule.model.IcssObjectOutput;");
        bw.newLine();
        bw.newLine();
        bw.write("//declare any global variables here");
        bw.newLine();
        bw.write("global IcssObjectOutput output;");
        bw.newLine();

        for (int i = 0; i < diagnoseInfos.size(); i++) {
        	DiagnoseInfo di = diagnoseInfos.get(i);
        	if (StringUtils.isEmpty(di.getCode())) {
        		System.out.println("数据验证未通过：诊断依据编码不允许为空！(id:" + di.getId() + ", description:" + di.getDescription() + ")");
        		continue;
        	}
        	if (StringUtils.isEmpty(di.getFormula())) {
        		System.out.println("数据验证未通过：诊断依据规则公式不允许为空！(id:" + di.getId() + ", description:" + di.getDescription() + ")");
        		continue;
        	}
        	bw.newLine();
            bw.write("//"+di.getDescription());
        	bw.newLine();
        	bw.write("rule \"" + di.getCode() + "\"");
        	bw.newLine();
        	bw.write("	salience " + (i+100));
        	bw.newLine();
        	bw.write("	no-loop true");
        	bw.newLine();
        	bw.write("	when");
        	bw.newLine();
        	bw.write("		input: IcssObjectInput(");
        	
        	String[] or_s = di.getFormula().split(",");
        	for (int k = 0; k < or_s.length; k++) {
        		String[] and_s = or_s[k].split("&&");
        		for (int j = 0; j < and_s.length; j++) {
        			formulaToRule(di, bw, and_s[j]);
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
        	bw.write("		output.getDiagnosisValidateResult().put(\"" + di.getCode() + "\", true);");
        	bw.newLine();
        	bw.write("		System.out.println(\"" + di.getCode() + ": \" + output.getDiagnosisValidateResult().get(\"" + di.getCode() + "\"));");
        	bw.newLine();
        	bw.write("end");
        	bw.newLine();
        }
       
        
     
        bw.flush();
        bw.close();
    }
    
    private Map<String, String> symbolMap = new HashMap<String, String>();
    
    private BufferedWriter formulaToRule(DiagnoseInfo di, BufferedWriter bw, String and_part) throws IOException {
    	String[] formula = and_part.split("/");
    	if (formula.length != 1 && formula.length != 4) {
    		System.out.println("数据验证未通过：诊断依据规则公式有误！(id:" + di.getId() + ", description:" + di.getDescription() + ")");
    	}
    	String[] symbols = formula[0].split("_");
    	if (symbols.length != 2) {
    		System.out.println("数据验证未通过：诊断依据规则公式有误！(id:" + di.getId() + ", description:" + di.getDescription() + ")");
    	}
    	
    	String[] childs = {};
    	if (formula.length == 4) {
    		childs = formula[1].split("_");
    	}
    	switch (symbols[0]) {
    		case "SYMPTOM":
    			if (formula.length == 1) {
    				if(symbols[1].indexOf("^")!=-1){
    					String[] symptoms =  symbols[1].split("\\^");
    					for(String sym : symptoms){
    						if(sym.equals(symptoms[symptoms.length-1])){
    							bw.append("input.getChiefComplaint().getComplaints().get(\"" + sym + "\") != null");
    							break;
    						}
    						bw.append("input.getChiefComplaint().getComplaints().get(\"" + sym + "\") != null || ");
    					}
    					
    				}else{
    				bw.append("input.getChiefComplaint().getComplaints().get(\"" + symbols[1] + "\") != null");
    				}
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
    			if (formula.length == 1) {
    				bw.append("input.getPastMedicalHistory().getPast.get(\"" + symbols[1] + "\") != null");
    			}
    			if (formula.length == 4) {
    				if (childs[0].equals("NUMBER")) {
        				bw.append("input.getPastMedicalHistory().getPast().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().phraseObject2Numerical(input.getPastMedicalHistory().getPast().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) " + symbolMap.get(formula[2]) + formula[3]);
    				} else {
    					if (formula[2].equals("In")) {
    						bw.append("input.getPastMedicalHistory().getPast().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().isInArray(\"" + formula[3] + "\", input.getPastMedicalHistory().getPast().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) == true");
    					} else {
    						bw.append("input.getPastMedicalHistory().getPast().get(\"" + symbols[1] + "\") != null && input.getPastMedicalHistory().getPast().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\") " + symbolMap.get(formula[2]) + " \"" + formula[3] + "\"");
    					}
        			}
    			}
    			break;
    		case "OTHER":
    			if (formula.length == 1) {
    				bw.append("input.getOtherHistory().getOther().get(\"" + symbols[1] + "\") != null");
    			}
    			if (formula.length == 4) {
    				if (childs[0].equals("NUMBER")) {
        				bw.append("input.getOtherHistory().getOther().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().phraseObject2Numerical(input.getOtherHistory().getOther().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) " + symbolMap.get(formula[2]) + formula[3]);
    				} else {
    					if (formula[2].equals("In")) {
    						bw.append("input.getOtherHistory().getOther().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().isInArray(\"" + formula[3] + "\", input.getOtherHistory().getOther().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) == true");
    					} else {
    						bw.append("input.getOtherHistory().getOther().get(\"" + symbols[1] + "\") != null && input.getOtherHistory().getOther().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\") " + symbolMap.get(formula[2]) + " \"" + formula[3] + "\"");
    					}
        			}
    			}
    			break;
    		case "VITAL":
    			if (formula.length == 1) {
    				bw.append("input.getVitalSign().getVitals.get(\"" + symbols[1] + "\") != null");
    			}
    			if (formula.length == 4) {
    				if (childs[0].equals("NUMBER")) {
    					if (formula[2].equals("LessThanOrEqualTo") || formula[2].equals("LessThan"))
    					{
    						bw.append("input.getVitalSign().getVitals().get(\"" + symbols[1] + "\") != null && input.getVitalSign().getVitals().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\") != null && input.getPhraseUtil().phraseObject2Numerical(input.getVitalSign().getVitals().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) " + symbolMap.get(formula[2]) + formula[3]);
    					}else{
    						bw.append("input.getVitalSign().getVitals().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().phraseObject2Numerical(input.getVitalSign().getVitals().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) " + symbolMap.get(formula[2]) + formula[3]);	
    					}
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
    				bw.append("input.getLabResult().getLabs.get(\"" + symbols[1] + "\") != null");
    			}
    			if (formula.length == 4) {
    				if (childs[0].equals("NUMBER")) {
    					if (formula[2].equals("LessThanOrEqualTo") || formula[2].equals("LessThan"))
    					{
    						bw.append("input.getLabResult().getLabs().get(\"" + symbols[1] + "\") != null && input.getLabResult().getLabs().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\") != null && input.getPhraseUtil().phraseObject2Numerical(input.getLabResult().getLabs().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) " + symbolMap.get(formula[2]) + formula[3]);
    					}else{
    						bw.append("input.getLabResult().getLabs().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().phraseObject2Numerical(input.getLabResult().getLabs().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) " + symbolMap.get(formula[2]) + formula[3]);
    					}
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
    			if (formula.length == 1) {
    				bw.append("input.getPacsResult().getPacs.get(\"" + symbols[1] + "\") != null");
    			}
    			if (formula.length == 4) {
    				if (childs[0].equals("NUMBER")) {
        				bw.append("input.getPacsResult().getPacs().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().phraseObject2Numerical(input.getPacsResult().getPacs().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) " + symbolMap.get(formula[2]) + formula[3]);
    				} else {
    					if (formula[2].equals("In")) {
    						bw.append("input.getPacsResult().getPacs().get(\"" + symbols[1] + "\") != null && input.getPhraseUtil().isInArray(\"" + formula[3] + "\", input.getPacsResult().getPacs().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\")) == true");
    					} else {
    						bw.append("input.getPacsResult().getPacs().get(\"" + symbols[1] + "\") != null && input.getPacsResult().getPacs().get(\"" + symbols[1] + "\").get(\"" + childs[1] + "\") " + symbolMap.get(formula[2]) + " \"" + formula[3] + "\"");
    					}
    				}
    			}
    			break;
    	}
    	return bw;
    }
 
 
    public void generate() throws ClassNotFoundException, SQLException, IOException {
        init();
        List<DiagnoseInfo> diagnoseInfos = findDiagnoseInfos();
        
        symbolMap.put("EqualTo", "==");
    	symbolMap.put("NotEqualTo", "!=");
    	symbolMap.put("GreaterThan", ">");
    	symbolMap.put("GreaterThanOrEqualTo", ">=");
    	symbolMap.put("LessThan", "<");
    	symbolMap.put("LessThanOrEqualTo", "<=");
    	symbolMap.put("In", "contains");
    	
    	
        buildRules(diagnoseInfos);
        conn.close();
    }
 
    public static void main( String[] args ) {
        try {
            new SubitemDrlFileUtil().generate();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
