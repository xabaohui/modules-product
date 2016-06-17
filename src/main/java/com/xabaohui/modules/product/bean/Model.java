package com.xabaohui.modules.product.bean;

import java.sql.Timestamp;

/**
 * Model entity. @author MyEclipse Persistence Tools
 */

public class Model implements java.io.Serializable {

	// Fields

	private Integer modelId;
	private String modelName;
	private String spec1Name;
	private String spec1Values;
	private String spec2Name;
	private String spec2Values;
	private String status;
	private Timestamp gmtCreate;
	private Timestamp gmtModify;
	private Integer version;

	// Constructors

	/** default constructor */
	public Model() {
	}

	/** minimal constructor */
	public Model(String modelName, String spec1Name, String spec1Values,
			String status, Timestamp gmtCreate, Timestamp gmtModify) {
		this.modelName = modelName;
		this.spec1Name = spec1Name;
		this.spec1Values = spec1Values;
		this.status = status;
		this.gmtCreate = gmtCreate;
		this.gmtModify = gmtModify;
	}

	/** full constructor */
	public Model(String modelName, String spec1Name, String spec1Values,
			String spec2Name, String spec2Values, String status,
			Timestamp gmtCreate, Timestamp gmtModify, Integer version) {
		this.modelName = modelName;
		this.spec1Name = spec1Name;
		this.spec1Values = spec1Values;
		this.spec2Name = spec2Name;
		this.spec2Values = spec2Values;
		this.status = status;
		this.gmtCreate = gmtCreate;
		this.gmtModify = gmtModify;
		this.version = version;
	}

	// Property accessors

	public Integer getModelId() {
		return this.modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getSpec1Name() {
		return this.spec1Name;
	}

	public void setSpec1Name(String spec1Name) {
		this.spec1Name = spec1Name;
	}

	public String getSpec1Values() {
		return this.spec1Values;
	}

	public void setSpec1Values(String spec1Values) {
		this.spec1Values = spec1Values;
	}

	public String getSpec2Name() {
		return this.spec2Name;
	}

	public void setSpec2Name(String spec2Name) {
		this.spec2Name = spec2Name;
	}

	public String getSpec2Values() {
		return this.spec2Values;
	}

	public void setSpec2Values(String spec2Values) {
		this.spec2Values = spec2Values;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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