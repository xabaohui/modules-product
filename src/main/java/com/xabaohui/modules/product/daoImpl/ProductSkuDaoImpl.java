package com.xabaohui.modules.product.daoImpl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xabaohui.modules.product.bean.Product;
import com.xabaohui.modules.product.bean.ProductSku;
import com.xabaohui.modules.product.dao.ProductSkuDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProductSku entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.ProductSku
 * @author MyEclipse Persistence Tools
 */

public class ProductSkuDaoImpl extends HibernateDaoSupport implements ProductSkuDao {
	private static final Logger log = LoggerFactory
			.getLogger(ProductSkuDaoImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#save(com.xabaoihui.modules.product.bean.ProductSku)
	 */
	@Override
	public void save(ProductSku transientInstance) {
		log.debug("saving ProductSku instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#delete(com.xabaoihui.modules.product.bean.ProductSku)
	 */
	public void delete(ProductSku persistentInstance) {
		log.debug("deleting ProductSku instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#findById(java.lang.Integer)
	 */
	@Override
	public ProductSku findById(java.lang.Integer id) {
		log.debug("getting ProductSku instance with id: " + id);
		try {
			ProductSku instance = (ProductSku) getHibernateTemplate().get(
					"com.bean.ProductSku", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#findByExample(com.xabaoihui.modules.product.bean.ProductSku)
	 */
	@Override
	public List findByExample(ProductSku instance) {
		log.debug("finding ProductSku instance by example");
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
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ProductSku instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ProductSku as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#findByProductId(java.lang.Object)
	 */
	public List findByProductId(Object productId) {
		return findByProperty(PRODUCT_ID, productId);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#findBySpec1Value(java.lang.Object)
	 */
	public List findBySpec1Value(Object spec1Value) {
		return findByProperty(SPEC1_VALUE, spec1Value);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#findBySpec2Value(java.lang.Object)
	 */
	public List findBySpec2Value(Object spec2Value) {
		return findByProperty(SPEC2_VALUE, spec2Value);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#findByStatus(java.lang.Object)
	 */
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#findByVersion(java.lang.Object)
	 */
	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all ProductSku instances");
		try {
			String queryString = "from ProductSku";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#merge(com.xabaoihui.modules.product.bean.ProductSku)
	 */
	public ProductSku merge(ProductSku detachedInstance) {
		log.debug("merging ProductSku instance");
		try {
			ProductSku result = (ProductSku) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#attachDirty(com.xabaoihui.modules.product.bean.ProductSku)
	 */
	public void attachDirty(ProductSku instance) {
		log.debug("attaching dirty ProductSku instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductSkuDao#attachClean(com.xabaoihui.modules.product.bean.ProductSku)
	 */
	public void attachClean(ProductSku instance) {
		log.debug("attaching clean ProductSku instance");
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
	
	public void update(ProductSku sku){
		getHibernateTemplate().update(sku);
	}
	
	public static ProductSkuDao getFromApplicationContext(ApplicationContext ctx) {
		return (ProductSkuDao) ctx.getBean("ProductSkuDAO");
	}
}