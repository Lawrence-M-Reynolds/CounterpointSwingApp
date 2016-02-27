package userInterface.configInterfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import composition.Composition;
import composition.CompositionManager;
import composition.components.trackComponents.InstrumentType;
import composition.components.trackComponents.VoicePitchRangeLimitObject;
import composition.components.tracks.InstrumentTrack;
import composition.components.tracks.InstrumentTrackManager;
import musicRelatedEntities.Clef;
import musicRelatedEntities.chord.Chord;
import musicRelatedEntities.chord.chordTypes.ChordType;
import musicRelatedEntities.key.Key;
import musicRelatedEntities.key.KeyNote;
import musicRelatedEntities.key.MusicScale;
import musicRelatedEntities.note.Note;
import musicRelatedEntities.note.NoteLetter;
import musicRelatedEntities.note.noteAttributes.lengthValues.NoteLengthValue;
import musicRelatedEntities.time.Tempo;
import musicRelatedEntities.time.TimeSignature;

import config.staticStrings.UserInterfaceLabels;
import controller.viewLogic.ViewEventListenerMappingKeys;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerListModel;

import userInterface.dto.CompositionDTO;

/**
 * Used for configuring and creating a new composition object. Required values:
 * - Title - Key - TimeSignature - Tempo - NumberOfBars
 * 
 * @author UKGC
 * 
 */
public class CompositionCreatorWindow extends JDialog {
	private Key key;
	private Tempo tempo;

	private JSpinner numberOfBarsSpinner;
	private JTextField titleTextField;
	private JSpinner beatsPerBarSpinner;
	private JSpinner noteValue;

	public CompositionCreatorWindow(
			Frame parent,
			Map<ViewEventListenerMappingKeys, EventListener> viewEventListenerMap,
			boolean modal) {
		// FrameConfiguration
		super(parent, UserInterfaceLabels.COMPOSITION_CREATOR_TITLE, modal);
		setVisible(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		

		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.NORTH);

		mainPanel.setLayout(new BorderLayout());

		JButton createCompositionBtn = new JButton(
				UserInterfaceLabels.CREATE_COMPOSITION);
		createCompositionBtn
				.addActionListener((ActionListener) viewEventListenerMap
						.get(ViewEventListenerMappingKeys.CREATE_COMPOSITION));
		mainPanel.add(createCompositionBtn, BorderLayout.SOUTH);

		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(0, 2));
		mainPanel.add(settingsPanel, BorderLayout.CENTER);

		// Title
		JLabel titleLbl = new JLabel("Title: ");
		settingsPanel.add(titleLbl);
		
		titleTextField = new JTextField();
		settingsPanel.add(titleTextField);
		
		// Number of bars selector
		JLabel numberOfBarsLbl = new JLabel("Number of bars: ");
		settingsPanel.add(numberOfBarsLbl);

		numberOfBarsSpinner = new JSpinner();
		numberOfBarsSpinner.setModel(new SpinnerNumberModel(new Integer(5),
				new Integer(1), null, new Integer(1)));
		settingsPanel.add(numberOfBarsSpinner);
		
		// Time Signature
		JLabel timeSignatureLbl = new JLabel("Time Signature: ");
		settingsPanel.add(timeSignatureLbl);
		
		JPanel timeSignatureControl = new JPanel();
		timeSignatureControl.setLayout(new GridLayout(0, 2));
		beatsPerBarSpinner = new JSpinner();
		beatsPerBarSpinner.setModel(new SpinnerNumberModel(
				new Integer(4), new Integer(1), null, new Integer(1)));
		timeSignatureControl.add(beatsPerBarSpinner);
		noteValue = new JSpinner();
		noteValue.setModel(new SpinnerListModel(
				NoteLengthValue.valuesAsRecipricol()){
			@Override
			public Object getValue() {
				return super.getValue();
			}
		});
		timeSignatureControl.add(noteValue);
		settingsPanel.add(timeSignatureControl);

		//Tempo
		
		// Creating the compositon
		key = new Key(MusicScale.Diatonic, new Note(NoteLetter.C, null));
		tempo = new Tempo(100, NoteLengthValue.QuaterNote);
		
