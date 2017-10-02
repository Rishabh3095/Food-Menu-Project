package application;


public class AmericanExpress extends Creditcard{
	
	private String creditcardnumber;
	 
    public AmericanExpress()
    {   }
    
    public AmericanExpress(String thecardnumber)
    {
    	this.creditcardnumber = thecardnumber;
    	}

    public Creditcard getCard(String creditcardnumber){          
            
            
            if( creditcardnumber.length() > 19 )
            {
                return null;
            }
            
        	boolean check = true;
            
            for(int i=0; i< creditcardnumber.length();i++){
                
            	if(!Character.isDigit(creditcardnumber.charAt(i))){
            		
                    check = false;
                    break;
                }
            	else
            	{
            		check = true;
            	}
            }
            
            if(!check) 
            {
            	return null;
            }
                                    
            //consider the requirements of the american express card
            
            if(creditcardnumber.length() == 15 && check == true && creditcardnumber.charAt(0)=='3' && 
            		
              (creditcardnumber.charAt(1)=='4' || creditcardnumber.charAt(1)=='7')){
            	
               AmericanExpress am = new AmericanExpress(creditcardnumber);
               
            	return am;
            }           
            else
            {
            //if alldigits is true but cardnumber is not any of the card types above

            	return null;      
            }
    }

    public  String CardType()
    {
        return "AMERICAN EXPRESS";
    } 
 
    public String  getNumber()
    {
    	return this.creditcardnumber;
    	}
    
  
    public void setNumber(String thecardnumber)
    {
    	this.creditcardnumber = thecardnumber;
    	}

    
}