package controller.viewLogic;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import composition.Composition;
import composition.CompositionManager;
import composition.components.trackComponents.Bar;
import composition.components.trackComponents.barComponents.CompositionBarEvent;
import composition.components.tracks.InstrumentTrack;
import controller.validators.CompositionDtoValidator;

import reynolds.lawrence.CompositionPlayback.soundGeneration.MusicPlayerFacade;
import counterpointOperations.facades.CounterpointOperationsFacade;

import model.IApplicationModel;
import userInterface.IUserInterface;
import userInterface.dto.CompositionDTO;
import userInterface.stavePanel.barEventMapping.MappedCompositionObject;
import util.PropertiesManager;
import util.serialization.Serializer;

public class ViewEventListenerBuilder {

	private Map<ViewEventListenerMappingKeys, EventListener> viewEventListenerMap = 
			new HashMap<ViewEventListenerMappingKeys, EventListener>();

	// Model and UI
	private IApplicationModel applicationModel;
	private IUserInterface userInterface;

	// Back-end components
	private MusicPlayerFacade musicPlayerFacade;
	private CounterpointOperationsFacade counterpointOperationsFacade;

	// Util
	private Serializer serializer;
	private PropertiesManager propertiesManager;
	
	//FIXME - This should be injected.
	private CompositionManager compositionManager = 
			CompositionManager.getInstance();
	private CompositionDtoValidator compositionDtoValidator = new CompositionDtoValidator();

	public ViewEventListenerBuilder(IApplicationModel model,
			IUserInterface aUserInterface, Serializer aSerializer,
			PropertiesManager aPropertiesManager,
			MusicPlayerFacade aMusicPlayerFacade,
			CounterpointOperationsFacade aCounterpointOperationsFacade) {
		applicationModel = model;
		userInterface = aUserInterface;
		serializer = aSerializer;
		propertiesManager = aPropertiesManager;
		musicPlayerFacade = aMusicPlayerFacade;
		counterpointOperationsFacade = aCounterpointOperationsFacade;

		setFrameListeners();
		setFrameMenuListeners();
		setStavePanelListeners();
		setCompositionCreatorListeners();
	}

	public Map<ViewEventListenerMappingKeys, EventListener> getViewLogicMap() {
		return viewEventListenerMap;
	}

	private void setFrameListeners() {
		// Window closing
		WindowAdapter windowAdapter = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Properties appProperties = propertiesManager.getAppProperties();
				Dimension windowSize = userInterface.getWindowSize();
				appProperties.setProperty("WindowClosingWidth",
						Double.toString(windowSize.getWidth()));
				appProperties.setProperty("WindowClosingHeight",
						Double.toString(windowSize.getHeight()));
				appProperties.setProperty("LastSaveOrLoadLocation", serializer
						.getLastSaveOrLoadLocation().getAbsolutePath());

				propertiesManager.saveAllProperties();
				System.exit(0);
			}
		};

		viewEventListenerMap.put(
				ViewEventListenerMappingKeys.CLOSE_APPLICATION, windowAdapter);

	}

	private void setFrameMenuListeners() {
		setFileMenuItemListeners();
		setCounterpointMenuItemListeners();
		setPlaybackMenuItemListeners();
	}

	private void setFileMenuItemListeners() {
		// New Composition
		ActionListener newCompositionActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Show the interface which will handle it from there.
				userInterface.showCreateNewCompositionInterface();
			}
		};
		viewEventListenerMap.put(ViewEventListenerMappingKeys.NEW_COMPOSITION,
				newCompositionActionListener);

		// Load Composition
		ActionListener loadCompositionActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Show the load interface which will handle it from there.
				userInterface.showLoadCompositionInterface();
				userInterface.showAlert("Not implemented yet.");
			}
		};
		viewEventListenerMap.put(ViewEventListenerMappingKeys.LOAD_COMPOSITION,
				loadCompositionActionListener);

		// Save Composition
		ActionListener saveCompositionActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Show the save interface which will handle it from there.
