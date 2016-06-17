package com.xabaohui.modules.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xabaohui.modules.product.bean.Product;
import com.xabaohui.modules.product.bean.ProductProp;

public interface ProductPropDao {

	// property constants
	public static final String PRODUCT_ID = "productId";
	public static final String PROP_ID = "propId";
	public static final String PROP_VALUE = "propValue";
	public static final String STATUS = "status";
	public static final String VERSION = "version";

	public abstract void save(ProductProp transientInstance);

	public abstract ProductProp findById(java.lang.Integer id);

	public abstract List<ProductProp> findByExample(ProductProp instance);
	
	public abstract List<ProductProp> findByCriteria(DetachedCriteria dc);
	
	public abstract void update(ProductProp prop);
}