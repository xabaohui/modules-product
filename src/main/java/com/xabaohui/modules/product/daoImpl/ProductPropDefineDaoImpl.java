package com.xabaohui.modules.product.daoImpl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xabaohui.modules.product.bean.ProductProp;
import com.xabaohui.modules.product.bean.ProductPropDefine;
import com.xabaohui.modules.product.dao.ProductPropDefineDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProductPropDefine entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.ProductPropDefine
 * @author MyEclipse Persistence Tools
 */

public class ProductPropDefineDaoImpl extends HibernateDaoSupport implements ProductPropDefineDao {
	private static final Logger log = LoggerFactory
			.getLogger(ProductPropDefineDaoImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#save(com.xabaoihui.modules.product.bean.ProductPropDefine)
	 */
	@Override
	public void save(ProductPropDefine transientInstance) {
		log.debug("saving ProductPropDefine instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#delete(com.xabaoihui.modules.product.bean.ProductPropDefine)
	 */
	public void delete(ProductPropDefine persistentInstance) {
		log.debug("deleting ProductPropDefine instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#findById(java.lang.Integer)
	 */
	@Override
	public ProductPropDefine findById(java.lang.Integer id) {
		log.debug("getting ProductPropDefine instance with id: " + id);
		try {
			ProductPropDefine instance = (ProductPropDefine) getHibernateTemplate()
					.get("com.bean.ProductPropDefine", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#findByExample(com.xabaoihui.modules.product.bean.ProductPropDefine)
	 */
	@Override
	public List findByExample(ProductPropDefine instance) {
		log.debug("finding ProductPropDefine instance by example");
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
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ProductPropDefine instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ProductPropDefine as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#findByPropName(java.lang.Object)
	 */
	public List findByPropName(Object propName) {
		return findByProperty(PROP_NAME, propName);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#findByStatus(java.lang.Object)
	 */
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#findByPropType(java.lang.Object)
	 */
	public List findByPropType(Object propType) {
		return findByProperty(PROP_TYPE, propType);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#findByPropValues(java.lang.Object)
	 */
	public List findByPropValues(Object propValues) {
		return findByProperty(PROP_VALUES, propValues);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#findByVersion(java.lang.Object)
	 */
	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all ProductPropDefine instances");
		try {
			String queryString = "from ProductPropDefine";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#merge(com.xabaoihui.modules.product.bean.ProductPropDefine)
	 */
	public ProductPropDefine merge(ProductPropDefine detachedInstance) {
		log.debug("merging ProductPropDefine instance");
		try {
			ProductPropDefine result = (ProductPropDefine) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#attachDirty(com.xabaoihui.modules.product.bean.ProductPropDefine)
	 */
	public void attachDirty(ProductPropDefine instance) {
		log.debug("attaching dirty ProductPropDefine instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductPropDefineDao#attachClean(com.xabaoihui.modules.product.bean.ProductPropDefine)
	 */
	public void attachClean(ProductPropDefine instance) {
		log.debug("attaching clean ProductPropDefine instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<ProductPropDefine> findByCriteria(DetachedCriteria dc){
		log.debug("updating Departmentinfo departmentinfo");
		try {
			return getHibernateTemplate().findByCriteria(dc);
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	public void update(ProductPropDefine prop){
		getHibernateTemplate().update(prop);
	}

	public static ProductPropDefineDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProductPropDefineDao) ctx.getBean("ProductPropDefineDAO");
	}
}