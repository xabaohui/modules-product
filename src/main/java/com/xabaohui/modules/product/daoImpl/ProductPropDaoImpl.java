package com.xabaohui.modules.product.daoImpl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xabaohui.modules.product.bean.Product;
import com.xabaohui.modules.product.bean.ProductProp;
import com.xabaohui.modules.product.dao.ProductPropDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProductProp entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.ProductProp
 * @author MyEclipse Persistence Tools
 */

public class ProductPropDaoImpl extends HibernateDaoSupport implements ProductPropDao {
	private static final Logger log = LoggerFactory
			.getLogger(ProductPropDaoImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#save(com.xabaoihui.modules.product.bean.ProductProp)
	 */
	@Override
	public void save(ProductProp transientInstance) {
		log.debug("saving ProductProp instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#delete(com.xabaoihui.modules.product.bean.ProductProp)
	 */
	public void delete(ProductProp persistentInstance) {
		log.debug("deleting ProductProp instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#findById(java.lang.Integer)
	 */
	@Override
	public ProductProp findById(java.lang.Integer id) {
		log.debug("getting ProductProp instance with id: " + id);
		try {
			ProductProp instance = (ProductProp) getHibernateTemplate().get(
					"com.bean.ProductProp", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#findByExample(com.xabaoihui.modules.product.bean.ProductProp)
	 */
	@Override
	public List findByExample(ProductProp instance) {
		log.debug("finding ProductProp instance by example");
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
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ProductProp instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ProductProp as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#findByProductId(java.lang.Object)
	 */
	public List findByProductId(Object productId) {
		return findByProperty(PRODUCT_ID, productId);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#findByPropId(java.lang.Object)
	 */
	public List findByPropId(Object propId) {
		return findByProperty(PROP_ID, propId);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#findByPropValue(java.lang.Object)
	 */
	public List findByPropValue(Object propValue) {
		return findByProperty(PROP_VALUE, propValue);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#findByStatus(java.lang.Object)
	 */
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#findByVersion(java.lang.Object)
	 */
	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all ProductProp instances");
		try {
			String queryString = "from ProductProp";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#merge(com.xabaoihui.modules.product.bean.ProductProp)
	 */
	public ProductProp merge(ProductProp detachedInstance) {
		log.debug("merging ProductProp instance");
		try {
			ProductProp result = (ProductProp) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#attachDirty(com.xabaoihui.modules.product.bean.ProductProp)
	 */
	public void attachDirty(ProductProp instance) {
		log.debug("attaching dirty ProductProp instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void update(ProductProp prop){
		getHibernateTemplate().update(prop);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDao#attachClean(com.xabaoihui.modules.product.bean.ProductProp)
	 */
	public void attachClean(ProductProp instance) {
		log.debug("attaching clean ProductProp instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
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

	public static ProductPropDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProductPropDao) ctx.getBean("ProductPropDAO");
	}
}