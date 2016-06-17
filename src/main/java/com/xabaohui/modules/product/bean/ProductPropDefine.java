package com.xabaohui.modules.product.bean;

import java.sql.Timestamp;

/**
 * ProductPropDefine entity. @author MyEclipse Persistence Tools
 */

public class ProductPropDefine implements java.io.Serializable {

	// Fields

	private Integer propId;
	private String propName;
	private Integer modelId;
	private String status;
	private String propCode;
	private String propType;
	private Integer fieldRequired;
	private String defaultValue;
	private String promptMsg;
	private Integer width;
	private Integer maxLength;
	private String validateCode;
	private String dateType;
	private String options;
	private Integer height;
	private Timestamp gmtCreate;
	private Timestamp gmtModify;
	private Integer version;

	// Constructors

	/** default constructor */
	public ProductPropDefine() {
	}

	/** minimal constructor */
	public ProductPropDefine(String propName, Integer modelId, String status,
			String propType, Timestamp gmtCreate, Timestamp gmtModify,
			Integer version) {
		this.propName = propName;
		this.modelId = modelId;
		this.status = status;
		this.propType = propType;
		this.gmtCreate = gmtCreate;
		this.gmtModify = gmtModify;
		this.version = version;
	}

	/** full constructor */
	public ProductPropDefine(String propName, Integer modelId, String status,
			String propCode, String propType, Integer fieldRequired,
			String defaultValue, String promptMsg, Integer width,
			Integer maxLength, String validateCode, String dateType,
			String options, Integer height, Timestamp gmtCreate,
			Timestamp gmtModify, Integer version) {
		this.propName = propName;
		this.modelId = modelId;
		this.status = status;
		this.propCode = propCode;
		this.propType = propType;
		this.fieldRequired = fieldRequired;
		this.defaultValue = defaultValue;
		this.promptMsg = promptMsg;
		this.width = width;
		this.maxLength = maxLength;
		this.validateCode = validateCode;
		this.dateType = dateType;
		this.options = options;
		this.height = height;
		this.gmtCreate = gmtCreate;
		this.gmtModify = gmtModify;
		this.version = version;
	}

	// Property accessors

	public Integer getPropId() {
		return this.propId;
	}

	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	public String getPropName() {
		return this.propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public Integer getModelId() {
		return this.modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPropCode() {
		return this.propCode;
	}

	public void setPropCode(String propCode) {
		this.propCode = propCode;
	}

	public String getPropType() {
		return this.propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public Integer getFieldRequired() {
		return this.fieldRequired;
	}

	public void setFieldRequired(Integer fieldRequired) {
		this.fieldRequired = fieldRequired;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getPromptMsg() {
		return this.promptMsg;
	}

	public void setPromptMsg(String promptMsg) {
		this.promptMsg = promptMsg;
	}

	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getMaxLength() {
		return this.maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public String getValidateCode() {
		return this.validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getDateType() {
		return this.dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getOptions() {
		return this.options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Timestamp getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Timestamp getGmtModify() {
		return this.gmtModify;
	}

	public void setGmtModify(Timestamp gmtModify) {
		this.gmtModify = gmtModify;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}