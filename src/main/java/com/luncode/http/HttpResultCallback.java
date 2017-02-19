package com.luncode.http;

public interface HttpResultCallback {
	void processResult(HttpResult result);
	void processError(Throwable t);
}
