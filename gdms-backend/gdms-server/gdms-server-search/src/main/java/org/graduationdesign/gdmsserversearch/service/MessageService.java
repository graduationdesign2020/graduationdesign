package org.graduationdesign.gdmsserversearch.service;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.graduationdesign.gdmsserversearch.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class MessageService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    public List<Message> searchPage(String keyword,String id,String department, int pageNo, int pageSize) throws IOException {
        if(pageNo<=0){
            pageNo=0;
        }

        SearchRequest searchRequest=new SearchRequest("gdms");
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();

        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        BoolQueryBuilder queryBuilders= QueryBuilders.boolQuery()
                .must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("title",keyword))
                        .should(QueryBuilders.matchQuery("content",keyword)))
                .must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.termQuery("type",0))
                        .should(QueryBuilders.boolQuery()
                                .must(QueryBuilders.termQuery("type",1))
                                .must(QueryBuilders.matchQuery("department",department))
                        )
                        .should(QueryBuilders.boolQuery()
                                .must(QueryBuilders.termQuery("type",2))
                                .must(QueryBuilders.termQuery("student",id))
                        ));
        searchSourceBuilder.query(queryBuilders);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse);
        ArrayList<Message> list=new ArrayList<>();
        for (SearchHit documentFields:searchResponse.getHits().getHits()){
            Message message=new Message();
            message.setTitle((String) documentFields.getSourceAsMap().get("title"));
            message.setType((Integer) documentFields.getSourceAsMap().get("type"));
            message.setId((Integer) documentFields.getSourceAsMap().get("id"));
            message.setContent((String) documentFields.getSourceAsMap().get("content"));
            list.add(message);
        }
        return list;
    }

}
