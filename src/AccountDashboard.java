import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.text.DecimalFormat;

import javax.swing.*;

import javax.swing.border.EmptyBorder;

public class AccountDashboard extends JFrame implements ActionListener {
	Database db = Database.getInstance();
	Account ac;

	JLabel welcomeText = new JLabel();

	JTextField accountNoT = new JTextField("", 20);
	JTextField amountT = new JTextField("", 10);

	JPasswordField oldP = new JPasswordField("", 20);
	JPasswordField newP = new JPasswordField("", 20);
	JPasswordField reNewP = new JPasswordField("", 20);

	JButton transferB = new JButton("Transfer_Money");
	JButton withdrawB = new JButton("Withdraw_Money");
	JButton depositB = new JButton("Deposit_Money");
	JButton balanceB = new JButton("Balance");
	JButton payBillB = new JButton("Pay Loan EMI");

	JButton transacHB = new JButton("Transaction History");

	// JButton userDetailB = new JButton("User Detail");
	// JButton changePinB = new JButton("Change PIN");
	JButton saveB = new JButton("Save");
	JButton logoutB = new JButton("Log Out");

	JButton transferSB = new JButton("Transfer");
	JButton withdrawalSB = new JButton("Withdraw");
	JButton depositSB = new JButton("Deposit");
	JButton payBillSB = new JButton("Pay");
	JButton PinB = new JButton("Submit");

	JButton tranHB = new JButton("Print Report");

	JPanel left = new JPanel();
	JPanel right = new JPanel();
	JPanel top = new JPanel();
	JPanel bottom = new JPanel();
	JPanel center = new JPanel();

