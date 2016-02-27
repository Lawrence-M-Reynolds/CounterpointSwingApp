package userInterface.configInterfaces.counterpointEvaluationConfigurer;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import userInterface.configInterfaces.counterpointEvaluationConfigurer.controlPanels.CounterpointRulesPanel;
import config.staticStrings.UserInterfaceLabels;
import controller.viewLogic.ViewEventListenerMappingKeys;

public class CounterpointEvaluationConfigurer extends JFrame {

	private Map<ViewEventListenerMappingKeys, EventListener> viewEventListenerMap;
	
	private CounterpointRulesPanel counterpointRulesPanel;
	
	public CounterpointEvaluationConfigurer( 
			Map<ViewEventListenerMappingKeys, EventListener> aViewEventListenerMap){
		//FrameConfiguration
		super(UserInterfaceLabels.COUNTERPOINT_EVALUATION_CONFIGURER);
		viewEventListenerMap = aViewEventListenerMap;
		setVisible(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		
		this.setLayout(new BorderLayout(0, 0));
						
		JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		counterpointRulesPanel = new CounterpointRulesPanel();
		tabbedPane.addTab("Counterpoint Rules", counterpointRulesPanel);

		JComponent panel2 = new JPanel();
		tabbedPane.addTab("Other Options", panel2);
		
		JButton createCompositionBtn = new JButton("Commit Changes");
		createCompositionBtn.addActionListener(
				(ActionListener)viewEventListenerMap.get(
						ViewEventListenerMappingKeys.DUMMY_KEY));
		getContentPane().add(createCompositionBtn, BorderLayout.SOUTH);
		
		setSize(getPreferredSize());
	}

}
