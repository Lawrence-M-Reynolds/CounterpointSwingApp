package userInterface.stavePanel.barEventMapping;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import composition.components.trackComponents.Bar;
import composition.components.trackComponents.barComponents.CompositionBarEvent;
import composition.components.tracks.InstrumentTrack;
import config.GlobalConstants;

import musicRelatedEntities.note.writtenNotes.CompositeWrittenNote;
import musicRelatedEntities.note.writtenNotes.WrittenNote;

/**
 * When notes and other objects are drawn onto the stave panel, there needs to be some
 * way of relating the location to that object so the user can interact with it. That is what
 * this object is for.
 * 
 */
public class BarEventMappingObject {	
	private Map<Point, MappedCompositionObject> compositionObjectMap = 
			new HashMap<Point, MappedCompositionObject>();

	/**
	 * Used by the composition drawer, this method taks the CompositionBarEvent that was drawn 
	 * and also the point on which it was drawn so that they can be mapped together. 
	 * However, it also takes the Bar and  InstrumentTrack in which the note was contained. The reason
	 * for this is that it is not just a case of changing the properties of the CompositionBarEvent,
	 * it may overwrite an existing CompositionBarEvent or it may over run into the next bar. If this
	 * is the case then further logic is required in the track and bar to deal with it. 
	 * 
	 * yLineLocations provides the y axis location of each pitch line value. Each bar/event... is mapped
	 * for each of these values. This allows each of the pitch lines of the event to respond to it.
	 * The pitch line number itself is also mapped so that when it comes to writing a change, we
	 * know what the new pitch should be.
	 * @param point
	 * @param track
	 * @param bar
	 * @param noteEvent
	 * @param yLineLocations
	 */
	public void mapEventPoint(Point point, InstrumentTrack track, Bar bar, CompositionBarEvent noteEvent, int[] yLineLocations){
		/*
		 * Each event/bar etc must be mapped along with each pitch line value 
		 */
		for(int pitchLocationNumber = 0; pitchLocationNumber < yLineLocations.length ; pitchLocationNumber++){			
			Point pitchPoint = new Point(point.x, yLineLocations[pitchLocationNumber]);			
			MappedCompositionObject mappedCompositionObject = 
					new MappedCompositionObject(noteEvent, bar, track, pitchLocationNumber); 
			
			compositionObjectMap.put(pitchPoint, mappedCompositionObject);
		}
	}
	
	public String toString(){
		return "compositionObjectMap: " + compositionObjectMap.toString();
	}

	/**
	 * Clears all mappings created from last drawing.
	 */
	public void clearMappings() {
		this.compositionObjectMap.clear();
	}

	/**
	 * If a point has been mapped within the window of the passed 
	 * in point then it will be returned. Otherwise it will
	 * return null. 
	 * @param point
	 * @return
	 */
	public Point getRelatedMapPoint(Point mouseListenerPoint) {

		//Set up window around clicked point
		int windowBoxSize = GlobalConstants.POINT_WINDOW_BOX_SIZE;
		int x0 = mouseListenerPoint.x - windowBoxSize/2;
		int x1 = mouseListenerPoint.x + windowBoxSize/2;
		int y0 = mouseListenerPoint.y - windowBoxSize/2;
		int y1 = mouseListenerPoint.y + windowBoxSize/2;
		
		//Iterate through all mapped points and check if they fall with in the
		//window.
		Set<Point> mappedPoints = compositionObjectMap.keySet();
		for(Point mappedPoint : mappedPoints){
			int x = mappedPoint.x;
			int y = mappedPoint.y;
			if((x > x0 && x < x1) && (y > y0 && y < y1)){
				//Found one so returning it.
				return mappedPoint;
			}
		}		
		//None were found so return null.
		return null;
	}

	public MappedCompositionObject getMappedCompositionObjectAtPoint(Point point) {
		return compositionObjectMap.get(point);
	}
}
