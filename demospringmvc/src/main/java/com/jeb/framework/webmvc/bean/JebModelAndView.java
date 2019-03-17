package com.jeb.framework.webmvc.bean;

import java.util.Map;

/**
 * 文件名：JebModelAndView
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-17 08:23
 */
public class JebModelAndView {
    //页面模版
    private String view;

    private Map<String, Object> model;

    public JebModelAndView(String view) {
        this.view = view;

    }

    public JebModelAndView(String view, Map<String, Object> model) {
        this.view = view;
        this.model = model;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
