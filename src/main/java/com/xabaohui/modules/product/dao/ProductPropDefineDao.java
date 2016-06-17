package com.xabaohui.modules.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xabaohui.modules.product.bean.Product;
import com.xabaohui.modules.product.bean.ProductProp;
import com.xabaohui.modules.product.bean.ProductPropDefine;

public interface ProductPropDefineDao {

	// property constants
	public static final String PROP_NAME = "propName";
	public static final String STATUS = "status";
	public static final String PROP_TYPE = "propType";
	public static final String PROP_VALUES = "propValues";
	public static final String VERSION = "version";

	public abstract void save(ProductPropDefine transientInstance);

	public abstract ProductPropDefine findById(java.lang.Integer id);

	public abstract List<ProductPropDefine> findByExample(ProductPropDefine instance);
	
	public abstract List<ProductPropDefine> findByCriteria(DetachedCriteria dc);
	
	public abstract void update(ProductPropDefine prop);
}