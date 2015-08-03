package com.gensuite.search.repository;

import static org.elasticsearch.index.query.QueryBuilders.queryString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gensuite.search.entities.Catalog;
import com.gensuite.search.entities.Description;
import com.gensuite.search.entities.Part;
import com.gensuite.search.service.CatalogService;
import com.gensuite.search.service.PartService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring*.xml")
public class SearchTest {

	@Resource
	private CatalogRepository catalogRepository;

	@Resource
	private PartRepository partRepository;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	CatalogService catalogService;

	@Autowired
	PartService partService;

	@Test
	public void searchCatalog() {
		// Iterable<Catalog> catalogList =
		// catalogRepository.findByCatalogNameStartsWith("H08A0400061");
		// Iterable<Catalog> catalogList =
		// catalogRepository.findByCatalogNameStartingWith("B83A01");
		Iterable<Catalog> catalogList = catalogRepository
				.findAll();

		// Iterable<Catalog> catalogList = catalogRepository.findAll();
		// Catalog catalog = catalogRepository.findByCatalogId(8924L);
		System.out.println("catalogList===" + catalogList);
		Iterator<Catalog> catalogItr = catalogList.iterator();
		Catalog catalog = null;
		int i=0;
		while (catalogItr.hasNext()) {
			catalog = catalogItr.next();
			System.out.println("part.catalog()="+i+++"=====" + catalog.getCatalogId()
					+ "==" + catalog.getCatalogName());
		}
		System.out.println("Completed============");
	}
	@Test
	public void searchBycatalogName() {
		List<Catalog> partsList = catalogRepository
				.findByCatalogNameStartsWith("550 197");
		System.out.println(partsList.size());
	}

