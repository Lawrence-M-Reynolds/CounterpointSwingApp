package userInterface;

import java.awt.Dimension;
import java.awt.Point;
import java.util.EventListener;
import java.util.List;
import java.util.Map;

import musicRelatedEntities.note.writtenNotes.WrittenNote;
import userInterface.dto.CompositionDTO;
import userInterface.mainInterface.CompAppJFrame;
import userInterface.stavePanel.barEventMapping.MappedCompositionObject;

import composition.Composition;

import controller.viewLogic.ViewEventListenerMappingKeys;

public interface IUserInterface {

	public Dimension getWindowSize();

	public void setViewEventListenerMap(
			Map<ViewEventListenerMappingKeys, EventListener> viewEventListenerMap);

	public void createUI(int windowWidth, int windowHeight);

	public void loadCompositionView(Composition aComposition);

	/**
	 * Currently, this method just redraws the entire composition as well as the 
	 * currently selected note at the passed in point. In future this could be
	 * refactored to only draw at the specific point.
	 * @param aPoint
	 */
	public void drawSelectedNoteAtPoint(Point aPoint);

	/**
	 * If the passed in point is within the window of a mapped point then the
	 * mapped point is returned. If there isn't one then null is returned.
	 * @param point
	 * @return
	 */
	public Point getRelatedMapPoint(Point point);

	/**
	 * Draws the composition.
	 * Before:
	 * 	stavePanel.repaint();
	 */
	public void drawComposition();

	/**
	 * Gets the WrittenNote that is currently selected in the note selector.
	 * @return
	 */
	public WrittenNote getSelectedNote();

	/**
	 * Will load interface to show all of the generated counterpoint solutions.
	 *  The user can select from these to then load them into the UI.
	 * @param counterpointSolutions
	 */
	public void loadGeneratedCompositionsInterface(
			List<Composition> counterpointSolutions);

	/**
	 * Used to show warning messages to the user. Will probably be a separate
	 * JFrame.
	 * @param string
	 */
	public void showAlert(String warningMessage);

	/**
	 * Closes the composition.
	 */
	public void closeCompositionView();

	/**
	 * When choosing to close the composition this will be called if the composition
	 * hasn't been saved. The event listener of the frame will close the composition
	 * when a choice has been made.
	 */
	public void showSaveBeforeCloseInterface();

	/**
	 * Shows the save composition interface where the user can choose the 
	 * save location. The event hanlder then handles the save. 
	 * Old code:
	 * 				serializer.saveObject(userInterface.getCompAppJFrame(),
						applicationModel.getCurrentComposition());
	 */
	public void showSaveCompositionInterface();

	/**
	 * Shows the load composition interface. The event to deserialize worked like this
	 * before:
	 * 
	 * 				// TODO - below is what did it before
				Composition tempComposition = (Composition) serializer
						.loadObject(frame);
				if (tempComposition != null) {
					applicationModel.setCurrentComposition(tempComposition);
					stavePanelScroller = new StavePanelScroller(
							counterpointOperationsDisplay);
					frame.setContentPane(stavePanelScroller);
					frame.pack();
				}
	 */
	public void showLoadCompositionInterface();

	/**
	 * Shows the create new composition interface.
	 */
	public void showCreateNewCompositionInterface();

	/**
	 * Gets the MappedCompositionObject containing all of the relavent
	 * objects that are mapped to the point.
	 * @param point
	 * @return
	 */
	public MappedCompositionObject getMappedCompositionObject(Point point);

	public void showCounterpointEvaluationConfigurer();

	/**
	 * Gets the CompositionDTO from the UI to validate and create/
	 * edit a Composition.
	 * @return
	 */
	public Composition getCurrentComposition();
	
	/**
	 * Temporary - until i've implemented the DTO method.
	 * @return
	 */
	public Composition getCreatedComposition();
	
	/**
	 * Closes the Composition creator window.
	 */
	public void closeCreator();
}
