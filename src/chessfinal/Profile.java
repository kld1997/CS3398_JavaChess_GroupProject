
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



}
