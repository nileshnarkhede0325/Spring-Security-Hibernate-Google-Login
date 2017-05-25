package net.saama.spring.service;

import net.saama.spring.dao.AppDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppServices implements IAppServices {

	@Autowired
	AppDao appDao;

	public void checkConnection() throws Exception {
		appDao.checkConnection();
	}

//	@Override
//	public void checkConnection() throws Exception {
//		appDao.checkConnection();
//	}

}
