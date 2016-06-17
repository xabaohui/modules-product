package com.xabaohui.modules.product.bean;

import java.sql.Timestamp;

/**
 * ProductSku entity. @author MyEclipse Persistence Tools
 */

public class ProductSku implements java.io.Serializable {

	// Fields

	private Integer skuId;
	private Integer productId;
	private String skuNo;
	private String spec1Value;
	private String spec2Value;
	private String status;
	private Timestamp gmtCreate;
	private Timestamp gmtModify;
	private Integer version;

	// Constructors

	/** default constructor */
	public ProductSku() {
	}

	/** minimal constructor */
	public ProductSku(Integer productId, String skuNo, String spec1Value,
			String status, Timestamp gmtCreate, Timestamp gmtModify,
			Integer version) {
		this.productId = productId;
		this.skuNo = skuNo;
		this.spec1Value = spec1Value;
		this.status = status;
		this.gmtCreate = gmtCreate;
		this.gmtModify = gmtModify;
		this.version = version;
	}

	/** full constructor */
	public ProductSku(Integer productId, String skuNo, String spec1Value,
			String spec2Value, String status, Timestamp gmtCreate,
			Timestamp gmtModify, Integer version) {
		this.productId = productId;
		this.skuNo = skuNo;
		this.spec1Value = spec1Value;
		this.spec2Value = spec2Value;
		this.status = status;
		this.gmtCreate = gmtCreate;
		this.gmtModify = gmtModify;
		this.version = version;
	}

	// Property accessors

	public Integer getSkuId() {
		return this.skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getSkuNo() {
		return this.skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo;
	}

	public String getSpec1Value() {
		return this.spec1Value;
	}

	public void setSpec1Value(String spec1Value) {
		this.spec1Value = spec1Value;
	}

	public String getSpec2Value() {
		return this.spec2Value;
	}

	public void setSpec2Value(String spec2Value) {
		this.spec2Value = spec2Value;
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