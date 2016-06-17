package com.xabaohui.modules.product.daoImpl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xabaohui.modules.product.bean.Label;
import com.xabaohui.modules.product.dao.LabelDao;

/**
 * A data access object (DAO) providing persistence and search support for Label
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.Label
 * @author MyEclipse Persistence Tools
 */

public class LabelDaoImpl extends HibernateDaoSupport implements LabelDao {
	private static final Logger log = LoggerFactory.getLogger(LabelDaoImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.LabelDao#save(com.xabaoihui.modules.product.bean.Label)
	 */
	@Override
	public void save(Label transientInstance) {
		log.debug("saving Label instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.LabelDao#delete(com.xabaoihui.modules.product.bean.Label)
	 */
	public void delete(Label persistentInstance) {
		log.debug("deleting Label instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.LabelDao#findById(java.lang.Integer)
	 */
	@Override
	public Label findById(java.lang.Integer id) {
		log.debug("getting Label instance with id: " + id);
		try {
			Label instance = (Label) getHibernateTemplate().get(
					"com.bean.Label", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.LabelDao#findByExample(com.xabaoihui.modules.product.bean.Label)
	 */
	@Override
	public List findByExample(Label instance) {
		log.debug("finding Label instance by example");
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
	 * @see com.xabaohui.modules.product.daoImpl.LabelDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Label instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Label as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.LabelDao#findByLabelName(java.lang.Object)
	 */
	public List findByLabelName(Object labelName) {
		return findByProperty(LABEL_NAME, labelName);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.LabelDao#findByStatus(java.lang.Object)
	 */
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.LabelDao#findByVersion(java.lang.Object)
	 */
	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.LabelDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all Label instances");
		try {
			String queryString = "from Label";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.LabelDao#merge(com.xabaoihui.modules.product.bean.Label)
	 */
	public Label merge(Label detachedInstance) {
		log.debug("merging Label instance");
		try {
			Label result = (Label) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.LabelDao#attachDirty(com.xabaoihui.modules.product.bean.Label)
	 */
	public void attachDirty(Label instance) {
		log.debug("attaching dirty Label instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.xabaohui.modules.product.daoImpl.LabelDao#attachClean(com.xabaoihui.modules.product.bean.Label)
	 */
	public void attachClean(Label instance) {
		log.debug("attaching clean Label instance");
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
	
	public static LabelDao getFromApplicationContext(ApplicationContext ctx) {
		return (LabelDao) ctx.getBean("LabelDAO");
	}
}