package antonburshteyn.post.service.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Aspect
@Slf4j(topic = "Post service")
public class PostServiceLogger {
	
	@Pointcut("execution(* antonburshteyn.post.service.PostServiceImpl.findPostById(String)) && args(id)")
	public void findById(String id) {}
	
	@Pointcut("execution(public Iterable<antonburshteyn.post.dto.PostDto> antonburshteyn.post.service.PostServiceImpl.findPosts*(..))")
	public void bulkFindPosts() {}
	
	@Pointcut("@annotation(antonburshteyn.post.service.logging.PostLogger)")
	public void annotatedPostLogger() {}
	
	@Before("findById(id)")
	public void getPostLogging(String id) {
		log.info("post with id {}, requested", id);
	}
	
	@Around("bulkFindPosts()")
	public Object bulkFindPostsLogging(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		long start = System.currentTimeMillis();
		Object result = pjp.proceed(args);
		long end = System.currentTimeMillis();
		log.info("method {} took {} ms", pjp.getSignature().getName(), end - start);
		return result;
	}
	
	@Around("annotatedPostLogger()")
	public Object annotatedPostLoggerLogging(ProceedingJoinPoint pjp) throws Throwable{
		long start = System.currentTimeMillis();
		Object result = pjp.proceed();
		long end = System.currentTimeMillis();
		log.info("method {} took {} ms", pjp.getSignature().getName(), end - start);
		return result;
	}


}
