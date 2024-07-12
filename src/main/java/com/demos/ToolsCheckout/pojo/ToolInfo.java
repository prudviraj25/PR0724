package com.demos.ToolsCheckout.pojo;

import java.util.HashMap;
import java.util.Map;

import com.demos.ToolsCheckout.exception.InvalidInputToolCode;
import com.demos.ToolsCheckout.pojo.ToolType.Type;

import lombok.Data;

/**
 * Tool
 */
@Data
public class ToolInfo {

	public enum Code {
		CHNS, LADW, JAKD, JAKR;

		static Code get(String codeStr) {
			for (Code code : Code.values()) {
				if (code.name().equalsIgnoreCase(codeStr)) {
					return code;
				}
			}
			return null;
		}

		@Override
		public String toString() {
			return this.name();
		}
	}
	
	static public enum Brand {
		DeWalt, Ridgid, Stihl, Werner;

		@Override
		public String toString() {
			return this.name();
		}
	}

	static private Map<Code, ToolType> codeTypeMap = new HashMap<>();
	static private Map<Code, Brand> codeBrandMap = new HashMap<>();

	static {
		codeTypeMap.put(Code.CHNS, new ToolType(Type.Chainsaw));
		codeBrandMap.put(Code.CHNS, Brand.Stihl);
		
		codeTypeMap.put(Code.LADW, new ToolType(Type.Ladder));
		codeBrandMap.put(Code.LADW, Brand.Werner);
		
		codeTypeMap.put(Code.JAKD, new ToolType(Type.Jackhammer));
		codeBrandMap.put(Code.JAKD, Brand.DeWalt);
		
		codeTypeMap.put(Code.JAKR, new ToolType(Type.Jackhammer));
		codeBrandMap.put(Code.JAKR, Brand.Ridgid);
	}

	private Code code;
	private ToolType type;
	private Brand brand;

	public ToolInfo(Code code) {
		super();
		
		this.setCode(code);
		this.setType(codeTypeMap.get(this.code));
		this.setBrand(codeBrandMap.get(this.code));
	}

	public ToolInfo(String codeStr) throws InvalidInputToolCode {
		super();
		
		Code code = Code.get(codeStr);
		if (code != null) {
			this.setCode(code);
		} else {
			throw new InvalidInputToolCode();
		}
		this.setType(codeTypeMap.get(this.code));
		this.setBrand(codeBrandMap.get(this.code));
	}
	
	public double getDailyCharge() {
		return this.type.getDefaultDailyCharge();
	}

}
