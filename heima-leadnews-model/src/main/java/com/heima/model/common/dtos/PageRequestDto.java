package com.heima.model.common.dtos;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class PageRequestDto {

    protected Integer size;
    protected Integer page;

    public void checkParam() {
        if (this.page == null || this.page < 0) {
            setPage(1);
        }
        if (this.size == null || this.size < 0 || this.size > 100) {
            setSize(10);
        }
    }

//    public static void main(String[] args) {
//        PageRequestDto dto = new PageRequestDto();
//        PageRequestDto dto2 = new PageRequestDto();
//        System.out.println(dto);
//        List list = new ArrayList();
//        dto.setPage(1);
//        dto2.setPage(1);
//        dto.setSize(10);
//        dto2.setSize(10);
//        list.add(dto);
//        list.add(dto2);
//        String jsonString = JSON.toJSONString(list);
//        String dtoString = JSON.toJSONString(dto);
//        System.out.println(jsonString);
//        System.out.println(dtoString);
//    }
}
