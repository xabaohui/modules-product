package com.xabaohui.modules.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xabaohui.modules.product.bean.Model;
import com.xabaohui.modules.product.bean.Product;

public interface ModelDao {

	// property constants
	public static final String MODEL_NAME = "modelName";
	public static final String SPEC1_NAME = "spec1Name";
	public static final String SPEC1_VALUES = "spec1Values";
	public static final String SPEC2_NAME = "spec2Name";
	public static final String SPEC2_VALUES = "spec2Values";
	public static final String STATUS = "status";
	public static final String VERSION = "version";

	public abstract void save(Model transientInstance);

	public abstract Model findById(java.lang.Integer id);

	public abstract List<Model> findByExample(Model instance);

	public abstract void update(Model model);

	public abstract List<Model> findByCriteria(DetachedCriteria dc);

}