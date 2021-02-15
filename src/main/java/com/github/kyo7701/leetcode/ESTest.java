package com.github.kyo7701.leetcode;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * Author:Mr.Cris
 * Date:2021-01-07 16:03
 *
 * @description
 */
public class ESTest {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        IndexRequest indexRequest = new IndexRequest("posts").id("1");
        indexRequest.source("user","24k");
        client.index(indexRequest,RequestOptions.DEFAULT);
        client.close();
    }



}
