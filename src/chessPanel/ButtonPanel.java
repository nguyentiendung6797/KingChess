package chessPanel;

import gameframe.MainChessFrame;
import gameframe.SettingFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import core.ButtonSave;
import core.GameSetting;
import core.Move;
import core.Utils;

public class ButtonPanel extends JPanel implements MouseListener {
	private GameSetting gameSetting;
	private JLabel btnUndo, btnMenu, btnExit, btnSave;
	private MainChessFrame mainChessFrame;

	public ButtonPanel(GameSetting gameSetting, MainChessFrame mainChessFrame) {
		this.gameSetting = gameSetting;
		this.mainChessFrame = mainChessFrame;
		this.setBorder(new LineBorder(Color.LIGHT_GRAY));
		this.setBounds(Utils.BOARD_GAME_WIDTH + 5, 520, Utils.GAME_WIDTH-Utils.BOARD_GAME_WIDTH-15, 115);
		this.setFocusable(true);
		this.setBackground(Color.GRAY);
		addButton();
	}

	public JLabel createButtonControl(String iconName, Box parent) {
		JLabel btn = new JLabel(Utils.resizeImageIcon(iconName, Utils.BUTTON_CONTROL_WIDTH, Utils.BUTTON_CONTROL_HEIGHT));
		btn.setBorder(null);
		btn.addMouseListener(this);
		btn.setFocusable(true);
		parent.add(btn);
		return btn;
	}
		
	public void addButton() {
		Box boxPanel1 = Box.createHorizontalBox();
		boxPanel1.setAlignmentX(CENTER_ALIGNMENT);
		Box boxPanel2 = Box.createHorizontalBox();
		boxPanel2.setAlignmentX(CENTER_ALIGNMENT);
		
		btnUndo = createButtonControl("undo3", boxPanel1);
		btnMenu = createButtonControl("setting3", boxPanel1);
		btnSave = new ButtonSave(mainChessFrame.getBoardPanel());
		btnSave.setIcon(Utils.ICON_SAVE1);
		btnSave.addMouseListener(this);
		
		boxPanel2.add(btnSave);
		btnExit = createButtonControl("exit3", boxPanel2);
		
		Box boxPanel3 = Box.createVerticalBox();
		boxPanel3.add(boxPanel1);
		boxPanel3.add(boxPanel2);
		this.add(boxPanel3);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.drawImage(Utils.IMG_PANEL, 0, 0, d.width, d.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JLabel lbl = (JLabel) e.getSource();
		if(lbl == btnUndo) {
			// First Button: Undo
			if (!gameSetting.isAiPlay()) {	
				mainChessFrame.getPanelInformation().insertText("Undo: ");
				Move move = mainChessFrame.getBoardPanel().getPositionBoard().getParentMove();
				mainChessFrame.getPanelInformation().appendTextYellow(move.toString());
				mainChessFrame.getBoardPanel()
								.setPositionBoard(mainChessFrame.getBoardPanel().getPositionBoard().getOldPositionBoard());
				mainChessFrame.getBoardPanel().repaint();
				if (mainChessFrame.getBoardPanel().isHumanTurn()) {
					mainChessFrame.getBoardPanel().setHumanTurn(false);
				} else {
					mainChessFrame.getBoardPanel().setHumanTurn(true);
				}
			} else {
				for (int i = 0; i <= 1; i++) {
					mainChessFrame.getPanelInformation().insertText("Undo: ");
					Move move = mainChessFrame.getBoardPanel().getPositionBoard().getParentMove();
					mainChessFrame.getPanelInformation().appendTextYellow(move.toString());
					mainChessFrame.getBoardPanel().setPositionBoard(mainChessFrame.getBoardPanel().getPositionBoard().getOldPositionBoard());
					mainChessFrame.getBoardPanel().repaint();
					if (mainChessFrame.getBoardPanel().isHumanTurn()) {
						mainChessFrame.getBoardPanel().setHumanTurn(false);
					} else {
						mainChessFrame.getBoardPanel().setHumanTurn(true);
					}
				}
			}
		} else if(lbl == btnExit) {
			Utils.inGame = false;
			mainChessFrame.getMenuFrame().setVisible(true);
			mainChessFrame.dispose();
		} else if(lbl == btnMenu) {
			new SettingFrame(gameSetting);
		} else if(lbl == btnSave) {
			
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		if(label == btnUndo){
			btnUndo.setIcon(Utils.ICON_UNDO2);
		} else if(label == btnMenu) {
			btnMenu.setIcon(Utils.ICON_SETTING2);
		} else if(label == btnExit) {
			btnExit.setIcon(Utils.ICON_EXIT2);
		} else {
			btnSave.setIcon(Utils.ICON_SAVE2);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		if(label == btnUndo){
			btnUndo.setIcon(Utils.ICON_UNDO1);
		} else if(label == btnMenu) {
			btnMenu.setIcon(Utils.ICON_SETTING1);
		} else if(label == btnExit) {
			btnExit.setIcon(Utils.ICON_EXIT1);
		} else {
			btnSave.setIcon(Utils.ICON_SAVE1);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
