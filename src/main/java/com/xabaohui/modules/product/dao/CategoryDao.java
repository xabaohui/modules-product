package com.xabaohui.modules.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xabaohui.modules.product.bean.Category;
import com.xabaohui.modules.product.bean.Product;

public interface CategoryDao {

	// property constants
	public static final String PARENT_ID = "parentId";
	public static final String CATEGORY_NAME = "categoryName";
	public static final String CATEGORY_DESC = "categoryDesc";
	public static final String STATUS = "status";
	public static final String VERSION = "version";

	public abstract void save(Category transientInstance);

	public abstract Category findById(java.lang.Integer id);

	public abstract List<Category> findByExample(Category instance);

	public abstract List<Category> findByCriteria(DetachedCriteria dc);
	
	public abstract void update(Category cg);
}