package userInterface.mainInterface;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.EventListener;
import java.util.Map;

import javax.swing.JFrame;

import controller.viewLogic.ViewEventListenerMappingKeys;

public class CompAppJFrame extends JFrame{

	private Map<ViewEventListenerMappingKeys, EventListener> viewEventListenerMap;
	
	public CompAppJFrame(String title, Map<ViewEventListenerMappingKeys,
			EventListener> aViewEventListenerMap, int windowWidth, int windowHeight) {
		super(title);
		viewEventListenerMap = aViewEventListenerMap;
		setMenuBar(new CompAppMenuBar());
		setVisible(true);
		setState ( Frame.MAXIMIZED_BOTH );
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		pack();
		addWindowListener(
				(WindowListener)viewEventListenerMap.get(ViewEventListenerMappingKeys.CLOSE_APPLICATION));
	}

	
	private class CompAppMenuBar extends MenuBar{
		private CompAppMenuBar(){
			setupFileMenu();
			setupCounterpointMenu();
			setupPlayBackMenu();
		}
		
		private void setupFileMenu(){
			Menu fileMenu = new Menu("File");
			MenuItem newMenuItem = new MenuItem("New");
			fileMenu.add(newMenuItem);
			newMenuItem.addActionListener(
					(ActionListener)viewEventListenerMap.get(ViewEventListenerMappingKeys.NEW_COMPOSITION));
			
			MenuItem openMenuItem = new MenuItem("Open");
			fileMenu.add(openMenuItem);
			openMenuItem.addActionListener(
					(ActionListener)viewEventListenerMap.get(ViewEventListenerMappingKeys.LOAD_COMPOSITION));
			
			MenuItem saveAsMenuItem = new MenuItem("Save As...");
			fileMenu.add(saveAsMenuItem);
			saveAsMenuItem.addActionListener(
					(ActionListener)viewEventListenerMap.get(ViewEventListenerMappingKeys.SAVE_COMPOSITION));
			
			MenuItem closeMenuItem = new MenuItem("Close");
			fileMenu.add(closeMenuItem);
			closeMenuItem.addActionListener(
					(ActionListener)viewEventListenerMap.get(ViewEventListenerMappingKeys.CLOSE_COMPOSITION));
			
			add(fileMenu);
		}
		
		
		private void setupCounterpointMenu(){
			Menu counterpointMenu = new Menu("Counterpoint");
			
			MenuItem configureEvaluationMenuItem = new MenuItem("Configure Counterpoint Evaluation Rules");
			counterpointMenu.add(configureEvaluationMenuItem);
			configureEvaluationMenuItem.addActionListener(
					(ActionListener)viewEventListenerMap.get(ViewEventListenerMappingKeys.SHOW_COUNTERPOINT_EVALUATION_CONFIGURER));	
			
			MenuItem evaluateMenuItem = new MenuItem("Evaluate Composition");
			counterpointMenu.add(evaluateMenuItem);
			evaluateMenuItem.addActionListener(
					(ActionListener)viewEventListenerMap.get(ViewEventListenerMappingKeys.EVALUATE_COMPOSITION));	
			
			MenuItem generateFirstSpeciesSolutionsMenuItem = new MenuItem("Generate First Species Solutions");
			counterpointMenu.add(generateFirstSpeciesSolutionsMenuItem);
			add(counterpointMenu);			
			generateFirstSpeciesSolutionsMenuItem.addActionListener(
					(ActionListener)viewEventListenerMap.get(ViewEventListenerMappingKeys.GENERATE_COUNTERPOINT_SOLUTIONS));				
		}
		private void setupPlayBackMenu(){
			Menu playBackMenu = new Menu("Playback");
			
			MenuItem playMenuItem = new MenuItem("Play");
			playBackMenu.add(playMenuItem);
			playMenuItem.addActionListener(
					(ActionListener)viewEventListenerMap.get(ViewEventListenerMappingKeys.PLAY_COMPOSITION));	
			
			MenuItem exportMidiMenuItem = new MenuItem("Export Midi");
			playBackMenu.add(exportMidiMenuItem);
			exportMidiMenuItem.addActionListener(
					(ActionListener)viewEventListenerMap.get(ViewEventListenerMappingKeys.EXPORT_COMPOSITION_AS_MIDI));
					
			add(playBackMenu);
		}
	}
}
