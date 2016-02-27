//package law.counterpoint.compositionTranslation;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import junit.framework.TestCase;
//import musicRelatedEntities.Clef;
//import musicRelatedEntities.key.Key;
//import musicRelatedEntities.key.MusicScale;
//import musicRelatedEntities.note.Note;
//import musicRelatedEntities.note.NoteLetter;
//import musicRelatedEntities.note.noteAttributes.lengthValues.NoteLengthValue;
//import musicRelatedEntities.note.writtenNotes.CompositeNoteValue;
//import musicRelatedEntities.note.writtenNotes.WrittenNote;
//import musicRelatedEntities.time.Tempo;
//import musicRelatedEntities.time.TimeSignature;
//
//import composition.Composition;
//import composition.components.trackComponents.Bar;
//import composition.components.trackComponents.InstrumentType;
//import composition.components.tracks.InstrumentTrack;
//
//import counterpointOperations.compositionTranslation.CompositionToCounterpointCompositionTranslator;
//import counterpointOperations.counterpointComposition.CounterpointComposition;
//import counterpointOperations.permutation.BarNotePermutation;
//import counterpointOperations.permutation.speciesObjects.SpeciesElement;
//import counterpointOperations.permutation.speciesObjects.SpeciesType;
//
//public class CompositionToCounterpointCompositionTranslatorTest extends TestCase{
//
//	public void testgenerateCounterpointCompositionFromComposition_44_1WholeNote(){
//		Composition composition = new Composition("Test Title", new Key(MusicScale.Diatonic_Major, new Note(NoteLetter.C, null)), 
//				new TimeSignature(4, NoteLengthValue.QuaterNote), new Tempo(70, NoteLengthValue.QuaterNote), 10);
//		
//		List<InstrumentTrack> instrumentTracks = new ArrayList<InstrumentTrack>();
//		List<Bar> aListOfBars = new ArrayList<Bar>();
//		Bar bar = new Bar(Clef.G_CLEF, new TimeSignature(4, NoteLengthValue.QuaterNote), new Key(MusicScale.Diatonic_Major, 
//				new Note(NoteLetter.C, null)),  new Tempo(70, NoteLengthValue.QuaterNote), 0, 0);
//		bar.writeCompositeNoteValue(0, new CompositeNoteValue(new WrittenNote(NoteLengthValue.WholeNote, null)), 7, false, null);
//		InstrumentTrack instrumentTrack = new InstrumentTrack(0, "Test Track", InstrumentType.Piano);
//		instrumentTrack.addBar(bar);
//		instrumentTracks.add(instrumentTrack);
//		composition.setInstrumentTracks(instrumentTracks);
//		
//		CounterpointComposition resultCounterpointComposition = 
//		CompositionToCounterpointCompositionTranslator.generateCounterpointCompositionFromComposition(composition);
//		
//		List<BarNotePermutation> permutations = resultCounterpointComposition.getPermutations();
//		BarNotePermutation barNotePermutation = permutations.get(0);
//		List<SpeciesElement> firstSpeciesElements = barNotePermutation.getFirstSpeciesElementsForEachVoice();
//		
//		assertEquals(1, firstSpeciesElements.size());
//		
//		SpeciesElement speciesElement = firstSpeciesElements.get(0);
//		assertEquals(1, speciesElement.getBaseInnerElementsList().size());
//		assertEquals(SpeciesType.FIRST, speciesElement.getBaseInnerElementsList().get(0).getSpeciesType());
//		assertEquals(32, speciesElement.getLegnth());
//		assertEquals(speciesElement.getStartTimeLocation(), 0);
//	}
//	
//	public void testgenerateCounterpointCompositionFromComposition_44_2HalfNotes(){
//		Composition composition = new Composition("Test Title", new Key(MusicScale.Diatonic_Major, new Note(NoteLetter.C, null)), 
//				new TimeSignature(4, NoteLengthValue.QuaterNote), new Tempo(70, NoteLengthValue.QuaterNote), 10);
//		
//		List<InstrumentTrack> instrumentTracks = new ArrayList<InstrumentTrack>();
//		List<Bar> aListOfBars = new ArrayList<Bar>();
//		Bar bar = new Bar(Clef.G_CLEF, new TimeSignature(4, NoteLengthValue.QuaterNote), new Key(MusicScale.Diatonic_Major, 
//				new Note(NoteLetter.C, null)),  new Tempo(70, NoteLengthValue.QuaterNote), 0, 0);
//		bar.writeCompositeNoteValue(0, new CompositeNoteValue(new WrittenNote(NoteLengthValue.HalfNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(16, new CompositeNoteValue(new WrittenNote(NoteLengthValue.HalfNote, null)), 7, false, null);
//		InstrumentTrack instrumentTrack = new InstrumentTrack(0, "Test Track", InstrumentType.Piano);
//		instrumentTrack.addBar(bar);
//		instrumentTracks.add(instrumentTrack);
//		composition.setInstrumentTracks(instrumentTracks);
//		
//		CounterpointComposition resultCounterpointComposition = 
//		CompositionToCounterpointCompositionTranslator.generateCounterpointCompositionFromComposition(composition);
//		
//		List<BarNotePermutation> permutations = resultCounterpointComposition.getPermutations();
//		BarNotePermutation barNotePermutation = permutations.get(0);
//		List<SpeciesElement> firstSpeciesElements = barNotePermutation.getFirstSpeciesElementsForEachVoice();
//		
//		//Number of tracks
//		assertEquals(1, firstSpeciesElements.size());
//		
//		SpeciesElement speciesElement = firstSpeciesElements.get(0);
//		assertEquals(2, speciesElement.getBaseInnerElementsList().size());
//		
//		SpeciesElement firstSecondSpeciesElement = speciesElement.getBaseInnerElementsList().get(0);
//		assertEquals(SpeciesType.SECOND, firstSecondSpeciesElement.getSpeciesType());
//		assertEquals(16, firstSecondSpeciesElement.getLegnth());
//		assertEquals(0, firstSecondSpeciesElement.getStartTimeLocation());
//		
//		SpeciesElement secondSecondSpeciesElement = speciesElement.getBaseInnerElementsList().get(1);
//		assertEquals(SpeciesType.SECOND, secondSecondSpeciesElement.getSpeciesType());
//		assertEquals(16, secondSecondSpeciesElement.getLegnth());
//		assertEquals(16, secondSecondSpeciesElement.getStartTimeLocation());
//	}
//
//	public void testgenerateCounterpointCompositionFromComposition_44_4QuarterNotes(){
//		Composition composition = new Composition("Test Title", new Key(MusicScale.Diatonic_Major, new Note(NoteLetter.C, null)), 
//				new TimeSignature(4, NoteLengthValue.QuaterNote), new Tempo(70, NoteLengthValue.QuaterNote), 10);
//		
//		List<InstrumentTrack> instrumentTracks = new ArrayList<InstrumentTrack>();
//		List<Bar> aListOfBars = new ArrayList<Bar>();
//		Bar bar = new Bar(Clef.G_CLEF, new TimeSignature(4, NoteLengthValue.QuaterNote), new Key(MusicScale.Diatonic_Major, 
//				new Note(NoteLetter.C, null)),  new Tempo(70, NoteLengthValue.QuaterNote), 0, 0);
//		
//		/*
//		 * 0-8
//		 * 8-16
//		 * 16-24
//		 * 24-32 
//		 */
//		
//		bar.writeCompositeNoteValue(0, new CompositeNoteValue(new WrittenNote(NoteLengthValue.QuaterNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(8, new CompositeNoteValue(new WrittenNote(NoteLengthValue.QuaterNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(16, new CompositeNoteValue(new WrittenNote(NoteLengthValue.QuaterNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(24, new CompositeNoteValue(new WrittenNote(NoteLengthValue.QuaterNote, null)), 7, false, null);
//		InstrumentTrack instrumentTrack = new InstrumentTrack(0, "Test Track", InstrumentType.Piano);
//		instrumentTrack.addBar(bar);
//		instrumentTracks.add(instrumentTrack);
//		composition.setInstrumentTracks(instrumentTracks);
//		
//		CounterpointComposition resultCounterpointComposition = 
//		CompositionToCounterpointCompositionTranslator.generateCounterpointCompositionFromComposition(composition);
//		
//		List<BarNotePermutation> permutations = resultCounterpointComposition.getPermutations();
//		BarNotePermutation barNotePermutation = permutations.get(0);
//		List<SpeciesElement> firstSpeciesElements = barNotePermutation.getFirstSpeciesElementsForEachVoice();
//		
//		//Number of tracks
//		assertEquals(1, firstSpeciesElements.size());
//		
//		SpeciesElement speciesElement = firstSpeciesElements.get(0);
//		assertEquals(4, speciesElement.getBaseInnerElementsList().size());
//		
//		SpeciesElement firstThirdSpeciesElement = speciesElement.getBaseInnerElementsList().get(0);
//		assertEquals(SpeciesType.THIRD, firstThirdSpeciesElement.getSpeciesType());
//		assertEquals(8, firstThirdSpeciesElement.getLegnth());
//		assertEquals(firstThirdSpeciesElement.getStartTimeLocation(), 0);
//		
//		SpeciesElement secondThirdSpeciesElement = speciesElement.getBaseInnerElementsList().get(1);
//		assertEquals(SpeciesType.THIRD, secondThirdSpeciesElement.getSpeciesType());
//		assertEquals(8, secondThirdSpeciesElement.getLegnth());
//		assertEquals(secondThirdSpeciesElement.getStartTimeLocation(), 8);
//		
//		SpeciesElement thirdThirdSpeciesElement = speciesElement.getBaseInnerElementsList().get(2);
//		assertEquals(SpeciesType.THIRD, thirdThirdSpeciesElement.getSpeciesType());
//		assertEquals(8, thirdThirdSpeciesElement.getLegnth());
//		assertEquals(thirdThirdSpeciesElement.getStartTimeLocation(), 16);
//		
//		SpeciesElement fourthThirdSpeciesElement = speciesElement.getBaseInnerElementsList().get(3);
//		assertEquals(SpeciesType.THIRD, fourthThirdSpeciesElement.getSpeciesType());
//		assertEquals(8, fourthThirdSpeciesElement.getLegnth());
//		assertEquals(fourthThirdSpeciesElement.getStartTimeLocation(), 24);
//	}
//	
//	public void testgenerateCounterpointCompositionFromComposition_44_8EigthNotes(){
//		Composition composition = new Composition("Test Title", new Key(MusicScale.Diatonic_Major, new Note(NoteLetter.C, null)), 
//				new TimeSignature(4, NoteLengthValue.QuaterNote), new Tempo(70, NoteLengthValue.QuaterNote), 10);
//		
//		List<InstrumentTrack> instrumentTracks = new ArrayList<InstrumentTrack>();
//		List<Bar> aListOfBars = new ArrayList<Bar>();
//		Bar bar = new Bar(Clef.G_CLEF, new TimeSignature(4, NoteLengthValue.QuaterNote), new Key(MusicScale.Diatonic_Major, 
//				new Note(NoteLetter.C, null)),  new Tempo(70, NoteLengthValue.QuaterNote), 0, 0);
//		
//		/*
//		 * 0-8
//		 * 8-16
//		 * 16-24
//		 * 24-32 
//		 */
//		
//		bar.writeCompositeNoteValue(0, new CompositeNoteValue(new WrittenNote(NoteLengthValue.EigthNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(4, new CompositeNoteValue(new WrittenNote(NoteLengthValue.EigthNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(8, new CompositeNoteValue(new WrittenNote(NoteLengthValue.EigthNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(12, new CompositeNoteValue(new WrittenNote(NoteLengthValue.EigthNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(16, new CompositeNoteValue(new WrittenNote(NoteLengthValue.EigthNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(20, new CompositeNoteValue(new WrittenNote(NoteLengthValue.EigthNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(24, new CompositeNoteValue(new WrittenNote(NoteLengthValue.EigthNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(28, new CompositeNoteValue(new WrittenNote(NoteLengthValue.EigthNote, null)), 7, false, null);
//		InstrumentTrack instrumentTrack = new InstrumentTrack(0, "Test Track", InstrumentType.Piano);
//		instrumentTrack.addBar(bar);
//		instrumentTracks.add(instrumentTrack);
//		composition.setInstrumentTracks(instrumentTracks);
//		
//		CounterpointComposition resultCounterpointComposition = 
//		CompositionToCounterpointCompositionTranslator.generateCounterpointCompositionFromComposition(composition);
//		
//		List<BarNotePermutation> permutations = resultCounterpointComposition.getPermutations();
//		BarNotePermutation barNotePermutation = permutations.get(0);
//		List<SpeciesElement> firstSpeciesElements = barNotePermutation.getFirstSpeciesElementsForEachVoice();
//		
//		//Number of tracks
//		assertEquals(1, firstSpeciesElements.size());
//		
//		SpeciesElement speciesElement = firstSpeciesElements.get(0);
//		assertEquals(8, speciesElement.getBaseInnerElementsList().size());
//		
//		Iterator<SpeciesElement> iter = speciesElement.getBaseInnerElementsList().iterator();
//		SpeciesElement innerSpeciesElement = iter.next();
//		assertEquals(SpeciesType.DECORATION, innerSpeciesElement.getSpeciesType());
//		assertEquals(4, innerSpeciesElement.getLegnth());
//		assertEquals(0, innerSpeciesElement.getStartTimeLocation());
//		
//		innerSpeciesElement = iter.next();
//		assertEquals(SpeciesType.DECORATION, innerSpeciesElement.getSpeciesType());
//		assertEquals(4, innerSpeciesElement.getLegnth());
//		assertEquals(4, innerSpeciesElement.getStartTimeLocation());
//		
//		innerSpeciesElement = iter.next();
//		assertEquals(SpeciesType.DECORATION, innerSpeciesElement.getSpeciesType());
//		assertEquals(4, innerSpeciesElement.getLegnth());
//		assertEquals(8, innerSpeciesElement.getStartTimeLocation());
//		
//		innerSpeciesElement = iter.next();
//		assertEquals(SpeciesType.DECORATION, innerSpeciesElement.getSpeciesType());
//		assertEquals(4, innerSpeciesElement.getLegnth());
//		assertEquals(12, innerSpeciesElement.getStartTimeLocation());
//		
//		innerSpeciesElement = iter.next();
//		assertEquals(SpeciesType.DECORATION, innerSpeciesElement.getSpeciesType());
//		assertEquals(4, innerSpeciesElement.getLegnth());
//		assertEquals(16, innerSpeciesElement.getStartTimeLocation());
//		
//		innerSpeciesElement = iter.next();
//		assertEquals(SpeciesType.DECORATION, innerSpeciesElement.getSpeciesType());
//		assertEquals(4, innerSpeciesElement.getLegnth());
//		assertEquals(20, innerSpeciesElement.getStartTimeLocation());
//		
//		innerSpeciesElement = iter.next();
//		assertEquals(SpeciesType.DECORATION, innerSpeciesElement.getSpeciesType());
//		assertEquals(4, innerSpeciesElement.getLegnth());
//		assertEquals(24, innerSpeciesElement.getStartTimeLocation());
//		
//		innerSpeciesElement = iter.next();
//		assertEquals(SpeciesType.DECORATION, innerSpeciesElement.getSpeciesType());
//		assertEquals(4, innerSpeciesElement.getLegnth());
//		assertEquals(28, innerSpeciesElement.getStartTimeLocation());
//	}
//	
//	public void testgenerateCounterpointCompositionFromComposition_44_OddNotes(){
//		Composition composition = new Composition("Test Title", new Key(MusicScale.Diatonic_Major, new Note(NoteLetter.C, null)), 
//				new TimeSignature(4, NoteLengthValue.QuaterNote), new Tempo(70, NoteLengthValue.QuaterNote), 10);
//		
//		List<InstrumentTrack> instrumentTracks = new ArrayList<InstrumentTrack>();
//		List<Bar> aListOfBars = new ArrayList<Bar>();
//		Bar bar = new Bar(Clef.G_CLEF, new TimeSignature(4, NoteLengthValue.QuaterNote), new Key(MusicScale.Diatonic_Major, 
//				new Note(NoteLetter.C, null)),  new Tempo(70, NoteLengthValue.QuaterNote), 0, 0);
//		
//		/*
//		 * 0-4 eigth
//		 * 4-20 half
//		 * 20-28 quarter
//		 * 28-32 eigth
//		 */
//		
//		bar.writeCompositeNoteValue(0, new CompositeNoteValue(new WrittenNote(NoteLengthValue.EigthNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(4, new CompositeNoteValue(new WrittenNote(NoteLengthValue.HalfNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(20, new CompositeNoteValue(new WrittenNote(NoteLengthValue.QuaterNote, null)), 7, false, null);
//		bar.writeCompositeNoteValue(28, new CompositeNoteValue(new WrittenNote(NoteLengthValue.EigthNote, null)), 7, false, null);
//		InstrumentTrack instrumentTrack = new InstrumentTrack(0, "Test Track", InstrumentType.Piano);
//		instrumentTrack.addBar(bar);
//		instrumentTracks.add(instrumentTrack);
//		composition.setInstrumentTracks(instrumentTracks);
//		
//		CounterpointComposition resultCounterpointComposition = 
//		CompositionToCounterpointCompositionTranslator.generateCounterpointCompositionFromComposition(composition);
//		
//		List<BarNotePermutation> permutations = resultCounterpointComposition.getPermutations();
//		BarNotePermutation barNotePermutation = permutations.get(0);
//		List<SpeciesElement> firstSpeciesElements = barNotePermutation.getFirstSpeciesElementsForEachVoice();
//		
//		//Number of tracks
//		assertEquals(1, firstSpeciesElements.size());
//		
//
//	}
//}
