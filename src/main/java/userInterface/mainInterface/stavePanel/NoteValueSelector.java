package userInterface.mainInterface.stavePanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import musicRelatedEntities.note.noteAttributes.Accidental;
import musicRelatedEntities.note.noteAttributes.lengthValues.NoteLengthValue;
import musicRelatedEntities.note.writtenNotes.WrittenNote;




/**
 * Used by the user to select the properties of the note that they wish to
 * draw. When generateNoteValue() is called a WrittenNote is returned with
 * all of the properties selected in the selector.
 * By default a Whole note is selected with no accidental. 
 * @author BAZ
 *
 */
public class NoteValueSelector extends JFrame{
	private WrittenNote selectedWrittenNote;	
	private boolean deleteNote = false;
	
	public NoteValueSelector(){
		super("Note Selector");
		setLayout(new GridLayout(2, 0));
		
		addNoteLengthValueButtons();
		addAccidentalButons();

		this.setLocation(1000, 500);
		setSize(200, 200);
		setAlwaysOnTop(true);
		setVisible(true);
		
		//Start with a default whole note with no accidental. 
		selectedWrittenNote = new WrittenNote(NoteLengthValue.WholeNote, null);
	}
	
	private void createSelectedNote(
			NoteLengthValue noteLengthValue, Accidental accidental){
		if(noteLengthValue == null){
			//Must be setting the accidental - this may be null so need to check in this order.
			selectedWrittenNote = new WrittenNote(
					selectedWrittenNote.getNoteLengthValue(), accidental);
		}else{
			//Must be setting the Length value
			selectedWrittenNote = new WrittenNote(
					noteLengthValue, selectedWrittenNote.getAccidental());
		}
	}
	
	public WrittenNote getSelectedNote(){
		if(!deleteNote){
			return selectedWrittenNote;
		}else{
			return null;
		}
	}
	
	private void addNoteLengthValueButtons(){
		ImageIcon wholeNotePic = IconGenerator.WHOLE_NOTE;
		ImageIcon halfNotePic = IconGenerator.HALF_NOTE;
		ImageIcon quarterNotePic = IconGenerator.QUARTER_NOTE;
		ImageIcon eigthNotePic = IconGenerator.EIGTH_NOTE;
		ImageIcon sixteenthNotePic = IconGenerator.SIXTEENTH_NOTE;
		ImageIcon thirtySecondNotePic = IconGenerator.THRIRTY_SECOND_NOTE;		
		
		JButton wholeNoteButton = new JButton(wholeNotePic);
		JButton halfNoteButton = new JButton(halfNotePic);
		JButton quarterNoteButton = new JButton(quarterNotePic);
		JButton eigthNoteButton = new JButton(eigthNotePic);
		JButton sixteenthNoteButton = new JButton(sixteenthNotePic);
		JButton thirtySecondNoteButton = new JButton(thirtySecondNotePic);
		JButton deleteNoteButton = new JButton("DELETE NOTE");
				
		wholeNoteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				createSelectedNote(NoteLengthValue.WholeNote, null);
				deleteNote = false;
			}		
		});
		
		halfNoteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				createSelectedNote(NoteLengthValue.HalfNote, null);
				deleteNote = false;
			}		
		});
		
		quarterNoteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				createSelectedNote(NoteLengthValue.QuaterNote, null);
				deleteNote = false;
			}		
		});
		
		eigthNoteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				createSelectedNote(NoteLengthValue.EigthNote, null);
				deleteNote = false;
			}		
		});
		
		sixteenthNoteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				createSelectedNote(NoteLengthValue.SixteenthNote, null);
				deleteNote = false;
			}		
		});
		
		thirtySecondNoteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				createSelectedNote(NoteLengthValue.ThirtySecondNote, null);
				deleteNote = false;
			}		
		});
		
		deleteNoteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				deleteNote = true;
			}		
		});
		
		add(wholeNoteButton);
		add(halfNoteButton);
		add(quarterNoteButton);
		add(eigthNoteButton);
		add(sixteenthNoteButton);
		add(thirtySecondNoteButton);
		add(deleteNoteButton);
	}

	private void addAccidentalButons(){
		ImageIcon sharpPic = IconGenerator.SHARP;
		ImageIcon naturalPic = IconGenerator.NATURAL;
		ImageIcon flatPic = IconGenerator.FLAT;
		
		JButton sharpButton = new JButton(sharpPic);
		JButton naturalButton = new JButton(naturalPic);
		JButton flatButton = new JButton(flatPic);
		JButton clearAccidentalButton = new JButton("Clear Accidental");
		
		sharpButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				createSelectedNote(null, Accidental.SHARP);
			}		
		});
		
		naturalButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				createSelectedNote(null, Accidental.NATURAL);
			}		
		});
		
		flatButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				createSelectedNote(null, Accidental.FLAT);
			}		
		});
		
		clearAccidentalButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				createSelectedNote(null, null);
			}		
		});
		
		add(sharpButton);
		add(naturalButton);
		add(flatButton);
		add(clearAccidentalButton);
	}
}
