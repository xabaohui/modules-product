package com.xabaohui.modules.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xabaohui.modules.product.bean.Label;
import com.xabaohui.modules.product.bean.Product;

public interface LabelDao {

	// property constants
	public static final String LABEL_NAME = "labelName";
	public static final String STATUS = "status";
	public static final String VERSION = "version";

	public abstract void save(Label transientInstance);

	public abstract Label findById(java.lang.Integer id);

	public abstract List<Label> findByExample(Label instance);
	
	public abstract List<Label> findByCriteria(DetachedCriteria dc);

}