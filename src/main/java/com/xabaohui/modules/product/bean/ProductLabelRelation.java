package com.xabaohui.modules.product.bean;

import java.sql.Timestamp;

/**
 * ProductLabelRelation entity. @author MyEclipse Persistence Tools
 */

public class ProductLabelRelation implements java.io.Serializable {

	// Fields

	private Integer productLabelRelationId;
	private Integer labelId;
	private Integer subjectId;
	private String status;
	private Timestamp gmtCreate;
	private Timestamp gmtModify;
	private Integer version;

	// Constructors

	/** default constructor */
	public ProductLabelRelation() {
	}

	/** full constructor */
	public ProductLabelRelation(Integer labelId, Integer subjectId,
			String status, Timestamp gmtCreate, Timestamp gmtModify,
			Integer version) {
		this.labelId = labelId;
		this.subjectId = subjectId;
		this.status = status;
		this.gmtCreate = gmtCreate;
		this.gmtModify = gmtModify;
		this.version = version;
	}

	// Property accessors

	public Integer getProductLabelRelationId() {
		return this.productLabelRelationId;
	}

	public void setProductLabelRelationId(Integer productLabelRelationId) {
		this.productLabelRelationId = productLabelRelationId;
	}

	public Integer getLabelId() {
		return this.labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}

	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
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