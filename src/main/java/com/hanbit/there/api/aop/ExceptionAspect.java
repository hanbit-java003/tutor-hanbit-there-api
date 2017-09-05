package com.hanbit.there.api.aop;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbit.there.api.exception.ExceptionVO;
import com.hanbit.there.api.exception.HanbitException;

@Aspect
@Component
@Order(10)
public class ExceptionAspect {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);

	private static final ObjectMapper jsonMapper = new ObjectMapper();

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void requestMapping() {

	}
	@Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
	public void postMapping() {

	}
	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	public void getMapping() {

	}

	@Around("requestMapping() || postMapping() || getMapping()")
	public Object handleException(ProceedingJoinPoint pjp)
			throws UnsupportedEncodingException, IOException {
		try {
			return pjp.proceed();
		}
		catch (Throwable t) {
			ServletRequestAttributes reuqestAttributes
				= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletResponse response = reuqestAttributes.getResponse();

			String message = "사용자가 많아 서비스가 지연되고있습니다.";

			if (t instanceof HanbitException) {
				message = t.getMessage();
			}
			else {
				logger.error(t.getMessage(), t);
			}

			ExceptionVO exceptionVO = new ExceptionVO(message);

			byte[] bytes = jsonMapper.writeValueAsBytes(exceptionVO);

			response.setStatus(500);
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			response.flushBuffer();
		}

		return null;
	}

}







