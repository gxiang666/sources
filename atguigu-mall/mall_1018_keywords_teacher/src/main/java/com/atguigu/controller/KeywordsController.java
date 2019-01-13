package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.KEYWORDS_T_MALL_SKU;
import com.atguigu.util.MyPropertiesUtil;

@Controller
public class KeywordsController {

	@RequestMapping(value = "keywords")
	@ResponseBody
	public List<KEYWORDS_T_MALL_SKU> keywords(String keywords) {
		List<KEYWORDS_T_MALL_SKU> list_sku = new ArrayList<KEYWORDS_T_MALL_SKU>();
		HttpSolrServer solr = new HttpSolrServer(MyPropertiesUtil.getMyProperty("solr.properties", "solr_sku1018"));
		solr.setParser(new XMLResponseParser());
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("combine_item:" + keywords);
		solrQuery.setRows(50);
		QueryResponse query = null;
		try {
			query = solr.query(solrQuery);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list_sku = query.getBeans(KEYWORDS_T_MALL_SKU.class);
	
		return list_sku;
	}

}
