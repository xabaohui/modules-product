package com.xabaohui.modules.product.daoImpl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xabaohui.modules.product.bean.Model;
import com.xabaohui.modules.product.bean.Product;
import com.xabaohui.modules.product.dao.ModelDao;

/**
 * A data access object (DAO) providing persistence and search support for Model
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.sys.bean.Model
 * @author MyEclipse Persistence Tools
 */

public class ModelDaoImpl extends HibernateDaoSupport implements ModelDao {
	private static final Logger log = LoggerFactory.getLogger(ModelDaoImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ModelDao#save(com.xabaohui.modules.product.bean.Model)
	 */
	@Override
	public void save(Model transientInstance) {
		log.debug("saving Model instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Model persistentInstance) {
		log.debug("deleting Model instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ModelDao#findById(java.lang.Integer)
	 */
	@Override
	public Model findById(java.lang.Integer id) {
		log.debug("getting Model instance with id: " + id);
		try {
			Model instance = (Model) getHibernateTemplate().get(
					"com.sys.bean.Model", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ModelDao#findByExample(com.xabaohui.modules.product.bean.Model)
	 */
	@Override
	public List findByExample(Model instance) {
		log.debug("finding Model instance by example");
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

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Model instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Model as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByModelName(Object modelName) {
		return findByProperty(MODEL_NAME, modelName);
	}

	public List findBySpec1Name(Object spec1Name) {
		return findByProperty(SPEC1_NAME, spec1Name);
	}

	public List findBySpec1Values(Object spec1Values) {
		return findByProperty(SPEC1_VALUES, spec1Values);
	}

	public List findBySpec2Name(Object spec2Name) {
		return findByProperty(SPEC2_NAME, spec2Name);
	}

	public List findBySpec2Values(Object spec2Values) {
		return findByProperty(SPEC2_VALUES, spec2Values);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	public List findAll() {
		log.debug("finding all Model instances");
		try {
			String queryString = "from Model";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Model merge(Model detachedInstance) {
		log.debug("merging Model instance");
		try {
			Model result = (Model) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Model instance) {
		log.debug("attaching dirty Model instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Model instance) {
		log.debug("attaching clean Model instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ModelDao#update(com.xabaohui.modules.product.bean.Product)
	 */
	@Override
	public void update(Model model){
		getHibernateTemplate().update(model);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.ModelDao#findByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	public List findByCriteria(DetachedCriteria dc){
		log.debug("updating Departmentinfo departmentinfo");
		try {
			return getHibernateTemplate().findByCriteria(dc);
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public static ModelDao getFromApplicationContext(ApplicationContext ctx) {
		return (ModelDao) ctx.getBean("ModelDAO");
	}
}