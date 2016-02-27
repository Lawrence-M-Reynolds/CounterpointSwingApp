package model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.API_CompositionService;

import composition.Composition;

public class ApplicationModel implements IApplicationModel {
	private static Logger logger = LoggerFactory.getLogger(ApplicationModel.class);
	private Composition currentComposition;
	private List<Composition> counterpointSolutions;
	private API_CompositionService compositionService;

	public Composition getCurrentComposition() {
		return currentComposition;
	}

	public void setCounterpointSolutions(List<Composition> counterpointSolutions) {
		// TODO Auto-generated method stub

	}

	public void setCurrentComposition(Composition composition) {
		currentComposition = composition;
	}

	public boolean isCompositionSerialised() {
		// TODO Auto-generated method stub
		return false;
	}

	public void saveCompositionToDb(Composition composition) {
		logger.info("Saving composition.");
		compositionService.persistCompositionToDb(composition);
		logger.info("Composition Saved.");
		
	}
}


