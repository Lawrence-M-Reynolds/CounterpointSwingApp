package model;

import java.util.List;

import musicRelatedEntities.note.writtenNotes.WrittenNote;
import userInterface.stavePanel.barEventMapping.MappedCompositionObject;
import composition.Composition;

public interface IApplicationModel {

	public Composition getCurrentComposition();

	public void setCounterpointSolutions(List<Composition> counterpointSolutions);

	public void setCurrentComposition(Composition composition);

	/**
	 * Checks if the state of the composition is the same as when it was serialised.
	 * @return
	 */
	public boolean isCompositionSerialised();

	public void saveCompositionToDb(Composition composition);
}
