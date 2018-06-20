package com.zfsoft.boot.metrics.ext.health;

import java.sql.Connection;

import com.codahale.metrics.health.HealthCheck;

public class ConnectionHealthCheck extends HealthCheck{
	
	protected Connection connection;
    protected int timeout = 5000;

    public ConnectionHealthCheck(Connection connection,int timeout) {
        this.connection = connection;
    }

    @Override
    protected Result check() throws Exception {
        if (connection.isValid(timeout)) {
            return Result.healthy();
        }
        return Result.unhealthy(" Connection Timeout.");
    }


}
