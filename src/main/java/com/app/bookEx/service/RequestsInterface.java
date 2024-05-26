package com.app.bookEx.service;

import com.app.bookEx.domain.pojo.Requests;

import java.util.List;

public interface RequestsInterface {
    String sendRequest(String body);

    List<Requests> getRequestForId(Integer id);
}
