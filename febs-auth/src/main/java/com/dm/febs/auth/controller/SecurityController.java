package com.dm.febs.auth.controller;

import com.dm.febs.common.entity.FebsResponse;
import com.dm.febs.common.exception.FebsAuthException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class SecurityController {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public FebsResponse signout(HttpServletRequest request) throws FebsAuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        FebsResponse febsResponse = new FebsResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new FebsAuthException("退出登录失败");
        }
        return febsResponse.message("退出登录成功");
    }
}
