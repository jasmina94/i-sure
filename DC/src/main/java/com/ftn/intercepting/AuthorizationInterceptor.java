package com.ftn.intercepting;

import java.lang.reflect.Method;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.model.authorisation.Permission;
import com.ftn.model.authorisation.Role;
import com.ftn.service.RoleService;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor  {

	@Autowired
	RoleService roleService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("========================Regular interceptor=================================");
		
		HandlerMethod hm=(HandlerMethod)handler; 
		Method method=hm.getMethod();
		String annotationValue = null;
		//if(method.getDeclaringClass().isAnnotationPresent(Controller.class)){
			if(method.isAnnotationPresent(CustomAnnotation.class)){
			CustomAnnotation ano = method.getAnnotation(CustomAnnotation.class);
			annotationValue = ano.value();
			}else{
				System.out.println("else");
				return true;
			}
		//}
		
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		for(SimpleGrantedAuthority auth : authorities) {
			System.out.println(auth.getAuthority());
			Role role;
			try {
				role = roleService.findByName(auth.getAuthority());
				if(!role.equals(null)) {
					for(Permission perm : role.getPermissions()) {
						if(perm.getName().equals(annotationValue)) {
							System.out.println("Nasao permisiju testniiiiiiiiiiiiiiiii");
							return true;
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
		}
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
