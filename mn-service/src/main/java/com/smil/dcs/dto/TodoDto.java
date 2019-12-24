package com.smil.dcs.dto;

public class TodoDto {
    private String menuCode;
    private Integer count;

    public TodoDto(String menuCode, Integer count) {
        this.menuCode = menuCode;
        this.count = count;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
