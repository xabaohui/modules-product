package com.xabaohui.modules.product.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.xabaohui.modules.product.bean.Category;
import com.xabaohui.modules.product.bean.CategoryStatus;
import com.xabaohui.modules.product.bean.Label;
import com.xabaohui.modules.product.bean.LabelStatus;
import com.xabaohui.modules.product.bean.Model;
import com.xabaohui.modules.product.bean.ModelStatus;
import com.xabaohui.modules.product.bean.Product;
import com.xabaohui.modules.product.bean.ProductLabelRelation;
import com.xabaohui.modules.product.bean.ProductLabelRelationStatus;
import com.xabaohui.modules.product.bean.ProductProp;
import com.xabaohui.modules.product.bean.ProductPropDefine;
import com.xabaohui.modules.product.bean.ProductPropDefineStatus;
import com.xabaohui.modules.product.bean.ProductPropStatus;
import com.xabaohui.modules.product.bean.ProductSku;
import com.xabaohui.modules.product.bean.ProductSkuStatus;
import com.xabaohui.modules.product.bean.ProductStatus;
import com.xabaohui.modules.product.dao.CategoryDao;
import com.xabaohui.modules.product.dao.LabelDao;
import com.xabaohui.modules.product.dao.ModelDao;
import com.xabaohui.modules.product.dao.ProductDao;
import com.xabaohui.modules.product.dao.ProductLabelRelationDao;
import com.xabaohui.modules.product.dao.ProductPropDao;
import com.xabaohui.modules.product.dao.ProductPropDefineDao;
import com.xabaohui.modules.product.dao.ProductSkuDao;
import com.xabaohui.modules.product.dto.AddToProductDTO;
import com.xabaohui.modules.product.dto.ProductDTO;
import com.xabaohui.modules.product.dto.ProductPropDTO;
import com.xabaohui.modules.product.dto.PropDTO;
import com.xabaohui.modules.product.dto.SkuDTO;
import com.xabaohui.modules.product.dto.SpecDTO;
import com.xabaohui.modules.product.util.DateFormat;

public class ProductBO {
	
	private ProductDao productDao;
	private ProductLabelRelationDao productLabelRelationDao;
	private LabelDao labelDao;
	private CategoryDao categoryDao;
	private ProductPropDefineDao productPropDefineDao;
	private ProductPropDao productPropDao;
	private ProductSkuDao productSkuDao;
	private ModelDao modelDao;
	private Logger log = LoggerFactory.getLogger(ProductBO.class);

