package com.xabaohui.modules.product.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.xabaohui.modules.product.bean.Product;
import com.xabaohui.modules.product.dao.ProductDao;
import com.xabaohui.modules.product.dto.AddToProductDTO;
import com.xabaohui.modules.product.dto.ProductPropDTO;
import com.xabaohui.modules.product.dto.SkuDTO;

@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:applicationContext.xml")
@Component
public class ProductServiceTest extends AbstractJUnit4SpringContextTests{
	@Resource
	private ProductService productService;
	@Resource
	private ProductDao productDao;
	//修改商品属性
	@Test
	public void updateProductProp(){
		
	}
}