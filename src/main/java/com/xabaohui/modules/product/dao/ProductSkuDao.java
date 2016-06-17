package com.xabaohui.modules.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xabaohui.modules.product.bean.Product;
import com.xabaohui.modules.product.bean.ProductSku;

public interface ProductSkuDao {

	// property constants
	public static final String PRODUCT_ID = "productId";
	public static final String SPEC1_VALUE = "spec1Value";
	public static final String SPEC2_VALUE = "spec2Value";
	public static final String STATUS = "status";
	public static final String VERSION = "version";

	public abstract void save(ProductSku transientInstance);

	public abstract ProductSku findById(java.lang.Integer id);

	public abstract List<ProductSku> findByExample(ProductSku instance);
	
	public abstract List<ProductSku> findByCriteria(DetachedCriteria dc);
	
	public abstract void update(ProductSku sku);
}