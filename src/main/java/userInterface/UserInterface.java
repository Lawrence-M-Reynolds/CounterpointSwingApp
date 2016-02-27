package userInterface;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.WindowListener;
import java.util.EventListener;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import musicRelatedEntities.note.writtenNotes.WrittenNote;

import composition.Composition;

import userInterface.configInterfaces.CompositionCreatorWindow;
import userInterface.configInterfaces.counterpointEvaluationConfigurer.CounterpointEvaluationConfigurer;
import userInterface.dto.CompositionDTO;
import userInterface.mainInterface.CompAppJFrame;
import userInterface.mainInterface.stavePanel.CompositionDrawer;
import userInterface.mainInterface.stavePanel.NoteValueSelector;
import userInterface.mainInterface.stavePanel.StavePanelScroller;
import userInterface.stavePanel.barEventMapping.MappedCompositionObject;
import controller.viewLogic.ViewEventListenerMappingKeys;

/**
 * This is effectively the controller for the view.
 * @author UKGC
 *
 */
public class UserInterface implements IUserInterface{	
	private CompAppJFrame compAppJFrame;
	private NoteValueSelector noteSelector = new NoteValueSelector();
	private StavePanelScroller stavePanelScroller;
	private Map<ViewEventListenerMappingKeys, EventListener> viewEventListenerMap;	
	private CompositionCreatorWindow compositionCreatorWindow;
	private CounterpointEvaluationConfigurer counterpointEvaluationConfigurer;

	public Dimension getWindowSize() {
		return compAppJFrame.getSize();
	}

	public void createUI(int windowWidth, int windowHeight) {
		compAppJFrame = new CompAppJFrame("Counterpoint Tutor", viewEventListenerMap,
				windowWidth, windowHeight);
	}

	/**
	 * Called when a new composition is created or one
	 * has been de-serialised. 
	 */
	public void loadCompositionView(Composition aComposition) {
		stavePanelScroller = new StavePanelScroller(
				aComposition, viewEventListenerMap);
		compAppJFrame.setContentPane(stavePanelScroller);
		compAppJFrame.pack();		
	}

	public void setViewEventListenerMap(
			Map<ViewEventListenerMappingKeys, EventListener> aViewEventListenerMap) {
		viewEventListenerMap = aViewEventListenerMap;
		
	}

	/**
	 * Draws the selected note at the point.
	 * At the moment this just sets the point and the selected note value
	 * in the panel before calling redraw. This will redraw the whole
	 * composition again so there may be better way to implement this
	 * that will be more efficient.
	 * TODO
	 */
	public void drawSelectedNoteAtPoint(Point aPoint) {
		stavePanelScroller.setMousePointToDrawNote(aPoint);
		stavePanelScroller.setSelectedWrittenNote(noteSelector.getSelectedNote());
		drawComposition();
	}

	/**
	 * If the passed in point is within the window of a mapped point then the
	 * mapped point is returned. If there isn't one then null is returned.
	 * @param The point to check if it falls within the range of a mapped
	 * point.
	 * @return Point point The point that the passed in point relates to
	 * if there is one. Null if there isn't one and also if the bar event
	 * mapping object hasn't been setup yet. (Prevents null pointer
	 * when first creating a composition.)
	 */
	public Point getRelatedMapPoint(Point point) {
		//Check the mapping object has been created yet (after the
		//staves are drawn.)
		if (stavePanelScroller.getBarEventMappingObject() 
				== null) {
			return null;
		}
		return stavePanelScroller.getBarEventMappingObject()
				.getRelatedMapPoint(point);
	}

	
	public MappedCompositionObject getMappedCompositionObject(Point point) {
		return stavePanelScroller.getBarEventMappingObject().
				getMappedCompositionObjectAtPoint(point);
	}

	public void drawComposition() {
		stavePanelScroller.drawComposition();
	}

	public WrittenNote getSelectedNote() {
		return noteSelector.getSelectedNote();
	}

	public void loadGeneratedCompositionsInterface(
			List<Composition> counterpointSolutions) {
		// TODO Auto-generated method stub
		
	}

	public void showAlert(String warningMessage) {
		JOptionPane.showMessageDialog(compAppJFrame,
				warningMessage,
			    "Warning",
			    JOptionPane.WARNING_MESSAGE);
		
	}

	public void closeCompositionView() {
		// TODO Auto-generated method stub
		
	}

	public void showSaveBeforeCloseInterface() {
		// TODO Auto-generated method stub
		
	}

	public void showSaveCompositionInterface() {
		// TODO Auto-generated method stub
		
	}

	public void showLoadCompositionInterface() {
		// TODO Auto-generated method stub
		
	}

	public void showCreateNewCompositionInterface() {
		compositionCreatorWindow = new CompositionCreatorWindow(
				compAppJFrame, viewEventListenerMap, false);
	}

	public void closeCreator() {
		compositionCreatorWindow.dispose();
		compositionCreatorWindow = null;
	}

	public void showCounterpointEvaluationConfigurer() {
		//TODO this will be taking in the original settings from the db as well.
		counterpointEvaluationConfigurer = 
				new CounterpointEvaluationConfigurer(viewEventListenerMap);		
	}

	public Composition getCurrentComposition() {
		return stavePanelScroller.getComposition();
	}

	public Composition getCreatedComposition() {
		return compositionCreatorWindow.generateComposition();
	}
}
