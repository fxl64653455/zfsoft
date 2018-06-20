/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.setup.event.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.zfsoft.boot.apimgr.dao.daointerface.IApiDatabaseDao;
import com.zfsoft.boot.apimgr.dao.entities.ApiDatabaseModel;
import com.zfsoft.boot.apimgr.service.svcinterface.IApiDatabaseService;
import com.zfsoft.boot.flyway.ext.FlywayMigratedEvent;

@Component
public class APIDataSourceOnFlywayMigratedEventListener implements ApplicationListener<FlywayMigratedEvent> {

	@Autowired
	private IApiDatabaseService apiDatabaseService;
	@Autowired
	private IApiDatabaseDao apiDatabaseDao;

	@Override
	public void onApplicationEvent(FlywayMigratedEvent event) {

		//((ApiDatabaseServiceImpl)apiDatabaseService).setDao(apiDatabaseDao);
	
		ApiDatabaseModel model = new ApiDatabaseModel();
		List<ApiDatabaseModel> dbList = apiDatabaseDao.getModelList(model);
		for (ApiDatabaseModel dbModel : dbList) {
			apiDatabaseService.setNewDatasource(dbModel);
		}
		
	}
	
}