	public AccountDashboard(Account ac) {
		this.ac = ac;
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				db.saveData();
				System.exit(0);
			}
		});
		this.setTitle("Dashboard");
		this.setSize(700, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		// this.setLayout(new FlowLayout());

		transferSB.addActionListener(this);
		withdrawalSB.addActionListener(this);
		payBillSB.addActionListener(this);
		depositSB.addActionListener(this);
		saveB.addActionListener(this);
		PinB.addActionListener(this);

		tranHB.addActionListener(this);

		this.add(left, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);
		this.add(top, BorderLayout.NORTH);
		this.add(bottom, BorderLayout.SOUTH);
		this.add(center, BorderLayout.CENTER);

		top.setBorder(new EmptyBorder(10, 0, 10, 0));
		welcomeText.setText("Welcome, " + ac.user.firstName + " " + ac.user.lastName);
		top.add(welcomeText);

		left.setLayout(new GridLayout(7, 1, 5, 10));
		transferB.addActionListener(this);
		left.add(transferB);
		withdrawB.addActionListener(this);
		left.add(withdrawB);
		depositB.addActionListener(this);
		left.add(depositB);
		balanceB.addActionListener(this);
		left.add(balanceB);
		payBillB.addActionListener(this);
		left.add(payBillB);
		transacHB.addActionListener(this);
		left.add(transacHB);

		right.setLayout(new GridLayout(7, 1, 5, 10));
		// userDetailB.addActionListener(this);
		// right.add(userDetailB);
		// changePinB.addActionListener(this);
		// right.add(changePinB);

		// center.setPreferredSize(new Dimension(100, 100));
		// center.setBackground(new Color(24, 14, 255));

		logoutB.addActionListener(this);
		bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottom.add(logoutB);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Transfer_Money")) {
			generateTransferPanel();
		} else if (e.getActionCommand().equals("Withdraw_Money")) {
			generateWithdrawalPanel();
		} else if (e.getActionCommand().equals("Deposit_Money")) {
			generateDepositPanel();
		} else if (e.getActionCommand().equals("Balance")) {
			generateBalancePanel();
		} else if (e.getActionCommand().equals("Pay Loan EMI")) {
			generatePayBillPanel();
		}

		else if (e.getActionCommand().equals("Transaction History")) {
			generateTranHistoryPanel();
		}

		else if (e.getActionCommand().equals("Transfer")) {
			transfer();
		} else if (e.getActionCommand().equals("Withdraw")) {
			withdraw();
		} else if (e.getActionCommand().equals("Deposit")) {
			deposit();

		} else if (e.getActionCommand().equals("Pay")) {
			payBill();
		} 
		// else if (e.getActionCommand().equals("User Detail")) {
		// 	generateUserDetailPanel();
		// } else if (e.getActionCommand().equals("Change PIN")) {
		// 	generatePinPanel();
		// } 
		// else if (e.getActionCommand().equals("Submit")) {
		// 	changePIN();
		// }ssss
		 else if (e.getActionCommand().equals("Log Out")) {
			this.dispose();
			new LoginWindow();
		}

	}

	void generateTransferPanel() {
		panelClear();
		center.add(new JLabel("Enter Account Number: "));
		center.add(accountNoT);
		center.add(new JLabel("Enter Amount: "));
		center.add(amountT);
		center.add(new JLabel());
		center.add(transferSB);
		this.revalidate();
	}

	void generateWithdrawalPanel() {
		panelClear();
		center.add(new JLabel("Enter Amount: "));
		center.add(amountT);
		center.add(new JLabel());
		center.add(withdrawalSB);
		this.revalidate();
	}

	void generateDepositPanel() {
		panelClear();
		center.add(new JLabel("Enter Amount: "));
		center.add(amountT);
		center.add(new JLabel());
		center.add(depositSB);
		this.revalidate();
	}

	void generateBalancePanel() {
		panelClear();
		center.add(new JLabel("Current Balance: "));
		DecimalFormat df = new DecimalFormat("0.00");
		center.add(new JLabel("" + df.format(ac.getBalance())));
		center.add(new JLabel());
		center.add(new JLabel());
		this.revalidate();
	}

	void generatePayBillPanel() {
		panelClear();
		center.add(new JLabel("Enter Loan Number: "));
		center.add(accountNoT);
		center.add(new JLabel("Enter Amount: "));
		center.add(amountT);
		center.add(new JLabel());
		center.add(payBillSB);
		this.revalidate();
	}

	void generateTranHistoryPanel() {
		panelClear();
		//changed
		center.add(new JLabel("No Transaction History at present"));
		// center.add(accountNoT);
		// center.add(new JLabel("Enter Amount: "));
		// center.add(amountT);
		// center.add(new JLabel());
		// center.add(payBillSB);
		this.revalidate();
	}

	// void generateUserDetailPanel() {
	// 	panelClear();
	// 	center.setBorder(new EmptyBorder(50, 50, 50, 50));
	// 	center.setLayout(new GridLayout(0, 2, 5, 10));
	// 	center.add(new JLabel("Full Name:"));
	// 	center.add(new JLabel(ac.user.firstName + " " + ac.user.lastName));
	// 	center.add(new JLabel("Sex:"));
	// 	center.add(new JLabel(ac.user.sex));
	// 	center.add(new JLabel("Date of Birth:"));
	// 	center.add(new JLabel(
	// 			ac.user.birthdate.getDate() + "/" + ac.user.birthdate.getMonth() + "/" + ac.user.birthdate.getYear()));
	// 	center.add(new JLabel("Email:"));
	// 	center.add(new JLabel(ac.user.email));
	// 	center.add(new JLabel("Phone No.:"));
	// 	center.add(new JLabel(ac.user.phoneNo));
	// 	center.add(new JLabel("Pan Card Number:"));
	// 	center.add(new JLabel(ac.user.NID));
	// 	center.add(new JLabel("Address:"));
	// 	center.add(new JLabel(ac.user.address));
	// 	center.add(new JLabel("Occupation:"));
	// 	center.add(new JLabel(ac.user.occupation));
	// 	this.revalidate();

	// }

	// void generatePinPanel() {
	// 	panelClear();
	// 	center.setBorder(new EmptyBorder(80, 50, 70, 50));
	// 	center.setLayout(new GridLayout(4, 2, 5, 10));
	// 	center.add(new JLabel("Old PIN:"));
	// 	center.add(oldP);
	// 	center.add(new JLabel("Enter New PIN:"));
	// 	center.add(newP);
	// 	center.add(new JLabel("Re-Enter New PIN:"));
	// 	center.add(reNewP);
	// 	center.add(new JLabel(" "));
	// 	center.add(PinB);
	// 	this.revalidate();

	// }

	void panelClear() {
		center.removeAll();
		center.setBorder(new EmptyBorder(80, 50, 110, 50));
		center.setLayout(new GridLayout(3, 2, 5, 10));
		accountNoT.setText("");
		amountT.setText("");
		oldP.setText("");
		newP.setText("");
		reNewP.setText("");
	}

	void payBill() {
		try {
			double amount = Double.parseDouble(amountT.getText());
			if (ac.payBill(amount)) {
				JOptionPane.showMessageDialog(this, "EMI successfully Paid!", "Success", JOptionPane.OK_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, "You don't have enough Balance", "Failed",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Enter valid amount", "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	void transfer() {
		try {
			double amount = Double.parseDouble(amountT.getText());

			Account otherAccount;
			if ((otherAccount = db.getAccount(accountNoT.getText())) != null) {
				if (ac.transferMoney(otherAccount, amount)) {
					JOptionPane.showMessageDialog(this, "Successfully Transferred", "Success", JOptionPane.OK_OPTION);
				} else {
					JOptionPane.showMessageDialog(this, "You don't have enough Balance", "Failed",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Sorry, Given Account not Found", "Failed",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Enter valid amount", "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	void withdraw() {
		try {
			double amount = Double.parseDouble(amountT.getText());
			int t = ac.withdrawMoney(amount);
			if (t == 0)
				JOptionPane.showMessageDialog(this, "Successfully Withdrawn", "Success", JOptionPane.OK_OPTION);
			else if (t == Account.INSUFFICIENT_BALANCE)
				JOptionPane.showMessageDialog(this, "You don't have enough Balance", "Failed",
						JOptionPane.ERROR_MESSAGE);
			else if (t == Account.WITHDRAWAL_LIMIT_UNDER)
				JOptionPane.showMessageDialog(this, "Minimum withdrawal amount is: " + ac.minWithdrawal, "Failed",
						JOptionPane.ERROR_MESSAGE);
			else if (t == Account.WITHDRAWAL_LIMIT_OVER)
				JOptionPane.showMessageDialog(this, "Maximum Withdrawal amount is: " + ac.maxWithdrawal, "Failed",
						JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Enter valid amount", "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	void deposit() {
		try {
			double amount = Double.parseDouble(amountT.getText());
			ac.depositMoney(amount);
			JOptionPane.showMessageDialog(this, "Successfully Deposited", "Success", JOptionPane.OK_OPTION);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Enter valid amount", "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	// void changePIN() {
	// 	if (oldP.getText().isEmpty() || newP.getText().isEmpty() || reNewP.getText().isEmpty()) {
	// 		JOptionPane.showMessageDialog(this, "Please Fill all the Fields", "Failed", JOptionPane.ERROR_MESSAGE);
	// 	} else if (!oldP.getText().equals(ac.getPIN())) {
	// 		JOptionPane.showMessageDialog(this, "Wrong PIN Entered", "Failed", JOptionPane.ERROR_MESSAGE);
	// 	} else if (!newP.getText().equals(reNewP.getText())) {
	// 		JOptionPane.showMessageDialog(this, "New PINs doesn't Match", "Failed", JOptionPane.ERROR_MESSAGE);
	// 	} else {
	// 		ac.setPIN(newP.getText());
	// 		JOptionPane.showMessageDialog(this, "PIN successfully changed", "Success", JOptionPane.OK_OPTION);
	// 	}
	// }
}