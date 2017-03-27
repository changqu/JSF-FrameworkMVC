package com.sdi.business.impl;

import com.sdi.business.PublicoService;
import com.sdi.business.RegistradoService;
import com.sdi.business.ServicesFactory;

public class SimpleServicesFactory implements ServicesFactory{

	@Override
	public PublicoService createPublicoService() {
		return new SimplePublicoService();
	}

	@Override
	public RegistradoService createRegistradoService() {
		return new SimpleRegistradoService();
	}

}
