package com.xabaohui.modules.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xabaohui.modules.product.bean.Product;
import com.xabaohui.modules.product.bean.ProductLabelRelation;

public interface ProductLabelRelationDao {

	// property constants
	public static final String LABEL_ID = "labelId";
	public static final String SUBJECT_ID = "subjectId";
	public static final String STATUS = "status";
	public static final String VERSION = "version";

	public abstract void save(ProductLabelRelation transientInstance);

	public abstract ProductLabelRelation findById(java.lang.Integer id);

	public abstract List<ProductLabelRelation> findByExample(ProductLabelRelation instance);
	
	public abstract List<ProductLabelRelation> findByCriteria(DetachedCriteria dc);
	
	public abstract void update(ProductLabelRelation relation);
}