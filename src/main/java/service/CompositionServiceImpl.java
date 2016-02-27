package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import composition.Composition;


class CompositionServiceImpl implements API_CompositionService{
	private static Logger logger = LoggerFactory.getLogger(CompositionServiceImpl.class);
	
	public void persistCompositionToDb(Composition composition){
		logger.debug("In the CompositionServiceIml");
	}
}