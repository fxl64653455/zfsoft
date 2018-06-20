package com.zfsoft.boot.metrics.ext.servlet;


import com.codahale.metrics.MetricRegistry;
import com.zfsoft.boot.metrics.ext.MetricsFactory;

public class InstrumentedFilterContextListener extends com.codahale.metrics.servlet.InstrumentedFilterContextListener {
	
    @Override
    protected MetricRegistry getMetricRegistry() {
        return MetricsFactory.getContextMetricRegistry();
    }
    
}
