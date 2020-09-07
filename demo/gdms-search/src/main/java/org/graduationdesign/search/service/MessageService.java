package org.graduationdesign.search.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.graduationdesign.search.entity.Message;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class MessageService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    public List<Message> searchPage(String keywords,String id,String department,int pageNo,int pageSize) throws IOException {
        if(pageNo<=0){
            pageNo=0;
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://54.234.98.178:9200/gdms/_search";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
        String content="{\"size\":"+pageSize+",\"from\":"+pageSize*pageNo+",\"query\": {\"bool\": {\"must\":[{\"bool\":{\"should\": [{\"match\": {\"title\":\""+keywords+"\"}},{\"match\":{\"content\": \""+keywords+"\"}}]}},{\"bool\":{\"should\":[{ \"term\": {\"type\": 0}},{\"bool\":{\"must\": [{\"term\": {\"type\": 1}},{\"match\": {\"department\":\""+department+"\"}}]}},{\"bool\":{\"must\": [{\"term\": {\"type\": 2}},{\"match\": {\"student\":\""+id+"\"}}]}}]}}]}}}";
        HttpEntity<String> request = new HttpEntity<>(content,headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class);
//        System.out.println(response.getBody());
        Gson gson = new Gson();
        Map map = new HashMap<String, String>();
        map = gson.fromJson(response.getBody(), map.getClass());
        System.out.println(map.get("hits"));
        Map<String,Object> hits= (Map<String, Object>) map.get("hits");
//        System.out.println(hits.get("hits").getClass());
        ArrayList<Map> hitList= (ArrayList) hits.get("hits");
        ArrayList<Message> list=new ArrayList<>();
        for(Map record:hitList){
//            System.out.println(record.getClass());
            Map<String,Object> source= (Map<String,Object>)record.get("_source");
//            System.out.println(source);
            Message message=new Message();
            message.setTitle((String) source.get("title"));
            message.setType((double) source.get("type"));
            message.setId((double) source.get("id"));
            message.setContent((String) source.get("content"));
            list.add(message);
        }
        return list;
    }

}
