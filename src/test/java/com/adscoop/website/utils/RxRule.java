package com.adscoop.website.utils;

import java.lang.reflect.Method;

import org.junit.rules.ExternalResource;

import rx.plugins.RxJavaPlugins;

public class RxRule extends ExternalResource {

	@Override
	protected void before() throws Throwable {
		Method method = RxJavaPlugins.class.getDeclaredMethod("reset");
		method.setAccessible(true);
		method.invoke(RxJavaPlugins.getInstance());
	}
}
