package com.xabaohui.modules.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xabaohui.modules.product.bean.Product;

public interface ProductDao {

	// property constants
	public static final String PRODUCT_NAME = "productName";
	public static final String PRODUCT_PRICE = "productPrice";
	public static final String PRODUCT_SORT_ID = "productSortId";
	public static final String SPEC1_NAME = "spec1Name";
	public static final String SPEC1_VALUES = "spec1Values";
	public static final String SPEC2_NAME = "spec2Name";
	public static final String SPEC2_VALUES = "spec2Values";
	public static final String STATUS = "status";
	public static final String VERSION = "version";

	public abstract void save(Product transientInstance);

	public abstract Product findById(java.lang.Integer id);

	public abstract List<Product> findByExample(Product instance);
	
	public abstract void update(Product product);
	
	public abstract List<Product> findByCriteria(DetachedCriteria dc);
}