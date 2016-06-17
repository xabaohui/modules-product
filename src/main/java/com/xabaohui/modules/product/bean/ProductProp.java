package com.xabaohui.modules.product.bean;

import java.sql.Timestamp;

/**
 * ProductProp entity. @author MyEclipse Persistence Tools
 */

public class ProductProp implements java.io.Serializable {

	// Fields

	private Integer productPropId;
	private Integer productId;
	private Integer propId;
	private String propValue;
	private String status;
	private Timestamp gmtCreate;
	private Timestamp gmtModify;
	private Integer version;

	// Constructors

	/** default constructor */
	public ProductProp() {
	}

	/** full constructor */
	public ProductProp(Integer productId, Integer propId, String propValue,
			String status, Timestamp gmtCreate, Timestamp gmtModify,
			Integer version) {
		this.productId = productId;
		this.propId = propId;
		this.propValue = propValue;
		this.status = status;
		this.gmtCreate = gmtCreate;
		this.gmtModify = gmtModify;
		this.version = version;
	}

	// Property accessors

	public Integer getProductPropId() {
		return this.productPropId;
	}

	public void setProductPropId(Integer productPropId) {
		this.productPropId = productPropId;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getPropId() {
		return this.propId;
	}

	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	public String getPropValue() {
		return this.propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
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