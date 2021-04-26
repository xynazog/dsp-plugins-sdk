package com.splunk.streaming.user.functions;
class HelloWorld {
	public HelloWorld() {

	}

	public String print(String input) {
		StringBuilder sb = new StringBuilder();
		sb.append("Hello, ");
		sb.append(input);
		return sb.toString();
	}
}