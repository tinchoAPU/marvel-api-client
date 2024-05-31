package com.fullstack.marvel_api_client.dto;

import java.util.List;

public class MarvelApiDataDTO {
    private int count;
    private int limit;
    private int offSet;
    private List<CharacterDTO> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffSet() {
        return offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    public List<CharacterDTO> getResults() {
        return results;
    }

    public void setResults(List<CharacterDTO> results) {
        this.results = results;
    }
}
