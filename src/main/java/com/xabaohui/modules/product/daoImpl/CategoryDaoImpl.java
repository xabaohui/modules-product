package com.xabaohui.modules.product.daoImpl;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xabaohui.modules.product.bean.Category;
import com.xabaohui.modules.product.bean.Product;
import com.xabaohui.modules.product.dao.CategoryDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * Category entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.Category
 * @author MyEclipse Persistence Tools
 */

public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {
	private static final Logger log = LoggerFactory
			.getLogger(CategoryDaoImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.CategoryDao#save(com.xabaoihui.modules.product.bean.Category)
	 */
	@Override
	public void save(Category transientInstance) {
		log.debug("saving Category instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}


	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.CategoryDao#findById(java.lang.Integer)
	 */
	@Override
	public Category findById(java.lang.Integer id) {
		log.debug("getting Category instance with id: " + id);
		try {
			Category instance = (Category) getHibernateTemplate().get(
					"com.bean.Category", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.CategoryDao#findByExample(com.xabaoihui.modules.product.bean.Category)
	 */
	@Override
	public List findByExample(Category instance) {
		log.debug("finding Category instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.CategoryDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Category instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Category as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.CategoryDao#findByParentId(java.lang.Object)
	 */
	public List findByParentId(Object parentId) {
		return findByProperty(PARENT_ID, parentId);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.CategoryDao#findByCategoryName(java.lang.Object)
	 */
	public List findByCategoryName(Object categoryName) {
		return findByProperty(CATEGORY_NAME, categoryName);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.CategoryDao#findByCategoryDesc(java.lang.Object)
	 */
	public List findByCategoryDesc(Object categoryDesc) {
		return findByProperty(CATEGORY_DESC, categoryDesc);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.CategoryDao#findByStatus(java.lang.Object)
	 */
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}
	
	public List findByCriteria(DetachedCriteria dc){
		log.debug("updating Departmentinfo departmentinfo");
		try {
			return getHibernateTemplate().findByCriteria(dc);
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	public void update(Category product){
		getHibernateTemplate().update(product);
	}

	public static CategoryDao getFromApplicationContext(ApplicationContext ctx) {
		return (CategoryDao) ctx.getBean("CategoryDAO");
	}
}