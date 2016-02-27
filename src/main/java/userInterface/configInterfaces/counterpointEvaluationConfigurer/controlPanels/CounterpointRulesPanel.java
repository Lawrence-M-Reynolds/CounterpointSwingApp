package userInterface.configInterfaces.counterpointEvaluationConfigurer.controlPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class CounterpointRulesPanel extends JPanel{
	
	
	//Melodic
	private JCheckBox parallelOctavesChkbx;
	private JSpinner parallelOctavesSpinner;
	

	public CounterpointRulesPanel(){
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		add(createMelodicRulesPanel());
		add(createFirstSpeciesRulesPanel());
		add(createSecondSpeciesRulesPanel());
		add(createThirdSpeciesRulesPanel());
		add(createFourthSpeciesRulesPanel());
	}

	private Component createFourthSpeciesRulesPanel() {
		JPanel melodicRulesPanel = new JPanel();
		melodicRulesPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel enableRuleLbl = new JLabel("Enable");
		enableRuleLbl.setForeground(Color.DARK_GRAY);
		enableRuleLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		enableRuleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel applyRuleWeigtingLbl = new JLabel("Apply Weighting");
		applyRuleWeigtingLbl.setForeground(Color.DARK_GRAY);
		applyRuleWeigtingLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		applyRuleWeigtingLbl.setHorizontalAlignment(SwingConstants.CENTER);

		melodicRulesPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel melodicRulesPanelTitle = new JLabel("Fourth Species Rules");
		melodicRulesPanelTitle.setFont(new Font("Segoe UI", Font.BOLD, 21));
		melodicRulesPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		melodicRulesPanel.add(melodicRulesPanelTitle, BorderLayout.NORTH);
		
		JPanel melodicRulesControlPanel = new JPanel();
		melodicRulesControlPanel.setLayout(new GridLayout(0, 2));
		
		melodicRulesPanel.add(melodicRulesControlPanel, BorderLayout.SOUTH);

		//ParallelOctaves
		melodicRulesControlPanel.add(enableRuleLbl);
		melodicRulesControlPanel.add(applyRuleWeigtingLbl);
		
		parallelOctavesChkbx = new JCheckBox("Forbid Parallel Octaves", false);
		melodicRulesControlPanel.add(parallelOctavesChkbx);
		parallelOctavesSpinner = new JSpinner(); 
		parallelOctavesSpinner.setModel(new SpinnerNumberModel(0, null, 100, 1));
		melodicRulesControlPanel.add(parallelOctavesSpinner);
		
		return melodicRulesPanel;
	}

	private Component createThirdSpeciesRulesPanel() {
		JPanel melodicRulesPanel = new JPanel();
		melodicRulesPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel enableRuleLbl = new JLabel("Enable");
		enableRuleLbl.setForeground(Color.DARK_GRAY);
		enableRuleLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		enableRuleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel applyRuleWeigtingLbl = new JLabel("Apply Weighting");
		applyRuleWeigtingLbl.setForeground(Color.DARK_GRAY);
		applyRuleWeigtingLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		applyRuleWeigtingLbl.setHorizontalAlignment(SwingConstants.CENTER);

		melodicRulesPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel melodicRulesPanelTitle = new JLabel("Third Species Rules");
		melodicRulesPanelTitle.setFont(new Font("Segoe UI", Font.BOLD, 21));
		melodicRulesPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		melodicRulesPanel.add(melodicRulesPanelTitle, BorderLayout.NORTH);
		
		JPanel melodicRulesControlPanel = new JPanel();
		melodicRulesControlPanel.setLayout(new GridLayout(0, 2));
		
		melodicRulesPanel.add(melodicRulesControlPanel, BorderLayout.SOUTH);

		//ParallelOctaves
		melodicRulesControlPanel.add(enableRuleLbl);
		melodicRulesControlPanel.add(applyRuleWeigtingLbl);
		
		parallelOctavesChkbx = new JCheckBox("Forbid Parallel Octaves", false);
		melodicRulesControlPanel.add(parallelOctavesChkbx);
		parallelOctavesSpinner = new JSpinner(); 
		parallelOctavesSpinner.setModel(new SpinnerNumberModel(0, null, 100, 1));
		melodicRulesControlPanel.add(parallelOctavesSpinner);
		
		return melodicRulesPanel;
	}

	private Component createSecondSpeciesRulesPanel() {
		JPanel melodicRulesPanel = new JPanel();
		melodicRulesPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel enableRuleLbl = new JLabel("Enable");
		enableRuleLbl.setForeground(Color.DARK_GRAY);
		enableRuleLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		enableRuleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel applyRuleWeigtingLbl = new JLabel("Apply Weighting");
		applyRuleWeigtingLbl.setForeground(Color.DARK_GRAY);
		applyRuleWeigtingLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		applyRuleWeigtingLbl.setHorizontalAlignment(SwingConstants.CENTER);

		melodicRulesPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel melodicRulesPanelTitle = new JLabel("Second Species Rules");
		melodicRulesPanelTitle.setFont(new Font("Segoe UI", Font.BOLD, 21));
		melodicRulesPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		melodicRulesPanel.add(melodicRulesPanelTitle, BorderLayout.NORTH);
		
		JPanel melodicRulesControlPanel = new JPanel();
		melodicRulesControlPanel.setLayout(new GridLayout(0, 2));
		
		melodicRulesPanel.add(melodicRulesControlPanel, BorderLayout.SOUTH);

		//ParallelOctaves
		melodicRulesControlPanel.add(enableRuleLbl);
		melodicRulesControlPanel.add(applyRuleWeigtingLbl);
		
		parallelOctavesChkbx = new JCheckBox("Forbid Parallel Octaves", false);
		melodicRulesControlPanel.add(parallelOctavesChkbx);
		parallelOctavesSpinner = new JSpinner(); 
		parallelOctavesSpinner.setModel(new SpinnerNumberModel(0, null, 100, 1));
		melodicRulesControlPanel.add(parallelOctavesSpinner);
		
		return melodicRulesPanel;
	}

	private Component createFirstSpeciesRulesPanel() {
		JPanel melodicRulesPanel = new JPanel();
		melodicRulesPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel enableRuleLbl = new JLabel("Enable");
		enableRuleLbl.setForeground(Color.DARK_GRAY);
		enableRuleLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		enableRuleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel applyRuleWeigtingLbl = new JLabel("Apply Weighting");
		applyRuleWeigtingLbl.setForeground(Color.DARK_GRAY);
		applyRuleWeigtingLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		applyRuleWeigtingLbl.setHorizontalAlignment(SwingConstants.CENTER);

		melodicRulesPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel melodicRulesPanelTitle = new JLabel("First Species Rules");
		melodicRulesPanelTitle.setFont(new Font("Segoe UI", Font.BOLD, 21));
		melodicRulesPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		melodicRulesPanel.add(melodicRulesPanelTitle, BorderLayout.NORTH);
		
		JPanel melodicRulesControlPanel = new JPanel();
		melodicRulesControlPanel.setLayout(new GridLayout(0, 2));
		
		melodicRulesPanel.add(melodicRulesControlPanel, BorderLayout.SOUTH);

		//ParallelOctaves
		melodicRulesControlPanel.add(enableRuleLbl);
		melodicRulesControlPanel.add(applyRuleWeigtingLbl);
		
		parallelOctavesChkbx = new JCheckBox("Forbid Parallel Octaves", false);
		melodicRulesControlPanel.add(parallelOctavesChkbx);
		parallelOctavesSpinner = new JSpinner(); 
		parallelOctavesSpinner.setModel(new SpinnerNumberModel(0, null, 100, 1));
		melodicRulesControlPanel.add(parallelOctavesSpinner);
		
		return melodicRulesPanel;
	}

	private Component createMelodicRulesPanel() {
		JPanel melodicRulesPanel = new JPanel();
		melodicRulesPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel enableRuleLbl = new JLabel("Enable");
		enableRuleLbl.setForeground(Color.DARK_GRAY);
		enableRuleLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		enableRuleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel applyRuleWeigtingLbl = new JLabel("Apply Weighting");
		applyRuleWeigtingLbl.setForeground(Color.DARK_GRAY);
		applyRuleWeigtingLbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		applyRuleWeigtingLbl.setHorizontalAlignment(SwingConstants.CENTER);

		melodicRulesPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel melodicRulesPanelTitle = new JLabel("Melodic Rules");
		melodicRulesPanelTitle.setFont(new Font("Segoe UI", Font.BOLD, 21));
		melodicRulesPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		melodicRulesPanel.add(melodicRulesPanelTitle, BorderLayout.NORTH);
		
		JPanel melodicRulesControlPanel = new JPanel();
		GridLayout gl_melodicRulesControlPanel = new GridLayout(0, 2);
		melodicRulesControlPanel.setLayout(gl_melodicRulesControlPanel);
		
		melodicRulesPanel.add(melodicRulesControlPanel, BorderLayout.SOUTH);

		//ParallelOctaves
		melodicRulesControlPanel.add(enableRuleLbl);
		melodicRulesControlPanel.add(applyRuleWeigtingLbl);
		
		parallelOctavesChkbx = new JCheckBox("Forbid Parallel Octaves", false);
		melodicRulesControlPanel.add(parallelOctavesChkbx);
		parallelOctavesSpinner = new JSpinner(); 
		parallelOctavesSpinner.setModel(new SpinnerNumberModel(0, null, 100, 1));
		melodicRulesControlPanel.add(parallelOctavesSpinner);
		
		return melodicRulesPanel;
	}

}