//				userInterface.showSaveCompositionInterface();
//				userInterface.showAlert("Not implemented yet.");
				
				//DB persistance
				applicationModel.saveCompositionToDb(
						userInterface.getCurrentComposition());
				userInterface.showAlert("Persisting to DB...");
			}
		};
		viewEventListenerMap.put(ViewEventListenerMappingKeys.SAVE_COMPOSITION,
				saveCompositionActionListener);

		// Close Composition
		ActionListener closeCompositionActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(applicationModel.isCompositionSerialised()){
					applicationModel.setCurrentComposition(null);
					userInterface.closeCompositionView();
				} else{					
					userInterface.showSaveBeforeCloseInterface();					
				}				
				userInterface.showAlert("Not implemented yet.");
			}
		};
		viewEventListenerMap.put(
				ViewEventListenerMappingKeys.CLOSE_COMPOSITION,
				closeCompositionActionListener);
	}

	private void setCounterpointMenuItemListeners() {
		// Bring up counterpoint evaluation options window.
		ActionListener configureCounterpointEvaluationActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				userInterface.showCounterpointEvaluationConfigurer();
			}
		};
		viewEventListenerMap.put(
				ViewEventListenerMappingKeys.SHOW_COUNTERPOINT_EVALUATION_CONFIGURER,
				configureCounterpointEvaluationActionListener);		
		
		// Evaluate Composition
		ActionListener evaluateCompositionActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 *  TODO - Need to come back to this. The way that the markings are shown needs
				 *  to be reviewed.
				 */
				
				userInterface.showAlert("Need to implement counterpoint annotations differently.");
			}
		};
		viewEventListenerMap.put(
				ViewEventListenerMappingKeys.EVALUATE_COMPOSITION,
				evaluateCompositionActionListener);

		// Generate counterpoint solutions
		ActionListener generateCounterpointSolutionsActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<Composition> counterpointSolutions = counterpointOperationsFacade
						.generateFirstSpeciesSolutions(applicationModel.getCurrentComposition());
				applicationModel.setCounterpointSolutions(counterpointSolutions);

				userInterface.loadGeneratedCompositionsInterface(counterpointSolutions);
				userInterface.showAlert("Not implemented yet.");
			}

		};
		viewEventListenerMap.put(
				ViewEventListenerMappingKeys.GENERATE_COUNTERPOINT_SOLUTIONS,
				evaluateCompositionActionListener);
	}

	private void setPlaybackMenuItemListeners() {
		// Play Composition
		ActionListener playCompositionActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				musicPlayerFacade.playComposition(applicationModel
						.getCurrentComposition());
			}
		};
		viewEventListenerMap.put(ViewEventListenerMappingKeys.PLAY_COMPOSITION,
				playCompositionActionListener);

		// Export Composition as midi file
		ActionListener exportCompositionAsMidiActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				musicPlayerFacade.exportCompositionAsMidi(applicationModel
						.getCurrentComposition());
			}
		};
		viewEventListenerMap.put(
				ViewEventListenerMappingKeys.EXPORT_COMPOSITION_AS_MIDI,
				exportCompositionAsMidiActionListener);
	}

	private void setStavePanelListeners() {
		// Write annotation on mouse click
		MouseListener writeAnotationMouseListener = new MouseAdapter() {
			/**
			 * Takes the point clicked on and checks it with the bar event
			 * mapping object from the ui. If it is a mapped point then the
			 * related track, bar, existing event to write over, pitch line
			 * value are returned. The currently selected note is then obtained
			 * from the UI and all of this information is passed to the
			 * BarEventManager which will make the change to the composition.
			 */
			public void mouseClicked(MouseEvent e) {
				/*
				 * 1. Get point from event. 2. Check if the point is mapped. 3.
				 * Get related objects in mapper 4. Write the change to the
				 * composition. 5. Call redraw on the view.
				 */

				Point mappedPoint = userInterface.getRelatedMapPoint(e.getPoint());
				if (mappedPoint != null) {
					//There was a mapped point.
					MappedCompositionObject mappedCompositionObject = userInterface
							.getMappedCompositionObject(mappedPoint);
					
					CompositionBarEvent compositionBarEvent = 
							mappedCompositionObject.getCompositionBarEvent();
					Bar bar = mappedCompositionObject.getBar();
					InstrumentTrack instrumentTrack = 
							mappedCompositionObject.getInstrumentTrack();
					int pitchLineNumber = 
							mappedCompositionObject.getPitchLocationNumber();
					
					compositionManager.writeSelectedNoteToComposition(
							compositionBarEvent, bar, instrumentTrack, pitchLineNumber,
							userInterface.getSelectedNote());
					userInterface.drawComposition();
				}
			}
		};
		viewEventListenerMap.put(ViewEventListenerMappingKeys.WRITE_ANNOTATION,
				writeAnotationMouseListener);

		MouseMotionAdapter showNoteAtPointMouseListener = new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {

				/*
				 * 1. Check if the point is mapped 
				 * 2. If it is mapped then get
				 * the exact point. 
				 * 3. If it isn't mapped then just use the
				 * point itself. 
				 * 4. Call redraw on the ui, passing in the point
				 * (Use a separate method as I may find a way to just draw at
				 * the specific point.)
				 */
				Point point = e.getPoint();
				if (userInterface.getRelatedMapPoint(point) != null) {
					point = userInterface.getRelatedMapPoint(point);
				}

				userInterface.drawSelectedNoteAtPoint(point);
			}
		};

		viewEventListenerMap.put(
				ViewEventListenerMappingKeys.SHOW_NOTE_AT_POINT,
				showNoteAtPointMouseListener);
	}
	
	private void setCompositionCreatorListeners() {
		ActionListener createCompositionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Have decided not to implement the DTO way yet as
				//it's too much work without any real benifit.
//				/*
//				 * 1. Get the CompositionDTO object
//				 * 2. Validate
//				 * 		If failed - loadup another view
//				 * 3. Create the composition object.
//				 * 4. load composition view.
//				 */			
//				CompositionDTO compositionDTO = 
//						userInterface.getCompositionDTO();
//				boolean passValidation = 
//						compositionDtoValidator.validateCompositionDTO(
//						compositionDTO);
//				if(passValidation){
//					Composition newComposition = 
//							compositionManager.createCompositionFromDto(
//							compositionDTO);
//					applicationModel.setCurrentComposition(newComposition);
//					userInterface.loadCompositionView(newComposition);
//				}else{
//					
//				}
				
				Composition newComposition = 
						userInterface.getCreatedComposition();
				userInterface.closeCreator();
				applicationModel.setCurrentComposition(newComposition);
				userInterface.loadCompositionView(newComposition);
			}			
		};

		viewEventListenerMap.put(
				ViewEventListenerMappingKeys.CREATE_COMPOSITION,
				createCompositionListener);
	}
}
