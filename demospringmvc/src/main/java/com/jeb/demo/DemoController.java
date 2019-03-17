package com.jeb.demo;

import com.jeb.framework.annotation.JebAutowired;
import com.jeb.framework.annotation.JebController;
import com.jeb.framework.annotation.JebRequestMapping;
import com.jeb.framework.annotation.JebRequestParam;
import com.jeb.framework.webmvc.bean.JebModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jinBiaoHu
 * @date 2019-01-13 17:18
 */
@JebController
@JebRequestMapping("/demo")
public class DemoController {

    @JebAutowired
    IDemoService demoService;

    @JebRequestMapping("/quary")
    public JebModelAndView quary(HttpServletRequest req, HttpServletResponse resp,
                                 @JebRequestParam("name") String name) {

        String result = demoService.get(name);
       /* try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", result);
        model.put("addr", "nj");
        return  new JebModelAndView("first.jebml",model);
    }

    @JebRequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp, @JebRequestParam("a") String a, @JebRequestParam("b") String b) {
        try {
            resp.getWriter().write("add:" + a + "___" + b);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
