package kr.co.kmarket2.utils;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCondition {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String cate1;
    private String cate2;
    private Integer no = 0;
    private String searchField;
    private String searchWord;

    // 추가 필드
    private int type;
    private String uid;
    private String csType;

    public String getQueryString(Integer page){
        // ?page=1&pageSize=10&option="T"&keyword="title"
        return getQueryString(page, no);
    }

    public String getQueryString(Integer page, Integer no){
        // ?page=1&pageSize=10&option="T"&keyword="title"
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .queryParam("cate1", cate1)
                .queryParam("cate2", cate2)
                .queryParam("page", page);


        if (no != null && no != 0)
            builder.queryParam("no", no);

        // 검색 기능
        if(searchField != null && !searchWord.isBlank()){
            builder.queryParam("searchField", searchField)
                    .queryParam("searchWord", searchWord);
        }

        return builder.toUriString();
    }

    public String getQueryString(){
        // ?page=1&pageSize=10&option="T"&keyword="title"
        return getQueryString(page);
    }

    // limit
    public Integer getOffset() {
        return (page-1) * pageSize;
    }

    public void setPage(Integer page) {
        this.page = page == 0 ? 1:page;
    }

}