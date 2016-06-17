package com.xabaohui.modules.product.daoImpl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xabaohui.modules.product.bean.Product;
import com.xabaohui.modules.product.dao.ProductDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * Product entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.Product
 * @author MyEclipse Persistence Tools
 */

public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao {
	private static final Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#save(com.xabaoihui.modules.product.bean.Product)
	 */
	@Override
	public void save(Product transientInstance) {
		log.debug("saving Product instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#delete(com.xabaoihui.modules.product.bean.Product)
	 */
	public void delete(Product persistentInstance) {
		log.debug("deleting Product instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#findById(java.lang.Integer)
	 */
	@Override
	public Product findById(java.lang.Integer id) {
		log.debug("getting Product instance with id: " + id);
		try {
			Product instance = (Product) getHibernateTemplate().get(
					"com.bean.Product", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#findByExample(com.xabaoihui.modules.product.bean.Product)
	 */
	@Override
	public List findByExample(Product instance) {
		log.debug("finding Product instance by example");
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
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Product instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Product as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#findByProductName(java.lang.Object)
	 */
	public List findByProductName(Object productName) {
		return findByProperty(PRODUCT_NAME, productName);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#findByProductPrice(java.lang.Object)
	 */
	public List findByProductPrice(Object productPrice) {
		return findByProperty(PRODUCT_PRICE, productPrice);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#findByProductSortId(java.lang.Object)
	 */
	public List findByProductSortId(Object productSortId) {
		return findByProperty(PRODUCT_SORT_ID, productSortId);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#findBySpec1Name(java.lang.Object)
	 */
	public List findBySpec1Name(Object spec1Name) {
		return findByProperty(SPEC1_NAME, spec1Name);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#findBySpec1Values(java.lang.Object)
	 */
	public List findBySpec1Values(Object spec1Values) {
		return findByProperty(SPEC1_VALUES, spec1Values);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#findBySpec2Name(java.lang.Object)
	 */
	public List findBySpec2Name(Object spec2Name) {
		return findByProperty(SPEC2_NAME, spec2Name);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#findBySpec2Values(java.lang.Object)
	 */
	public List findBySpec2Values(Object spec2Values) {
		return findByProperty(SPEC2_VALUES, spec2Values);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#findByStatus(java.lang.Object)
	 */
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#findByVersion(java.lang.Object)
	 */
	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all Product instances");
		try {
			String queryString = "from Product";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#merge(com.xabaoihui.modules.product.bean.Product)
	 */
	public Product merge(Product detachedInstance) {
		log.debug("merging Product instance");
		try {
			Product result = (Product) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#attachDirty(com.xabaoihui.modules.product.bean.Product)
	 */
	public void attachDirty(Product instance) {
		log.debug("attaching dirty Product instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductDao#attachClean(com.xabaoihui.modules.product.bean.Product)
	 */
	public void attachClean(Product instance) {
		log.debug("attaching clean Product instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void update(Product product){
		getHibernateTemplate().update(product);
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
	
	public static ProductDao getFromApplicationContext(ApplicationContext ctx) {
		return (ProductDao) ctx.getBean("ProductDAO");
	}
}