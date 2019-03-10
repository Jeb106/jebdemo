package com.example.customannotation;

import com.example.customannotation.SetValue;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jinBiaoHu
 * @date 2019-01-14 22:02
 */
@Component
public class BeanUtil implements ApplicationContextAware {
	ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (this.applicationContext == null) {
			this.applicationContext = applicationContext;
			System.out.println("spring applicationContext init ok");
		}
	}

	public void setFiledValueForController(Collection result) {
		if (result.isEmpty()) {
			return;
		}
		// Order == aClass
		Class<?> aClass = result.iterator().next().getClass();
		Field[] declaredFields = aClass.getDeclaredFields();
		//加入本地缓存
		Map<String, Object> cache = new HashMap<>();
		try {
			for (Field field : declaredFields) {
				boolean present = field.isAnnotationPresent(SetValue.class);
				if (present) {
					SetValue annotation = field.getAnnotation(SetValue.class);
					// userMapper
					Class<?> beanClass = annotation.beanClass();

				 /* 下面的反射实现的这段注释代码
				 for(Order order : orders){
					User user = userMapper.find(order.getCustomerId());
					if(null !=user){
						order.setCustomeName(user.getUserName);
					}
				  }
					*/
					//要调用的对象
					Object bean = this.applicationContext.getBean(beanClass);
					//要调用的方法
					Method method = beanClass.getMethod(annotation.method(),
							aClass.getDeclaredField(annotation.paramField()).getType());
					// 获取给入参的值的获取方法
					Method paraValueGetMethtod = aClass
							.getMethod("get" + StringUtils.capitalize(annotation.paramField()));
					// 设置值的set方法
					Method setValueMethtod = aClass
							.getMethod("set" + StringUtils.capitalize(field.getName()), field.getType());

					//获取目标属性值的方法
					Method getTargetValueMethod = null;
					boolean needInnerFiled = !StringUtils.isEmpty(annotation.targetField());
					//遍历对象 List<Order>
					for (Object obj : result) {
						// 获取参数值 customerId
						Object paramValue = paraValueGetMethtod.invoke(obj);
						if (paramValue == null) {
							continue;
						}
						Object value = null;
						//先从缓存获取
						//if (cache.containsKey("key")) {
						if (false) {
							value = cache.get("key");
						}
						else {
							//bean  order
							//调用bean的方法获取目标对象value = user的对象
							value = method.invoke(bean, paramValue);
							// public String find(String customerId) {
							//		return "customerName:"+customerId;
							//	}
							// find()方法返回的直接是String
							/*if (needInnerFiled) {
								if (value != null) {
									if (getTargetValueMethod == null) {
										getTargetValueMethod = value.getClass()
												.getMethod("get" + StringUtils.capitalize(annotation.targetField()));
									}
									value = getTargetValueMethod.invoke(value);
								}
							}*/
							cache.put("key",value);
							setValueMethtod.invoke(obj,value);
						}
					}
				}
			}
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