	@Test
	public void startsWithQuery() {
		QueryBuilder query = null;
		query = queryString("B83A" + "*").field("catalogName").analyzeWildcard(
				true);

		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(
				query).build();
		List<Catalog> catalogsList = elasticsearchTemplate.queryForList(
				searchQuery, Catalog.class);

		CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria(
				"catalogName").startsWith("B83A"));
		List<Catalog> catalogList = elasticsearchTemplate.queryForList(
				criteriaQuery, Catalog.class);
		System.out.println("catalogList==" + catalogList.size());
	}

	@Test
	public void searchPart() {
		// List<Part> partsList = partRepository.findByPartNumber("part1");
		List<Part> partsList = partRepository
				.findByPartNumberStartingWith("part");
		System.out.println(partsList.size());
	}

	@Test
	public void searchPartNumberStart() {
		Iterable<Part> partsList = partRepository
				.findByPartNumberStartingWith("ring");
		System.out.println("partList===" + partsList);
		Iterator<Part> partItr = partsList.iterator();
		Part part = null;
		while (partItr.hasNext()) {
			part = partItr.next();
			System.out.println("part.partno======" + part.getPartNumber()
					+ "==" + part.getDescription());
		}
		System.out.println("Completed============");
		String s1 = "1123";
	}

	@Test
	public void startsWithQueryAll() {
		QueryBuilder query = null;
		query = QueryBuilders.multiMatchQuery("Mila", "_all").type(MatchQueryBuilder.Type.PHRASE_PREFIX);
		// query = queryString("B83A" +
		// "*").field("_all").analyzeWildcard(true);

		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(
				query).build();
		List<Catalog> catalogsList = elasticsearchTemplate.queryForList(
				searchQuery, Catalog.class);

		/*
		 * CriteriaQuery criteriaQuery = new CriteriaQuery(new
		 * Criteria("catalogName").startsWith("B83A")); List<Catalog>
		 * catalogList =elasticsearchTemplate.queryForList(criteriaQuery,
		 * Catalog.class);
		 */
		System.out.println("catalogList==" + catalogsList.size());
		System.out.println(catalogsList);
		Iterator<Catalog> catalogItr = catalogsList.iterator();
		Catalog catalog = null;
		while (catalogItr.hasNext()) {
			catalog = catalogItr.next();
			System.out.println("part.catalog()======" + catalog.getCatalogId()
					+ "==" + catalog.getCatalogName());
		}
	}
	
	
	
	// Highlighting
	@Test
	public void highlightCatalog() {
		QueryBuilder query = null;
		query = QueryBuilders.multiMatchQuery("Mil" ,"_all").type(MatchQueryBuilder.Type.PHRASE_PREFIX);
		
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(query)
		.withHighlightFields(new HighlightBuilder.Field("*"))
		.build();
//		SearchResponse res = elasticsearchTemplate.queryForPage(searchQuery, Catalog.class);
	
		Page<Catalog> sampleEntities = elasticsearchTemplate.queryForPage(searchQuery, Catalog.class, new SearchResultMapper() {
			@Override
			public <T> FacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				Map<String, String> hightlightMap = new HashMap<String, String>();
				List<String> chunk = new ArrayList<String>();
				for (SearchHit searchHit : response.getHits()) {
					if (response.getHits().getHits().length <= 0) {
						return null;
					}
					
					Map<String,HighlightField> highlightFields=searchHit.getHighlightFields();
			    	for (String fieldName : highlightFields.keySet()) {
			    		HighlightField highlightField=highlightFields.get(fieldName);
			    		Text[] str = highlightField.getFragments();
//			    		System.out.println(fieldName+"---->" +str[0]);
			    		chunk.add(String.valueOf(str[0]));
			    	}
					
				}
				if (chunk.size() > 0) {
					return new FacetedPageImpl<T>((List<T>) chunk);
				}
				return null;
			}
		});
		System.out.println(sampleEntities.getContent());
	}
	
	
	// Indexing..
	@Test
	public void populatePartType() {
		Part part = new Part();
		part.setPartId(1L);
		part.setPartNumber("part1");

		List<String> vendorPartList = new ArrayList<String>();
		vendorPartList.add("vendorPart1");
		vendorPartList.add("vendorPart2");
		part.setVendorPartNumber(vendorPartList);

		List<String> legacyPartList = new ArrayList<String>();
		legacyPartList.add("legacyPart1");
		legacyPartList.add("legacyPart2");
		part.setLegacyPartNumber(legacyPartList);

		List<Description> descriptionList = new ArrayList<Description>();
		Description descriptionEn = new Description();
		descriptionEn.setCommercialDescription("CommercialEN");
		descriptionEn.setEngineeringDescription("EngineeringEn");
		descriptionEn.setLanguageName("EN");

		Description descriptionGer = new Description();
		descriptionGer.setCommercialDescription("CommercialGer");
		descriptionGer.setEngineeringDescription("EngineeringGer");
		descriptionGer.setLanguageName("Ger");

		descriptionList.add(descriptionEn);
		descriptionList.add(descriptionGer);

		part.setDescription(descriptionList);
		part.setMediaId(2L);
		part.setWebPath("webPath");
		part.setThumbNailPath("thumbNailPath");
		part.setExportFilePath("exportFilePath");
		partRepository.index(part);
		System.out.println("Completed===");

	}

	@Test
	public void populateCatalogIndex() {
		catalogService.populateCatalogIndex();
	}

	// adding the partIndex
	@Test
	public void populatePartIndex() {
		partService.populatePartIndex();
		System.out.println("complete");
		//246284
	}
	@Test
	public void searchCatalogTest(){
	/*	QueryBuilder query = null;
		query = QueryBuilders.multiMatchQuery("T38A0194", "_all").;

		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(
				query).build();
		System.out.println();*/
		Pageable page = null;
		List<Catalog> catalogList = catalogRepository.findByCatalogName("550 197",new PageRequest(0, (int)catalogRepository.count()) );//(searchQuery, Catalog.class);
		System.out.println(catalogList.size());
		for(Catalog catalog :catalogList){
			System.out.println(catalog.getCatalogName());
		}
	}

}
