package org.graduationdesign.gdmsserversearch.controller;

import org.graduationdesign.gdmsserversearch.entity.Message;
import org.graduationdesign.gdmsserversearch.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/search")
    public  List<Message> searchPage(@RequestBody Map<String,String> params) throws IOException {
        String department= params.get("dept");
        String id=params.get("id");
        String keywords=params.get("keywords");
        System.out.println(keywords);
        int pageNo= Integer.parseInt(params.get("pageNo"));
        int pageSize= Integer.parseInt(params.get("pageSize"));
        return messageService.searchPage(keywords,id,department, pageNo, pageSize);
    }
}
