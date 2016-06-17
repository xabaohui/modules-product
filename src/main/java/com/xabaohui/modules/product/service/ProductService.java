package com.xabaohui.modules.product.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xabaohui.modules.product.bean.Category;
import com.xabaohui.modules.product.bean.Model;
import com.xabaohui.modules.product.bean.Product;
import com.xabaohui.modules.product.bean.ProductProp;
import com.xabaohui.modules.product.bean.ProductPropDefine;
import com.xabaohui.modules.product.bean.ProductSku;
import com.xabaohui.modules.product.bo.ProductBO;
import com.xabaohui.modules.product.dto.AddToProductDTO;
import com.xabaohui.modules.product.dto.ProductDTO;
import com.xabaohui.modules.product.dto.PropDTO;
import com.xabaohui.modules.product.dto.SkuDTO;
import com.xabaohui.modules.product.dto.SpecDTO;

public class ProductService {
	private Logger log = LoggerFactory.getLogger(ProductService.class);
	private ProductBO productBO;
	
	/**
	 * ɾ��ģ���µ����Զ���
	 * @param propDefineId
	 */
	public void deletePropDefine(Integer propDefineId){
		productBO.deletePropDefine(propDefineId);
	}
	
	/**
	 * ���ģ���µ����Զ���
	 * @param propDTO
	 */
	public void addPropDefine(ProductPropDefine define){
		productBO.addPropDefine(define);
	}
	
	/**
	 * �޸����Զ���ʱ�Ļ���
	 * @param propId
	 * @return
	 */
	public ProductPropDefine findPropEcho(Integer propId){
		return productBO.findPropEcho(propId);
	}
	
	/**
	 * ��ѯ����ģ��
	 * @return
	 */
	public List<Model> findAllModel(){
		return productBO.findAllModel();
	}
	
	/**
	 * �޸���Ʒ����
	 * @param productId
	 * @param list
	 */
	public void updateProductProp(Integer productId,List<ProductProp> list){
		productBO.updateProductProp(productId, list);
	}
	
	/**
	 * ȥ������ԭ����ģ��
	 * @param productId
	 */
	public void deleteModelFromCategory(Integer categoryId){
		productBO.deleteModelFromCategory(categoryId);
	}
	
	/**
	 * ���ݸ�id��ѯ����
	 * @param categoryId
	 * @return
	 */
	public List<Category> findCategory(Integer parentId){
		return productBO.findCategory(parentId);
	}
	
	/**
	 * ��ѯ���з���
	 * @return
	 */
	public List<Category> findAllCategory(){
		return productBO.findAllCategory();
	}
	
	/**
	 * ���ģ��
	 * @param model
	 */
	public void addModel(Model model){
		productBO.addModel(model);
	}
	
	/**
	 * �޸�ģ��ʱ����
	 * @param modelId
	 * @return
	 */
	public Model findModelEcho(Integer modelId){
		return productBO.findModelEcho(modelId);
	}
	
	/**
	 * �޸�ģ��
	 * @param model
	 */
	public void updateModel(Model model){
		productBO.updateModel(model);
	}
	
	/**
	 * ɾ��ģ��
	 * @param modelId
	 */
	public void deleteModel(Integer modelId){
		productBO.deleteModel(modelId);
	}
	
	/**
	 * ��ѯģ���µ����Զ���
	 * @param categoryId
	 * @return
	 */
	public List<ProductPropDefine> findPropDefineByModelId(Integer categoryId){
		return productBO.findPropDefineByModelId(categoryId);
	}
	
	/**
	 * �޸���Ʒ������
	 * @param propId
	 * @param propName
	 */
	public void updatePropDefine(ProductPropDefine define){
		productBO.updatePropDefine(define);
	}
	
	/**
	 * ɾ������
	 * @param productId
	 * @param categoryId
	 */
	public void deleteCategory(Integer categoryId){
		//XXX ɾ����ʱ�÷�������û���κ���Ʒ
		//XXX ȱ�ٵ���ӵ���������ķ���������id����Ϊ�գ�����Ʒȥ��ԭ���ķ���
		productBO.deleteCategory(categoryId);
	}
	
	/**
	 * �����Ʒ���Ա�
	 * @param productId
	 * @param list
	 */
	public void addToProductProp(Integer productId,List<ProductProp> list){
		productBO.addToProductProp(productId,list);
	}
	/**
	 * ������Ʒ���ǩ
	 * @param productId
	 * @param labelNames
	 */
	public void addToLabelByProductId(Integer productId,List<String> labelNames){
		productBO.addToLabelByProductId(productId, labelNames);
	}
	
	/**
	 * ��Ʒ�������ǩ
	 * @param productIds
	 * @param labelNames
	 */
	public void addToLabelByProductIdList(List<Integer> productIds,List<String> labelNames){
		productBO.addToLabelByProductIdList(productIds, labelNames);
	}
	
