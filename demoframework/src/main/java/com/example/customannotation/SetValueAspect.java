package com.example.customannotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author jinBiaoHu
 * @date 2019-01-14 21:54
 * 自动赋值切面
 */
@Aspect
@Component
public class SetValueAspect {
	@Autowired
	private BeanUtil beanUtil;

	/**
	 * 环绕通知:
	 * @param joinPoint
	 * @return
	 */
	@Around("@annotation(com.example.customannotation.NeedSetFieldValue)")
	public Object doSetFieldValue(ProceedingJoinPoint joinPoint) throws Throwable {
/*		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String beanName = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		String uri = request.getRequestURI();*/


		// 前置操作
		// 执行目标方法
		Object result = joinPoint.proceed();
		// 后置操作
		if (result instanceof Collection) {
			beanUtil.setFiledValueForController((Collection)result);
		}else{
			//不是集合 单个对象需要设置 需要在beanUtil中单独设置
		}
		return result;
	}
}
