package userInterface.mainInterface.stavePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import musicRelatedEntities.note.writtenNotes.WrittenNote;
import userInterface.stavePanel.barEventMapping.BarEventMappingObject;

import composition.Composition;

import controller.viewLogic.ViewEventListenerMappingKeys;

/**
 * This class ecnapsulates the StavePanel which is the UI that displays the
 * composition and any output from the counterpoint algorithms to display to the
 * user.
 * 
 * @author BAZ
 * 
 */
public class StavePanelScroller extends JPanel {
	
	/**
	 * These two values are for the current note needs to be drawn
	 * over the current mouse position when moving the mouse.
	 */
	private Point mousePointToDrawNote = null;
	private WrittenNote selectedNote = null;
	
	private CompositionDrawer compositionDrawer;
	private BarEventMappingObject barEventMappingObject;
	
	private StavePanel stavePanel;
	private Composition composition;

	private Map<ViewEventListenerMappingKeys, EventListener> viewLogicMap;

	public StavePanelScroller(Composition aComposition,
			Map<ViewEventListenerMappingKeys, EventListener> aViewLogicMap) {
		composition = aComposition;
		viewLogicMap = aViewLogicMap;
		compositionDrawer = new CompositionDrawer();
		stavePanel = new StavePanel();
		setOpaque(true);
		setLayout(new BorderLayout());
		JScrollPane scroller = new JScrollPane(stavePanel);
		add(scroller, BorderLayout.CENTER);
	}

	public void reDrawStaves() {
		stavePanel.repaint();
	}

	/**
	 * The UI for the user to write notes on and view output from the
	 * counterpoint algorithms. This uses a CompositionDrawer to perform the
	 * actual drawing operations.
	 * 
	 * @author BAZ
	 * 
	 */
	private class StavePanel extends JPanel {
		public StavePanel(){
			setBackground(Color.WHITE);
			this.addMouseListener(
					(MouseListener)viewLogicMap.get(ViewEventListenerMappingKeys.WRITE_ANNOTATION));
			
			this.addMouseMotionListener(
					(MouseMotionListener)viewLogicMap.get(
							ViewEventListenerMappingKeys.SHOW_NOTE_AT_POINT));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			revalidate();

			if (selectedNote != null) {
				selectedNote.drawSymbol(g, mousePointToDrawNote);
			}

			barEventMappingObject = compositionDrawer.drawComposition(g, composition);
			setPreferredSize(compositionDrawer.getDrawingDimension());
		}
	}

	public void setMousePointToDrawNote(Point aPoint) {
		mousePointToDrawNote = aPoint;		
	}

	public void setSelectedWrittenNote(WrittenNote aSelectedNote) {
		selectedNote = aSelectedNote;		
	}

	public void drawComposition() {
		stavePanel.repaint();
	}

	public BarEventMappingObject getBarEventMappingObject() {
		return barEventMappingObject;
	}
	public Composition getComposition() {
		return composition;
	}

}