		setSize(new Dimension(331, 235));
	}

	/**
	 * Easier to just return a composition than the individual values.
	 */
	public Composition generateComposition() {
		Composition composition = new Composition(titleTextField.getText(),
				key, getTimeSignature(), tempo, getNumberOfBars());
		addTracksToComposition(composition);
		setCompositionChordLockdownMap(composition);
		return composition;
	}

	private TimeSignature getTimeSignature() {
		NoteLengthValue noteLengthValue = NoteLengthValue.getNoteLengthValueWithRecipricolValue(
				(Integer)noteValue.getValue());
		return new TimeSignature((Integer)beatsPerBarSpinner.getValue(), 
				noteLengthValue);
	}

	private void addTracksToComposition(Composition composition) {	
		/*
		 * FIXME - This highlights why the details should be sent back to the
		 * controller. The logic of the CompositionManager shouldn't be needed.
		 */
		InstrumentTrackManager instrumentTrackManager = 
				InstrumentTrackManager.getInstance();
		//The barVoicePitchRangeLimitObjectMaps would be obtained from another control
		Map<Integer, VoicePitchRangeLimitObject> barVoicePitchRangeLimitObjectMap1 = 
				new HashMap<Integer, VoicePitchRangeLimitObject>();
		Map<Integer, VoicePitchRangeLimitObject> barVoicePitchRangeLimitObjectMap2 = 
				new HashMap<Integer, VoicePitchRangeLimitObject>();
		Map<Integer, VoicePitchRangeLimitObject> barVoicePitchRangeLimitObjectMap3 = 
				new HashMap<Integer, VoicePitchRangeLimitObject>();
		Map<Integer, VoicePitchRangeLimitObject> barVoicePitchRangeLimitObjectMap4 = 
				new HashMap<Integer, VoicePitchRangeLimitObject>();
		for (int i=0; i < getNumberOfBars(); i++) {
			barVoicePitchRangeLimitObjectMap1.put(
					i, new VoicePitchRangeLimitObject(Clef.G_CLEF, 0, 8));
			barVoicePitchRangeLimitObjectMap2.put(
					i, new VoicePitchRangeLimitObject(Clef.G_CLEF, 7, 15));
			barVoicePitchRangeLimitObjectMap3.put(
					i, new VoicePitchRangeLimitObject(Clef.F_CLEF, 4, 12));
			barVoicePitchRangeLimitObjectMap4.put(
					i, new VoicePitchRangeLimitObject(Clef.F_CLEF, 9, 17));
		}
		
		InstrumentTrack instrumentTrack1 = 
				instrumentTrackManager.generateNewInstrumentTrack(0, "First Track", 
						InstrumentType.Piano, getNumberOfBars(), Clef.G_CLEF, 
						getTimeSignature(), key, tempo, barVoicePitchRangeLimitObjectMap1);
		
		InstrumentTrack instrumentTrack2 = 
				instrumentTrackManager.generateNewInstrumentTrack(1, "Second Track", 
						InstrumentType.Piano, getNumberOfBars(), Clef.G_CLEF, 
						getTimeSignature(), key, tempo, barVoicePitchRangeLimitObjectMap2);
		
		InstrumentTrack instrumentTrack3 = 
				instrumentTrackManager.generateNewInstrumentTrack(2, "Third Track", 
						InstrumentType.Piano, getNumberOfBars(), Clef.F_CLEF, 
						getTimeSignature(), key, tempo, barVoicePitchRangeLimitObjectMap3);
		
		InstrumentTrack instrumentTrack4 = 
				instrumentTrackManager.generateNewInstrumentTrack(3, "Fourth Track", 
						InstrumentType.Piano, getNumberOfBars(), Clef.F_CLEF, 
						getTimeSignature(), key, tempo, barVoicePitchRangeLimitObjectMap4);
		
		composition.addNewTrack(instrumentTrack1);
		composition.addNewTrack(instrumentTrack2);
		composition.addNewTrack(instrumentTrack3);
		composition.addNewTrack(instrumentTrack4);
	}

	private void setCompositionChordLockdownMap(Composition composition) {
		// Obtain the lock down chords from the key for the beggining and the
		// cadence
		Key key = composition.getKey();
		KeyNote tonicKeyNote = key.getKeyNoteFromNoteLetter(NoteLetter.C);
		KeyNote fifthKeyNote = key.getKeyNoteFromNoteLetter(NoteLetter.G);

		// Locking the first and last sets of bars to the tonic C chord
		Set<Chord> tonicChordOnly = new HashSet<Chord>();
		tonicChordOnly.add(tonicKeyNote.buildKeyChord(ChordType.TRIAD));
		// the first chord should always be the tonic
		composition.addHarmonyLockdownMapping(0, tonicChordOnly);
		// And the last bar: -1 because the bars start at zero
		composition.addHarmonyLockdownMapping(getNumberOfBars() - 1, tonicChordOnly);

		// Locking the first and last sets of bars to the tonic C chord
		Set<Chord> cadenceChords = new HashSet<Chord>();
		cadenceChords.add(fifthKeyNote.buildKeyChord(ChordType.TRIAD));

		composition.addHarmonyLockdownMapping(getNumberOfBars() - 2, cadenceChords);
	}
	
	private int getNumberOfBars(){
		return (Integer) numberOfBarsSpinner.getValue();
	}

	public CompositionDTO getCompositionDTO() {
		// TODO Auto-generated method stub
		return null;
	}
}