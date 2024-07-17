package com.mirror.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mirror.anno.RequireLogin;
import com.mirror.common.Constants;
import com.mirror.common.Result;
import com.mirror.common.ResultEnum;
import com.mirror.utils.AuthenticationUtil;
import com.mirror.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Component
public class RequireLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 0.1 判断 handler 是否是 HandlerMethod 实例, 如果不是, 直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 0.2 将 handler 对象转换为 HandlerMethod 对象
        HandlerMethod hm = (HandlerMethod) handler;
        // 0.3 从 HandlerMethod 对象中获取对应的 Controller 对象
        Class<?> controllerClass = hm.getBeanType();
        // 0.4 分别从 Controller 和 HandlerMethod 上获取 @RequireLogin 注解
        RequireLogin classAnno = controllerClass.getAnnotation(RequireLogin.class);
        RequireLogin methodAnno = hm.getMethodAnnotation(RequireLogin.class);
        // 0.5 如果一个都拿不到, 直接放行
        if (classAnno == null && methodAnno == null) {
            return true;
        }

        // 1. 从请求头中拿到 jwt token
        String token = request.getHeader(Constants.Header);
        if (StrUtil.isBlankOrUndefined(token)) {
            return false;
        }
        //2. 验证token
        try {
            JwtUtil.verify(token);
        } catch (JWTVerificationException e) {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(JSONUtil.toJsonStr(Result.builder().code(ResultEnum.TOKEN_EXPIRE.getCode()).message(ResultEnum.TOKEN_EXPIRE.getMessage()).build()));
            log.info("[登录拦截] jwt token 解析失败");
            return false;
        }
        // 3. 其他情况就放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 请求执行完成以后准备响应之前执行
        // 线程即将完成本次请求, 先将当前线程空间内存储的数据清除掉
        AuthenticationUtil.removeUser();
    }
}

