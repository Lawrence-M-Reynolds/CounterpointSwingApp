package userInterface.stavePanel.barEventMapping;

import composition.components.trackComponents.Bar;
import composition.components.trackComponents.barComponents.CompositionBarEvent;
import composition.components.tracks.InstrumentTrack;

public class MappedCompositionObject {

	private CompositionBarEvent compositionBarEvent;
	private Bar bar;
	private InstrumentTrack instrumentTrack;
	private int pitchLocationNumber;
	
	public MappedCompositionObject(CompositionBarEvent compositionBarEvent,
			Bar bar, InstrumentTrack instrumentTrack, int pitchLocationNumber) {
		super();
		this.compositionBarEvent = compositionBarEvent;
		this.bar = bar;
		this.instrumentTrack = instrumentTrack;
		this.pitchLocationNumber = pitchLocationNumber;
	}

	public CompositionBarEvent getCompositionBarEvent() {
		return compositionBarEvent;
	}

	public Bar getBar() {
		return bar;
	}

	public InstrumentTrack getInstrumentTrack() {
		return instrumentTrack;
	}

	public int getPitchLocationNumber() {
		return pitchLocationNumber;
	}

}
