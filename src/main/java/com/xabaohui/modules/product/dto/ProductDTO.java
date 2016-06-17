package com.xabaohui.modules.product.dto;

import java.util.List;

import com.xabaohui.modules.product.bean.ProductSku;

public class ProductDTO {
	private String productNo;
	private String isbn;
	private String brand;
	private String productImg;
	private String productDesc;
	private List<ProductSku> skuList;
	
	private String productName;
	private long productPrice;
	private String spec1Name;
	private String spec1Values;
	private String spec2Name;
	private String spec2Values;
	private List<ProductPropDTO> list;
	private String categoryName;
	private List<String> labelList;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}
	public String getSpec1Name() {
		return spec1Name;
	}
	public void setSpec1Name(String spec1Name) {
		this.spec1Name = spec1Name;
	}
	public String getSpec1Values() {
		return spec1Values;
	}
	public void setSpec1Values(String spec1Values) {
		this.spec1Values = spec1Values;
	}
	public String getSpec2Name() {
		return spec2Name;
	}
	public void setSpec2Name(String spec2Name) {
		this.spec2Name = spec2Name;
	}
	public String getSpec2Values() {
		return spec2Values;
	}
	public void setSpec2Values(String spec2Values) {
		this.spec2Values = spec2Values;
	}
	public List<ProductPropDTO> getList() {
		return list;
	}
	public void setList(List<ProductPropDTO> list) {
		this.list = list;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<String> getLabelList() {
		return labelList;
	}
	public void setLabelList(List<String> labelList) {
		this.labelList = labelList;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public List<ProductSku> getSkuList() {
		return skuList;
	}
	public void setSkuList(List<ProductSku> skuList) {
		this.skuList = skuList;
	}
}
