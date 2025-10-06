package com.verto.quiz.helper;

import com.verto.quiz.custom.PageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public class Helper {


    public static <U,V>PageableResponse<V> getPageableResponse(Page<U> page, Class<V> type){

        List<U> entityContent = page.getContent();

        List<V> dtoContent = entityContent.stream()
                .map(content -> new ModelMapper().map(content, type)).toList();


        PageableResponse<V> response=new PageableResponse<>();
        response.setContent(dtoContent);
        response.setLastPage(page.isLast());
        response.setPageNumber(page.getNumber());
        response.setTotalPage(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setPageSize(page.getSize());

        return response;
    }

}
