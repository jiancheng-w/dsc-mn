package com.smil.dcs.controller;

import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {

    protected void setResponseToExcel(HttpServletResponse response,String fileName){
        response.setHeader("Content-type", "textml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" +fileName+ ".xlsx");
    }

    protected void setResponseToCsv(HttpServletResponse response,String fileName){
        response.setHeader("Content-type", "textml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/csv");
        response.setHeader("Content-disposition", "attachment;filename=" +fileName+ ".csv");
    }

}
