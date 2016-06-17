package com.xabaohui.modules.product.daoImpl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xabaohui.modules.product.bean.Product;
import com.xabaohui.modules.product.bean.ProductLabelRelation;
import com.xabaohui.modules.product.dao.ProductLabelRelationDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProductLabelRelation entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.ProductLabelRelation
 * @author MyEclipse Persistence Tools
 */

public class ProductLabelRelationDaoImpl extends HibernateDaoSupport implements ProductLabelRelationDao {
	private static final Logger log = LoggerFactory
			.getLogger(ProductLabelRelationDaoImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductLabelRelationDao#save(com.xabaoihui.modules.product.bean.ProductLabelRelation)
	 */
	@Override
	public void save(ProductLabelRelation transientInstance) {
		log.debug("saving ProductLabelRelation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductLabelRelationDao#delete(com.xabaoihui.modules.product.bean.ProductLabelRelation)
	 */
	public void delete(ProductLabelRelation persistentInstance) {
		log.debug("deleting ProductLabelRelation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductLabelRelationDao#findById(java.lang.Integer)
	 */
	@Override
	public ProductLabelRelation findById(java.lang.Integer id) {
		log.debug("getting ProductLabelRelation instance with id: " + id);
		try {
			ProductLabelRelation instance = (ProductLabelRelation) getHibernateTemplate()
					.get("com.bean.ProductLabelRelation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductLabelRelationDao#findByExample(com.xabaoihui.modules.product.bean.ProductLabelRelation)
	 */
	@Override
	public List findByExample(ProductLabelRelation instance) {
		log.debug("finding ProductLabelRelation instance by example");
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
	 * @see com.xabaohui.modules.product.daoImpl.ProductLabelRelationDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ProductLabelRelation instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ProductLabelRelation as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductLabelRelationDao#findByLabelId(java.lang.Object)
	 */
	public List findByLabelId(Object labelId) {
		return findByProperty(LABEL_ID, labelId);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductLabelRelationDao#findBySubjectId(java.lang.Object)
	 */
	public List findBySubjectId(Object subjectId) {
		return findByProperty(SUBJECT_ID, subjectId);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductLabelRelationDao#findByStatus(java.lang.Object)
	 */
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductLabelRelationDao#findByVersion(java.lang.Object)
	 */
	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductLabelRelationDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all ProductLabelRelation instances");
		try {
			String queryString = "from ProductLabelRelation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductLabelRelationDao#merge(com.xabaoihui.modules.product.bean.ProductLabelRelation)
	 */
	public ProductLabelRelation merge(ProductLabelRelation detachedInstance) {
		log.debug("merging ProductLabelRelation instance");
		try {
			ProductLabelRelation result = (ProductLabelRelation) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductLabelRelationDao#attachDirty(com.xabaoihui.modules.product.bean.ProductLabelRelation)
	 */
	public void attachDirty(ProductLabelRelation instance) {
		log.debug("attaching dirty ProductLabelRelation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ProductLabelRelationDao#attachClean(com.xabaoihui.modules.product.bean.ProductLabelRelation)
	 */
	public void attachClean(ProductLabelRelation instance) {
		log.debug("attaching clean ProductLabelRelation instance");
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
	
	public void update(ProductLabelRelation relation){
		getHibernateTemplate().update(relation);
	}

	public static ProductLabelRelationDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProductLabelRelationDao) ctx.getBean("ProductLabelRelationDAO");
	}
}