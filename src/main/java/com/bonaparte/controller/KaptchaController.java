package com.bonaparte.controller;

import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisCommands;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * Created by yangmingquan on 2018/7/16.
 */
@Api(value = "KaptchaController", description = "Kaptcha 验证码API")
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {
    @Autowired
    private Producer producer;
    @Resource(name = "jedisCluster")
    private JedisCommands jedis;

    @ApiOperation(value = "Kafcha图片验证码接口",notes = "Kafcha图片验证码接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/kaptchaImage")
    public ModelAndView getKaptchaImage(HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        HttpSession httpSession = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control",
                "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = producer.createText();
        BufferedImage bi = producer.createImage(capText);
        httpSession.setAttribute("kaptcha", capText);
        Cookie cookie = new Cookie("checkCode", capText);
        response.addCookie(cookie);

        //保存验证到redis
        jedis.set(httpSession.getId(), capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    @ApiOperation(value = "验证登录",notes = "验证登录接口",httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "")
    })
    @RequestMapping("/kaptchaCheck")
    public Object getKaptchaImage(HttpServletRequest request,
                                        String data) throws Exception {
        String sessionId = request.getRequestedSessionId();
        //
        String kaptchaKey = jedis.get(sessionId);
        //从 data解析验证码与Jedis中保存的该session的验证码是否一致，如果一致则验证通过
        return null;
    }
}
