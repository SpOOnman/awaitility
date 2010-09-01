/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jayway.awaitility.proxy;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.junit.Test;

import com.jayway.awaitility.classes.ClassWithMethods;

public class ProxyCreatorTest {

    @Test
    public void interceptsStandardMethodCalls() throws Exception {
        ClassWithMethods object = (ClassWithMethods) ProxyCreator.create(ClassWithMethods.class,
                new InvocationHandler() {

                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return "test";
                    }
                });

        assertEquals("test", object.aMethod());
    }

    @Test
    public void interceptsReflectionMethodCalls() throws Exception {
        ClassWithMethods object = (ClassWithMethods) ProxyCreator.create(ClassWithMethods.class,
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return "test";
                    }
                });
        Method method = object.getClass().getMethod("aMethod");
        assertEquals("test", method.invoke(object));
    }
}
