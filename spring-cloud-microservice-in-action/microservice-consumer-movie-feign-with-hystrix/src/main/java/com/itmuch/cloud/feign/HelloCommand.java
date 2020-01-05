package com.itmuch.cloud.feign;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class HelloCommand extends HystrixCommand<String> {
    protected HelloCommand() {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("example"))
                        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000))
        );
    }
 
    @Override
    protected String run() throws Exception {
        throw new NullPointerException();
    }
 
    @Override
    protected String getFallback() {
        return "failed";
    }
    public static void main( String[] args )
    {
        try {
            System.out.println(new HelloCommand().execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}