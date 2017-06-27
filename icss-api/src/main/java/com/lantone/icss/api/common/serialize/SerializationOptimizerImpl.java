package com.lantone.icss.api.common.serialize;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;

public class SerializationOptimizerImpl implements SerializationOptimizer {
	public Collection<Class> getSerializableClasses() {
        List<Class> classes = new LinkedList<Class>();
        return classes;
	}
}
