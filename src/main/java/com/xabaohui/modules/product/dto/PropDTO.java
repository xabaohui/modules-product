package com.xabaohui.modules.product.dto;

public class PropDTO {
	private String propName;
	private String propValues;//预设取值范围
	private String propType;//属性类型
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
	public String getPropValues() {
		return propValues;
	}
	public void setPropValues(String propValues) {
		this.propValues = propValues;
	}
	public String getPropType() {
		return propType;
	}
	public void setPropType(String propType) {
		this.propType = propType;
	}
}	
