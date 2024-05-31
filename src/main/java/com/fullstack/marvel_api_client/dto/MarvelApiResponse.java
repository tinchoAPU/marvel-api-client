package com.fullstack.marvel_api_client.dto;

public class MarvelApiResponse {
    private int code;
    private String copyright;
    private String status;
    private MarvelApiDataDTO data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MarvelApiDataDTO getData() {
        return data;
    }

    public void setData(MarvelApiDataDTO data) {
        this.data = data;
    }
}
