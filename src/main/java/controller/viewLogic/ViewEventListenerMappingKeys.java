package controller.viewLogic;

public enum ViewEventListenerMappingKeys {

	DUMMY_KEY,
	
		//frame listeners
	CLOSE_APPLICATION,
	
	//Frame menu event mappings
	//File Menu
	NEW_COMPOSITION, SAVE_COMPOSITION, LOAD_COMPOSITION, CLOSE_COMPOSITION,
	
	//Counterpoint menu
	SHOW_COUNTERPOINT_EVALUATION_CONFIGURER, EVALUATE_COMPOSITION, GENERATE_COUNTERPOINT_SOLUTIONS,
	
	//Play back menu
	PLAY_COMPOSITION, EXPORT_COMPOSITION_AS_MIDI,
	
		//Stave panel listners.
	WRITE_ANNOTATION, SHOW_NOTE_AT_POINT, 
	
		//Composition Creator
	CREATE_COMPOSITION
}
