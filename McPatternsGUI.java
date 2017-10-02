package application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import javax.swing.text.DocumentFilter;

class McPatternsGUI extends JFrame {
	
	McPatternsPresenter presenter;
        
	private static double costTotal =0.0;
        
	private static AmericanExpress factory;
	
	public McPatternsGUI(McPatternsPresenter presenter) {
		
		this.presenter = presenter;
		presenter.attachView(this);
		showGUI();

	}
	private void showGUI() {
		presenter.loadMenuItems();

		JFrame theFrame = new JFrame("Swing Example");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setLayout(new BorderLayout());
		
		JPanel title = new JPanel(new FlowLayout());
		title.add(new JLabel("Welcome"));
                
                JPanel receipt = new JPanel();
                receipt.setLayout(new BoxLayout(receipt, BoxLayout.PAGE_AXIS));
                JLabel label = new JLabel("Total Price:\n $"+getTotalPrice());
                label.setBackground(Color.gray);
                receipt.setBorder(BorderFactory.createRaisedBevelBorder());
                receipt.add(label);
               
                JTextArea receiptDis = new JTextArea(10,20);
                receiptDis.setText("");
                JScrollPane scrl = new JScrollPane(receiptDis);
                scrl.setBounds(30,100,900,400);
                scrl.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                
		JPanel orderPane = new JPanel();
		orderPane.setLayout(new BoxLayout(orderPane, BoxLayout.PAGE_AXIS));
		JLabel orderDetails = new JLabel("Your order");
		orderPane.setBorder(BorderFactory.createRaisedBevelBorder());
		orderPane.add(orderDetails);
		JTextField ccEntry = new JTextField("Enter CC #");

		JButton confirm = new JButton("Place Order");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(receiptDis.getText().length()!=0){             
                            	
                 boolean area = true;                                                               
                                
               String input = ccEntry.getText().substring(10);
                 for(int i =0; i<input.length();i++)
                 {
                   	if(ccEntry.getText().charAt(i)!=' ')
                    	{
                       		area = false;
                       	}
                 }
                            
                 if(input == "" || area)
                 {                                   
                    orderDetails.setText("Not Approved! Try Again Later."); 
                 }				
                 else
                 {                	
                    factory = new AmericanExpress();
                    Creditcard thecard = factory.getCard(ccEntry.getText().substring(10));
                                    
                    if( thecard !=null)
                 {   
                  	orderDetails.setText("Confirmation to " + ccEntry.getText().substring(6));      
                                        
                    System.out.print("RECENT ORDER: \n"+receiptDis.getText());

                  Receipt fr = new Receipt(receiptDis.getText()
                             +"\nTotal: $"+getTotalPrice()  +"\nCard type: "+ thecard.CardType());                                   
                                        
                     clearAccount();
                                        
                     DecimalFormat frmat = new DecimalFormat("#.00");    
                                        
                     label.setText("Total Cost: \n$"+ String.format( "%.2f", getTotalPrice()) );                                   
                     receiptDis.setText("");   
                     ccEntry.setText("Enter Credit Card Number#");
                       }
                    else
                   {
                      orderDetails.setText("Invalid Entry " + ccEntry.getText().substring(5)+" please try again");
                      ccEntry.setText("Enter Credit Card Number#");
                   }
             }
        }
                   else if(receiptDis.getText().length()==0)
                   {
                      orderDetails.setText("Nothing to display!");
                   }
                }
		});
                
		JButton cancel = new JButton("Cancel Order");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				orderDetails.setText("Order cancelled");
				clearAccount();
				label.setText("Total Price:\n $"+getTotalPrice());
				ccEntry.setText("Enter Credit Card #");
				receiptDis.setText("");                
				
			}
		});
                
                JButton button = new JButton("Clear order");
                button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                
				orderDetails.setText("Clear Order");
                clearAccount();
                label.setText("Total:\n $"+getTotalPrice());
                receiptDis.setText("");                               
			}
		});                

		
		orderPane.add(ccEntry);
		orderPane.add(confirm);
		orderPane.add(cancel);
		
        orderPane.add(button);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 4));

		ArrayList<ItemListed> items = presenter.model.getI();
        
         for(int i=0; i<items.size();i++){ 
        	JButton itemButton = new JButton(items.get(i).getItemName());
        	
        	itemButton.addActionListener(new click(items.get(i).getItemName(),
                                         receiptDis, items.get(i).getItemPrice(), label,orderDetails));                               
        	buttonPanel.add(itemButton);
                }
		
		theFrame.add(title,BorderLayout.NORTH);
		theFrame.add(buttonPanel, BorderLayout.CENTER);
		theFrame.add(orderPane, BorderLayout.EAST);
                theFrame.add(scrl, BorderLayout.SOUTH);
                theFrame.add(receipt, BorderLayout.WEST);
		theFrame.setSize(1100, 900);
                				
		theFrame.pack();
		boolean set = true;
		theFrame.setVisible(set);
		
	}
        
        public static class click implements ActionListener{
           
        	private String name ="";
            private double itemPrice=0.0;
            private JTextArea receipt;
            private JLabel labels;
            private JLabel detail;
            
            public click(String name, JTextArea receipt, double itemPrice,  
                    JLabel labels, JLabel detail){
                
            	this.name = name;
                this.receipt = receipt;
                this.itemPrice = itemPrice;
                this.labels = labels;
                this.detail = detail;

            }
            
            public void actionPerformed(ActionEvent e) {
                
            	receipt.append(name+" $"+itemPrice+"\n");

                addPrice(itemPrice);   
                labels.setText("Total:\n $"+getTotalPrice()+"");
                
                if(detail.getText()=="Order cleared")
                    detail.setText("Your order");
            }            
        }
        
        public static void addPrice(double price)
        {
            costTotal = price + costTotal;
        }
        
        public static void subtractPrice(double price)
        {
            costTotal = price- costTotal;
            if(costTotal<0)
                costTotal=0.0;
        }
        
        public static void clearAccount()
        {
        	costTotal = 0.0;
        	}
        

        public static double getTotalPrice()
        {
        	return costTotal;
        	}
}