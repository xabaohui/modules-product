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
	 * 删除模型下的属性定义
	 * @param propDefineId
	 */
	public void deletePropDefine(Integer propDefineId){
		productBO.deletePropDefine(propDefineId);
	}
	
	/**
	 * 添加模型下的属性定义
	 * @param propDTO
	 */
	public void addPropDefine(ProductPropDefine define){
		productBO.addPropDefine(define);
	}
	
	/**
	 * 修改属性定义时的回显
	 * @param propId
	 * @return
	 */
	public ProductPropDefine findPropEcho(Integer propId){
		return productBO.findPropEcho(propId);
	}
	
	/**
	 * 查询所有模型
	 * @return
	 */
	public List<Model> findAllModel(){
		return productBO.findAllModel();
	}
	
	/**
	 * 修改商品属性
	 * @param productId
	 * @param list
	 */
	public void updateProductProp(Integer productId,List<ProductProp> list){
		productBO.updateProductProp(productId, list);
	}
	
	/**
	 * 去除分类原来的模型
	 * @param productId
	 */
	public void deleteModelFromCategory(Integer categoryId){
		productBO.deleteModelFromCategory(categoryId);
	}
	
	/**
	 * 根据父id查询分类
	 * @param categoryId
	 * @return
	 */
	public List<Category> findCategory(Integer parentId){
		return productBO.findCategory(parentId);
	}
	
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<Category> findAllCategory(){
		return productBO.findAllCategory();
	}
	
	/**
	 * 添加模型
	 * @param model
	 */
	public void addModel(Model model){
		productBO.addModel(model);
	}
	
	/**
	 * 修改模型时回显
	 * @param modelId
	 * @return
	 */
	public Model findModelEcho(Integer modelId){
		return productBO.findModelEcho(modelId);
	}
	
	/**
	 * 修改模型
	 * @param model
	 */
	public void updateModel(Model model){
		productBO.updateModel(model);
	}
	
	/**
	 * 删除模型
	 * @param modelId
	 */
	public void deleteModel(Integer modelId){
		productBO.deleteModel(modelId);
	}
	
	/**
	 * 查询模型下的属性定义
	 * @param categoryId
	 * @return
	 */
	public List<ProductPropDefine> findPropDefineByModelId(Integer categoryId){
		return productBO.findPropDefineByModelId(categoryId);
	}
	
	/**
	 * 修改商品属性名
	 * @param propId
	 * @param propName
	 */
	public void updatePropDefine(ProductPropDefine define){
		productBO.updatePropDefine(define);
	}
	
	/**
	 * 删除分类
	 * @param productId
	 * @param categoryId
	 */
	public void deleteCategory(Integer categoryId){
		//XXX 删分类时该分类下面没有任何商品
		//XXX 缺少到添加到其他分类的方法，分类id可以为空，将商品去除原本的分类
		productBO.deleteCategory(categoryId);
	}
	
	/**
	 * 添加商品属性表
	 * @param productId
	 * @param list
	 */
	public void addToProductProp(Integer productId,List<ProductProp> list){
		productBO.addToProductProp(productId,list);
	}
	/**
	 * 单个商品打标签
	 * @param productId
	 * @param labelNames
	 */
	public void addToLabelByProductId(Integer productId,List<String> labelNames){
		productBO.addToLabelByProductId(productId, labelNames);
	}
	
	/**
	 * 商品批量打标签
	 * @param productIds
	 * @param labelNames
	 */
	public void addToLabelByProductIdList(List<Integer> productIds,List<String> labelNames){
		productBO.addToLabelByProductIdList(productIds, labelNames);
	}
	
	/**
	 * 删除商品标签
	 * @param productId
	 * @param labelId
	 */
	public void deleteProductLabel(Integer productId,Integer labelId){
		productBO.deleteProductLabel(productId, labelId);
	}
	
	/**
	 * 根据分类id修改属性名
	 * @param categoryId
	 * @param categoryName
	 */
	public void updateCategoryInfo(Integer categoryId,String categoryName){
		productBO.updateCategoryInfo(categoryId, categoryName);
	}
	
	/**
	 * 删除商品属性
	 * @param productId
	 * @param propId
	 */
	public void deleteProductProp(Integer productId,Integer propId){
		productBO.deleteProductProp(productId, propId);
	}
	
	/**
	 * 添加商品表
	 * @param addToProductDTO
	 */
	public Integer addProduct(AddToProductDTO addToProductDTO){
		return productBO.addProduct(addToProductDTO);
	}
	
	/**
	 * 添加sku表
	 * @param productId
	 * @param skuNo
	 */
	public void addToProductSku(Integer productId,SkuDTO skuDTO){
		productBO.addToProductSku(productId,skuDTO);
	}

	/**
	 * 获取一个商品信息
	 * @param productId
	 * @return Product
	 */
	public Product findProduct(Integer productId){
		return productBO.findProduct(productId);
	}
	
	/**
	 * 批量获取商品信息
	 * @param productIdList
	 * @return
	 */
	public List<Product> findProductList(Integer categoryId){
		return productBO.findProductListByCategoryId(categoryId);
	}
	
	/**
	 * 通过skuId获取sku
	 * @param productId
	 * @return
	 */
	public ProductSku findSkuBySkuId(Integer skuId){
		return productBO.findSkuBySkuId(skuId);
	}
	
	/**
	 * 通过商品id获取sku列表
	 * @param productId
	 * @return
	 */
	public List<ProductSku> findSkuList(Integer productId){
		return productBO.findSkuList(productId);
	}
	
	/**
	 * 删除单件商品
	 * @param productId
	 */
	public void deleteProduct(Integer productId){
		productBO.deleteProduct(productId);
	}
	
	/**
	 * 批量删除商品
	 * @param productIdList
	 */
	public void deleteProduct(List<Integer> productIdList){
		productBO.deleteProduct(productIdList);
	}

	/**
	 * 删除商品sku
	 * @param skuId
	 */
	public void deleteProductSku(Integer skuId){
		productBO.deleteProductSku(skuId);
	}
	
	/**
	 * 批量删除商品sku
	 * @param skuIdList
	 */
	public void deleteProductSku(List<Integer> skuIdList){
		productBO.deleteProductSku(skuIdList);
	}
	
	/**
	 * 更新商品价格
	 * @param productId
	 * @param price
	 */
	public void updateProductPrice(int productId,Long price){
		productBO.updateProductPrice(productId, price);
	}
	
	/**
	 * 获取产品的规格信息
	 * @param productId
	 * @return
	 */
	public SpecDTO findProductSpec(int productId){
		return productBO.findProductSpec(productId);
	}
	
	/**
	 * 由标签返回商品
	 * @param labelName
	 * @return
	 */
	public List<Product> findProductByLabel(String labelName){
		return productBO.findProductByLabel(labelName);
	}
	
	/**
	 * 获取单个商品的详细信息
	 * @param productId
	 * @return ProductDTO
	 */
	public ProductDTO findProductDetail(int productId){
		return productBO.findProductDetail(productId);
	}
	
	/**
	 * 批量获取商品的详细信息
	 * @param productIdList
	 * @return List<ProductDTO>
	 */
	public List<ProductDTO> findProductDetail(List<Integer> productIdList){
		return productBO.findProductDetail(productIdList);
	}
	
	/**
	 * 商品上架
	 * @param productId
	 */
	public void shelveProduct(Integer productId){
		productBO.shelveProduct(productId);
	}
	
	/**
	 * 商品下架
	 * @param productId
	 */
	public void outOfShelve(Integer productId){
		productBO.outOfShelve(productId);
	}
	
	/**
	 * 修改商品表
	 * @param productId
	 * @param productDTO
	 */
	public void updateProduct(Product product){//XXX 传参为product
		productBO.updateProductInfo(product);
	}
	
	/**
	 * 添加商品分类表
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
