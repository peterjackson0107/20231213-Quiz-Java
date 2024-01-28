//package com.example.quiz.config;
//
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.caffeine.CaffeineCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.github.benmanes.caffeine.cache.Caffeine;
//
//@EnableCaching // Spring framework ���Á��Ӿ�����]�⣬
////������ֻ��Ҫ��һ�� CacheManager �� Bean 
//@Configuration // ���ó��O���n���K���� Spring Boot Ӛ��
//public class CaffeineCacheConfig {
//
//	@Bean
//	public CacheManager cacheManager() {
//		CaffeineCacheManager cacheManager = new CaffeineCacheManager();
//		cacheManager.setCaffeine(Caffeine.newBuilder()
//// �O���^�ڕr�g: 1. ����һ�Ό���� 2. �L������^�̶�һ�Εr�g
//				.expireAfterAccess(600, TimeUnit.SECONDS)
//// ��ʼ�ľ�����g��С
//				.initialCapacity(100)
//// ��������P��
//				.maximumSize(500));
//		return cacheManager;
//	}
//
//}