	/**
	 * ɾ����Ʒ��ǩ
	 * @param productId
	 * @param labelId
	 */
	public void deleteProductLabel(Integer productId,Integer labelId){
		productBO.deleteProductLabel(productId, labelId);
	}
	
	/**
	 * ���ݷ���id�޸�������
	 * @param categoryId
	 * @param categoryName
	 */
	public void updateCategoryInfo(Integer categoryId,String categoryName){
		productBO.updateCategoryInfo(categoryId, categoryName);
	}
	
	/**
	 * ɾ����Ʒ����
	 * @param productId
	 * @param propId
	 */
	public void deleteProductProp(Integer productId,Integer propId){
		productBO.deleteProductProp(productId, propId);
	}
	
	/**
	 * �����Ʒ��
	 * @param addToProductDTO
	 */
	public Integer addProduct(AddToProductDTO addToProductDTO){
		return productBO.addProduct(addToProductDTO);
	}
	
	/**
	 * ���sku��
	 * @param productId
	 * @param skuNo
	 */
	public void addToProductSku(Integer productId,SkuDTO skuDTO){
		productBO.addToProductSku(productId,skuDTO);
	}

	/**
	 * ��ȡһ����Ʒ��Ϣ
	 * @param productId
	 * @return Product
	 */
	public Product findProduct(Integer productId){
		return productBO.findProduct(productId);
	}
	
	/**
	 * ������ȡ��Ʒ��Ϣ
	 * @param productIdList
	 * @return
	 */
	public List<Product> findProductList(Integer categoryId){
		return productBO.findProductListByCategoryId(categoryId);
	}
	
	/**
	 * ͨ��skuId��ȡsku
	 * @param productId
	 * @return
	 */
	public ProductSku findSkuBySkuId(Integer skuId){
		return productBO.findSkuBySkuId(skuId);
	}
	
	/**
	 * ͨ����Ʒid��ȡsku�б�
	 * @param productId
	 * @return
	 */
	public List<ProductSku> findSkuList(Integer productId){
		return productBO.findSkuList(productId);
	}
	
	/**
	 * ɾ��������Ʒ
	 * @param productId
	 */
	public void deleteProduct(Integer productId){
		productBO.deleteProduct(productId);
	}
	
	/**
	 * ����ɾ����Ʒ
	 * @param productIdList
	 */
	public void deleteProduct(List<Integer> productIdList){
		productBO.deleteProduct(productIdList);
	}

	/**
	 * ɾ����Ʒsku
	 * @param skuId
	 */
	public void deleteProductSku(Integer skuId){
		productBO.deleteProductSku(skuId);
	}
	
	/**
	 * ����ɾ����Ʒsku
	 * @param skuIdList
	 */
	public void deleteProductSku(List<Integer> skuIdList){
		productBO.deleteProductSku(skuIdList);
	}
	
	/**
	 * ������Ʒ�۸�
	 * @param productId
	 * @param price
	 */
	public void updateProductPrice(int productId,Long price){
		productBO.updateProductPrice(productId, price);
	}
	
	/**
	 * ��ȡ��Ʒ�Ĺ����Ϣ
	 * @param productId
	 * @return
	 */
	public SpecDTO findProductSpec(int productId){
		return productBO.findProductSpec(productId);
	}
	
	/**
	 * �ɱ�ǩ������Ʒ
	 * @param labelName
	 * @return
	 */
	public List<Product> findProductByLabel(String labelName){
		return productBO.findProductByLabel(labelName);
	}
	
	/**
	 * ��ȡ������Ʒ����ϸ��Ϣ
	 * @param productId
	 * @return ProductDTO
	 */
	public ProductDTO findProductDetail(int productId){
		return productBO.findProductDetail(productId);
	}
	
	/**
	 * ������ȡ��Ʒ����ϸ��Ϣ
	 * @param productIdList
	 * @return List<ProductDTO>
	 */
	public List<ProductDTO> findProductDetail(List<Integer> productIdList){
		return productBO.findProductDetail(productIdList);
	}
	
	/**
	 * ��Ʒ�ϼ�
	 * @param productId
	 */
	public void shelveProduct(Integer productId){
		productBO.shelveProduct(productId);
	}
	
	/**
	 * ��Ʒ�¼�
	 * @param productId
	 */
	public void outOfShelve(Integer productId){
		productBO.outOfShelve(productId);
	}
	
	/**
	 * �޸���Ʒ��
	 * @param productId
	 * @param productDTO
	 */
	public void updateProduct(Product product){//XXX ����Ϊproduct
		productBO.updateProductInfo(product);
	}
	
	/**
	 * �����Ʒ�����
	 * @param categoryNames
	 * @return
	 */
	public Category addToCategory(Integer parentId,String categoryName){
		return productBO.addToCategory(parentId, categoryName);
	}

	public ProductBO getProductBO() {
		return productBO;
	}

	public void setProductBO(ProductBO productBO) {
		this.productBO = productBO;
	}
}
