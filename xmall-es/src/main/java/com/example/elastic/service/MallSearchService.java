package com.example.elastic.service;

import com.example.elastic.vo.SearchParam;
import com.example.elastic.vo.SearchResult;

public interface MallSearchService {
    SearchResult search(SearchParam param);
}
