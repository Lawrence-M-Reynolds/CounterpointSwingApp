package controller;


import java.util.EventListener;
import java.util.Map;
import java.util.Properties;

import model.IApplicationModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import reynolds.lawrence.CompositionPlayback.soundGeneration.MusicPlayerFacade;
import userInterface.IUserInterface;
import util.PropertiesManager;
import util.serialization.Serializer;
import controller.viewLogic.ViewEventListenerBuilder;
import controller.viewLogic.ViewEventListenerMappingKeys;
import counterpointOperations.facades.CounterpointOperationsFacade;

/**
 * The starting point of the application and acts as the controller. It connects the 
 * stave UI, which is used for writing a composition and reviewing the output of the 
 * counterpoint algorithms, to both the sound generation counterpoint algorithm
 * parts of the application.  
 * @author BAZ
 *
 */
public class ApplicationController{
	//Util
	private static Logger logger = LoggerFactory.getLogger(ApplicationController.class);
	private PropertiesManager propertiesManager = new PropertiesManager();
	private Serializer serializer;
	
	private static ApplicationContext context;
	
	//TODO should be configured from spring.
	public static String[] fileExtensions = {".compapp"};
	
	private IApplicationModel applicationModel;
	private IUserInterface userInterface;
	
	
	public static void main(String[] args)
	{
		logger.info("Logging!");
		logger.info("Starting app: ");
		System.out.println("Herer!~! : " + logger);
		context = new ClassPathXmlApplicationContext("Spring-Config.xml");
		context.getBean("applicationController");
	}
		
	public ApplicationController(IApplicationModel model, IUserInterface aUserInterface,
			MusicPlayerFacade musicPlayerFacade, CounterpointOperationsFacade counterpointOperationsFacade){
		userInterface = aUserInterface;
		applicationModel = model;
		
		Properties appProperties = propertiesManager.getAppProperties();
		serializer = new Serializer(appProperties.getProperty("LastSaveOrLoadLocation", "C:"),
				ApplicationController.fileExtensions, 0);
		
		//Listeners need values from the drawer
		ViewEventListenerBuilder viewEventListenerBuilder = 
				new ViewEventListenerBuilder(model, userInterface, serializer,
				propertiesManager, musicPlayerFacade, counterpointOperationsFacade);
		
		Map<ViewEventListenerMappingKeys, EventListener> viewEventListenerMap = 
				viewEventListenerBuilder.getViewLogicMap();
		userInterface.setViewEventListenerMap(viewEventListenerMap);
		
		int windowWidth = (int)Double.parseDouble(appProperties.getProperty("WindowClosingWidth", "700"));
		int windowHeight = (int)Double.parseDouble(appProperties.getProperty("WindowClosingHeight", "800"));
		userInterface.createUI(windowWidth, windowHeight);
	}
}
