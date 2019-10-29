package PlayerProfile;
import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;




public class Profile {
	
	// adding the probability of someone winning and the elo scoring algorithem 3
	//
	// in the GUI we will have the player enter their information. 
	// create an interface between the GUI and this constructor to set up the player profile.
	// for the date of birth portion we will have the gui only use ints, from there we will pass the concatination of those number 
	// convert that concatination into coressponding dates 
	// then calculate the age 
	
	 String Name,GeographicalLocation, Quote;
	 int DateOfBirth, Age, NumberOfLosses, NumberOfWins,NumberOfTies;
	 double ELOScoring, score, ChanceOfWinning;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	public Profile(String PlayerName, int PlayerDateOfBirth, String PlayerGeographicalLocation, String PQuote) {
		
		this.Name = PlayerName;
		this.DateOfBirth = PlayerDateOfBirth;
		this.GeographicalLocation = PlayerGeographicalLocation;
		this.Quote = PQuote;
		this.ELOScoring = 1000;
		this.NumberOfLosses = 0;
		this.NumberOfWins = 0;
		this.score= getWinningPercentage();

		
	}
	
	
	
	
    public double getWinningPercentage()
    {
        double totalGames = NumberOfWins + NumberOfTies + NumberOfLosses;
        double totalWins  = NumberOfWins + (NumberOfTies / 2.0);
        double winningPercentage = (totalWins / totalGames);

        return winningPercentage;
    }
	
    public void Save() { 
    	try { 
    		FileWrite fw = new FileWritter("Chess Profile.txt");
    		PrintWriter pw = new PrintWriter(fw);
    		
    		pw.print("Name: " + this.Name);
    		pw.print("Birthday : " + this.DateOfBirth);
    		pw.print("Location: " + this.GeographicalLocation);
    		pw.print("Quote: " + this.Quote);
    		pw.print("\n");
    		pw.print("ELO Score: " + this.ELOScoring);
    		pw.print("Losses: " + this.NumberOfLosses);
    		pw.print("Wins: " + this.NumberOfWins);
    		pw.print("Wining Percentage: " + this.score);
    		
    		pw.close();
    	}catch(IOException e) {
    		pw.print("Error");
    	}
    }
	public void Read() {
		
		try {
			FileReader fr = new Filereader("Chess Profile.txt");
			BufferedReader br=new BufferedReader(fr);
			
			String str;
			while ((str = br.readLine()) != null ) {
				out.print("\n");
			}
			br.close();
		}catch (IOException e ) { 
			out.println("File Not Found");
		}
		
	}
	
	
	
    // Function to calculate the Probability 
    static float Probability(float rating1,  
                               float rating2) 
    { 
        return 1.0f * 1.0f / (1 + 1.0f *  
                (float)(Math.pow(10, 1.0f *  
               (rating1 - rating2) / 400))); 
    } 
      
    // Function to calculate Elo rating 
    // K is a constant. 
    // d determines whether Player A wins 
    // or Player B.  
    static void Rating(float Player1Rating, float Player2Rating, 
                            int K, boolean PlayerAResults) 
    {  
      
        // To calculate the Winning 
        // Probability of Player B 
        float Pb = Probability(Player1Rating, Player2Rating); 
      
        // To calculate the Winning 
        // Probability of Player A 
        float Pa = Probability(Player2Rating, Player1Rating); 
      	this.ChanceOfWinning = Probability(Player2Rating, Player1Rating);
        // Case -1 When Player A wins 
        // Updating the Elo Ratings 
        if (PlayerAResults == true) { 
            Player1Rating = Player1Rating + K * (1 - Pa); 
            Player2Rating = Player2Rating + K * (0 - Pb); 
        } 
      
        // Case -2 When Player B wins 
        // Updating the Elo Ratings 
        else { 
            Player1Rating = Player1Rating + K * (0 - Pa); 
            Player2Rating = Player2Rating + K * (1 - Pb); 
        } 
        
        
        
        
        this.ELOScoring= (Math.round(Player1Rating * 1000000.0) / 1000000.0);
        double PlayerBScore= (Math.round(Player2Rating* 1000000.0) / 1000000.0);
        
       
        
        System.out.print("Updated Ratings:-\n"); 
          
        System.out.print("Player 1 = " +  df2.format(PlayerAScore) 
                     + " Player 2 = " + df2.format(PlayerBScore)); 
    } 





}
