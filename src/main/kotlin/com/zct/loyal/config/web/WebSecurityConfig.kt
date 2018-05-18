package com.zct.loyal.config.web


import com.zct.loyal.core.security.CustomUserService
import com.zct.loyal.core.security.SecurityProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启security注解
class WebSecurityConfig : WebSecurityConfigurerAdapter() {


    @Autowired
    var customUserService: CustomUserService? = null


    @Autowired
    var securityProvider: SecurityProvider? = null


    @Bean
    internal fun customUserService(): UserDetailsService { //注册UserDetailsService 的bean
        return this.customUserService!!
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(securityProvider)

    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/error/**").permitAll()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler { request, response, auth ->
                    val principal = SecurityContextHolder.getContext().authentication
                    request.session.setAttribute("user", principal.principal)
                    response.sendRedirect("/")
                }
                .failureHandler{ httpServletRequest, httpServletResponse, authenticationException ->
                    httpServletRequest.setAttribute("message",authenticationException.localizedMessage)
                    httpServletRequest.getRequestDispatcher("/login?error=true").forward(httpServletRequest,httpServletResponse)
                }
                .permitAll() //登录页面用户任意访问
                .and()
                .headers().frameOptions().disable()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .addLogoutHandler{ httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, authentication: Authentication ->
                    httpServletRequest.session.removeAttribute("user")
                }
                .permitAll() //注销行为任意访问
        http.csrf().disable()
        // http.addFilterBefore(myFilterSecurityInterceptor!!, FilterSecurityInterceptor::class.java)
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/static/css/**", "/static/js/**", "/static/img/**", "/static/fonts/**","/static/js/plugin/**")
    }
}
