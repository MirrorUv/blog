package com.mirror.utils;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mirror.common.Constants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Slf4j
public class AuthenticationUtil {

    private static final ThreadLocal<String> USER_HOLDER = new ThreadLocal<>();

    public static HttpServletRequest getRequest() {
        // 只要是在 Spring MVC 环境中运行, 就不可能为空
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr == null) {
            throw new RuntimeException("该方法只能在 Spring MVC 环境下调用!");
        }
        return attr.getRequest();
    }

    public static String getToken() {
        return getRequest().getHeader(Constants.Header);
    }

    public static String getUser() {
        String UserName = USER_HOLDER.get();
        if (UserName != null) {
            return UserName;
        }

        String token = getToken();
        if (StrUtil.isBlankOrUndefined(token)){
            return null;
        }
        DecodedJWT tokenInfo = JwtUtil.getTokenInfo(token);

        try {
            JwtUtil.verify(token);
            String tokenName = tokenInfo.getClaims().get("name").asString();
            // 将第一次查询到用户信息保存起来
            USER_HOLDER.set(tokenName);
            return tokenName;
        } catch (Exception e) {
            log.warn("[认证工具] 获取用户信息失败");
        }
        return null;
    }
    public static void removeUser() {
        USER_HOLDER.remove();
    }
}
