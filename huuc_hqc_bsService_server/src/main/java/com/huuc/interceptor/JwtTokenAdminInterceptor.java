package com.huuc.interceptor;

import com.huuc.context.BaseContext;
import com.huuc.exception.ResponseException;
import com.huuc.properties.JwtProperties;
import com.huuc.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
@AllArgsConstructor
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    private final JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     */
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader(jwtProperties.getAdminTokenName());
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtils.parseJWT(jwtProperties.getAdminKey(), token);
            String userId = claims.get("userId").toString();
            BaseContext.setCurrentId(userId);
            log.info("当前用户id:{}", userId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
          throw new ResponseException("请先登录");
        }
    }

    /**
     * 请求处理完成后释放线程上下文
     */
    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) {
        BaseContext.removeCurrentId();
        log.info("线程上下文已释放");
    }
}
