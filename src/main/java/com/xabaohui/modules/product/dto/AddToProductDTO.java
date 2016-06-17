package com.xabaohui.modules.product.dto;

import java.util.List;

import com.xabaohui.modules.product.bean.ProductProp;


public class AddToProductDTO {
	private String productDesc;
	private String productImg;
	private String brand;
	private String isbn;
	private String productNo;
	private String productName;
	private Long productPrice;
	private String spec1Name;
	private String spec1Values;
	private String spec2Name;
	private String spec2Values;
	private Integer categoryId;
	
	private List<SkuDTO> skuDTOList;
	private List<ProductProp> productPropList;
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Long productPrice) {
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
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public List<SkuDTO> getSkuDTOList() {
		return skuDTOList;
	}
	public void setSkuDTOList(List<SkuDTO> skuDTOList) {
		this.skuDTOList = skuDTOList;
	}
	public List<ProductProp> getProductPropList() {
		return productPropList;
	}
	public void setProductPropList(List<ProductProp> productPropList) {
		this.productPropList = productPropList;
	}
}
