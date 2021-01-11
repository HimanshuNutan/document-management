package com.ac.documentmanagement.configuration;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
public class SecurityContextHystrixConcurrencyStrategyConfig extends HystrixConcurrencyStrategy{
	
	public SecurityContextHystrixConcurrencyStrategyConfig() {

        try {
            HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public <T> Callable<T> wrapCallable(Callable<T> callable) {
		return new SecurityContextCallable<>(callable);
	}
	
	public static class SecurityContextCallable<K> implements Callable<K> {

		private final Callable<K> actual;
		private final SecurityContext securityContext;

		public SecurityContextCallable(Callable<K> actual) {
		    this.actual = actual;
		    this.securityContext = SecurityContextHolder.getContext();
		}

		@Override
		public K call() throws Exception {
			SecurityContext existingSecurityContext = SecurityContextHolder.getContext();
		    try {
		    	SecurityContextHolder.setContext(securityContext);
		        return actual.call();
		    } finally {
		    	SecurityContextHolder.setContext(existingSecurityContext);
		    }
		}
		}

}