	/**
	 * ��������id��ѯ���Զ���
	 * @param propId
	 * @return
	 */
	public ProductPropDefine findPropEcho(Integer propId){
		DetachedCriteria dc = DetachedCriteria.forClass(ProductPropDefine.class);
		dc.add(Restrictions.eq("propId", propId));
		dc.add(Restrictions.eq("status", ProductPropDefineStatus.BEEN_USED));
		List<ProductPropDefine> list = productPropDefineDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("�޸�ʧ�ܣ�����idΪ{}�����ݲ�����",propId);
			throw new RuntimeException("�޸�ʧ�ܣ�����idΪ"+propId+"�����ݲ�����");
		}
		if (list.size()>1){
			log.warn("�����쳣������idΪ{}�������ж���",propId);
			throw new RuntimeException("�����쳣������idΪ"+propId+"�������ж���");
		}
		return list.get(0);
	}
	
	/**
	 * ɾ��ģ��
	 * @param modelId
	 */
	public void deleteModel(Integer modelId){
		Model model = findModelByModelId(modelId);
		model.setStatus(ModelStatus.BEEN_DELETE);
		updateModel(model);
	}
	
	/**
	 * �޸�ģ�ͻ���
	 * @param modelId
	 * @return
	 */
	public Model findModelEcho(Integer modelId){
		checkModelId(modelId);
		return findModelByModelId(modelId);
	}
	
	/**
	 * �޸�ģ��
	 * @param model
	 */
	public void updateModel(Model model){
		model.setGmtModify(DateFormat.getCurrentTime());
		model.setVersion(model.getVersion()+1);
		modelDao.update(model);
	}
	
	/**
	 * ��ѯ����ģ��
	 * @return
	 */
	public List<Model> findAllModel(){
		DetachedCriteria dc = DetachedCriteria.forClass(Model.class);
		dc.add(Restrictions.eq("status", ModelStatus.BEEN_USED));
		return modelDao.findByCriteria(dc);
	}
	
	/**
	 * �޸���Ʒ������
	 * @param define
	 */
	public void updatePropDefine(ProductPropDefine define){
		define.setGmtModify(DateFormat.getCurrentTime());
		define.setVersion(define.getVersion()+1);
		productPropDefineDao.update(define);
	}
	
	private void checkPropName(String propName){
		if (StringUtils.isBlank(propName)){
			log.warn("�����������Ϊ��");
			throw new RuntimeException("�����������Ϊ��");
		}
	}
	
	//ͨ������id��ģ��id
	private Integer findModelIdByCategoryId(Integer categoryId){
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.add(Restrictions.eq("categoryId", categoryId));
		dc.add(Restrictions.eq("status", CategoryStatus.BEEN_USED));
		List<Category> list = categoryDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("������з���idΪ{}�����ݲ�����",categoryId);
			throw new RuntimeException("������з���idΪ"+categoryId+"�����ݲ�����");
		}
		if (list.size()>1){
			log.warn("�����쳣��������з���idΪ{}�ļ�¼�ж���",categoryId);
			throw new RuntimeException("�����쳣��������з���idΪ"+categoryId+"�ļ�¼�ж���");
		}
		return list.get(0).getModelId();
	}
	
	private void checkModel(Model model){
		if (model == null){
			log.warn("�����modelΪ��");
			throw new RuntimeException("�����modelΪ��");
		}
		if (StringUtils.isBlank(model.getSpec1Name())){
			log.warn("���1����Ϊ��");
			throw new RuntimeException("���1����Ϊ��");
		}
		if (StringUtils.isBlank(model.getSpec1Values())){
			log.warn("���1��ȡֵ��ΧΪ��");
			throw new RuntimeException("���1��ȡֵ��ΧΪ��");
		}
		if (StringUtils.isBlank(model.getModelName())){
			log.warn("ģ����Ϊ��");
			throw new RuntimeException("ģ����Ϊ��");
		}
	}
	
	/**
	 * ���ģ��
	 * @param model
	 */
	public void addModel(Model model){
		checkModel(model);
		model.setStatus(ModelStatus.BEEN_USED);
		model.setGmtCreate(DateFormat.getCurrentTime());
		model.setGmtModify(DateFormat.getCurrentTime());
		model.setVersion(1);
		modelDao.save(model);
		log.info("�ɹ���ӵ�ģ�ͱ�");
	}
	
	/**
	 * ��ѯģ���µ����Զ���
	 * @param categoryId
	 * @return
	 */
	public List<ProductPropDefine> findPropDefineByModelId(Integer modelId){
		checkModelId(modelId);
		DetachedCriteria dc = DetachedCriteria.forClass(ProductPropDefine.class);
		dc.add(Restrictions.eq("modelId", modelId));
		dc.add(Restrictions.eq("status", ProductPropDefineStatus.BEEN_USED));
		List<ProductPropDefine> list = productPropDefineDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			return null;
		}
		return list;
	}
	
	private void checkLabelList(List<String> labelNames){
		if (labelNames == null || labelNames.isEmpty()){
			log.warn("����ı�ǩ��Ϊ��");
			throw new RuntimeException("����ı�ǩ��Ϊ��");
		}
	}
	
	private void deleteProductPropByProductId(Integer productId){
		checkProductId(productId);
		DetachedCriteria dc = DetachedCriteria.forClass(ProductProp.class);
		dc.add(Restrictions.eq("productId", productId));
		dc.add(Restrictions.eq("status", ProductPropStatus.BEEN_USED));
		List<ProductProp> list = productPropDao.findByCriteria(dc);
		if (list != null && !list.isEmpty()){
			for (ProductProp productProp : list) {
				productProp.setStatus(ProductPropStatus.BEEN_DELETE);
				updateProductProp(productProp);
			}
		}
	}
	//TODO ֻ���޸�ֵ��������ӻ���ɾ��
	/**
	 * �޸���Ʒ����
	 * @param productId
	 * @param list
	 */
	public void updateProductProp(Integer productId,List<ProductProp> list){
		deleteProductPropByProductId(productId);
		addToProductProp(productId,list);
	}
	
	/**
	 * ȥ������ԭ����ģ��
	 * @param productId
	 */
	public void deleteModelFromCategory(Integer categoryId){
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.add(Restrictions.eq("categoryId", categoryId));
		dc.add(Restrictions.ne("status", CategoryStatus.BEEN_USED));
		dc.add(Restrictions.isNotNull("modelId"));
		List<Category> list = categoryDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("�������idΪ{}��״̬δ��ɾ����ģ��id��Ϊ�յļ�¼Ϊ��",categoryId);
			throw new RuntimeException("�������idΪ"+categoryId+"��״̬δ��ɾ����ģ��id��Ϊ�յļ�¼Ϊ��");
		}
		if (list.size()>1){
			log.warn("�����쳣���������idΪ{}��״̬δ��ɾ����ģ��id��Ϊ�յļ�¼�ж���",categoryId);
			throw new RuntimeException("�����쳣���������idΪ"+categoryId+"��״̬δ��ɾ����ģ��id��Ϊ�յļ�¼�ж���");
		}
		Category category = list.get(0);
		category.setModelId(null);
		updateCategory(category);
		log.info("�ɹ��޸ķ����ģ��idΪ��");
	}
	
	/**
	 * ɾ������
	 * @param productId
	 * @param categoryId
	 */
	public void deleteCategory(Integer categoryId){
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.add(Restrictions.eq("parentId", categoryId));
		dc.add(Restrictions.eq("status", CategoryStatus.BEEN_USED));
		List<Category> list = categoryDao.findByCriteria(dc);
		if (list != null && !list.isEmpty()){
			log.warn("����idΪ{}�ķ������ӷ��࣬�޷�ɾ��",categoryId);
			throw new RuntimeException("����idΪ"+categoryId+"�ķ������ӷ��࣬�޷�ɾ��");
		}
		Integer modelId = findModelIdByCategoryId(categoryId);
		DetachedCriteria dc3 = DetachedCriteria.forClass(Product.class);
		dc3.add(Restrictions.eq("modelId", modelId));
		dc3.add(Restrictions.ne("status", ProductStatus.BEEN_DELETE));
		List<Product> productList = productDao.findByCriteria(dc3);
		if (productList !=null && !productList.isEmpty()){
			log.warn("����idΪ{}�ķ���������Ʒ���޷�ɾ��",categoryId);
			throw new RuntimeException("����idΪ"+categoryId+"�ķ���������Ʒ���޷�ɾ��");
		}
		DetachedCriteria dc2 = DetachedCriteria.forClass(Category.class);
		dc2.add(Restrictions.eq("categoryId", categoryId));
		dc2.add(Restrictions.eq("status", CategoryStatus.BEEN_USED));
		List<Category> cList = categoryDao.findByCriteria(dc2);
		if (cList == null || cList.isEmpty()){
			log.warn("����idΪ��");
			throw new RuntimeException("����idΪ��");
		}
		if (cList.size()>1){
			log.warn("�����쳣������idΪ{}�������ж�����¼",categoryId);
			throw new RuntimeException("�����쳣������idΪ"+categoryId+"�������ж�����¼");
		}
		Category cg = cList.get(0);
		cg.setStatus(CategoryStatus.BEEN_DELETE);
		updateCategory(cg);
		log.info("�ɹ��޸ķ����״̬Ϊ��ɾ��");
	}
	
	private void updateCategory(Category cg){
		cg.setGmtModify(DateFormat.getCurrentTime());
		cg.setVersion(cg.getVersion()+1);
		categoryDao.update(cg);
	}
	
	private Integer findPropIdByPropName(String propName){
		DetachedCriteria dc = DetachedCriteria.forClass(ProductPropDefine.class);
		dc.add(Restrictions.eq("propName", propName));
		dc.add(Restrictions.eq("status", ProductPropDefineStatus.BEEN_USED));
		List<ProductPropDefine> list = productPropDefineDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			return null;
		}
		if (list.size()>1){
			log.warn("���Զ������������Ϊ{}�������ж���",propName);
			throw new RuntimeException("���Զ������������Ϊ"+propName+"�������ж���");
		}
		return list.get(0).getPropId();
	}
	
	//ͨ��һ���ǩ��ӵ���ǩ������ids
	private List<Integer> addToLabelAndReturnIdList(List<String> labelNames){
		checkLabelList(labelNames);
		List<Integer> list = new ArrayList<Integer>();
		for (String labelName : labelNames) {
			Integer labelId = findLabelIdByLabelName(labelName);
			if ( labelId != null){
				list.add(labelId);
			}else {
				Label label = new Label();
				label.setLabelName(labelName);
				label.setStatus(LabelStatus.BEEN_USED);
				label.setGmtCreate(DateFormat.getCurrentTime());
				label.setGmtModify(DateFormat.getCurrentTime());
				label.setVersion(1);
				labelDao.save(label);
				list.add(label.getLabelId());
			}
		}
		return list;
	}
	
	//��ӵ���Ʒ��ǩ������
	private void addToRelation(Integer productId,Integer labelId){
		checkProductId(productId);
		ProductLabelRelation relation = new ProductLabelRelation();
		relation.setSubjectId(productId);
		relation.setLabelId(labelId);
		relation.setStatus(ProductLabelRelationStatus.BEEN_USED);
		relation.setGmtCreate(DateFormat.getCurrentTime());
		relation.setGmtModify(DateFormat.getCurrentTime());
		relation.setVersion(1);
		productLabelRelationDao.save(relation);
		log.info("�ɹ���ӵ���Ʒ��ǩ�������У���ƷidΪ{}����ǩidΪ{}������idΪ{}",productId,labelId,relation.getProductLabelRelationId());
	}
	
	private void checkLabelId(Integer labelId) {
		if (labelId == null){
			log.warn("�����labelIdΪ��");
			throw new RuntimeException("�����labelIdΪ��");
		}
	}

	/**
	 * ������Ʒ���ǩ
	 * @param productId
	 * @param labelNames
	 */
	public void addToLabelByProductId(Integer productId,List<String> labelNames){
		List<Integer> list = addToLabelAndReturnIdList(labelNames);
		for (Integer labelId : list) {
			addToRelation(productId,labelId);
		}
	}
	
	private void checkProductIdList(List<Integer> productIds){
		if (productIds == null||productIds.isEmpty()){
			log.warn("�����һ����ƷidΪ��");
			throw new RuntimeException("�����һ����ƷidΪ��");
		}
	}
	
	/**
	 * ��Ʒ�������ǩ
	 * @param productIds
	 * @param labelNames
	 */
	public void addToLabelByProductIdList(List<Integer> productIds,List<String> labelNames){
		checkProductIdList(productIds);
		for (Integer productId : productIds) {
			addToLabelByProductId(productId,labelNames);
		}
	}
	
	/**
	 * ɾ����Ʒ��ǩ
	 * @param productId
	 * @param labelId
	 */
	public void deleteProductLabel(Integer productId,Integer labelId){
		checkProductId(productId);
		checkLabelId(labelId);
		DetachedCriteria criteria = DetachedCriteria.forClass(ProductLabelRelation.class);
		criteria.add(Restrictions.eq("productId", productId));
		criteria.add(Restrictions.eq("labelId", labelId));
		criteria.add(Restrictions.eq("status", ProductLabelRelationStatus.BEEN_USED));
		List<ProductLabelRelation> relationList = productLabelRelationDao.findByCriteria(criteria);
		if (relationList == null || relationList.isEmpty()){
			log.warn("����������ƷidΪ{},��ǩidΪ{}����״̬Ϊ���õļ�¼������",productId,labelId);
			throw new RuntimeException("����������ƷidΪ"+productId+",��ǩidΪ"+labelId+"����״̬Ϊ���õļ�¼������");
		}
		if (relationList.size()>1){
			log.warn("����������ƷidΪ{},��ǩidΪ{}�ļ�¼�ж���",productId,labelId);
			throw new RuntimeException("����������ƷidΪ"+productId+",��ǩidΪ"+labelId+"�ļ�¼�ж���");
		}
		ProductLabelRelation relation = relationList.get(0);
		relation.setStatus(ProductLabelRelationStatus.BEEN_DELETE);
		updateProductLabelRelation(relation);
	}
	
	private void updateProductLabelRelation(ProductLabelRelation relation){
		relation.setGmtModify(DateFormat.getCurrentTime());
		relation.setVersion(relation.getVersion()+1);
		productLabelRelationDao.update(relation);
	}
	
	private void checkCategoryList(List<String> categoryNames){
		if (categoryNames == null || categoryNames.isEmpty()){
			log.warn("����ķ����б�Ϊ��");
			throw new RuntimeException("����ķ����б�Ϊ��");
		}
	}
	
	/**
	 * ���ݸ�id��ѯ����
	 * @param categoryId
	 * @return
	 */
	public List<Category> findCategory(Integer parentId){
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.add(Restrictions.eq("parentId", parentId));
		dc.add(Restrictions.eq("status", CategoryStatus.BEEN_USED));
		List<Category> list = categoryDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			return null;
		}
		return list;
	}
	
	/**
	 * ���ݷ���id�޸�������
	 * @param categoryId
	 * @param categoryName
	 */
	public void updateCategoryInfo(Integer categoryId,String categoryName){
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.add(Restrictions.eq("categoryId", categoryId));
		dc.add(Restrictions.eq("status", CategoryStatus.BEEN_USED));
		List<Category> list = categoryDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("�޸�ʧ�ܣ�����id��{}������",categoryId);
			throw new RuntimeException("�޸�ʧ�ܣ�����id��"+categoryId+"������");
		}
		if (list.size()>1){
			log.warn("�����쳣������idΪ{}�������ж���",categoryId);
			throw new RuntimeException("�����쳣������idΪ"+categoryId+"�������ж���");
		}
		Category category = list.get(0);
		category.setCategoryName(categoryName);
		category.setCategoryDesc(categoryName);
		updateCategory(category);
		log.info("�޸ķ������Ƴɹ�������idΪ{},������Ϊ{}",categoryId,categoryName);
	}
	
	/**
	 * ��ѯ���з���
	 * @return
	 */
	public List<Category> findAllCategory(){
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.add(Restrictions.eq("status", CategoryStatus.BEEN_USED));
		List<Category> list = categoryDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			return null;
		}
		return list;
	}
	
	private Category findCategoryByCategoryName(String categoryName){
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.add(Restrictions.eq("categoryName", categoryName));
		dc.add(Restrictions.eq("status", CategoryStatus.BEEN_USED));
		List<Category> list = categoryDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			return null;
		}
		if (list.size()>1){
			log.warn("�����쳣��������Ϊ{}�ж�����¼",categoryName);
			throw new RuntimeException("�����쳣��������Ϊ"+categoryName+"�ж�����¼");
		}
		return list.get(0);
	}
	
	private void checkCategoryName(String categoryName){
		if (StringUtils.isBlank(categoryName)){
			log.warn("����ķ�����Ϊ��");
			throw new RuntimeException("����ķ�����Ϊ��");
		}
	}
	
	private void checkParentId(Integer parentId){
		if (parentId == null){
			log.warn("����ĸ�����idΪ��");
			throw new RuntimeException("����ĸ�����idΪ��");
		}
	}
	
	/**
	 * �����Ʒ�����
	 * @param categoryNames
	 * @return
	 */
	//XXX ��ӷ��ഫ������id���ӷ�������
	public Category addToCategory(Integer parentId,String categoryName){
		checkParentId(parentId);
		checkCategoryName(categoryName);
		Category category = findCategoryByCategoryName(categoryName);
		if (category != null){
			log.warn("������Ϊ{}�ķ����Ѵ��ڣ���ӷ���ʧ��",categoryName);
			throw new RuntimeException("������Ϊ"+categoryName+"�ķ����Ѵ��ڣ���ӷ���ʧ��");
		}
		Category cg = new Category();
		cg.setCategoryName(categoryName);
		cg.setCategoryDesc(categoryName);
		cg.setParentId(parentId);
		cg.setStatus(CategoryStatus.BEEN_USED);
		cg.setGmtCreate(DateFormat.getCurrentTime());
		cg.setGmtModify(DateFormat.getCurrentTime());
		cg.setVersion(1);
		categoryDao.save(cg);
		return cg;
	}
	
	private void checkProduct(Product product){
		if (product == null){
			log.warn("��ƷΪ��");
			throw new RuntimeException("��ƷΪ��");
		}
		Integer productId = product.getProductId();
		if (StringUtils.isBlank(product.getProductName())){
			log.warn("��ƷidΪ{}��Ʒ����Ϊ��",productId);
			throw new RuntimeException("��ƷidΪ"+productId+"��Ʒ����Ϊ��");
		}
		if (product.getProductPrice() == null){
			log.warn("��ƷidΪ{}��Ʒ�۸�Ϊ��",productId);
			throw new RuntimeException("��ƷidΪ"+productId+"��Ʒ�۸�Ϊ��");
		}
		if (StringUtils.isBlank(product.getStatus())){
			log.warn("��ƷidΪ{}��Ʒ״̬Ϊ��",productId);
			throw new RuntimeException("��ƷidΪ"+productId+"��Ʒ״̬Ϊ��");
		}
	}
	
	//�޸���Ʒ
	private void updateProduct(Product product){
		product.setGmtModify(DateFormat.getCurrentTime());
		product.setVersion(product.getVersion()+1);
		productDao.update(product);
	}
	
	private void checkProductPropDTOList(List<ProductPropDTO> list){
		if (list == null || list.isEmpty()){
			log.warn("�����productPropDTOListΪ��");
			throw new RuntimeException("�����productPropDTOListΪ��");
		}
	}
	
	private void checkPropDTO(PropDTO propDTO){
		if (propDTO == null){
			log.warn("�����propDTOΪ��");
			throw new RuntimeException("�����propDTOΪ��");
		}
		if (StringUtils.isBlank(propDTO.getPropName())){
			log.warn("������Ϊ��");
			throw new RuntimeException("������Ϊ��");
		}
		if (StringUtils.isBlank(propDTO.getPropType())){
			log.warn("��������Ϊ��");
			throw new RuntimeException("��������Ϊ��");
		}
	}
	
	private void checkPropDTOList(List<PropDTO> propList){
		if (propList == null || propList.isEmpty()){
			log.warn("���������DTOΪ��");
			throw new RuntimeException("���������DTOΪ��");
		}
	}
	
	private void checkPropDefine(ProductPropDefine define){
		if (define == null){
			log.warn("��������Զ���Ϊ��");
			throw new RuntimeException("��������Զ���Ϊ��");
		}
		if (StringUtils.isBlank(define.getPropName())){
			log.warn("�����������Ϊ��");
			throw new RuntimeException("�����������Ϊ��");
		}
		if (StringUtils.isBlank(define.getPropType())){
			log.warn("��������Ϊ��");
			throw new RuntimeException("��������Ϊ��");
		}
		if (define.getFieldRequired() == null){
			log.warn("������Ƿ�Ϊ�����ֶ�Ϊ��");
			throw new RuntimeException("������Ƿ�Ϊ�����ֶ�Ϊ��");
		}
		if (StringUtils.isBlank(define.getPropCode())){
			log.warn("�ֶδ���Ϊ��");
			throw new RuntimeException("�ֶδ���Ϊ��");
		}
	}
	
	//�ж����Զ�������Ƿ�����������ͬ��ģ������ͬ��״̬Ϊɾ�������ݣ�������״̬��Ϊ����
	private List<ProductPropDefine> checkPropDefineExist(ProductPropDefine define){
		DetachedCriteria dc = DetachedCriteria.forClass(ProductPropDefine.class);
		dc.add(Restrictions.eq("propName", define.getPropName()));
		dc.add(Restrictions.eq("modelId", define.getModelId()));
		dc.add(Restrictions.eq("status", ProductPropDefineStatus.BEEN_DELETE));
		return productPropDefineDao.findByCriteria(dc);
	}
	
	private boolean checkPropDefineExist2(ProductPropDefine define){
		DetachedCriteria dc = DetachedCriteria.forClass(ProductPropDefine.class);
		dc.add(Restrictions.eq("propName", define.getPropName()));
		dc.add(Restrictions.eq("modelId", define.getModelId()));
		dc.add(Restrictions.eq("status", ProductPropDefineStatus.BEEN_USED));
		List<ProductPropDefine> list = productPropDefineDao.findByCriteria(dc);
		if (list != null && !list.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * ��ӵ����Զ����
	 * @param propDTO
	 * @return
	 */
	public void addPropDefine(ProductPropDefine define){
		checkPropDefine(define);
		List<ProductPropDefine> list = checkPropDefineExist(define);
		if (checkPropDefineExist2(define)){
			log.warn("���Զ����Ѵ��ڣ������ظ����");
			throw new RuntimeException("���Զ����Ѵ��ڣ������ظ����");
		}
		if (list != null && !list.isEmpty()){
			ProductPropDefine prop = list.get(0);
			prop.setStatus(ProductPropDefineStatus.BEEN_USED);
			updatePropDefine(prop);
		}else {
			define.setStatus(ProductPropDefineStatus.BEEN_USED);
			define.setGmtCreate(DateFormat.getCurrentTime());
			define.setGmtModify(DateFormat.getCurrentTime());
			define.setVersion(1);
			productPropDefineDao.save(define);
			log.info("�ɹ���ӵ����Զ�����У�����idΪ"+define.getPropId());
		}
	}
	
	/**
	 * �����Ʒ���Ա�
	 * @param productId
	 * @param list
	 */
	public void addToProductProp(Integer productId,List<ProductProp> list){
		checkProductId(productId);
		for (ProductProp productProp : list) {
			ProductProp prop = new ProductProp();
			prop.setProductId(productId);
			prop.setPropId(productProp.getPropId());
			prop.setPropValue(productProp.getPropValue());
			prop.setStatus(ProductPropStatus.BEEN_USED);
			prop.setGmtCreate(DateFormat.getCurrentTime());
			prop.setGmtModify(DateFormat.getCurrentTime());
			prop.setVersion(1);
			productPropDao.save(prop);
		}
	}
	
	private void checkPropNameList(List<String> propNames){
		if (propNames == null || propNames.isEmpty()){
			log.warn("�������б�Ϊ��");
			throw new RuntimeException("�������б�Ϊ��");
		}
	}
	
	private void checkPropId(Integer propId){
		if (propId == null){
			log.warn("�����propIdΪ��");
			throw new RuntimeException("�����propIdΪ��");
		}
	}
	
	private void checkModelId(Integer modelId){
		if (modelId == null ){
			log.warn("�����modelIdΪ��");
			throw new RuntimeException("�����modelIdΪ��");
		}
	}
	
	private ProductPropDefine findpropDefineByProDefineId(Integer propDefineId){
		checkPropId(propDefineId);
		DetachedCriteria dc = DetachedCriteria.forClass(ProductPropDefine.class);
		dc.add(Restrictions.eq("propId", propDefineId));
		dc.add(Restrictions.eq("status", ProductPropDefineStatus.BEEN_USED));
		List<ProductPropDefine> list = productPropDefineDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("���Զ������idΪ{}�ļ�¼Ϊ��",propDefineId);
			throw new RuntimeException("���Զ������idΪ"+propDefineId+"�ļ�¼Ϊ��");
		}
		if (list.size()>1){
			log.warn("�����쳣�����Ա���idΪ{}�ļ�¼�ж���",propDefineId);
			throw new RuntimeException("�����쳣�����Ա���idΪ"+propDefineId+"�ļ�¼�ж���");
		}
		return list.get(0);
	}
	
	/**
	 * ɾ��ģ���µ����Զ���
	 * @param propDefineId
	 */
	public void deletePropDefine(Integer propDefineId){
		ProductPropDefine define = findpropDefineByProDefineId(propDefineId);
		define.setStatus(ProductPropDefineStatus.BEEN_DELETE);
		updatePropDefine(define);
	}
	
	/**
	 * ɾ����Ʒ����
	 * @param productId
	 * @param propId
	 */
	public void deleteProductProp(Integer productId,Integer propId){
		checkProductId(productId);
		checkPropId(propId);
		DetachedCriteria criteria = DetachedCriteria.forClass(ProductProp.class);
		criteria.add(Restrictions.eq("productId", productId));
		criteria.add(Restrictions.eq("propId", propId));
		criteria.add(Restrictions.ne("status", ProductStatus.BEEN_DELETE));
		List<ProductProp> list = productPropDao.findByCriteria(criteria);
		if (list == null || list.isEmpty()){
			log.warn("��Ʒ���в�������ƷidΪ{}������idΪ{}�ļ�¼",productId,propId);
			throw new RuntimeException("��Ʒ���в�������ƷidΪ"+productId+"������idΪ"+propId+"�ļ�¼");
		}
		if (list.size()>1){
			log.warn("���Ա�����ƷidΪ{},����idΪ{}�ļ�¼�ж���",productId,propId);
			throw new RuntimeException("���Ա�����ƷidΪ"+productId+",����idΪ"+propId+"�ļ�¼�ж���");
		}
		ProductProp prop = list.get(0);
		prop.setStatus(ProductPropStatus.BEEN_DELETE);
		updateProductProp(prop);
	}
	
	private void updateProductProp(ProductProp prop){
		prop.setGmtModify(DateFormat.getCurrentTime());
		prop.setVersion(prop.getVersion()+1);
		productPropDao.update(prop);
	}
	
	private void checkAddToProductDTO(AddToProductDTO addToProductDTO){
		if (addToProductDTO == null){
			log.warn("�����dtoƷΪ��");
			throw new RuntimeException("�����dtoΪ��");
		}
		if (StringUtils.isBlank(addToProductDTO.getProductName())){
			log.warn("�����dto����Ʒ����Ϊ��");
			throw new RuntimeException("�����dto����Ʒ����Ϊ��");
		}
		if (addToProductDTO.getProductPrice() == null || addToProductDTO.getProductPrice() <= 0){
			log.warn("�����dto����Ʒ�۸���Ϊnull�Ҳ���С��0");
			throw new RuntimeException("�����dto����Ʒ�۸���Ϊnull�Ҳ���С��0");
		}
		if (StringUtils.isBlank(addToProductDTO.getSpec1Name())){
			log.warn("�����dto����Ʒ���1Ϊ��");
			throw new RuntimeException("�����dto����Ʒ���1Ϊ��");
		}
		if (StringUtils.isBlank(addToProductDTO.getSpec1Values())){
			log.warn("�����dto����Ʒ���1ȡֵΪ��");
			throw new RuntimeException("�����dto�Ĺ��1ȡֵΪ��");
		}
		if (StringUtils.isBlank(addToProductDTO.getBrand())){
			log.warn("�����dto��Ʒ��Ϊ��");
			throw new RuntimeException("�����dto��Ʒ��Ϊ��");
		}
		if (StringUtils.isBlank(addToProductDTO.getIsbn())){
			log.warn("�����dto��isbnΪ��");
			throw new RuntimeException("�����dto��isbnΪ��");
		}
		if (StringUtils.isBlank(addToProductDTO.getProductDesc())){
			log.warn("�����dto����Ʒ����Ϊ��");
			throw new RuntimeException("�����dto����Ʒ����Ϊ��");
		}
		if (StringUtils.isBlank(addToProductDTO.getProductImg())){
			log.warn("�����dto����ƷͼƬΪ��");
			throw new RuntimeException("�����dto����ƷͼƬΪ��");
		}
		if (StringUtils.isBlank(addToProductDTO.getProductNo())){
			log.warn("�����dto����Ʒ����Ϊ��");
			throw new RuntimeException("�����dto����Ʒ����Ϊ��");
		}
	}
	//XXX ��ѯĳ��������������Զ���
	/**
	 * �����Ʒ��
	 * @param addToProductDTO
	 */
	//XXX �����Ʒʱ����ֱ����ӷ��࣬������id
	public Integer addProduct(AddToProductDTO addToProductDTO){
		checkAddToProductDTO(addToProductDTO);
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("brand", addToProductDTO.getBrand()));
		dc.add(Restrictions.eq("productNo", addToProductDTO.getProductNo()));
		dc.add(Restrictions.ne("status", ProductSkuStatus.BEEN_DELETE));
		List<Product> list = productDao.findByCriteria(dc);
		if (list != null && !list.isEmpty()){
			log.warn("�����Ѵ��ڣ���Ҫ�ظ����");
			throw new RuntimeException("�����Ѵ��ڣ���Ҫ�ظ����");
		}
		Product product = new Product();
		BeanUtils.copyProperties(addToProductDTO, product);
		//�����Ʒ��
		product.setStatus(ProductStatus.OUT_OF_SHELVE);
		product.setGmtCreate(DateFormat.getCurrentTime());
		product.setGmtModify(DateFormat.getCurrentTime());
		product.setVersion(1);
		productDao.save(product);
		addToProductProp(product.getProductId(),addToProductDTO.getProductPropList());
		List<SkuDTO> skuDTOList = addToProductDTO.getSkuDTOList();
		for (SkuDTO skuDTO : skuDTOList) {
			addToProductSku(product.getProductId(),skuDTO);
		}
		log.info("�ɹ���ӵ���Ʒ���У���ƷidΪ{}",product.getProductId());
		return product.getProductId();
	}
	
	private Model findModelByModelId(Integer modelId){
		checkModelId(modelId);
		DetachedCriteria dc = DetachedCriteria.forClass(Model.class);
		dc.add(Restrictions.eq("modelId", modelId));
		dc.add(Restrictions.eq("status", ModelStatus.BEEN_USED));
		List<Model> list = modelDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("ģ�ͱ���idΪ{}�����ݲ�����",modelId);
			throw new RuntimeException("ģ�ͱ���idΪ"+modelId+"�����ݲ�����");
		}
		if (list.size()>1){
			log.warn("�����쳣��ģ�ͱ���idΪ{}�������ж���",modelId);
			throw new RuntimeException("�����쳣��ģ�ͱ���idΪ"+modelId+"�������ж���");
		}
		return list.get(0);
	}
	
	private Product findProductByCriteria(Integer productId){
		checkProductId(productId);
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("productId", productId));
		criteria.add(Restrictions.ne("status", ProductStatus.BEEN_DELETE));
		List<Product> productList =  productDao.findByCriteria(criteria);
		if (productList == null || productList.isEmpty()){
			return null;
		}
		if (productList.size()>1){
			log.warn("�����쳣��idΪ{}����Ʒ�ж�����¼",productId);
			throw new RuntimeException("�����쳣��idΪ"+productId+"����Ʒ�ж�����¼");
		}
		return productList.get(0);
	}
	
	private Integer findCategoryIdByProductId(Integer productId){
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("productId", productId));
		dc.add(Restrictions.ne("status", ProductStatus.BEEN_DELETE));
		List<Product> list = productDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("��Ʒ����idΪ{}�ļ�¼Ϊ��",productId);
			throw new RuntimeException("��Ʒ����idΪ"+productId+"�ļ�¼Ϊ��");
		}
		if (list.size()>1){
			log.warn("�����쳣����Ʒ����idΪ{}�ļ�¼�ж���",productId);
			throw new RuntimeException("�����쳣����Ʒ����idΪ"+productId+"�ļ�¼�ж���");
		}
		return list.get(0).getCategoryId();
	}
	
	
	
	/**
	 * ���sku��
	 * @param productId
	 * @param skuNo
	 */
	public void addToProductSku(Integer productId,SkuDTO skuDTO){
		Integer categoryId = findCategoryIdByProductId(productId);
		Integer modelId = findModelIdByCategoryId(categoryId);
		Model model = findModelByModelId(modelId);
		String spec1Values = model.getSpec1Values();
		String spec2Values = model.getSpec2Values();
		String[] spec1 = spec1Values.split(",");
		String[] spec2 = spec2Values.split(",");
		String sp1 = skuDTO.getSpec1Value();
		String sp2 = skuDTO.getSpec2Value();
		if (!Arrays.asList(spec1).contains(sp1) || !Arrays.asList(spec2).contains(sp2)){
			log.warn("����Ĺ���ڹ涨��Χ��");
			throw new RuntimeException("����Ĺ���ڹ涨��Χ��");
		}
		ProductSku sku = new ProductSku();
		BeanUtils.copyProperties(skuDTO, sku);
		sku.setProductId(productId);
		sku.setStatus(ProductSkuStatus.BEEN_USED);
		sku.setGmtCreate(DateFormat.getCurrentTime());
		sku.setGmtModify(DateFormat.getCurrentTime());
		sku.setVersion(1);
		productSkuDao.save(sku);
		log.info("�ɹ���ӵ�sku���У���ƷidΪ{}��skuIdΪ{}",productId,sku.getSkuId());
	}

	/**
	 * ��ȡһ����Ʒ��Ϣ
	 * @param productId
	 * @return Product
	 */
	public Product findProduct(Integer productId){
		return findProductByCriteria(productId);
	}
	
	private List<Product> findProduct(List<Integer> productIds){
		checkProductIdList(productIds);
		List<Product> list = new ArrayList<Product>();
		for (Integer productId : productIds) {
			list.add(findProduct(productId));
		}
		return list;
	}
	
	private void checkProductId(Integer productId) {
		if (productId == null){
			log.warn("�������ƷidΪ��");
			throw new RuntimeException("�������ƷidΪ��");
		}
	}
	
	/**
	 * ������ȡ��Ʒ��Ϣ
	 * @param productIdList
	 * @return
	 */
	public List<Product> findProductListByCategoryId(Integer categoryId){
		checkCategoryId(categoryId);
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("categoryId", categoryId));
		dc.add(Restrictions.ne("status", ProductStatus.BEEN_DELETE));
		List<Product> list = productDao.findByCriteria(dc);
		return list;
	}
	
	private ProductSku findProductSkuByCriteria(Integer skuId){
		checkSkuId(skuId);
		DetachedCriteria criteria = DetachedCriteria.forClass(ProductSku.class);
		criteria.add(Restrictions.eq("skuId", skuId));
		criteria.add(Restrictions.eq("status", ProductSkuStatus.BEEN_USED));
		List<ProductSku> skuList =  productSkuDao.findByCriteria(criteria);
		if (skuList == null || skuList.isEmpty()){
			log.warn("û�в�ѯ��skuidΪ{}����Ʒ",skuId);
			throw new RuntimeException("û�в�ѯ��skuidΪ"+skuId+"����Ʒ");
		}
		if (skuList.size()>1){
			log.warn("�����쳣��skuidΪ{}����Ʒ�ж�����¼",skuId);
			throw new RuntimeException("�����쳣��skuidΪ"+skuId+"����Ʒ�ж�����¼");
		}
		return skuList.get(0);
	} 
	
	/**
	 * ͨ��skuId��ȡsku
	 * @param productId
	 * @return
	 */
	public ProductSku findSkuBySkuId(Integer skuId){
		return findProductSkuByCriteria(skuId);
	}

	private void checkSkuId(Integer skuId) {
		if (skuId == null){
			log.warn("skuIdΪ��");
			throw new RuntimeException("skuIdΪ��");
		}
	}
	
	/**
	 * ͨ����Ʒid��ȡsku�б�
	 * @param productId
	 * @return
	 */
	public List<ProductSku> findSkuList(Integer productId){
		checkProductId(productId);
		DetachedCriteria criteria = DetachedCriteria.forClass(ProductSku.class);
		criteria.add(Restrictions.eq("productId", productId));
		criteria.add(Restrictions.eq("status", ProductSkuStatus.BEEN_USED));
		return productSkuDao.findByCriteria(criteria);
	}
	
	/**
	 * ɾ��������Ʒ
	 * @param productId
	 */
	public void deleteProduct(Integer productId){
		Product product = findProduct(productId);
		product.setStatus(ProductStatus.BEEN_DELETE);
		updateProduct(product);
		DetachedCriteria dc = DetachedCriteria.forClass(ProductLabelRelation.class);
		dc.add(Restrictions.eq("subjectId", productId));
		dc.add(Restrictions.eq("status", ProductLabelRelationStatus.BEEN_USED));
		List<ProductLabelRelation> list = productLabelRelationDao.findByCriteria(dc);
		if (list != null && !list.isEmpty()){
			for (ProductLabelRelation relation : list) {
				relation.setStatus(ProductLabelRelationStatus.BEEN_DELETE);
				updateProductLabelRelation(relation);
			} 
		}
	}
	
	/**
	 * ����ɾ����Ʒ
	 * @param productIdList
	 */
	public void deleteProduct(List<Integer> productIdList){
		checkProductIdList(productIdList);
		for (Integer productId : productIdList) {
			deleteProduct(productId);
		}
	}

	/**
	 * ɾ����Ʒsku
	 * @param skuId
	 */
	public void deleteProductSku(Integer skuId){
		ProductSku sku = findSkuBySkuId(skuId);
		sku.setStatus(ProductSkuStatus.BEEN_DELETE);
		updateSku(sku);
	}
	
	private void checkSkuIdList(List<Integer> skuIdList){
		if (skuIdList == null|| skuIdList.isEmpty()){
			throw new RuntimeException("�����skuIdListΪ��");
		}
	}
	
	/**
	 * ����ɾ����Ʒsku
	 * @param skuIdList
	 */
	public void deleteProductSku(List<Integer> skuIdList){
		checkSkuIdList(skuIdList);
		for (Integer skuId : skuIdList) {
			deleteProductSku(skuId);
		}
	}
	
	/**
	 * ������Ʒ�۸�
	 * @param productId
	 * @param price
	 */
	public void updateProductPrice(int productId,Long price){
		if (price == null){
			log.warn("����ļ۸�Ϊ��");
			throw new RuntimeException("����ļ۸�Ϊ��");
		}
		Product product = findProduct(productId);
		product.setProductPrice(price);
		updateProduct(product);
	}
	
	/**
	 * ��ȡ��Ʒ�Ĺ����Ϣ
	 * @param productId
	 * @return
	 */
	public SpecDTO findProductSpec(int productId){
		Product product = findProduct(productId);
		SpecDTO specDTO = new SpecDTO();
		BeanUtils.copyProperties(product, specDTO);
		return specDTO;
	}
	
	//�޸�sku��
	private void updateSku(ProductSku sku) {
		sku.setGmtModify(DateFormat.getCurrentTime());
		sku.setVersion(sku.getVersion()+1);
		productSkuDao.update(sku);
	}
	
	/**
	 * �ɱ�ǩ������Ʒ
	 * @param labelName
	 * @return
	 */
	public List<Product> findProductByLabel(String labelName){
		if (StringUtils.isBlank(labelName)){
			log.warn("����ı�ǩ��Ϊ��");
			throw new RuntimeException("����ı�ǩ��Ϊ��");
		}
		Integer labelId = findLabelIdByLabelName(labelName);
		return findProduct(findSubjectIdByLabelId(labelId));
	}
	
	/**
	 * ��ȡ������Ʒ����ϸ��Ϣ
	 * @param productId
	 * @return ProductDTO
	 */
	public ProductDTO findProductDetail(int productId){
//		Product product = findProduct(productId);
//		ProductDTO productDTO = new ProductDTO();
//		BeanUtils.copyProperties(product, productDTO);
//		productDTO.setCategoryName(findCategoryNameByCategoryId(product.getCategoryId()));
//		productDTO.setList(findPropByProductId(productId));
//		productDTO.setLabelList(findLabelNameByProductId(productId));
//		productDTO.setSkuList(findSkuList(productId));
//		return productDTO;
		return null;
	}
	
	/**
	 * ������ȡ��Ʒ����ϸ��Ϣ
	 * @param productIdList
	 * @return List<ProductDTO>
	 */
	public List<ProductDTO> findProductDetail(List<Integer> productIdList){
		checkProductIdList(productIdList);
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		for (Integer productId : productIdList) {
			list.add(findProductDetail(productId));
		}
		return list;
	}
	
	/**
	 * ��Ʒ�ϼ�
	 * @param productId
	 */
	public void shelveProduct(Integer productId){
		Product product = findProdctStatusOutOfShelve(productId);
		product.setStatus(ProductStatus.ON_SHELVE);
		updateProduct(product);
	}
	
	//����״̬Ϊ�¼ܵ���Ʒ
	private Product findProdctStatusOutOfShelve(Integer productId){
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("productId", productId));
		dc.add(Restrictions.eq("status", ProductStatus.OUT_OF_SHELVE));
		List<Product> list = productDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			return null;
		}
		if (list.size()>1){
			log.warn("���ݿ��쳣����ƷidΪ{},״̬Ϊ���ϼܵ������ж�����¼",productId);
			throw new RuntimeException("���ݿ��쳣����ƷidΪ"+productId+",״̬Ϊ���¼ܵ������ж�����¼");
		}
		return list.get(0);
	}
	
	//����״̬Ϊ�ϼܵ���Ʒ
	private Product findProdctStatusOnShelve(Integer productId){
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("productId", productId));
		dc.add(Restrictions.eq("status", ProductStatus.ON_SHELVE));
		List<Product> list = productDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			return null;
		}
		if (list.size()>1){
			log.warn("���ݿ��쳣����ƷidΪ{},״̬Ϊ���ϼܵ������ж�����¼",productId);
			throw new RuntimeException("���ݿ��쳣����ƷidΪ"+productId+",״̬Ϊ���ϼܵ������ж�����¼");
		}
		return list.get(0);
	}
	
	/**
	 * ��Ʒ�¼�
	 * @param productId
	 */
	public void outOfShelve(Integer productId){
		//XXX �ϼܡ��¼ܡ�ɾ��������Ҫ�ϸ���״̬
		Product product = findProdctStatusOnShelve(productId);
		product.setStatus(ProductStatus.OUT_OF_SHELVE);
		updateProduct(product);
	}
	
	/**
	 * �޸���Ʒ��
	 * @param productId
	 * @param productDTO
	 */
	public void updateProductInfo(Product product){
		checkProduct(product);
		updateProduct(product);
	}
	
	private void checkProductDTO(ProductDTO productDTO){
		if (productDTO == null){
			throw new RuntimeException("�����DTOΪ��");
		}
		//���Կ��Բ����ã����޸�ĳЩ����
	}
	
	private List<String> findLabelNameByProductId(Integer productId){
		checkProductId(productId);
		DetachedCriteria criteria = DetachedCriteria.forClass(ProductLabelRelation.class);
		criteria.add(Restrictions.eq("subjectId", productId));
		criteria.add(Restrictions.eq("status", ProductLabelRelationStatus.BEEN_USED));
		List<ProductLabelRelation> relationList = productLabelRelationDao.findByCriteria(criteria);
		if (relationList == null || relationList.isEmpty()){
			return null;
		}
		List<String> labelList = new ArrayList<String>();
		for (ProductLabelRelation relation : relationList) {
			labelList.add(findLabelNameByLabelId(relation.getLabelId()));
		}
		return labelList;
	}
	
	private String findLabelNameByLabelId(int labelId){
		checkLabelId(labelId);
		DetachedCriteria criteria = DetachedCriteria.forClass(Label.class);
		criteria.add(Restrictions.eq("labelId", labelId));
		criteria.add(Restrictions.eq("status", LabelStatus.BEEN_USED));
		List<Label> labelList = labelDao.findByCriteria(criteria);
		if (labelList == null || labelList.isEmpty()){
			log.warn("��ǩ���в����ڱ�ǩidΪ{}�ļ�¼",labelId);
			throw new RuntimeException("��ǩ���в����ڱ�ǩidΪ"+labelId+"�ļ�¼");
		}
		if (labelList.size()>1){
			log.warn("�����쳣����ǩ���д��ڶ�����ǩidΪ{}�ļ�¼",labelId);
			throw new RuntimeException("�����쳣����ǩ���д��ڶ�����ǩidΪ"+labelId+"�ļ�¼");
		}
		return labelList.get(0).getLabelName();
	}
	
	private List<ProductPropDTO> findPropByProductId(Integer productId){
		checkProductId(productId);
		DetachedCriteria criteria = DetachedCriteria.forClass(ProductProp.class);
		criteria.add(Restrictions.eq("productId", productId));
		criteria.add(Restrictions.eq("status", ProductPropStatus.BEEN_USED));
		List<ProductProp> list = productPropDao.findByCriteria(criteria);
		if (list == null || list.isEmpty()){
			return null;
		}
		List<ProductPropDTO> propList = new ArrayList<ProductPropDTO>();
		for (ProductProp productProp : list) {
			ProductPropDTO propDTO = new ProductPropDTO();
			propDTO.setPropValue(productProp.getPropValue());
			propDTO.setPropName(findPropNameByPropId(productProp.getPropId()));
			propList.add(propDTO);
		}
		return propList;
	}
	
	private String findPropNameByPropId(Integer propId){
		checkPropId(propId);
		return findPropEcho(propId).getPropName();
	}
	
	private void checkCategoryId(Integer categoryId){
		if (categoryId == null){
			log.warn("����ķ���idΪ��");
			throw new RuntimeException("����ķ���idΪ��");
		}
	}
	
	private Category findCategoryByCategoryId(Integer categoryId){
		checkCategoryId(categoryId);
		DetachedCriteria criteria = DetachedCriteria.forClass(Category.class);
		criteria.add(Restrictions.eq("categoryId", categoryId));
		criteria.add(Restrictions.eq("status", CategoryStatus.BEEN_USED));
		List<Category> list = categoryDao.findByCriteria(criteria);
		if (list == null || list.isEmpty()){
			log.warn("������в�����idΪ{}�ķ���",categoryId);
			throw new RuntimeException("������в�����idΪ"+categoryId+"�ķ���");
		}
		if (list.size()>1){
			throw new RuntimeException("�����쳣��������д��ڶ��idΪ"+categoryId+"�ļ�¼");
		}
		return list.get(0);
	}
	
	private void checkLabelName(String labelName){
		if (StringUtils.isBlank(labelName)){
			log.warn("��ǩ��Ϊ��");
			throw new RuntimeException("��ǩ��Ϊ��");
		}
	}
	
	private Integer findLabelIdByLabelName(String labelName){
		checkLabelName(labelName);
		DetachedCriteria criteria = DetachedCriteria.forClass(Label.class);
		criteria.add(Restrictions.eq("labelName", labelName));
		criteria.add(Restrictions.eq("status", LabelStatus.BEEN_USED));
		List<Label> labelList = labelDao.findByCriteria(criteria);
		if (labelList == null || labelList.isEmpty()){
			log.warn("û�в����ǩ��Ϊ{}����״̬Ϊ���õļ�¼",labelName);
			throw new RuntimeException("û�в����ǩ��Ϊ"+labelName+"����״̬Ϊ���õļ�¼");
		}
		if (labelList.size()>1){
			log.warn("�����쳣����ǩ��Ϊ{}�ı�ǩ�ж�����¼",labelName);
			throw new RuntimeException("�����쳣����ǩ��Ϊ"+labelName+"�ı�ǩ�ж�����¼");
		}
		return labelList.get(0).getLabelId();
	}
	
	private List<Integer> findSubjectIdByLabelId(Integer labelId){
		checkLabelId(labelId);
		DetachedCriteria criteria = DetachedCriteria.forClass(ProductLabelRelation.class);
		criteria.add(Restrictions.eq("labelId", labelId));
		criteria.add(Restrictions.eq("status", ProductLabelRelationStatus.BEEN_USED));
		List<ProductLabelRelation> relationList = productLabelRelationDao.findByCriteria(criteria);
		if (relationList == null|| relationList.isEmpty()){
			log.warn("�������в����ڱ�ǩidΪ{}�ļ�¼",labelId);
			throw new RuntimeException("�������в����ڱ�ǩidΪ"+labelId+"�ļ�¼");
		}
		List<Integer> subjectIdList = new ArrayList<Integer>();
		for (ProductLabelRelation relation : relationList) {
			subjectIdList.add(relation.getSubjectId());
		}
		return subjectIdList;
		
	}
	
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public ProductLabelRelationDao getProductLabelRelationDao() {
		return productLabelRelationDao;
	}

	public void setProductLabelRelationDao(
			ProductLabelRelationDao productLabelRelationDao) {
		this.productLabelRelationDao = productLabelRelationDao;
	}

	public LabelDao getLabelDao() {
		return labelDao;
	}

	public void setLabelDao(LabelDao labelDao) {
		this.labelDao = labelDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public ProductPropDefineDao getProductPropDefineDao() {
		return productPropDefineDao;
	}

	public void setProductPropDefineDao(ProductPropDefineDao productPropDefineDao) {
		this.productPropDefineDao = productPropDefineDao;
	}

	public ProductPropDao getProductPropDao() {
		return productPropDao;
	}

	public void setProductPropDao(ProductPropDao productPropDao) {
		this.productPropDao = productPropDao;
	}

	public ProductSkuDao getProductSkuDao() {
		return productSkuDao;
	}

	public void setProductSkuDao(ProductSkuDao productSkuDao) {
		this.productSkuDao = productSkuDao;
	}

	public ModelDao getModelDao() {
		return modelDao;
	}

	public void setModelDao(ModelDao modelDao) {
		this.modelDao = modelDao;
	}
}
