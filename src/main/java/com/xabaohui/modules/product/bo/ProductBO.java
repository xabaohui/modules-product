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
	 * 根据属性id查询属性定义
	 * @param propId
	 * @return
	 */
	public ProductPropDefine findPropEcho(Integer propId){
		DetachedCriteria dc = DetachedCriteria.forClass(ProductPropDefine.class);
		dc.add(Restrictions.eq("propId", propId));
		dc.add(Restrictions.eq("status", ProductPropDefineStatus.BEEN_USED));
		List<ProductPropDefine> list = productPropDefineDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("修改失败，属性id为{}的数据不存在",propId);
			throw new RuntimeException("修改失败，属性id为"+propId+"的数据不存在");
		}
		if (list.size()>1){
			log.warn("数据异常，属性id为{}的数据有多条",propId);
			throw new RuntimeException("数据异常，属性id为"+propId+"的数据有多条");
		}
		return list.get(0);
	}
	
	/**
	 * 删除模型
	 * @param modelId
	 */
	public void deleteModel(Integer modelId){
		Model model = findModelByModelId(modelId);
		model.setStatus(ModelStatus.BEEN_DELETE);
		updateModel(model);
	}
	
	/**
	 * 修改模型回显
	 * @param modelId
	 * @return
	 */
	public Model findModelEcho(Integer modelId){
		checkModelId(modelId);
		return findModelByModelId(modelId);
	}
	
	/**
	 * 修改模型
	 * @param model
	 */
	public void updateModel(Model model){
		model.setGmtModify(DateFormat.getCurrentTime());
		model.setVersion(model.getVersion()+1);
		modelDao.update(model);
	}
	
	/**
	 * 查询所有模型
	 * @return
	 */
	public List<Model> findAllModel(){
		DetachedCriteria dc = DetachedCriteria.forClass(Model.class);
		dc.add(Restrictions.eq("status", ModelStatus.BEEN_USED));
		return modelDao.findByCriteria(dc);
	}
	
	/**
	 * 修改商品属性名
	 * @param define
	 */
	public void updatePropDefine(ProductPropDefine define){
		define.setGmtModify(DateFormat.getCurrentTime());
		define.setVersion(define.getVersion()+1);
		productPropDefineDao.update(define);
	}
	
	private void checkPropName(String propName){
		if (StringUtils.isBlank(propName)){
			log.warn("传入的属性名为空");
			throw new RuntimeException("传入的属性名为空");
		}
	}
	
	//通过分类id找模型id
	private Integer findModelIdByCategoryId(Integer categoryId){
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.add(Restrictions.eq("categoryId", categoryId));
		dc.add(Restrictions.eq("status", CategoryStatus.BEEN_USED));
		List<Category> list = categoryDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("分类表中分类id为{}的数据不存在",categoryId);
			throw new RuntimeException("分类表中分类id为"+categoryId+"的数据不存在");
		}
		if (list.size()>1){
			log.warn("数据异常，分类表中分类id为{}的记录有多条",categoryId);
			throw new RuntimeException("数据异常，分类表中分类id为"+categoryId+"的记录有多条");
		}
		return list.get(0).getModelId();
	}
	
	private void checkModel(Model model){
		if (model == null){
			log.warn("传入的model为空");
			throw new RuntimeException("传入的model为空");
		}
		if (StringUtils.isBlank(model.getSpec1Name())){
			log.warn("规格1名称为空");
			throw new RuntimeException("规格1名称为空");
		}
		if (StringUtils.isBlank(model.getSpec1Values())){
			log.warn("规格1的取值范围为空");
			throw new RuntimeException("规格1的取值范围为空");
		}
		if (StringUtils.isBlank(model.getModelName())){
			log.warn("模型名为空");
			throw new RuntimeException("模型名为空");
		}
	}
	
	/**
	 * 添加模型
	 * @param model
	 */
	public void addModel(Model model){
		checkModel(model);
		model.setStatus(ModelStatus.BEEN_USED);
		model.setGmtCreate(DateFormat.getCurrentTime());
		model.setGmtModify(DateFormat.getCurrentTime());
		model.setVersion(1);
		modelDao.save(model);
		log.info("成功添加到模型表");
	}
	
	/**
	 * 查询模型下的属性定义
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
			log.warn("传入的标签名为空");
			throw new RuntimeException("传入的标签名为空");
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
	//TODO 只能修改值，不能添加或者删除
	/**
	 * 修改商品属性
	 * @param productId
	 * @param list
	 */
	public void updateProductProp(Integer productId,List<ProductProp> list){
		deleteProductPropByProductId(productId);
		addToProductProp(productId,list);
	}
	
	/**
	 * 去除分类原来的模型
	 * @param productId
	 */
	public void deleteModelFromCategory(Integer categoryId){
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.add(Restrictions.eq("categoryId", categoryId));
		dc.add(Restrictions.ne("status", CategoryStatus.BEEN_USED));
		dc.add(Restrictions.isNotNull("modelId"));
		List<Category> list = categoryDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("分类表中id为{}，状态未被删除，模型id不为空的记录为空",categoryId);
			throw new RuntimeException("分类表中id为"+categoryId+"，状态未被删除，模型id不为空的记录为空");
		}
		if (list.size()>1){
			log.warn("数据异常，分类表中id为{}，状态未被删除，模型id不为空的记录有多条",categoryId);
			throw new RuntimeException("数据异常，分类表中id为"+categoryId+"，状态未被删除，模型id不为空的记录有多条");
		}
		Category category = list.get(0);
		category.setModelId(null);
		updateCategory(category);
		log.info("成功修改分类表模型id为空");
	}
	
	/**
	 * 删除分类
	 * @param productId
	 * @param categoryId
	 */
	public void deleteCategory(Integer categoryId){
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.add(Restrictions.eq("parentId", categoryId));
		dc.add(Restrictions.eq("status", CategoryStatus.BEEN_USED));
		List<Category> list = categoryDao.findByCriteria(dc);
		if (list != null && !list.isEmpty()){
			log.warn("分类id为{}的分类有子分类，无法删除",categoryId);
			throw new RuntimeException("分类id为"+categoryId+"的分类有子分类，无法删除");
		}
		Integer modelId = findModelIdByCategoryId(categoryId);
		DetachedCriteria dc3 = DetachedCriteria.forClass(Product.class);
		dc3.add(Restrictions.eq("modelId", modelId));
		dc3.add(Restrictions.ne("status", ProductStatus.BEEN_DELETE));
		List<Product> productList = productDao.findByCriteria(dc3);
		if (productList !=null && !productList.isEmpty()){
			log.warn("分类id为{}的分类下有商品，无法删除",categoryId);
			throw new RuntimeException("分类id为"+categoryId+"的分类下有商品，无法删除");
		}
		DetachedCriteria dc2 = DetachedCriteria.forClass(Category.class);
		dc2.add(Restrictions.eq("categoryId", categoryId));
		dc2.add(Restrictions.eq("status", CategoryStatus.BEEN_USED));
		List<Category> cList = categoryDao.findByCriteria(dc2);
		if (cList == null || cList.isEmpty()){
			log.warn("分类id为空");
			throw new RuntimeException("分类id为空");
		}
		if (cList.size()>1){
			log.warn("数据异常，分类id为{}的数据有多条记录",categoryId);
			throw new RuntimeException("数据异常，分类id为"+categoryId+"的数据有多条记录");
		}
		Category cg = cList.get(0);
		cg.setStatus(CategoryStatus.BEEN_DELETE);
		updateCategory(cg);
		log.info("成功修改分类表状态为已删除");
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
			log.warn("属性定义表中属性名为{}的数据有多条",propName);
			throw new RuntimeException("属性定义表中属性名为"+propName+"的数据有多条");
		}
		return list.get(0).getPropId();
	}
	
	//通过一组标签添加到标签表并返回ids
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
	
	//添加到商品标签关联表
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
		log.info("成功添加到商品标签关联表中，商品id为{}，标签id为{}，关联id为{}",productId,labelId,relation.getProductLabelRelationId());
	}
	
	private void checkLabelId(Integer labelId) {
		if (labelId == null){
			log.warn("传入的labelId为空");
			throw new RuntimeException("传入的labelId为空");
		}
	}

	/**
	 * 单个商品打标签
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
			log.warn("传入的一组商品id为空");
			throw new RuntimeException("传入的一组商品id为空");
		}
	}
	
	/**
	 * 商品批量打标签
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
	 * 删除商品标签
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
			log.warn("关联表中商品id为{},标签id为{}并且状态为可用的记录不存在",productId,labelId);
			throw new RuntimeException("关联表中商品id为"+productId+",标签id为"+labelId+"并且状态为可用的记录不存在");
		}
		if (relationList.size()>1){
			log.warn("关联表中商品id为{},标签id为{}的记录有多条",productId,labelId);
			throw new RuntimeException("关联表中商品id为"+productId+",标签id为"+labelId+"的记录有多条");
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
			log.warn("传入的分类列表为空");
			throw new RuntimeException("传入的分类列表为空");
		}
	}
	
	/**
	 * 根据父id查询分类
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
	 * 根据分类id修改属性名
	 * @param categoryId
	 * @param categoryName
	 */
	public void updateCategoryInfo(Integer categoryId,String categoryName){
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.add(Restrictions.eq("categoryId", categoryId));
		dc.add(Restrictions.eq("status", CategoryStatus.BEEN_USED));
		List<Category> list = categoryDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("修改失败，分类id：{}不存在",categoryId);
			throw new RuntimeException("修改失败，分类id："+categoryId+"不存在");
		}
		if (list.size()>1){
			log.warn("数据异常，分类id为{}的数据有多条",categoryId);
			throw new RuntimeException("数据异常，分类id为"+categoryId+"的数据有多条");
		}
		Category category = list.get(0);
		category.setCategoryName(categoryName);
		category.setCategoryDesc(categoryName);
		updateCategory(category);
		log.info("修改分类名称成功，分类id为{},分类名为{}",categoryId,categoryName);
	}
	
	/**
	 * 查询所有分类
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
			log.warn("数据异常，分类名为{}有多条记录",categoryName);
			throw new RuntimeException("数据异常，分类名为"+categoryName+"有多条记录");
		}
		return list.get(0);
	}
	
	private void checkCategoryName(String categoryName){
		if (StringUtils.isBlank(categoryName)){
			log.warn("传入的分类名为空");
			throw new RuntimeException("传入的分类名为空");
		}
	}
	
	private void checkParentId(Integer parentId){
		if (parentId == null){
			log.warn("传入的父分类id为空");
			throw new RuntimeException("传入的父分类id为空");
		}
	}
	
	/**
	 * 添加商品分类表
	 * @param categoryNames
	 * @return
	 */
	//XXX 添加分类传单个父id和子分类名称
	public Category addToCategory(Integer parentId,String categoryName){
		checkParentId(parentId);
		checkCategoryName(categoryName);
		Category category = findCategoryByCategoryName(categoryName);
		if (category != null){
			log.warn("分类名为{}的分类已存在，添加分类失败",categoryName);
			throw new RuntimeException("分类名为"+categoryName+"的分类已存在，添加分类失败");
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
			log.warn("商品为空");
			throw new RuntimeException("商品为空");
		}
		Integer productId = product.getProductId();
		if (StringUtils.isBlank(product.getProductName())){
			log.warn("商品id为{}商品名称为空",productId);
			throw new RuntimeException("商品id为"+productId+"商品名称为空");
		}
		if (product.getProductPrice() == null){
			log.warn("商品id为{}商品价格为空",productId);
			throw new RuntimeException("商品id为"+productId+"商品价格为空");
		}
		if (StringUtils.isBlank(product.getStatus())){
			log.warn("商品id为{}商品状态为空",productId);
			throw new RuntimeException("商品id为"+productId+"商品状态为空");
		}
	}
	
	//修改商品
	private void updateProduct(Product product){
		product.setGmtModify(DateFormat.getCurrentTime());
		product.setVersion(product.getVersion()+1);
		productDao.update(product);
	}
	
	private void checkProductPropDTOList(List<ProductPropDTO> list){
		if (list == null || list.isEmpty()){
			log.warn("传入的productPropDTOList为空");
			throw new RuntimeException("传入的productPropDTOList为空");
		}
	}
	
	private void checkPropDTO(PropDTO propDTO){
		if (propDTO == null){
			log.warn("传入的propDTO为空");
			throw new RuntimeException("传入的propDTO为空");
		}
		if (StringUtils.isBlank(propDTO.getPropName())){
			log.warn("属性名为空");
			throw new RuntimeException("属性名为空");
		}
		if (StringUtils.isBlank(propDTO.getPropType())){
			log.warn("属性类型为空");
			throw new RuntimeException("属性类型为空");
		}
	}
	
	private void checkPropDTOList(List<PropDTO> propList){
		if (propList == null || propList.isEmpty()){
			log.warn("传入的属性DTO为空");
			throw new RuntimeException("传入的属性DTO为空");
		}
	}
	
	private void checkPropDefine(ProductPropDefine define){
		if (define == null){
			log.warn("传入的属性定义为空");
			throw new RuntimeException("传入的属性定义为空");
		}
		if (StringUtils.isBlank(define.getPropName())){
			log.warn("传入的属性名为空");
			throw new RuntimeException("传入的属性名为空");
		}
		if (StringUtils.isBlank(define.getPropType())){
			log.warn("属性类型为空");
			throw new RuntimeException("属性类型为空");
		}
		if (define.getFieldRequired() == null){
			log.warn("传入的是否为必填字段为空");
			throw new RuntimeException("传入的是否为必填字段为空");
		}
		if (StringUtils.isBlank(define.getPropCode())){
			log.warn("字段代码为空");
			throw new RuntimeException("字段代码为空");
		}
	}
	
	//判断属性定义表中是否有属性名相同，模型名相同，状态为删除的数据，若有则将状态改为可用
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
	 * 添加到属性定义表
	 * @param propDTO
	 * @return
	 */
	public void addPropDefine(ProductPropDefine define){
		checkPropDefine(define);
		List<ProductPropDefine> list = checkPropDefineExist(define);
		if (checkPropDefineExist2(define)){
			log.warn("属性定义已存在，不能重复添加");
			throw new RuntimeException("属性定义已存在，不能重复添加");
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
			log.info("成功添加到属性定义表中，属性id为"+define.getPropId());
		}
	}
	
	/**
	 * 添加商品属性表
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
			log.warn("属性名列表为空");
			throw new RuntimeException("属性名列表为空");
		}
	}
	
	private void checkPropId(Integer propId){
		if (propId == null){
			log.warn("传入的propId为空");
			throw new RuntimeException("传入的propId为空");
		}
	}
	
	private void checkModelId(Integer modelId){
		if (modelId == null ){
			log.warn("传入的modelId为空");
			throw new RuntimeException("传入的modelId为空");
		}
	}
	
	private ProductPropDefine findpropDefineByProDefineId(Integer propDefineId){
		checkPropId(propDefineId);
		DetachedCriteria dc = DetachedCriteria.forClass(ProductPropDefine.class);
		dc.add(Restrictions.eq("propId", propDefineId));
		dc.add(Restrictions.eq("status", ProductPropDefineStatus.BEEN_USED));
		List<ProductPropDefine> list = productPropDefineDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("属性定义表中id为{}的记录为空",propDefineId);
			throw new RuntimeException("属性定义表中id为"+propDefineId+"的记录为空");
		}
		if (list.size()>1){
			log.warn("数据异常，属性表中id为{}的记录有多条",propDefineId);
			throw new RuntimeException("数据异常，属性表中id为"+propDefineId+"的记录有多条");
		}
		return list.get(0);
	}
	
	/**
	 * 删除模型下的属性定义
	 * @param propDefineId
	 */
	public void deletePropDefine(Integer propDefineId){
		ProductPropDefine define = findpropDefineByProDefineId(propDefineId);
		define.setStatus(ProductPropDefineStatus.BEEN_DELETE);
		updatePropDefine(define);
	}
	
	/**
	 * 删除商品属性
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
			log.warn("商品表中不存在商品id为{}，属性id为{}的记录",productId,propId);
			throw new RuntimeException("商品表中不存在商品id为"+productId+"，属性id为"+propId+"的记录");
		}
		if (list.size()>1){
			log.warn("属性表中商品id为{},属性id为{}的记录有多条",productId,propId);
			throw new RuntimeException("属性表中商品id为"+productId+",属性id为"+propId+"的记录有多条");
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
			log.warn("传入的dto品为空");
			throw new RuntimeException("传入的dto为空");
		}
		if (StringUtils.isBlank(addToProductDTO.getProductName())){
			log.warn("传入的dto的商品名称为空");
			throw new RuntimeException("传入的dto的商品名称为空");
		}
		if (addToProductDTO.getProductPrice() == null || addToProductDTO.getProductPrice() <= 0){
			log.warn("传入的dto的商品价格不能为null且不能小于0");
			throw new RuntimeException("传入的dto的商品价格不能为null且不能小于0");
		}
		if (StringUtils.isBlank(addToProductDTO.getSpec1Name())){
			log.warn("传入的dto的商品规格1为空");
			throw new RuntimeException("传入的dto的商品规格1为空");
		}
		if (StringUtils.isBlank(addToProductDTO.getSpec1Values())){
			log.warn("传入的dto的商品规格1取值为空");
			throw new RuntimeException("传入的dto的规格1取值为空");
		}
		if (StringUtils.isBlank(addToProductDTO.getBrand())){
			log.warn("传入的dto的品牌为空");
			throw new RuntimeException("传入的dto的品牌为空");
		}
		if (StringUtils.isBlank(addToProductDTO.getIsbn())){
			log.warn("传入的dto的isbn为空");
			throw new RuntimeException("传入的dto的isbn为空");
		}
		if (StringUtils.isBlank(addToProductDTO.getProductDesc())){
			log.warn("传入的dto的商品描述为空");
			throw new RuntimeException("传入的dto的商品描述为空");
		}
		if (StringUtils.isBlank(addToProductDTO.getProductImg())){
			log.warn("传入的dto的商品图片为空");
			throw new RuntimeException("传入的dto的商品图片为空");
		}
		if (StringUtils.isBlank(addToProductDTO.getProductNo())){
			log.warn("传入的dto的商品货号为空");
			throw new RuntimeException("传入的dto的商品货号为空");
		}
	}
	//XXX 查询某个分类下面的属性定义
	/**
	 * 添加商品表
	 * @param addToProductDTO
	 */
	//XXX 添加商品时可以直接添加分类，传分类id
	public Integer addProduct(AddToProductDTO addToProductDTO){
		checkAddToProductDTO(addToProductDTO);
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("brand", addToProductDTO.getBrand()));
		dc.add(Restrictions.eq("productNo", addToProductDTO.getProductNo()));
		dc.add(Restrictions.ne("status", ProductSkuStatus.BEEN_DELETE));
		List<Product> list = productDao.findByCriteria(dc);
		if (list != null && !list.isEmpty()){
			log.warn("数据已存在，不要重复添加");
			throw new RuntimeException("数据已存在，不要重复添加");
		}
		Product product = new Product();
		BeanUtils.copyProperties(addToProductDTO, product);
		//添加商品表
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
		log.info("成功添加到商品表中，商品id为{}",product.getProductId());
		return product.getProductId();
	}
	
	private Model findModelByModelId(Integer modelId){
		checkModelId(modelId);
		DetachedCriteria dc = DetachedCriteria.forClass(Model.class);
		dc.add(Restrictions.eq("modelId", modelId));
		dc.add(Restrictions.eq("status", ModelStatus.BEEN_USED));
		List<Model> list = modelDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("模型表中id为{}的数据不存在",modelId);
			throw new RuntimeException("模型表中id为"+modelId+"的数据不存在");
		}
		if (list.size()>1){
			log.warn("数据异常，模型表中id为{}的数据有多条",modelId);
			throw new RuntimeException("数据异常，模型表中id为"+modelId+"的数据有多条");
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
			log.warn("数据异常，id为{}的商品有多条记录",productId);
			throw new RuntimeException("数据异常，id为"+productId+"的商品有多条记录");
		}
		return productList.get(0);
	}
	
	private Integer findCategoryIdByProductId(Integer productId){
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("productId", productId));
		dc.add(Restrictions.ne("status", ProductStatus.BEEN_DELETE));
		List<Product> list = productDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			log.warn("商品表中id为{}的记录为空",productId);
			throw new RuntimeException("商品表中id为"+productId+"的记录为空");
		}
		if (list.size()>1){
			log.warn("数据异常，商品表中id为{}的记录有多条",productId);
			throw new RuntimeException("数据异常，商品表中id为"+productId+"的记录有多条");
		}
		return list.get(0).getCategoryId();
	}
	
	
	
	/**
	 * 添加sku表
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
			log.warn("传入的规格不在规定范围内");
			throw new RuntimeException("传入的规格不在规定范围内");
		}
		ProductSku sku = new ProductSku();
		BeanUtils.copyProperties(skuDTO, sku);
		sku.setProductId(productId);
		sku.setStatus(ProductSkuStatus.BEEN_USED);
		sku.setGmtCreate(DateFormat.getCurrentTime());
		sku.setGmtModify(DateFormat.getCurrentTime());
		sku.setVersion(1);
		productSkuDao.save(sku);
		log.info("成功添加到sku表中，商品id为{}，skuId为{}",productId,sku.getSkuId());
	}

	/**
	 * 获取一个商品信息
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
			log.warn("传入的商品id为空");
			throw new RuntimeException("传入的商品id为空");
		}
	}
	
	/**
	 * 批量获取商品信息
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
			log.warn("没有查询到skuid为{}的商品",skuId);
			throw new RuntimeException("没有查询到skuid为"+skuId+"的商品");
		}
		if (skuList.size()>1){
			log.warn("数据异常，skuid为{}的商品有多条记录",skuId);
			throw new RuntimeException("数据异常，skuid为"+skuId+"的商品有多条记录");
		}
		return skuList.get(0);
	} 
	
	/**
	 * 通过skuId获取sku
	 * @param productId
	 * @return
	 */
	public ProductSku findSkuBySkuId(Integer skuId){
		return findProductSkuByCriteria(skuId);
	}

	private void checkSkuId(Integer skuId) {
		if (skuId == null){
			log.warn("skuId为空");
			throw new RuntimeException("skuId为空");
		}
	}
	
	/**
	 * 通过商品id获取sku列表
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
	 * 删除单件商品
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
	 * 批量删除商品
	 * @param productIdList
	 */
	public void deleteProduct(List<Integer> productIdList){
		checkProductIdList(productIdList);
		for (Integer productId : productIdList) {
			deleteProduct(productId);
		}
	}

	/**
	 * 删除商品sku
	 * @param skuId
	 */
	public void deleteProductSku(Integer skuId){
		ProductSku sku = findSkuBySkuId(skuId);
		sku.setStatus(ProductSkuStatus.BEEN_DELETE);
		updateSku(sku);
	}
	
	private void checkSkuIdList(List<Integer> skuIdList){
		if (skuIdList == null|| skuIdList.isEmpty()){
			throw new RuntimeException("传入的skuIdList为空");
		}
	}
	
	/**
	 * 批量删除商品sku
	 * @param skuIdList
	 */
	public void deleteProductSku(List<Integer> skuIdList){
		checkSkuIdList(skuIdList);
		for (Integer skuId : skuIdList) {
			deleteProductSku(skuId);
		}
	}
	
	/**
	 * 更新商品价格
	 * @param productId
	 * @param price
	 */
	public void updateProductPrice(int productId,Long price){
		if (price == null){
			log.warn("传入的价格为空");
			throw new RuntimeException("传入的价格为空");
		}
		Product product = findProduct(productId);
		product.setProductPrice(price);
		updateProduct(product);
	}
	
	/**
	 * 获取产品的规格信息
	 * @param productId
	 * @return
	 */
	public SpecDTO findProductSpec(int productId){
		Product product = findProduct(productId);
		SpecDTO specDTO = new SpecDTO();
		BeanUtils.copyProperties(product, specDTO);
		return specDTO;
	}
	
	//修改sku表
	private void updateSku(ProductSku sku) {
		sku.setGmtModify(DateFormat.getCurrentTime());
		sku.setVersion(sku.getVersion()+1);
		productSkuDao.update(sku);
	}
	
	/**
	 * 由标签返回商品
	 * @param labelName
	 * @return
	 */
	public List<Product> findProductByLabel(String labelName){
		if (StringUtils.isBlank(labelName)){
			log.warn("传入的标签名为空");
			throw new RuntimeException("传入的标签名为空");
		}
		Integer labelId = findLabelIdByLabelName(labelName);
		return findProduct(findSubjectIdByLabelId(labelId));
	}
	
	/**
	 * 获取单个商品的详细信息
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
	 * 批量获取商品的详细信息
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
	 * 商品上架
	 * @param productId
	 */
	public void shelveProduct(Integer productId){
		Product product = findProdctStatusOutOfShelve(productId);
		product.setStatus(ProductStatus.ON_SHELVE);
		updateProduct(product);
	}
	
	//查找状态为下架的商品
	private Product findProdctStatusOutOfShelve(Integer productId){
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("productId", productId));
		dc.add(Restrictions.eq("status", ProductStatus.OUT_OF_SHELVE));
		List<Product> list = productDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			return null;
		}
		if (list.size()>1){
			log.warn("数据库异常，商品id为{},状态为已上架的数据有多条记录",productId);
			throw new RuntimeException("数据库异常，商品id为"+productId+",状态为已下架的数据有多条记录");
		}
		return list.get(0);
	}
	
	//查找状态为上架的商品
	private Product findProdctStatusOnShelve(Integer productId){
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("productId", productId));
		dc.add(Restrictions.eq("status", ProductStatus.ON_SHELVE));
		List<Product> list = productDao.findByCriteria(dc);
		if (list == null || list.isEmpty()){
			return null;
		}
		if (list.size()>1){
			log.warn("数据库异常，商品id为{},状态为已上架的数据有多条记录",productId);
			throw new RuntimeException("数据库异常，商品id为"+productId+",状态为已上架的数据有多条记录");
		}
		return list.get(0);
	}
	
	/**
	 * 商品下架
	 * @param productId
	 */
	public void outOfShelve(Integer productId){
		//XXX 上架、下架、删除操作需要严格检查状态
		Product product = findProdctStatusOnShelve(productId);
		product.setStatus(ProductStatus.OUT_OF_SHELVE);
		updateProduct(product);
	}
	
	/**
	 * 修改商品表
	 * @param productId
	 * @param productDTO
	 */
	public void updateProductInfo(Product product){
		checkProduct(product);
		updateProduct(product);
	}
	
	private void checkProductDTO(ProductDTO productDTO){
		if (productDTO == null){
			throw new RuntimeException("传入的DTO为空");
		}
		//属性可以不设置，不修改某些属性
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
			log.warn("标签表中不存在标签id为{}的记录",labelId);
			throw new RuntimeException("标签表中不存在标签id为"+labelId+"的记录");
		}
		if (labelList.size()>1){
			log.warn("数据异常：标签表中存在多条标签id为{}的记录",labelId);
			throw new RuntimeException("数据异常：标签表中存在多条标签id为"+labelId+"的记录");
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
			log.warn("传入的分类id为空");
			throw new RuntimeException("传入的分类id为空");
		}
	}
	
	private Category findCategoryByCategoryId(Integer categoryId){
		checkCategoryId(categoryId);
		DetachedCriteria criteria = DetachedCriteria.forClass(Category.class);
		criteria.add(Restrictions.eq("categoryId", categoryId));
		criteria.add(Restrictions.eq("status", CategoryStatus.BEEN_USED));
		List<Category> list = categoryDao.findByCriteria(criteria);
		if (list == null || list.isEmpty()){
			log.warn("分类表中不存在id为{}的分类",categoryId);
			throw new RuntimeException("分类表中不存在id为"+categoryId+"的分类");
		}
		if (list.size()>1){
			throw new RuntimeException("数据异常，分类表中存在多个id为"+categoryId+"的记录");
		}
		return list.get(0);
	}
	
	private void checkLabelName(String labelName){
		if (StringUtils.isBlank(labelName)){
			log.warn("标签名为空");
			throw new RuntimeException("标签名为空");
		}
	}
	
	private Integer findLabelIdByLabelName(String labelName){
		checkLabelName(labelName);
		DetachedCriteria criteria = DetachedCriteria.forClass(Label.class);
		criteria.add(Restrictions.eq("labelName", labelName));
		criteria.add(Restrictions.eq("status", LabelStatus.BEEN_USED));
		List<Label> labelList = labelDao.findByCriteria(criteria);
		if (labelList == null || labelList.isEmpty()){
			log.warn("没有查出标签名为{}并且状态为可用的记录",labelName);
			throw new RuntimeException("没有查出标签名为"+labelName+"并且状态为可用的记录");
		}
		if (labelList.size()>1){
			log.warn("数据异常，标签名为{}的标签有多条记录",labelName);
			throw new RuntimeException("数据异常，标签名为"+labelName+"的标签有多条记录");
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
			log.warn("关联表中不存在标签id为{}的记录",labelId);
			throw new RuntimeException("关联表中不存在标签id为"+labelId+"的记录");
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
