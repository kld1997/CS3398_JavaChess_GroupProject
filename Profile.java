package PlayerProfile;
import java.io.*;

public class Profile {
	
	
	
	// in the GUI we will have the player enter their information. 
	// create an interface between the GUI and this constructor to set up the player profile.
	// for the date of birth portion we will have the gui only use ints, from there we will pass the concatination of those number 
	// convert that concatination into coressponding dates 
	// then calculate the age 
	
	 String Name,GeographicalLocation, Quote;
	 int DateOfBirth, Age, NumberOfLosses, NumberOfWins,NumberOfTies;
	 double ELOScoring, score;
	
	
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

    
}
